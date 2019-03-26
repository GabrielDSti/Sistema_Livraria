package br.com.gabriel.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.gabriel.loja.daos.LivroDao;
import br.com.gabriel.loja.models.CarrinhoCompra;
import br.com.gabriel.loja.models.CarrinhoItem;
import br.com.gabriel.loja.models.Livro;

@Model
public class CarrinhoComprasBean {

	@Inject
	private LivroDao livroDao;

	@Inject
	private CarrinhoCompra carrinho;

	public String add(Integer id) {
		Livro livro = livroDao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);

		return "carrinho?faces-redirect=true";
	}

	public List<CarrinhoItem> getItens() {
		return carrinho.getItens();
	}

	public void remover(CarrinhoItem item) {
		carrinho.remover(item);
	}
}
