package br.com.gabriel.loja.service;

import br.com.gabriel.loja.models.Pagamento;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import java.io.Serializable;
import java.math.BigDecimal;

public class PagamentoGateway implements Serializable {

	private static final long serialVersionUID = 5673439219534870862L;

	public String pagar(BigDecimal total) {

		Client client = ClientBuilder.newClient();
		Pagamento pagamento = new Pagamento(total);
		String target = "http://book-payment.herokuapp.com/payment";
		Entity<Pagamento> json = Entity.json(pagamento);
		return client.target(target).request().post(json, String.class);
	}
}
