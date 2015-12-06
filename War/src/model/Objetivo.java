package model;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Objetivo implements Serializable {
	protected String nome;
	protected String descricao;
	protected Exercito exercitoAlvo;
	
	public Exercito getExercitoAlvo(){
		return exercitoAlvo;
	}
	
	public String getNome(){
		return nome;
	}
	
	public String getDescricao(){
		return descricao;
	}
	
	public abstract boolean Check(ArrayList<Continente> lstContinentes, Exercito e);
	

}