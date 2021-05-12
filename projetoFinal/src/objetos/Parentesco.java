package objetos;

public enum Parentesco {

	FILHO("Filho"), SOBRINHO("Sobrinho"), OUTROS("Outros");
	
	private String parente;
	
	private Parentesco(String tipo) {
		this.parente = tipo;
	}

	public String getParente() {
		return parente;
	}
	
}
