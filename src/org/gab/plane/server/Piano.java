package org.gab.plane.server;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;





public class Piano 
{

private Integer id;
   private String nome;
   private Set<Corso> corsi;
   private Set<Sal> sals;
   private Timestamp last_update;
   private Studente studente;
   private double oreStudioGiorno;
   
   
   public Piano() 
   {
	   corsi = new HashSet<Corso>();
	   sals = new HashSet<Sal>();
   }

	public Piano(java.lang.Integer id) {
		this.id = id;
		corsi = new HashSet<Corso>();
		sals = new HashSet<Sal>();
	}


	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the corsi
	 */
	public Set<Corso> getCorsi() {
		return corsi;
	}

	/**
	 * @param corsi the corsi to set
	 */
	public void setCorsi(Set<Corso> corsi) {
		this.corsi = corsi;
	}

	/**
	 * @return the last_update
	 */
	public Timestamp getLast_update() {
		return last_update;
	}

	/**
	 * @param last_update the last_update to set
	 */
	public void setLast_update(Timestamp last_update) {
		this.last_update = last_update;
	}

	/**
	 * @return the studente
	 */
	public Studente getStudente() {
		return studente;
	}

	/**
	 * @param studente the studente to set
	 */
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	
	

	/**
	 * @return the oreStudioGiorno
	 */
	public double getOreStudioGiorno() {
		return oreStudioGiorno;
	}

	/**
	 * @param oreStudioGiorno the oreStudioGiorno to set
	 */
	public void setOreStudioGiorno(double oreStudioGiorno) {
		this.oreStudioGiorno = oreStudioGiorno;
	}

	/**
	 * @return the sals
	 */
	public Set<Sal> getSals() {
		return sals;
	}

	/**
	 * @param sals the sals to set
	 */
	public void setSals(Set<Sal> sals) {
		this.sals = sals;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((corsi == null) ? 0 : corsi.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		long temp;
		temp = Double.doubleToLongBits(oreStudioGiorno);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((sals == null) ? 0 : sals.hashCode());
		result = prime * result
				+ ((studente == null) ? 0 : studente.hashCode());
		return result;
	}

	/* (non-Javadoc)
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
		Piano other = (Piano) obj;
		if (corsi == null) {
			if (other.corsi != null)
				return false;
		} else if (!corsi.equals(other.corsi))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (Double.doubleToLongBits(oreStudioGiorno) != Double
				.doubleToLongBits(other.oreStudioGiorno))
			return false;
		if (sals == null) {
			if (other.sals != null)
				return false;
		} else if (!sals.equals(other.sals))
			return false;
		if (studente == null) {
			if (other.studente != null)
				return false;
		} else if (!studente.equals(other.studente))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Piano [id=" + id + ", nome=" + nome 
				+ ", last_update=" + last_update+ "oreStudioGiorno: "+ oreStudioGiorno +  "]";
	}

	
	
	
	   
}
