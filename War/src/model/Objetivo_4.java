//Conquistar na totalidade a AM�RICA DO NORTE e a �FRICA.

package model;

import java.util.ArrayList;

public class Objetivo_4 extends Objetivo {
	
	public Objetivo_4(){
		this.nome = "America do Norte e Africa";
		this.descricao = "Conquistar na totalidade America do Norte e Africa";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {

		for (Continente c : lstContinentes) {
			if (c.getNome().equals("Africa")) {
				for (Territorio t : c.getLstTerritorios()) {
					if (t.getLstSoldados().get(0).getExercito() != e) {
						return false;
					}
				}
			}
			if (c.getNome().equals("America do norte")){
				for(Territorio t: c.getLstTerritorios()){
					if(t.getLstSoldados().get(0).getExercito() != e){
						return false;
					}
				}
			}

		}
		
	return true;
		
	}

}
