package br.com.javamagazine.clinicajm.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Consulta implements Serializable {

	private static final long serialVersionUID = 7064809078222302493L;
	@Id
	@GeneratedValue(generator = "consulta_id_seq", strategy = GenerationType.SEQUENCE)
	@SequenceGenerator(name = "consulta_id_seq", sequenceName = "consulta_id_seq", allocationSize = 1)
	private Integer id;
	private String sintomas;
	private String receita;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_medico")
	private Medico medico = new Medico();
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_paciente")
	private Paciente paciente = new Paciente();
	@Column(name = "data_consulta")
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
	private Date dataConsulta;
	@Column(name = "data_atendimento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtendimento;

	public Consulta() {
	}

	public Consulta(Integer id, String sintomas, String receita, Medico medico,
			Paciente paciente, Date dataConsulta, Date dataAtendimento) {
		this.id = id;
		this.sintomas = sintomas;
		this.receita = receita;
		this.medico = medico;
		this.paciente = paciente;
		this.dataConsulta = dataConsulta;
		this.dataAtendimento = dataAtendimento;
	}

	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the sintomas
	 */
	public String getSintomas() {
		return sintomas;
	}

	/**
	 * @param sintomas
	 *            the sintomas to set
	 */
	public void setSintomas(String sintomas) {
		this.sintomas = sintomas;
	}

	/**
	 * @return the receita
	 */
	public String getReceita() {
		return receita;
	}

	/**
	 * @param receita
	 *            the receita to set
	 */
	public void setReceita(String receita) {
		this.receita = receita;
	}

	/**
	 * @return the medico
	 */
	public Medico getMedico() {
		return medico;
	}

	/**
	 * @param medico
	 *            the medico to set
	 */
	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	/**
	 * @return the paciente
	 */
	public Paciente getPaciente() {
		return paciente;
	}

	/**
	 * @param paciente
	 *            the paciente to set
	 */
	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	/**
	 * @return the dataConsulta
	 */
	public Date getDataConsulta() {
		return dataConsulta;
	}

	/**
	 * @param dataConsulta
	 *            the dataConsulta to set
	 */
	public void setDataConsulta(Date dataConsulta) {
		this.dataConsulta = dataConsulta;
	}

	/**
	 * @return the dataAtendimento
	 */
	public Date getDataAtendimento() {
		return dataAtendimento;
	}

	/**
	 * @param dataAtendimento
	 *            the dataAtendimento to set
	 */
	public void setDataAtendimento(Date dataAtendimento) {
		this.dataAtendimento = dataAtendimento;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((dataAtendimento == null) ? 0 : dataAtendimento.hashCode());
		result = prime * result
				+ ((dataConsulta == null) ? 0 : dataConsulta.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((medico == null) ? 0 : medico.hashCode());
		result = prime * result
				+ ((paciente == null) ? 0 : paciente.hashCode());
		result = prime * result + ((receita == null) ? 0 : receita.hashCode());
		result = prime * result
				+ ((sintomas == null) ? 0 : sintomas.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Consulta other = (Consulta) obj;
		if (dataAtendimento == null) {
			if (other.dataAtendimento != null)
				return false;
		} else if (!dataAtendimento.equals(other.dataAtendimento))
			return false;
		if (dataConsulta == null) {
			if (other.dataConsulta != null)
				return false;
		} else if (!dataConsulta.equals(other.dataConsulta))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (medico == null) {
			if (other.medico != null)
				return false;
		} else if (!medico.equals(other.medico))
			return false;
		if (paciente == null) {
			if (other.paciente != null)
				return false;
		} else if (!paciente.equals(other.paciente))
			return false;
		if (receita == null) {
			if (other.receita != null)
				return false;
		} else if (!receita.equals(other.receita))
			return false;
		if (sintomas == null) {
			if (other.sintomas != null)
				return false;
		} else if (!sintomas.equals(other.sintomas))
			return false;
		return true;
	}

}
