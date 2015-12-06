//Conquistar na totalidade a ASIA e a ÁFRICA

package model;

import java.util.ArrayList;

public class Objetivo_2 extends Objetivo {
	
	public Objetivo_2(){
		this.nome = "Asia e Africa";
		this.descricao = "Conquistar na totalidade Asia e Africa";
	}

	@Override
	public boolean Check(ArrayList<Continente> lstContinentes, Exercito e) {
	
		for (Continente c : lstContinentes) {
			if (c.getNome().equals("Asia")) {
				for (Territorio t : c.getLstTerritorios()) {
					if (t.getLstSoldados().get(0).getExercito() != e) {
						return false;
					}
				}
			}
			if (c.getNome().equals("Africa")){
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
