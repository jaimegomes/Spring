package br.com.javamagazine.clinicajm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.javamagazine.clinicajm.domain.Consulta;

/**
 * será responsável por realizar as operações que dizem respeito das consultas
 * no banco de dados.
 * 
 * @author Jaime Gomes
 *
 */
@Repository
public class ConsultaRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvaConsulta(Consulta consulta) {
		entityManager.persist(consulta);
	}

	@Transactional
	public void atualizaConsulta(Consulta consulta) {
		entityManager.merge(consulta);
	}

	public Consulta recuperaConsulta(Integer id) {
		return entityManager.find(Consulta.class, id);
	}

	@SuppressWarnings("unchecked")
	public List<Consulta> listarPorPaciente(Integer idPaciente) {
		Query query = entityManager
				.createQuery("Select c from Consulta c where c.paciente.id=:idPaciente order by c.dataConsulta");
		query.setParameter("idPaciente", idPaciente);
		return query.getResultList();
	}
}
