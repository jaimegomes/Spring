package br.com.javamagazine.clinicajm.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.enumeration.Especialidade;
import br.com.javamagazine.clinicajm.repository.ConsultaRepository;
import br.com.javamagazine.clinicajm.repository.MedicoRepository;
import br.com.javamagazine.clinicajm.util.Mensagem;
import br.com.javamagazine.clinicajm.util.Mensagem.TipoMensagem;

@Controller
@RequestMapping("/medico")
public class MedicoController {
	@Autowired
	private MedicoRepository medicoRepository;
	
	@Autowired
	private ConsultaRepository consultaRepository;

	@RequestMapping(value = "/cadastrar.do", method = RequestMethod.POST)
	public String cadastrar(Medico medico, Model model) {

		model.addAttribute("especialidades", Especialidade.values());

		if (medico.getNome() == null || medico.getNome().equals("")
				&& medico.getEspecialidade() != null) {
			model.addAttribute("mensagem", new Mensagem(
					"Erro ao cadastrar médico, o nome deve ser preenchido.",
					TipoMensagem.ERRO));
		}

		else if (medico.getEspecialidade() == null
				&& !medico.getNome().equals("")) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao cadastrar médico, a especialidade deve ser preenchida",
							TipoMensagem.ERRO));
		}

		else if (medico.getEspecialidade() == null
				&& medico.getNome().equals("")) {
			model.addAttribute(
					"mensagem",
					new Mensagem(
							"Erro ao cadastrar médico. Você deve preencher todos os campos do formulário para cadastrar um médico.",
							TipoMensagem.ERRO));
		}

		else {
			medicoRepository.salvaMedico(medico);
			model.addAttribute("medico", new Medico());
			model.addAttribute("mensagem", new Mensagem(
					"Sucesso ao cadastrar o médico.", TipoMensagem.SUCESSO));

		}
		return "cadastrarMedico";
	}

	@RequestMapping(value = "/listar.do", method = RequestMethod.GET)
	public String listar(Model model) {
		List<Medico> medicos = medicoRepository.listaMedicos();
		model.addAttribute("medicos", medicos);
		return "listarMedicos";
	}

	@RequestMapping(value = "/excluir.do", method = RequestMethod.GET)
	public String excluir(Integer idMedico, Model model) {
		
		boolean consulta = consultaRepository.hasConsulta(idMedico);
		
		if(consulta) {
			model.addAttribute("mensagem", new Mensagem(
					"Erro ao excluir o médico, este médico possui consultas registradas em seu nome.", TipoMensagem.ERRO));
		} else {
		
		medicoRepository.excluiMedico(idMedico);
		model.addAttribute("mensagem", new Mensagem(
				"Sucesso ao excluir o médico.", TipoMensagem.SUCESSO));
		
		}
		return "forward:/medico/listar.do";
	}

	@RequestMapping(value = "/listarPorEspecialidade.do", method = RequestMethod.GET)
	public @ResponseBody List<Medico> listarPorEspecialidade(
			Especialidade especialidade) {
		return medicoRepository.listaMedicosPorEspecialidade(especialidade);
	}
}
