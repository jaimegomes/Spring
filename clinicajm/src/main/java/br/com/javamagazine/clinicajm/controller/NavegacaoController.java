package br.com.javamagazine.clinicajm.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.javamagazine.clinicajm.domain.Consulta;
import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.domain.Paciente;
import br.com.javamagazine.clinicajm.enumeration.Especialidade;
import br.com.javamagazine.clinicajm.repository.PacienteRepository;

@Controller
public class NavegacaoController {
	@Autowired
	private PacienteRepository pacienteRepository;

	@RequestMapping(value = "/preparaCadastroMedico.do")
	public String redirecionaCadastroMedico(Map<String, Object> map) {
		map.put("especialidades", Especialidade.values());
		map.put("medico", new Medico());
		return "cadastrarMedico";
	}

	@RequestMapping(value = "/preparaCadastroPaciente.do")
	public String redirecionaCadastroPaciente(Map<String, Object> map) {
		map.put("paciente", new Paciente());
		return "cadastrarPaciente";
	}

	@RequestMapping(value = "/preparaCadastroConsulta.do")
	public String redirecionaCadastroConsulta(Map<String, Object> map) {
		map.put("especialidades", Especialidade.values());
		map.put("pacientes", pacienteRepository.listaPacientes());
		map.put("consulta", new Consulta());
		return "cadastrarConsulta";
	}

	@RequestMapping(value = "/preparaCadastroAtendimento.do")
	public String redirecionaCadastroAtendimento(Map<String, Object> map) {
		map.put("pacientes", pacienteRepository.listaPacientes());
		return "listarConsultas";
	}
}
