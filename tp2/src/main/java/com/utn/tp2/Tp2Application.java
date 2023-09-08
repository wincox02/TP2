package com.utn.tp2;

import com.utn.tp2.entidades.*;
import com.utn.tp2.repositorios.ClienteRepository;
import com.utn.tp2.repositorios.DomicilioRepository;
import com.utn.tp2.repositorios.RubroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class Tp2Application {
	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	RubroRepository rubroRepository;

	public static void main(String[] args) {
		SpringApplication.run(Tp2Application.class, args);
	}

	@Bean
	CommandLineRunner init() {
		return args -> {
			System.out.println("Funcionando");

			Cliente cliente = Cliente.builder()
					.apellido("Calca")
					.nombre("Mati")
					.email("mail@gmail.com")
					.telefono("2616319684")
					.build();

			Domicilio domicilio = Domicilio.builder()
					.calle("Jorge")
					.numero("477")
					.localidad("Mendoza")
					.build();

			cliente.agregarDomicilio(domicilio); //agregar domicilio al cliente

			Producto producto = Producto.builder()
					.tipo("insumo")
					.tiempoEstimadoCocina(15)
					.denominacion("Hamburguesa")
					.precioVenta(1500.0)
					.precioCompra(1000.0)
					.stockActual(88)
					.stockMinimo(10)
					.unidadMedida("Unidad")
					.receta("pan, lechuga, mayonesa y hamburguesa 15mins")
					.build();

			DetallePedido detallePedido = DetallePedido.builder()
					.cantidad(10)
					.subtotal(1500.0)
					.producto(producto) //agregar producto al detallePedido
					.build();

			Calendar calendar2 = Calendar.getInstance();
			calendar2.set(2023, 3, 1);
			Factura factura = Factura.builder()
					.numero(123123123)
					.fecha(calendar2.getTime())
					.descuento(12)
					.formaPago("efectivo")
					.total(15000)
					.build();

			Calendar calendar = Calendar.getInstance();
			calendar.set(2023, 2, 1);
			Pedido pedido = Pedido.builder()
					.estado("iniciado")
					.fecha(calendar.getTime())
					.tipoEnvio("delivery")
					.total(2500)
					.factura(factura) //agregar factura
					.build();

			Rubro rubro = Rubro.builder()
					.denominacion("Comida Rapida")
					.build();

			pedido.agregarDetallePedido(detallePedido); //agrego el detalle al pedido

			rubro.agregaProducto(producto); //agregar producto a un Rubro
			rubroRepository.save(rubro);

			cliente.agregarPedido(pedido);

			clienteRepository.save(cliente);

			Cliente clienteRecuperado = clienteRepository.findById(cliente.getId()).orElse(null);
			if (clienteRecuperado != null){
				System.out.println("Nombre: " + clienteRecuperado.getNombre());
				System.out.println("Apellido: " + clienteRecuperado.getApellido());
				System.out.println("Telefono: " + clienteRecuperado.getTelefono());
				System.out.println("email: " + clienteRecuperado.getEmail());
				clienteRecuperado.mostrarDomicilios();
				//clienteRecuperado.mostrarPedidos();

				for (Pedido pedidorec : clienteRecuperado.getPedidos()){
					if (pedidorec.getFactura() != null) {
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Se encontro una factura, detalles de la factura:");
						System.out.println("numero: " + pedidorec.getFactura().getNumero());
						System.out.println("Fecha: " + pedidorec.getFactura().getFecha());
						System.out.println("descuento: " + pedidorec.getFactura().getDescuento());
						System.out.println("forma de pago: " + pedidorec.getFactura().getFormaPago());
						System.out.println("total: " + pedidorec.getFactura().getTotal());
					}
					for (DetallePedido detallePedidorec : pedidorec.getDetallePedidos()){
						System.out.println("-------------------------------------------------------------------------");
						System.out.println("Se encontro detalle pedido:");
						System.out.println("Cantidad: " + detallePedidorec.getCantidad());
						System.out.println("Subtotal: " + detallePedidorec.getSubtotal());
						System.out.println("------------------Detalle producto ordenado: ----------------------------");
						System.out.println("Tipo: " + detallePedidorec.getProducto().getTipo());
						System.out.println("tiempoEstimadoCocina: " + detallePedidorec.getProducto().getTiempoEstimadoCocina());
						System.out.println("denominacion: " + detallePedidorec.getProducto().getDenominacion());
						System.out.println("precioVenta: " + detallePedidorec.getProducto().getPrecioVenta());
						System.out.println("precioCompra: " + detallePedidorec.getProducto().getPrecioCompra());
						System.out.println("stockActual: " + detallePedidorec.getProducto().getStockActual());
						System.out.println("stockMinimo: " + detallePedidorec.getProducto().getStockMinimo());
						System.out.println("unidadMedida: " + detallePedidorec.getProducto().getUnidadMedida());
						System.out.println("receta: " + detallePedidorec.getProducto().getReceta());
						System.out.println("---------------------------Rubro del producto----------------------------");
					}
				}
			}
			List<Rubro> rubroRecuperados = rubroRepository.findAll();
			for (Rubro rubroRecuperado : rubroRecuperados){
				System.out.println("-------------------------------------------------------------------------");
				System.out.println("Rubro encontrado: denominacion: " + rubroRecuperado.getDenominacion());
				System.out.println("PRODUCTOS ASOCIADOS: ");
				for (Producto prod : rubroRecuperado.getProductos()){
					System.out.println("Tipo: " + prod.getTipo());
					System.out.println("Denominacion: " + prod.getDenominacion());
				}
			}
		};
	}
}
