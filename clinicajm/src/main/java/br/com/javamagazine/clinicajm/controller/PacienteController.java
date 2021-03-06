package br.com.javamagazine.clinicajm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.repository.ConsultaRepository;
import br.com.javamagazine.clinicajm.repository.PacienteRepository;
import br.com.javamagazine.clinicajm.util.Mensagem;
import br.com.javamagazine.clinicajm.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/paciente")
public class PacienteController {
	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ConsultaRepository consultaRepository;

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Paciente paciente,
			@RequestParam String dataNascimento, Model model) {

		if (paciente.getNome().equals("")
				&& paciente.getDataNascimento() != null) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao cadastrar o paciente, o nome deve ser preenchido.",
							TipoMensagem.ERRO));
		}

		else if (!paciente.getNome().equals("")
				&& paciente.getDataNascimento() == null) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao cadastrar o paciente, a data de nascimento deve ser preenchida.",
							TipoMensagem.ERRO));
		}

		else if (paciente.getNome().equals("")
				&& paciente.getDataNascimento() == null) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao cadastrar o paciente, Voc� deve preencher todos os campos do formul�rio para cadastrar um paciente.",
							TipoMensagem.ERRO));
		}

		else {
			pacienteRepository.salvaPaciente(paciente);
			model.addAttribute("paciente", new Paciente());
			model.addAttribute("mensagem", new Mensagem(
					"Sucesso ao cadastrar o paciente", TipoMensagem.SUCESSO));

		}

		return "cadastrarPaciente";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Paciente> pacientes = pacienteRepository.listaPacientes();
		model.addAttribute("pacientes", pacientes);
		return "listarPacientes";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Integer idPaciente, Model model) {

		List<Consulta> listConsulta = consultaRepository
				.listarPorPaciente(idPaciente);
		if (listConsulta.size() > 0) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao excluir o paciente, este paciente possui consultas registradas em seu nome.",
							TipoMensagem.ERRO));
		} else {
			pacienteRepository.excluiPaciente(idPaciente);
			model.addAttribute("mensagem", new Mensagem(
					"Sucesso ao excluir o paciente", TipoMensagem.SUCESSO));
		}
		return "forward:/paciente/listar.do";
	}
}
