package br.com.gabriel.loja.service;

import br.com.gabriel.loja.daos.CompraDao;
import br.com.gabriel.loja.infra.MailSender;
import br.com.gabriel.loja.models.Compra;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

@MessageDriven(activationConfig = {
		@ActivationConfigProperty(
				propertyName="destinationLookup",
				propertyValue="java:/jms/topics/CarrinhoComprasTopico")
})
public class EnviaEmailCompra implements MessageListener {

	@Inject
	private MailSender mailSender;
	
	@Inject
	private CompraDao compraDao;

	@Override
	public void onMessage(Message message) {

		try {
			TextMessage textMessage = (TextMessage) message;

			Compra compra = compraDao.buscaPorUuid(textMessage.getText());

			String messageBody = "Sua compra foi realizada com sucesso, com número de pedido " + compra.getUuid();
			mailSender.send("compras@casadocodigo.com.br", compra.getUsuario().getEmail(), "Nova compra na CDC",
					messageBody);
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
