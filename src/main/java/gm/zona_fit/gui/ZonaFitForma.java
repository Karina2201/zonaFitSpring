package gm.zona_fit.gui;

import gm.zona_fit.servicio.ClienteServicio;
import gm.zona_fit.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

@Component
public class ZonaFitForma extends JFrame{
    private JPanel panelPrincial;
    private JTable clientesTabla;
    IClienteServicio clienteServicio;
    private DefaultTableModel tablaDeModeloClientes;

    @Autowired
    public ZonaFitForma(ClienteServicio clienteServicio){
        this.clienteServicio = clienteServicio;
        iniciarForma();
    }

    private void iniciarForma(){
        setContentPane(panelPrincial);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900,700);
        setLocationRelativeTo(null);
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        this.tablaDeModeloClientes = new DefaultTableModel(0,4);
        String[] cabeceros = {"ID", "NOMBRE", "APELLIDO", "MEMBRESIA"};
        this.tablaDeModeloClientes.setColumnIdentifiers(cabeceros);
        this.clientesTabla = new JTable(tablaDeModeloClientes);
        //cargar listado de clientes
        listarClientes();
    }

    private void listarClientes() {
        this.tablaDeModeloClientes.setRowCount(0);
        var clientes = this.clienteServicio.listarClientes();
        clientes.forEach(cliente -> {
            Object[] renglonCliente = {
                    cliente.getId(),
                    cliente.getNombre(),
                    cliente.getApellido(),
                    cliente.getMembresia()
            };
            this.tablaDeModeloClientes.addRow(renglonCliente);
        });
    }
}
