package br.com.javamagazine.clinicajm.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import br.com.javamagazine.clinicajm.domain.Medico;
import br.com.javamagazine.clinicajm.enumeration.Especialidade;

/**
 * será responsável por realizar as operações que dizem respeito aos medicos no
 * banco de dados.
 * 
 * @author Jaime Gomes
 *
 */
@Repository
public class MedicoRepository {

	@PersistenceContext
	private EntityManager entityManager;

	@Transactional
	public void salvaMedico(Medico medico) {
		entityManager.persist(medico);
	}

	@Transactional
	public void excluiMedico(Integer id) {
		Medico medico = entityManager.find(Medico.class, id);
		entityManager.remove(medico);
	}

	@SuppressWarnings("unchecked")
	public List<Medico> listaMedicos() {
		Query query = entityManager
				.createQuery("Select m from Medico m order by m.id");
		return query.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Medico> listaMedicosPorEspecialidade(Especialidade especialidade) {
		Query query = entityManager
				.createQuery("Select m from Medico m where m.especialidade=:especialidade order by m.nome");
		query.setParameter("especialidade", especialidade);
		return query.getResultList();
	}
}
