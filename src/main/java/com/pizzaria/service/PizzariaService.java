package com.pizzaria.service;

import com.pizzaria.model.Cliente;
import com.pizzaria.model.Pedido;
import com.pizzaria.model.StatusPedido;

import jakarta.jws.WebMethod;
import jakarta.jws.WebService;
import java.util.ArrayList;
import java.util.List;

@WebService
public class PizzariaService {
    private static List<Cliente> clientes = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    @WebMethod
    public String cadastrarCliente(Cliente cliente) {
        cliente.setId(clientes.size() + 1);
        clientes.add(cliente);
        return "Cliente cadastrado com ID: " + cliente.getId();
    }

    @WebMethod
    public String realizarPedido(int clienteId, List<String> sabores, String borda) {
        Cliente cliente = clientes.stream().filter(c -> c.getId() == clienteId).findFirst().orElse(null);
        if (cliente == null) return "Cliente não encontrado.";

        Pedido pedido = new Pedido();
        pedido.setId(pedidos.size() + 1);
        pedido.setCliente(cliente);
        pedido.setSabores(sabores);
        pedido.setBorda(borda);
        pedido.setStatus(StatusPedido.RECEBIDO);
        pedido.setValorTotal(50.0); // Valor fixo para simplificar
        pedidos.add(pedido);

        return "Pedido realizado com ID: " + pedido.getId() + ", total R$ " + pedido.getValorTotal();
    }

    @WebMethod
    public String consultarStatus(int pedidoId) {
        for (Pedido pedido : pedidos) {
            if (pedido.getId() == pedidoId) {
                return "Status do pedido " + pedidoId + ": " + pedido.getStatus();
            }
        }
        return "Pedido não encontrado.";
    }
}