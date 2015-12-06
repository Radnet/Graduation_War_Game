package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import controller.ControllerTabuleiro;

public class GameState {
	public List<model.Exercito>     lstJogadores         = new ArrayList<model.Exercito>();

	public List<Jogada>             lstJogadas           = new ArrayList<Jogada>();
	public ArrayList<Continente>    lstContinentes       = new ArrayList<Continente>();

	public ArrayList<Dado>          lstDadosAtaque       = new ArrayList<Dado>();
	public ArrayList<Dado>          lstDadosDefesa       = new ArrayList<Dado>();
	
	public Deck                     deck                  ;                       // 
	public Territorio               territorioOrigem      ;     // nao comunicar
	public Territorio               territorioDestino     ;    // nao comunicar
	public String                   mensagem                ;                 // 
	public int                      qtdTroca               ;
	public boolean                  conquistouTerritorio  ;
	public DeckObjetivos            deckObjetivos          ;
//	
	public Exercito                 jogadorDaVez            ;
	public Jogada					jogadaAtual				;
	public Exercito                  vencedor                  ;
}
