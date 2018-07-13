package com.edilson.cursoms;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.edilson.cursoms.domain.Categoria;
import com.edilson.cursoms.domain.Cidade;
import com.edilson.cursoms.domain.Cliente;
import com.edilson.cursoms.domain.Endereco;
import com.edilson.cursoms.domain.Estado;
import com.edilson.cursoms.domain.ItemPedido;
import com.edilson.cursoms.domain.Pagamento;
import com.edilson.cursoms.domain.PagamentoComBoleto;
import com.edilson.cursoms.domain.PagamentoComCartao;
import com.edilson.cursoms.domain.Pedido;
import com.edilson.cursoms.domain.Produto;
import com.edilson.cursoms.enums.EstadoPagamento;
import com.edilson.cursoms.enums.TipoCliente;
import com.edilson.cursoms.repositories.CategoriaRepository;
import com.edilson.cursoms.repositories.CidadeRepository;
import com.edilson.cursoms.repositories.ClienteRepository;
import com.edilson.cursoms.repositories.EnderecoRepository;
import com.edilson.cursoms.repositories.EstadoRepository;
import com.edilson.cursoms.repositories.ItemPedidoRepository;
import com.edilson.cursoms.repositories.PagamentoRepository;
import com.edilson.cursoms.repositories.PedidoRepository;
import com.edilson.cursoms.repositories.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner{

	@Autowired 
	private CategoriaRepository categoriaRepository; 	
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	
	
	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args); // o Spring não está lançando a exeção MethodArgumentNotValidException então não é possivel capturá-la no @controllerAdivice
	}

	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Informática");
		Categoria cat2 = new Categoria(null,"Escritório");		
		Produto p1 = new Produto(null,"Computador",2000.4);
		Produto p2 = new Produto(null,"Impressora",800.0);
		Produto p3  = new Produto(null,"Mouse",80.0);
		
		cat1.getProdutos().addAll(Arrays.asList(p1,p2,p3));
		cat2.getProdutos().addAll(Arrays.asList(p2));		
		p1.getCategorias().addAll(Arrays.asList(cat1));
		p2.getCategorias().addAll(Arrays.asList(cat1,cat2));
		p3.getCategorias().addAll(Arrays.asList(cat1));
		
		categoriaRepository.saveAll(Arrays.asList(cat1,cat2));
		produtoRepository.saveAll(Arrays.asList(p1,p2,p3));
		
		Estado est1 = new Estado(null, "Minas Gerais");
		Estado est2 = new Estado(null, "São Paulo");	
		Cidade c1 = new Cidade(null,"Uberlândia",est1);
		Cidade c2 = new Cidade(null,"São Paulo",est2);
		Cidade c3 = new Cidade(null,"Campinas",est2);		
		
		est1.getCidades().addAll(Arrays.asList(c1));
		est2.getCidades().addAll(Arrays.asList(c2,c3));
			
		estadoRepository.saveAll(Arrays.asList(est1,est2));		
		cidadeRepository.saveAll(Arrays.asList(c1,c2,c3));
		
		Cliente cli1 = new Cliente(null, "Maria Silva", "maria@gmail.com", "36378912377", TipoCliente.PESSOAFISICA);
		
		cli1.getTelefones().addAll(Arrays.asList("27363323","93838333"));
		
		Endereco e1 = new Endereco(null, "Rua Flores", "300", "Apto 303", "Jardin", "38220834", cli1, c1);
		Endereco e2 = new Endereco(null, "Av Matos", "105", "Sala 800", "Centro", "38777012", cli1, c2);
		
		cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
		
		clienteRepository.saveAll(Arrays.asList(cli1));		
		enderecoRepository.saveAll(Arrays.asList(e1,e2)); 
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), e1, cli1);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), e2, cli1);
		
		Pagamento pagt1 = new PagamentoComCartao(null, ped1, EstadoPagamento.QUITADO,6);
		ped1.setPagamento(pagt1);
		
		Pagamento pagt2 = new PagamentoComBoleto(null, ped2, EstadoPagamento.PENDENTE, sdf.parse("20/10/2017 00:00"), null);
		ped2.setPagamento(pagt2);
		
		cli1.getPedidos().addAll(Arrays.asList(ped1,ped2));
		
		pagamentoRepository.saveAll(Arrays.asList(pagt1,pagt2));
		pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
		
		
		ItemPedido ip1 = new ItemPedido(ped1, p1, 0.00, 1, 2000.00);
		ItemPedido ip2 = new ItemPedido(ped1, p3, 0.00, 2, 80.00);
		ItemPedido ip3 = new ItemPedido(ped2, p2, 100.00, 1, 800.00);
		
		ped1.getItens().addAll(Arrays.asList(ip1,ip2));
		ped2.getItens().addAll(Arrays.asList(ip3));
		
		p1.getItens().addAll(Arrays.asList(ip1));
		p2.getItens().addAll(Arrays.asList(ip3));
		p3.getItens().addAll(Arrays.asList(ip2));
		
		itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
		
		//novas categorias apenas para teste da ende point page
		categoriaRepository.saveAll(Arrays.asList(
					new Categoria(null, "Contrução de  robos"),
					new Categoria(null, "Brinquedos"),
					new Categoria(null, "Armas de fogo"),
					new Categoria(null, "Armas de água"),
					new Categoria(null, "Verduras"),
					new Categoria(null, "Coisas de nerd"),
					new Categoria(null, "Remédios"),
					new Categoria(null, "Coisa de matar mostros"),
					new Categoria(null, "Nada de Cobaias por aqui")
				));
		
		
		
		
		
	}
}
