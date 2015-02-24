package geek.hub.potionmaster.Models.Combination;

public class Spell {

	public String name;
	
	public int[][] combination = new int[3][3];
	
	public Spell(String name, int[][] combination) {
		this.name = name;
		this.combination = combination;
	}
	
	public boolean checkSpellCombination(int[][] selectedCombination) { 
		for (int i = 0; i < combination.length; i++)
			for (int j = 0; j < combination[i].length; j++)
				if (selectedCombination[j][i] != combination[i][j])
					return false;
		return true;
	}
	
}
