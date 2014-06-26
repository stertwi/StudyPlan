package org.gab.plane.server;



import java.net.PasswordAuthentication;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



public class Studente {
	
	
	private Integer id;
	private String nome;
	private String cognome;
	//private PasswordAuthentication pwd;
	private String pwd;
	private Timestamp last_update;
	
	private String matricola;
	private String corsoDiLaurea;
	private Set<Piano> piano;
	private Set<Sal> sals;
	

	public Studente() {
		this.piano = new HashSet<Piano>();
		this.setSals(new HashSet<Sal>());
	}
	public Studente(String nome, String cognome, String pwd,String matricola) {
		this.nome = nome;
		this.cognome = cognome;
		this.pwd = pwd;
		this.matricola = matricola;
		this.piano = new HashSet<Piano>();
		this.setSals(new HashSet<Sal>());
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
	 * @return the cognome
	 */
	public String getCognome() {
		return cognome;
	}
	/**
	 * @param cognome the cognome to set
	 */
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	/**
	 * @return the pwd
	 */
	public String getPwd() {
		return pwd;
	}
	/**
	 * @param pwd the pwd to set
	 */
	public void setPwd(String pwd) {
		this.pwd = pwd;
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
	 * @return the matricola
	 */
	public String getMatricola() {
		return matricola;
	}
	/**
	 * @param matricola the matricola to set
	 */
	public void setMatricola(String matricola) {
		this.matricola = matricola;
	}
	/**
	 * @return the corsoDiLaurea
	 */
	public String getCorsoDiLaurea() {
		return corsoDiLaurea;
	}
	/**
	 * @param corsoDiLaurea the corsoDiLaurea to set
	 */
	public void setCorsoDiLaurea(String corsoDiLaurea) {
		this.corsoDiLaurea = corsoDiLaurea;
	}
	/**
	 * @return the piano
	 */
	public Set<Piano> getPiano() {
		return piano;
	}
	/**
	 * @param piano the piano to set
	 */
	public void setPiano(Set<Piano> piano) {
		this.piano = piano;
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
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result
				+ ((corsoDiLaurea == null) ? 0 : corsoDiLaurea.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((matricola == null) ? 0 : matricola.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		result = prime * result + ((piano == null) ? 0 : piano.hashCode());
		result = prime * result + ((pwd == null) ? 0 : pwd.hashCode());
		result = prime * result + ((sals == null) ? 0 : sals.hashCode());
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
		Studente other = (Studente) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (corsoDiLaurea == null) {
			if (other.corsoDiLaurea != null)
				return false;
		} else if (!corsoDiLaurea.equals(other.corsoDiLaurea))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (matricola == null) {
			if (other.matricola != null)
				return false;
		} else if (!matricola.equals(other.matricola))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		if (piano == null) {
			if (other.piano != null)
				return false;
		} else if (!piano.equals(other.piano))
			return false;
		if (pwd == null) {
			if (other.pwd != null)
				return false;
		} else if (!pwd.equals(other.pwd))
			return false;
		if (sals == null) {
			if (other.sals != null)
				return false;
		} else if (!sals.equals(other.sals))
			return false;
		return true;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		String ris = "";
		try {
			
		

		ris = "Studente [id=" + id + ", nome=" + nome + ", cognome=" + cognome
				+ ", pwd=" + pwd + ", last_update=" + last_update
				+ ", matricola=" + matricola + ", corsoDiLaurea="
				+ corsoDiLaurea + ", piano=" + /*piano +*/"]";
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ris;
	}
	
	
	
	
}
