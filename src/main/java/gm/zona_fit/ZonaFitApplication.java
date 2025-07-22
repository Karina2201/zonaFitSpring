package gm.zona_fit;

import gm.zona_fit.modelo.Cliente;
import gm.zona_fit.servicio.IClienteServicio;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class ZonaFitApplication implements CommandLineRunner {

	@Autowired
	private IClienteServicio clienteServicio;

	private static final Logger logger = LoggerFactory.getLogger(ZonaFitApplication.class);

	String nl = System.lineSeparator();

	public static void main(String[] args) {
		logger.info("Inicicando la aplicacion");
		SpringApplication.run(ZonaFitApplication.class, args);
		logger.info("Aplicacion Finalizada");
	}

	@Override
	public void run(String... args) throws Exception {
		zonaFitApp();
	}

	private void zonaFitApp(){
		var salir = false;
		Scanner sc = new Scanner(System.in);
		while(!salir) {
				var opcion = mostrarMenu(sc);
				salir = ejecutarOpciones(opcion, sc);
				logger.info("");
		}
	}

	private int mostrarMenu(Scanner consola){
		logger.info("""
		\n***Aplicacion Zona Fit (Gym)***
		----------MENU---------
		1. Listar clientes
		2. Buscar cliente por id
		3. Agregar cliente
		4. Modicar cliente
		5. Eliminar cliente
		6. Salir
		Selecciona una opcion: \s""");
		return Integer.parseInt(consola.nextLine());
	}

	private boolean ejecutarOpciones(int opcion, Scanner consola) {
		var salir = false;
		switch (opcion) {
			case 1 -> listarClienteConsola();
			case 2 -> buscarClientePorIdConsola(consola);
			case 3 -> agregarClienteConsola(consola);
			case 4 -> modificarClienteConsola(consola);
			case 5 -> eliminarClienteConsola(consola);
			case 6 -> {
				logger.info(nl+ "Regresa pronto" + nl);
				salir = true;
			}
			default -> logger.info("Opcion Invalida " + opcion);
		}
		return salir;
	}

	private void listarClienteConsola(){
		logger.info(nl + " ---- Listado de clientes ---- " + nl);
		List<Cliente> clientes = clienteServicio.listarClientes();
		clientes.forEach(cliente -> logger.info(cliente.toString() + nl));
	}

	private void buscarClientePorIdConsola(Scanner consola){
		logger.info(nl + "---- Buscar cliente por Id ---- " + nl);
		logger.info("Ingresa el id de cliente a buscar: ");
		var idCliente = Integer.parseInt(consola.nextLine());
		Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
		if(cliente != null) {
			logger.info("Cliente encontrado: " + cliente + nl);
		} else {
			logger.info("Cliente NO encontrado : " + cliente + nl);
		}
	}

	private void agregarClienteConsola(Scanner consola) {
		logger.info(nl + "---- Agregar cliente ---- " + nl);
		logger.info("Ingresa el nombre: ");
		var nombre = consola.nextLine();
		logger.info("Ingresa el apellido: ");
		var apellido = consola.nextLine();
		logger.info("Ingresa el numero de membresia: ");
		var membresia = Integer.parseInt(consola.nextLine());
		var cliente =  new Cliente();
		cliente.setNombre(nombre);
		cliente.setApellido(apellido);
		cliente.setMembresia(membresia);
		clienteServicio.guardarClientePorId(cliente);
		logger.info("Cliente agregado: " + cliente + nl);
	}

	private void modificarClienteConsola(Scanner consola) {
		logger.info(nl + " ---- Modificar cliente ---- " + nl);
		logger.info("Ingresa el id del cliente a modificar: ");
		var idCliente = Integer.parseInt(consola.nextLine());
		Cliente cliente = clienteServicio.buscarClientePorId(idCliente);
		if (cliente != null) {
			logger.info("Ingresa el nombre: ");
			var nombre = consola.nextLine();
			logger.info("Ingresa el apellido: ");
			var apellido = consola.nextLine();
			logger.info("Ingresa el numero de membresia: ");
			var membresia = Integer.parseInt(consola.nextLine());
			cliente.setNombre(nombre);
			cliente.setApellido(apellido);
			cliente.setMembresia(membresia);
			clienteServicio.guardarClientePorId(cliente);
			logger.info("Cliente modificado: " + cliente + nl);
		} else {
			logger.info("Ese idCliente " + idCliente + " no existe, ingresa uno correcto." + nl);
		}
	}

	private void eliminarClienteConsola(Scanner consola) {
		logger.info(nl + " ---- Eliminar cliente ---- " + nl);
		logger.info("Ingresa el id de cliente a eliminar: ");
		var idCliente = Integer.parseInt(consola.nextLine());
		var cliente = clienteServicio.buscarClientePorId(idCliente);
		if (cliente != null) {
			clienteServicio.eliminarCliente(cliente);
			logger.info("Cliente Eliminado: " + cliente + nl);
		} else {
			logger.info("Cliente NO encontrado: " + cliente + nl);
		}


	}
}
