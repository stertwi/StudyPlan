package org.gab.plane.server;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;




public class Corso  {
	
	private Integer id;
	private String nome;
	private double oreEffettuate;
	private Timestamp last_update;
	private Piano piano;
	
	private double cfu;
	private Timestamp dataEsame;
	private String argomenti;
		
	public Corso() {
	}
	public Corso(java.lang.Integer id) {
		this.id = id;   
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
	 * @return the piano
	 */
	public Piano getPiano() {
		return piano;
	}
	/**
	 * @param piano the piano to set
	 */
	public void setPiano(Piano piano) {
		this.piano = piano;
	}
	
	
	/**
	 * @return the cfu
	 */
	public double getCfu() {
		return cfu;
	}
	/**
	 * @param cfu the cfu to set
	 */
	public void setCfu(double cfu) {
		this.cfu = cfu;
	}
	/**
	 * @return the dataEsame
	 */
	public Timestamp getDataEsame() {
		return dataEsame;
	}
	/**
	 * @param dataEsame the dataEsame to set
	 */
	public void setDataEsame(Timestamp dataEsame) {
		this.dataEsame = dataEsame;
	}
	/**
	 * @return the argomenti
	 */
	public String getArgomenti() {
		return argomenti;
	}
	/**
	 * @param argomenti the argomenti to set
	 */
	public void setArgomenti(String argomenti) {
		this.argomenti = argomenti;
	}
	
	/**
	 * @param argomenti the argomenti to add
	 */
	public void addArgomenti(String argomenti) {
		if(this.argomenti == null) {
			setArgomenti(argomenti);
		}
		else
		this.argomenti = this.argomenti + "////" + argomenti;
	}
	/**
	 * @return the oreEffettuate
	 */
	public double getOreEffettuate() {
		return oreEffettuate;
	}
	/**
	 * @param oreEffettuate the oreEffettuate to set
	 */
	public void setOreEffettuate(double oreEffettuate) {
		this.oreEffettuate = oreEffettuate;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((argomenti == null) ? 0 : argomenti.hashCode());
		long temp;
		temp = Double.doubleToLongBits(cfu);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((dataEsame == null) ? 0 : dataEsame.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		temp = Double.doubleToLongBits(oreEffettuate);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Corso other = (Corso) obj;
		if (argomenti == null) {
			if (other.argomenti != null)
				return false;
		} else if (!argomenti.equals(other.argomenti))
			return false;
		if (Double.doubleToLongBits(cfu) != Double.doubleToLongBits(other.cfu))
			return false;
		if (dataEsame == null) {
			if (other.dataEsame != null)
				return false;
		} else if (!dataEsame.equals(other.dataEsame))
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
		if (Double.doubleToLongBits(oreEffettuate) != Double
				.doubleToLongBits(other.oreEffettuate))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Corso [id=" + id + ", nome=" + nome + ", oreEffettuate="
				+ oreEffettuate + ", cfu=" + cfu + ", dataEsame=" + dataEsame
				+ ", argomenti=" + argomenti + "]";
	}
	
	
	
}
