package br.com.javamagazine.clinicajm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.javamagazine.clinicajm.domain.Paciente;

/**
 * será responsável por realizar as operações que dizem respeito aos pacientes no
 * banco de dados.
 * 
 * @author Jaime Gomes
 *
 */
@Repository
public class PacienteRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvaPaciente(Paciente paciente) {
		entityManager.persist(paciente);
	}

	@Transactional
	public void excluiPaciente(Integer id) {
		Paciente paciente = entityManager.find(Paciente.class, id);
		entityManager.remove(paciente);
	}

	@SuppressWarnings("unchecked")
	public List<Paciente> listaPacientes() {
		Query query = entityManager
				.createQuery("Select p from Paciente p order by p.id");
		return query.getResultList();
	}
}
