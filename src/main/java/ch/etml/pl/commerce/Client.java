package ch.etml.pl.commerce;

import java.math.BigDecimal;

public class Client {
	private int num;
	private String prenom;
	private BigDecimal solde;

	public Client(int num, String prenom, BigDecimal solde) {
		this.num = num;
		this.prenom = prenom;
		this.solde = solde;
	}

	public int getNum() {
		return num;
	}

	public String getPrenom() {
		return prenom;
	}

	public BigDecimal getSolde() {
		return solde;
	}

	public void debit(BigDecimal montant) {
		solde = solde.subtract(montant);
	}

	@Override
	public String toString() {
		return "prenom: "+prenom+", solde: "+solde;
	}
}