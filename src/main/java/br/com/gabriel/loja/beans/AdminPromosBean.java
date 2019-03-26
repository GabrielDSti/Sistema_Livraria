package br.com.gabriel.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.gabriel.loja.models.Promo;
import br.com.gabriel.loja.websockets.PromosEndpoint;

@Model
public class AdminPromosBean {

	private Promo promo = new Promo();

	@Inject
	private PromosEndpoint promos;
	
	public void enviar() {
		promos.send(promo);		
	}
	
	public Promo getPromo() {
		return promo;
	}

	public void setPromo(Promo promo) {
		this.promo = promo;
	}
}
