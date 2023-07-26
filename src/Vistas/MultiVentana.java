/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Modelo.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import modeloDAO.*;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;

import java.time.LocalDate;

import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;

import java.util.ArrayList;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public final class MultiVentana extends javax.swing.JFrame implements Printable{

    EmpresaHotelera eh = new EmpresaHotelera();
    UsuarioLogueado usuarioLogueado;
    ArrayList<Sucursal> sucursales;
    ArrayList<Cargo> cargos;
    ArrayList<TipoHabitacion> tiposHabitacion;

    //no usar este MultiVentana sin parametros
    public MultiVentana() {
        initComponents();
        pnlRegistrarCliente.setVisible(false);
        pnlPersonalNoRegistrado.setVisible(false);
        
    }
    

    public MultiVentana(UsuarioLogueado usuarioLogueado) {

        this.usuarioLogueado = usuarioLogueado;

        initComponents();
        this.setLocationRelativeTo(this);
        cerrarSesion();
        iniciarFechaHoy();
        pnlRegistrarCliente.setVisible(false);
        pnlPersonalNoRegistrado.setVisible(false);
        pnlEditarPersonal.setVisible(false);
        pnlAgregarPersonal.setVisible(false);
        mostrarAdministrarPersonal();
        System.out.println(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido() + " - " + usuarioLogueado.getId_empleado());

        cargarEmpresaHotelera();

        // Llenando los ComboBox
        llenarComboBoxSucursales();
        llenarComboBoxSucursalesCrearHab();
        llenarComboBoxTipoHab_creHab();
        cargarTiposHabitacion();
        llenarComboBoxSucursalesPersonal();
        llenarComboBoxCargo();
    }
    public void mostrarAdministrarPersonal(){
        if(this.usuarioLogueado.getId_cargo()==2)
        {
            pnlEditarPersonal.setVisible(true);
            pnlAgregarPersonal.setVisible(true);
        }
            

    }

    public void iniciarFechaHoy(){
        LocalDate fechaHoy = LocalDate.now();
        // Formatear la fecha como una cadena de texto
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String fechaTexto = fechaHoy.format(formatter);
        txtFechaPago.setText(fechaTexto);
    }
    // la empresa hotelera es de caracter global, "eh" se declara arriba
    public void cargarEmpresaHotelera(){
        EmpresaHoteleraDAO ehDAO = new EmpresaHoteleraDAO();

        eh = ehDAO.buscarUno(eh);

        //imprimir todos los datos de la empresa
        System.out.println("Razon Social: " + eh.getRazonSocial());
        System.out.println("Ruc: " + eh.getRuc());
    }

    public void llenarComboBoxSucursales() {
        // Limpiando comboBox
        cbxSucursal.removeAllItems();

        SucursalDAO sucursalDAO = new SucursalDAO();
        sucursales = sucursalDAO.buscarTodo();

        // Agregar los nombres de las sucursales al comboBox
        for (Sucursal sucursal : sucursales) {
            cbxSucursal.addItem(sucursal.getNombre());

            //busco el nombre de la sucursal mendiante el id del usuario loguado en los elementos del arraylist
            if (sucursal.getIdSucursal() == usuarioLogueado.getId_sucursal()) {
                cbxSucursal.setSelectedItem(sucursal.getNombre());
            }
        }
    }
    
    public void llenarComboBoxSucursalesCrearHab() {
        // Limpiando comboBox
        cbxSucursal_creHab.removeAllItems();

        SucursalDAO sucursalDAO = new SucursalDAO();
        sucursales = sucursalDAO.buscarTodo();

        // Agregar los nombres de las sucursales al comboBox
        for (Sucursal sucursal : sucursales) {
            cbxSucursal_creHab.addItem(sucursal.getNombre());

            //busco el nombre de la sucursal mendiante el id del usuario loguado en los elementos del arraylist
            if (sucursal.getIdSucursal() == usuarioLogueado.getId_sucursal()) {
                cbxSucursal_creHab.setSelectedItem(sucursal.getNombre());
            }
        }
    }
    
    TipoHabitacionDAO TipoHabDAO = new TipoHabitacionDAO();
    
    public void llenarComboBoxTipoHab_creHab(){
        cbxTipo_creHab.removeAllItems();

        tiposHabitacion = TipoHabDAO.buscarTodo();

        // Agregar los nombres de las sucursales al comboBox
        for (TipoHabitacion th : tiposHabitacion) {
            cbxTipo_creHab.addItem(th.getTipo());
        }
        
    }
    
    public void llenarComboBoxSucursalesPersonal() {
        // Limpiando comboBox
        cbxSucursalPersonalAgregar.removeAllItems();

        SucursalDAO sucursalDAO = new SucursalDAO();
        sucursales = sucursalDAO.buscarTodo();

        // Agregar los nombres de las sucursales al comboBox
        for (Sucursal sucursal : sucursales) {
            cbxSucursalPersonalAgregar.addItem(sucursal.getNombre());

            //busco el nombre de la sucursal mendiante el id del usuario loguado en los elementos del arraylist
            if (sucursal.getIdSucursal() == usuarioLogueado.getId_sucursal()) {
                cbxSucursalPersonalAgregar.setSelectedItem(sucursal.getNombre());
            }
        }
    }
    
    public void llenarComboBoxCargo() {
        // Limpiando comboBox
        cbxCargoPersonalAgregar.removeAllItems();

        CargoDAO cargoDAO = new CargoDAO();
        cargos = cargoDAO.buscarTodo();

        // Agregar los nombres de las sucursales al comboBox
        for (Cargo cargo : cargos) {
            cbxCargoPersonalAgregar.addItem(cargo.getNombre());

            //busco el nombre de la sucursal mendiante el id del usuario loguado en los elementos del arraylist
            if (cargo.getIdCargo()== usuarioLogueado.getId_cargo()) {
                cbxCargoPersonalAgregar.setSelectedItem(cargo.getNombre());
            }
        }
    }

    private void cargarTiposHabitacion() {
        // Antes de agregar los elementos desde la base de datos, asegúrate de que el combobox esté vacío
        cbxTipoHabitacion.removeAllItems();

        // Agregar el elemento "Seleccionar" como primer elemento del combobox
        cbxTipoHabitacion.addItem("");

        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();
        tiposHabitacion = tipoHabitacionDAO.buscarTodo();

        for (TipoHabitacion tipoHabitacion : tiposHabitacion) {
            cbxTipoHabitacion.addItem(tipoHabitacion.getTipo());
        }
    }
    
    


    public void validarCaracteres(java.awt.event.KeyEvent evento) {
        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 47 || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 8482) {
            evento.consume();
            String mensaje = "No se permiten caracteres normales, especiales ni espacios. SOLO NÚMEROS";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void cerrarSesion() {
        try {
            this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            addWindowListener(new WindowAdapter() {
                public void windowClosing(WindowEvent e) {
                    confirmarCierre();
                }
            }
            );
            this.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void confirmarCierre() {
        Object[] opciones = {"Si", "No"};

        int valor = JOptionPane.showOptionDialog(this, "¿Está seguro de cerrar sesión?", "Advertencia", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, null);
        if (valor == JOptionPane.YES_OPTION) {
            Login lg = new Login();
            JOptionPane.showMessageDialog(null, "Cierre de sesión exitosa", "Gracias", JOptionPane.INFORMATION_MESSAGE);
            lg.setVisible(true);
            this.dispose();

        }
    }




    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jTabbedPane1 = new javax.swing.JTabbedPane();
        panelBusquedaHabitacion = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        lblFechaEntrada = new javax.swing.JLabel();
        calFechaIni = new com.toedter.calendar.JCalendar();
        lblFechaSalida = new javax.swing.JLabel();
        calFechaFin = new com.toedter.calendar.JCalendar();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
        pnlFiltrar = new javax.swing.JPanel();
        lblSelectSucursal = new javax.swing.JLabel();
        cbxSucursal = new javax.swing.JComboBox<>();
        lblSelectTipoHabitacion = new javax.swing.JLabel();
        cbxTipoHabitacion = new javax.swing.JComboBox<>();
        btnBuscarHabitaciones = new javax.swing.JButton();
        ventanaReservar = new javax.swing.JPanel();
        pnlBuscarCliente = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtDNICliente = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblDatosCliente = new javax.swing.JTable();
        pnlRegistrarCliente = new javax.swing.JPanel();
        lblClienteNoRegistrado = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombreCliente = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtApellidoCliente = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtCelularCliente = new javax.swing.JTextField();
        btnRegistrar = new javax.swing.JButton();
        pnlReservarHabitacion = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        txtIDHabitacion = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        btnReservar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblDetalleReserva = new javax.swing.JTable();
        jSeparator1 = new javax.swing.JSeparator();
        fmtFechaIngreso = new javax.swing.JFormattedTextField();
        fmtFechaSalida = new javax.swing.JFormattedTextField();
        pnlBoleta = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        txtIDReserva = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtFechaPago = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtCuponSoles = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        txtRes = new javax.swing.JTextArea();
        btnConfirmarPago = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtTotalSoles = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        JTabbedPaneAdministrar = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel11 = new javax.swing.JPanel();
        jLabel37 = new javax.swing.JLabel();
        lblDNIMod = new javax.swing.JLabel();
        lblNombreMod = new javax.swing.JLabel();
        lblApeMod = new javax.swing.JLabel();
        lblFonoMod = new javax.swing.JLabel();
        txtDNIClienteMod = new javax.swing.JTextField();
        txtNameClienteModificar = new javax.swing.JTextField();
        txtApeClienteModificar = new javax.swing.JTextField();
        txtFonoClienteModificar = new javax.swing.JTextField();
        btnBuscarClienteModificar = new javax.swing.JButton();
        btnEliminarCliente = new javax.swing.JButton();
        btnGuardarCliente = new javax.swing.JButton();
        btnEditarClientesModificar = new javax.swing.JButton();
        btnLimpiarModifClientes = new javax.swing.JButton();
        pnlEditarPersonal = new javax.swing.JPanel();
        pnlAgregarPersonal = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        lblDNIPersonal = new javax.swing.JLabel();
        txtDNIPersonalAgregar = new javax.swing.JTextField();
        btnBuscarPersonal = new javax.swing.JButton();
        pnlPersonalNoRegistrado = new javax.swing.JPanel();
        lblNombre = new javax.swing.JLabel();
        lblApePersonal = new javax.swing.JLabel();
        lblFonoPersonal = new javax.swing.JLabel();
        txtNombrePersonalAgregar = new javax.swing.JTextField();
        txtApePersonalAgregar = new javax.swing.JTextField();
        txtFonoPersonalAgregar = new javax.swing.JTextField();
        btnAgregarPersonal = new javax.swing.JButton();
        lblCargoPersonal = new javax.swing.JLabel();
        cbxCargoPersonalAgregar = new javax.swing.JComboBox<>();
        cbxSucursalPersonalAgregar = new javax.swing.JComboBox<>();
        lblSucursalPersonal = new javax.swing.JLabel();
        btnModificarPersonal = new javax.swing.JButton();
        lblPersonalNoRegistrado = new javax.swing.JLabel();
        lblFonoPersonal1 = new javax.swing.JLabel();
        txtContraseñaPersonal = new javax.swing.JPasswordField();
        btnEditarPersonal = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        lblNombreTipoHabitacion = new javax.swing.JLabel();
        lblPrecioTipoHabitacion = new javax.swing.JLabel();
        txtPrecioTipoHabitacionAdmin = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        btnCrearTipoHabitacion = new javax.swing.JButton();
        lblPrecioTipoHabitacion1 = new javax.swing.JLabel();
        txtCapHabiAdmin = new javax.swing.JTextField();
        jScrollPane10 = new javax.swing.JScrollPane();
        tblTipoHabitacionesCreadas = new javax.swing.JTable();
        btnListarTipoHabitacion = new javax.swing.JButton();
        btnLimpiarTipoHabitacion1 = new javax.swing.JButton();
        txtTipoHabitacion = new javax.swing.JTextField();
        lblPrecioTipoHabitacion2 = new javax.swing.JLabel();
        txtDescTipoHabitacion = new javax.swing.JTextField();
        btnGuardarTipoHabitacion = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        tblHabitacionesCreadas = new javax.swing.JTable();
        btnGuardarHabitacion = new javax.swing.JButton();
        btnListarHabitacion = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        btnCrear_creHab = new javax.swing.JButton();
        spnrPiso_creHab = new javax.swing.JSpinner();
        spnrPuerta_creHab = new javax.swing.JSpinner();
        jLabel21 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        cbxTipo_creHab = new javax.swing.JComboBox<>();
        cbxSucursal_creHab = new javax.swing.JComboBox<>();
        jLabel17 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(221, 214, 206));

        panelBusquedaHabitacion.setBackground(new java.awt.Color(221, 214, 206));

        jPanel4.setBackground(new java.awt.Color(221, 214, 206));

        lblFechaEntrada.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFechaEntrada.setText("Fecha de Entrada");

        calFechaIni.setDecorationBordersVisible(true);
        calFechaIni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calFechaIniMouseClicked(evt);
            }
        });
        calFechaIni.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calFechaIniPropertyChange(evt);
            }
        });

        lblFechaSalida.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lblFechaSalida.setText("Fecha de Salida");

        calFechaFin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                calFechaFinMouseClicked(evt);
            }
        });
        calFechaFin.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                calFechaFinPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaEntrada)
                    .addComponent(calFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 431, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 424, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblFechaSalida))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblFechaSalida, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblFechaEntrada))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, 305, Short.MAX_VALUE)
                    .addComponent(calFechaIni, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(42, 42, 42))
        );

        jPanel6.setBackground(new java.awt.Color(221, 214, 206));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Habitaciones Disponibles:");

        tblDisponibles = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDisponibles.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Tipo", "Capacidad", "Descripción", "Habitación", "S/.", "Pendientes", "MaxAfectado dias"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDisponibles.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDisponiblesMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblDisponibles);
        if (tblDisponibles.getColumnModel().getColumnCount() > 0) {
            tblDisponibles.getColumnModel().getColumn(0).setMinWidth(35);
            tblDisponibles.getColumnModel().getColumn(0).setMaxWidth(40);
            tblDisponibles.getColumnModel().getColumn(1).setMinWidth(100);
            tblDisponibles.getColumnModel().getColumn(1).setMaxWidth(130);
            tblDisponibles.getColumnModel().getColumn(2).setMaxWidth(70);
            tblDisponibles.getColumnModel().getColumn(3).setMinWidth(370);
            tblDisponibles.getColumnModel().getColumn(4).setMaxWidth(65);
            tblDisponibles.getColumnModel().getColumn(5).setMinWidth(20);
            tblDisponibles.getColumnModel().getColumn(5).setMaxWidth(40);
            tblDisponibles.getColumnModel().getColumn(6).setMinWidth(70);
            tblDisponibles.getColumnModel().getColumn(6).setMaxWidth(70);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(91, 91, 91)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 261, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        pnlFiltrar.setBackground(new java.awt.Color(221, 214, 206));

        lblSelectSucursal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSelectSucursal.setText("Sucursal:");

        cbxSucursal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxSucursal.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxSucursalItemStateChanged(evt);
            }
        });

        lblSelectTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSelectTipoHabitacion.setText("Tipo de habitación:");

        cbxTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTipoHabitacion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                cbxTipoHabitacionItemStateChanged(evt);
            }
        });

        btnBuscarHabitaciones.setBackground(new java.awt.Color(171, 76, 89));
        btnBuscarHabitaciones.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscarHabitaciones.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarHabitaciones.setText("Buscar");
        btnBuscarHabitaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarHabitacionesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlFiltrarLayout = new javax.swing.GroupLayout(pnlFiltrar);
        pnlFiltrar.setLayout(pnlFiltrarLayout);
        pnlFiltrarLayout.setHorizontalGroup(
            pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrarLayout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(lblSelectSucursal)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59)
                .addComponent(lblSelectTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 97, Short.MAX_VALUE)
                .addComponent(btnBuscarHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        pnlFiltrarLayout.setVerticalGroup(
            pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlFiltrarLayout.createSequentialGroup()
                .addGroup(pnlFiltrarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblSelectSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSelectTipoHabitacion)
                    .addComponent(cbxTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarHabitaciones))
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBusquedaHabitacionLayout = new javax.swing.GroupLayout(panelBusquedaHabitacion);
        panelBusquedaHabitacion.setLayout(panelBusquedaHabitacionLayout);
        panelBusquedaHabitacionLayout.setHorizontalGroup(
            panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaHabitacionLayout.createSequentialGroup()
                .addGap(115, 115, 115)
                .addGroup(panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBusquedaHabitacionLayout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        panelBusquedaHabitacionLayout.setVerticalGroup(
            panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaHabitacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Buscar Disponibles", panelBusquedaHabitacion);

        ventanaReservar.setBackground(new java.awt.Color(221, 214, 206));

        pnlBuscarCliente.setBackground(new java.awt.Color(221, 214, 206));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("DNI Cliente:");

        txtDNICliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDNICliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIClienteKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDNIClienteKeyTyped(evt);
            }
        });

        tblDatosCliente = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDatosCliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblDatosCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Apellido", "Celular"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblDatosCliente);

        javax.swing.GroupLayout pnlBuscarClienteLayout = new javax.swing.GroupLayout(pnlBuscarCliente);
        pnlBuscarCliente.setLayout(pnlBuscarClienteLayout);
        pnlBuscarClienteLayout.setHorizontalGroup(
            pnlBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarClienteLayout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 691, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBuscarClienteLayout.setVerticalGroup(
            pnlBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBuscarClienteLayout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(pnlBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBuscarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2))))
        );

        pnlRegistrarCliente.setBackground(new java.awt.Color(221, 214, 206));

        lblClienteNoRegistrado.setForeground(new java.awt.Color(171, 76, 89));
        lblClienteNoRegistrado.setText("Cliente No Registrado");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel4.setText("Nombre:");

        txtNombreCliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel5.setText("Apellido:");

        txtApellidoCliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel6.setText("Celular:");

        txtCelularCliente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnRegistrar.setBackground(new java.awt.Color(171, 76, 89));
        btnRegistrar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnRegistrar.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrar.setText("Registrar");
        btnRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlRegistrarClienteLayout = new javax.swing.GroupLayout(pnlRegistrarCliente);
        pnlRegistrarCliente.setLayout(pnlRegistrarClienteLayout);
        pnlRegistrarClienteLayout.setHorizontalGroup(
            pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lblClienteNoRegistrado)
                    .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnRegistrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRegistrarClienteLayout.setVerticalGroup(
            pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblClienteNoRegistrado)
                .addGap(18, 18, 18)
                .addGroup(pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        pnlReservarHabitacion.setBackground(new java.awt.Color(221, 214, 206));

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel7.setText("ID Habitación");

        txtIDHabitacion.setEditable(false);
        txtIDHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIDHabitacion.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Fecha de Entrada");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Fecha de Salida");

        btnReservar.setBackground(new java.awt.Color(171, 76, 89));
        btnReservar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnReservar.setForeground(new java.awt.Color(255, 255, 255));
        btnReservar.setText("Reservar");
        btnReservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnReservarActionPerformed(evt);
            }
        });

        tblDetalleReserva = new javax.swing.JTable(){
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tblDetalleReserva.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Estado", "Sucursal", "Empleado", "ID_Hab", "Habitacion", "Tipo", "PrecioxNoche", "Inicio", "Fin", "NroNoche", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        tblDetalleReserva.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblDetalleReservaMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblDetalleReserva);

        fmtFechaIngreso.setEditable(false);
        fmtFechaIngreso.setFormatterFactory(new javax.swing.JFormattedTextField.AbstractFormatterFactory() {
            public javax.swing.JFormattedTextField.AbstractFormatter
            getFormatter(javax.swing.JFormattedTextField tf){

                try {
                    return new javax.swing.text.MaskFormatter("####-##-##");
                }
                catch (java.text.ParseException pe){
                    pe.printStackTrace();
                }
                return null;
            }
        });
        fmtFechaIngreso.setEnabled(false);

        fmtFechaSalida.setFormatterFactory(new javax.swing.JFormattedTextField.AbstractFormatterFactory() {
            public javax.swing.JFormattedTextField.AbstractFormatter
            getFormatter(javax.swing.JFormattedTextField tf){

                try {
                    return new javax.swing.text.MaskFormatter("####-##-##");
                }
                catch (java.text.ParseException pe){
                    pe.printStackTrace();
                }
                return null;
            }
        });
        fmtFechaSalida.setEditable(false);
        fmtFechaSalida.setEnabled(false);

        javax.swing.GroupLayout pnlReservarHabitacionLayout = new javax.swing.GroupLayout(pnlReservarHabitacion);
        pnlReservarHabitacion.setLayout(pnlReservarHabitacionLayout);
        pnlReservarHabitacionLayout.setHorizontalGroup(
            pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 1158, Short.MAX_VALUE)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1089, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReservar, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7)
                                    .addComponent(txtIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(156, 156, 156)
                                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8)
                                    .addComponent(fmtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                                        .addGap(117, 117, 117)
                                        .addComponent(jLabel9))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReservarHabitacionLayout.createSequentialGroup()
                                        .addGap(109, 109, 109)
                                        .addComponent(fmtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlReservarHabitacionLayout.setVerticalGroup(
            pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(jLabel9))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fmtFechaSalida)
                            .addComponent(fmtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnReservar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 125, Short.MAX_VALUE))
        );

        pnlBoleta.setBackground(new java.awt.Color(221, 214, 206));

        jLabel10.setBackground(new java.awt.Color(27, 35, 42));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Pago de Reserva");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("ID de Reserva:");

        txtIDReserva.setEditable(false);
        txtIDReserva.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtIDReserva.setCaretColor(new java.awt.Color(140, 140, 140));
        txtIDReserva.setEnabled(false);

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Fecha de pago:");

        txtFechaPago.setEditable(false);
        txtFechaPago.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtFechaPago.setEnabled(false);

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Cupon en soles:");

        txtCuponSoles.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtCuponSoles.setText("0");
        txtCuponSoles.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                txtCuponSolesPropertyChange(evt);
            }
        });
        txtCuponSoles.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCuponSolesKeyPressed(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCuponSolesKeyTyped(evt);
            }
        });

        txtRes.setColumns(20);
        txtRes.setRows(5);
        jScrollPane4.setViewportView(txtRes);

        btnConfirmarPago.setBackground(new java.awt.Color(171, 76, 89));
        btnConfirmarPago.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnConfirmarPago.setForeground(new java.awt.Color(255, 255, 255));
        btnConfirmarPago.setText("Confirmar Pago");
        btnConfirmarPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnConfirmarPagoActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("S/.");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel15.setText("Monto final:");

        txtTotalSoles.setEditable(false);
        txtTotalSoles.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtTotalSoles.setCaretColor(new java.awt.Color(140, 140, 140));
        txtTotalSoles.setEnabled(false);
        txtTotalSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTotalSolesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlBoletaLayout = new javax.swing.GroupLayout(pnlBoleta);
        pnlBoleta.setLayout(pnlBoletaLayout);
        pnlBoletaLayout.setHorizontalGroup(
            pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBoletaLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 1152, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(pnlBoletaLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBoletaLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(txtTotalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, pnlBoletaLayout.createSequentialGroup()
                            .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel12)
                                .addComponent(jLabel11)
                                .addComponent(jLabel10)
                                .addComponent(jLabel13))
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtFechaPago)
                                .addComponent(txtCuponSoles)
                                .addComponent(txtIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addComponent(btnConfirmarPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(106, 106, 106)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 342, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlBoletaLayout.setVerticalGroup(
            pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoletaLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(pnlBoletaLayout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(37, 37, 37)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtIDReserva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel12)
                            .addComponent(txtFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel13)
                            .addComponent(txtCuponSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTotalSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15))
                        .addGap(27, 27, 27)
                        .addComponent(btnConfirmarPago))
                    .addComponent(jScrollPane4))
                .addGap(18, 18, 18))
        );

        javax.swing.GroupLayout ventanaReservarLayout = new javax.swing.GroupLayout(ventanaReservar);
        ventanaReservar.setLayout(ventanaReservarLayout);
        ventanaReservarLayout.setHorizontalGroup(
            ventanaReservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRegistrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlReservarHabitacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventanaReservarLayout.setVerticalGroup(
            ventanaReservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaReservarLayout.createSequentialGroup()
                .addComponent(pnlBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlReservarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(pnlBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        jTabbedPane1.addTab("Reservar", ventanaReservar);

        jPanel5.setBackground(new java.awt.Color(221, 214, 206));

        jPanel11.setBackground(new java.awt.Color(221, 214, 206));

        jLabel37.setFont(new java.awt.Font("Consolas", 1, 18)); // NOI18N
        jLabel37.setText("Modificar clientes");

        lblDNIMod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDNIMod.setText("DNI");

        lblNombreMod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombreMod.setText("Nombre");

        lblApeMod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblApeMod.setText("Apellido");

        lblFonoMod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFonoMod.setText("Celular");

        txtDNIClienteMod.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtDNIClienteMod.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtDNIClienteModKeyPressed(evt);
            }
        });

        txtNameClienteModificar.setEditable(false);
        txtNameClienteModificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtNameClienteModificar.setEnabled(false);

        txtApeClienteModificar.setEditable(false);
        txtApeClienteModificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtApeClienteModificar.setEnabled(false);

        txtFonoClienteModificar.setEditable(false);
        txtFonoClienteModificar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtFonoClienteModificar.setEnabled(false);

        btnBuscarClienteModificar.setBackground(new java.awt.Color(171, 76, 89));
        btnBuscarClienteModificar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscarClienteModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarClienteModificar.setText("Buscar");
        btnBuscarClienteModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarClienteModificarActionPerformed(evt);
            }
        });

        btnEliminarCliente.setBackground(new java.awt.Color(171, 76, 89));
        btnEliminarCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEliminarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnEliminarCliente.setText("Eliminar");
        btnEliminarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarClienteActionPerformed(evt);
            }
        });

        btnGuardarCliente.setBackground(new java.awt.Color(171, 76, 89));
        btnGuardarCliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardarCliente.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarCliente.setText("Guardar");
        btnGuardarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarClienteActionPerformed(evt);
            }
        });

        btnEditarClientesModificar.setBackground(new java.awt.Color(171, 76, 89));
        btnEditarClientesModificar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEditarClientesModificar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarClientesModificar.setText("Editar");
        btnEditarClientesModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarClientesModificarActionPerformed(evt);
            }
        });

        btnLimpiarModifClientes.setBackground(new java.awt.Color(171, 76, 89));
        btnLimpiarModifClientes.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLimpiarModifClientes.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarModifClientes.setText("Limpiar");
        btnLimpiarModifClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarModifClientesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(63, 63, 63)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(btnBuscarClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnEditarClientesModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33))
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(lblApeMod, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtApeClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(lblFonoMod)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtFonoClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addGap(8, 8, 8)
                                        .addComponent(lblDNIMod, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(35, 35, 35)
                                        .addComponent(txtDNIClienteMod, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel11Layout.createSequentialGroup()
                                        .addComponent(lblNombreMod, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtNameClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(btnGuardarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnLimpiarModifClientes, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEliminarCliente)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel37, javax.swing.GroupLayout.PREFERRED_SIZE, 237, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(16, 16, 16))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel37)
                .addGap(28, 28, 28)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIMod)
                    .addComponent(txtDNIClienteMod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBuscarClienteModificar)
                    .addComponent(btnEditarClientesModificar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNameClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblNombreMod))
                .addGap(23, 23, 23)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApeMod)
                    .addComponent(txtApeClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFonoMod)
                    .addComponent(txtFonoClienteModificar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(60, 60, 60)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarCliente)
                    .addComponent(btnEliminarCliente)
                    .addComponent(btnLimpiarModifClientes))
                .addContainerGap(38, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(219, 219, 219)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(579, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(116, 116, 116)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        JTabbedPaneAdministrar.addTab("Cliente", jPanel5);

        pnlAgregarPersonal.setBackground(new java.awt.Color(221, 214, 206));

        jLabel28.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        jLabel28.setText("Administrar personal");

        lblDNIPersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblDNIPersonal.setText("DNI");

        txtDNIPersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnBuscarPersonal.setBackground(new java.awt.Color(171, 76, 89));
        btnBuscarPersonal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnBuscarPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnBuscarPersonal.setText("Buscar");
        btnBuscarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuscarPersonalActionPerformed(evt);
            }
        });

        pnlPersonalNoRegistrado.setBackground(new java.awt.Color(221, 214, 206));

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombre.setText("Nombre:");

        lblApePersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblApePersonal.setText("Apellido:");

        lblFonoPersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFonoPersonal.setText("Celular");

        txtNombrePersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtApePersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        txtFonoPersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        btnAgregarPersonal.setBackground(new java.awt.Color(171, 76, 89));
        btnAgregarPersonal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnAgregarPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnAgregarPersonal.setText("Agregar");
        btnAgregarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarPersonalActionPerformed(evt);
            }
        });

        lblCargoPersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblCargoPersonal.setText("Cargo");

        cbxCargoPersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        cbxSucursalPersonalAgregar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        lblSucursalPersonal.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblSucursalPersonal.setText("Sucursal");

        btnModificarPersonal.setBackground(new java.awt.Color(171, 76, 89));
        btnModificarPersonal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnModificarPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnModificarPersonal.setText("Modificar");
        btnModificarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarPersonalActionPerformed(evt);
            }
        });

        lblPersonalNoRegistrado.setForeground(new java.awt.Color(171, 76, 89));
        lblPersonalNoRegistrado.setText("Personal No Registrado");

        lblFonoPersonal1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblFonoPersonal1.setText("Contraseña");

        btnEditarPersonal.setBackground(new java.awt.Color(171, 76, 89));
        btnEditarPersonal.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnEditarPersonal.setForeground(new java.awt.Color(255, 255, 255));
        btnEditarPersonal.setText("Editar");
        btnEditarPersonal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarPersonalActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pnlPersonalNoRegistradoLayout = new javax.swing.GroupLayout(pnlPersonalNoRegistrado);
        pnlPersonalNoRegistrado.setLayout(pnlPersonalNoRegistradoLayout);
        pnlPersonalNoRegistradoLayout.setHorizontalGroup(
            pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                .addComponent(lblFonoPersonal1, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(txtContraseñaPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                        .addGap(361, 361, 361)
                                        .addComponent(lblCargoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                        .addGap(28, 28, 28)
                                        .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                                .addComponent(lblFonoPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(txtFonoPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(lblApePersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(lblNombre))
                                                .addGap(18, 18, 18)
                                                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtNombrePersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(txtApePersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lblSucursalPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cbxCargoPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(cbxSucursalPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                        .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(lblPersonalNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                                .addGap(63, 63, 63)
                                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnEditarPersonal)
                                    .addComponent(btnAgregarPersonal))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap(111, Short.MAX_VALUE))
            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                .addGap(209, 209, 209)
                .addComponent(btnModificarPersonal)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlPersonalNoRegistradoLayout.setVerticalGroup(
            pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlPersonalNoRegistradoLayout.createSequentialGroup()
                .addComponent(lblPersonalNoRegistrado)
                .addGap(30, 30, 30)
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblNombre)
                    .addComponent(txtNombrePersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblCargoPersonal)
                    .addComponent(cbxCargoPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblApePersonal)
                    .addComponent(txtApePersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblSucursalPersonal)
                    .addComponent(cbxSucursalPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFonoPersonal)
                    .addComponent(txtFonoPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFonoPersonal1)
                    .addComponent(txtContraseñaPersonal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(pnlPersonalNoRegistradoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnModificarPersonal)
                    .addComponent(btnEditarPersonal))
                .addGap(18, 18, 18)
                .addComponent(btnAgregarPersonal)
                .addGap(0, 18, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlAgregarPersonalLayout = new javax.swing.GroupLayout(pnlAgregarPersonal);
        pnlAgregarPersonal.setLayout(pnlAgregarPersonalLayout);
        pnlAgregarPersonalLayout.setHorizontalGroup(
            pnlAgregarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarPersonalLayout.createSequentialGroup()
                .addGroup(pnlAgregarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlAgregarPersonalLayout.createSequentialGroup()
                        .addGap(193, 193, 193)
                        .addComponent(lblDNIPersonal)
                        .addGap(18, 18, 18)
                        .addComponent(txtDNIPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(124, 124, 124)
                        .addComponent(btnBuscarPersonal))
                    .addGroup(pnlAgregarPersonalLayout.createSequentialGroup()
                        .addGap(67, 67, 67)
                        .addComponent(pnlPersonalNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlAgregarPersonalLayout.createSequentialGroup()
                        .addGap(296, 296, 296)
                        .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(341, Short.MAX_VALUE))
        );
        pnlAgregarPersonalLayout.setVerticalGroup(
            pnlAgregarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlAgregarPersonalLayout.createSequentialGroup()
                .addGap(92, 92, 92)
                .addComponent(jLabel28)
                .addGap(36, 36, 36)
                .addGroup(pnlAgregarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblDNIPersonal)
                    .addComponent(txtDNIPersonalAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnBuscarPersonal))
                .addGap(18, 18, 18)
                .addComponent(pnlPersonalNoRegistrado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(207, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout pnlEditarPersonalLayout = new javax.swing.GroupLayout(pnlEditarPersonal);
        pnlEditarPersonal.setLayout(pnlEditarPersonalLayout);
        pnlEditarPersonalLayout.setHorizontalGroup(
            pnlEditarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarPersonalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAgregarPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        pnlEditarPersonalLayout.setVerticalGroup(
            pnlEditarPersonalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlEditarPersonalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnlAgregarPersonal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        JTabbedPaneAdministrar.addTab("Personal", pnlEditarPersonal);

        jPanel1.setBackground(new java.awt.Color(221, 214, 206));

        jPanel8.setBackground(new java.awt.Color(221, 214, 206));

        jLabel20.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        jLabel20.setText("Agregar Tipos de Habitacion");

        lblNombreTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblNombreTipoHabitacion.setText("Tipo:");

        lblPrecioTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioTipoHabitacion.setText("Precio:  S/.");

        txtPrecioTipoHabitacionAdmin.setFont(new java.awt.Font("Shree Devanagari 714", 1, 16)); // NOI18N

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        btnCrearTipoHabitacion.setBackground(new java.awt.Color(171, 76, 89));
        btnCrearTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCrearTipoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnCrearTipoHabitacion.setText("Crear");
        btnCrearTipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearTipoHabitacionActionPerformed(evt);
            }
        });

        lblPrecioTipoHabitacion1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioTipoHabitacion1.setText("Capacidad:");

        txtCapHabiAdmin.setFont(new java.awt.Font("Shree Devanagari 714", 1, 16)); // NOI18N

        tblTipoHabitacionesCreadas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblTipoHabitacionesCreadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "Tipo", "Capacidad", "Descripción", "Precio"
            }
        ));
        jScrollPane10.setViewportView(tblTipoHabitacionesCreadas);

        btnListarTipoHabitacion.setBackground(new java.awt.Color(171, 76, 89));
        btnListarTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnListarTipoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnListarTipoHabitacion.setText("Listar");
        btnListarTipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarTipoHabitacionActionPerformed(evt);
            }
        });

        btnLimpiarTipoHabitacion1.setBackground(new java.awt.Color(171, 76, 89));
        btnLimpiarTipoHabitacion1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnLimpiarTipoHabitacion1.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiarTipoHabitacion1.setText("Limpiar");
        btnLimpiarTipoHabitacion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarTipoHabitacion1ActionPerformed(evt);
            }
        });

        txtTipoHabitacion.setFont(new java.awt.Font("Shree Devanagari 714", 1, 16)); // NOI18N

        lblPrecioTipoHabitacion2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblPrecioTipoHabitacion2.setText("Descripcion:");

        txtDescTipoHabitacion.setFont(new java.awt.Font("Shree Devanagari 714", 1, 16)); // NOI18N

        btnGuardarTipoHabitacion.setBackground(new java.awt.Color(171, 76, 89));
        btnGuardarTipoHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardarTipoHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarTipoHabitacion.setText("Guardar");
        btnGuardarTipoHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarTipoHabitacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel20, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel8Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(lblPrecioTipoHabitacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtPrecioTipoHabitacionAdmin))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(lblNombreTipoHabitacion)
                                    .addComponent(lblPrecioTipoHabitacion1)
                                    .addComponent(lblPrecioTipoHabitacion2))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTipoHabitacion)
                                    .addComponent(txtDescTipoHabitacion)
                                    .addComponent(txtCapHabiAdmin)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                        .addGap(0, 1, Short.MAX_VALUE)
                                        .addComponent(btnCrearTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btnLimpiarTipoHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(btnListarTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarTipoHabitacion))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 687, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(73, 73, 73))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel20)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(btnListarTipoHabitacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane10, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, Short.MAX_VALUE)
                                .addComponent(btnGuardarTipoHabitacion))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblNombreTipoHabitacion)
                                    .addComponent(txtTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrecioTipoHabitacion1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCapHabiAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(lblPrecioTipoHabitacion2, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtPrecioTipoHabitacionAdmin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lblPrecioTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnLimpiarTipoHabitacion1)
                                    .addComponent(btnCrearTipoHabitacion))))
                        .addContainerGap())
                    .addComponent(jSeparator3)))
        );

        jPanel7.setBackground(new java.awt.Color(221, 214, 206));

        jLabel3.setFont(new java.awt.Font("Consolas", 1, 16)); // NOI18N
        jLabel3.setText("Crear Habitaciones");

        tblHabitacionesCreadas.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblHabitacionesCreadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id_Habitacion", "id_Sucursal", "Piso", "Puerta", "id_Tipo_Hab"
            }
        ));
        jScrollPane9.setViewportView(tblHabitacionesCreadas);

        btnGuardarHabitacion.setBackground(new java.awt.Color(171, 76, 89));
        btnGuardarHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnGuardarHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnGuardarHabitacion.setText("Guardar");
        btnGuardarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarHabitacionActionPerformed(evt);
            }
        });

        btnListarHabitacion.setBackground(new java.awt.Color(171, 76, 89));
        btnListarHabitacion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnListarHabitacion.setForeground(new java.awt.Color(255, 255, 255));
        btnListarHabitacion.setText("Listar");
        btnListarHabitacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarHabitacionActionPerformed(evt);
            }
        });

        jPanel10.setBackground(new java.awt.Color(221, 214, 206));

        btnCrear_creHab.setBackground(new java.awt.Color(171, 76, 89));
        btnCrear_creHab.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCrear_creHab.setForeground(new java.awt.Color(255, 255, 255));
        btnCrear_creHab.setText("Crear");
        btnCrear_creHab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrear_creHabActionPerformed(evt);
            }
        });

        spnrPiso_creHab.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        spnrPuerta_creHab.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel21.setText("Puerta");

        jLabel19.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel19.setText("Piso");

        cbxTipo_creHab.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxTipo_creHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cbxSucursal_creHab.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        cbxSucursal_creHab.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel17.setText("Sucursal");

        jLabel18.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel18.setText("Tipo");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel18, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cbxTipo_creHab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cbxSucursal_creHab, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnrPiso_creHab, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(spnrPuerta_creHab, javax.swing.GroupLayout.DEFAULT_SIZE, 85, Short.MAX_VALUE)
                            .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCrear_creHab, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxSucursal_creHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cbxTipo_creHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel18))
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spnrPiso_creHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spnrPuerta_creHab, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnCrear_creHab)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnGuardarHabitacion)
                    .addComponent(btnListarHabitacion)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 706, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(64, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addComponent(btnListarHabitacion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane9, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnGuardarHabitacion)))
                .addContainerGap(83, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel7, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(47, Short.MAX_VALUE))
        );

        JTabbedPaneAdministrar.addTab("Habitaciones", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(JTabbedPaneAdministrar)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(JTabbedPaneAdministrar, javax.swing.GroupLayout.PREFERRED_SIZE, 723, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 50, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("Administrar", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Método para convertir una fecha en formato "yyyy-MM-dd" a String
    private String convertirFechaAString(Date fecha) {
        if (fecha != null) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            return dateFormat.format(fecha);
        }
        return null;
    }
    
    
    
    private void btnBuscarHabitacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarHabitacionesActionPerformed

        String fechaIni = convertirFechaAString(calFechaIni.getDate()); // Fecha de inicio
        String fechaFin = convertirFechaAString(calFechaFin.getDate()); // Fecha de fin

        if (fechaIni != null && fechaFin != null) {
            //pasamos las fechas String a LocalDate para utilizar sus procedimientos de comparacion de fechas:            
            LocalDate localDateIni = LocalDate.parse(fechaIni);
            LocalDate localDateFin = LocalDate.parse(fechaFin);
            LocalDate fechaActual = LocalDate.now();

            if (localDateIni.isBefore(fechaActual) || localDateFin.isBefore(fechaActual)) {
                String mensaje = "No se puede seleccionar una fecha anterior a la fecha actual.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else if (localDateIni.isAfter(localDateFin)) {
                String mensaje = "Fecha de Inicio debe ser menor a Fecha de Fin.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else if (localDateIni.equals(localDateFin)) {
                String mensaje = "La Fecha de Inicio no puede ser igual a la Fecha de Fin.";
                JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
            } else {

                int sucursalcbx = 0;
                //mediante el nombre de la sucursal, obtenemos su id
                for (Sucursal sucursal : sucursales) {
                    if (sucursal.getNombre().equals(cbxSucursal.getSelectedItem().toString())) {
                        sucursalcbx = sucursal.getIdSucursal();
                    }
                }

                String sucursalNom = cbxSucursal.getSelectedItem().toString() ;
                System.out.println("Sucursal: "+ sucursalcbx +" - " + sucursalNom + "; Inicio: " + localDateIni + "; Final: " + localDateFin);

                limpiarDisponibles();
                fmtFechaIngreso.setText(fechaIni);
                fmtFechaSalida.setText(fechaFin);

                // Borrar resultados anteriores de la tabla
                DefaultTableModel model = (DefaultTableModel) tblDisponibles.getModel();
                model.setRowCount(0);
                

                HabitacionesDisponiblesDAO hdDAO = new HabitacionesDisponiblesDAO();
                ArrayList<HabitacionDisponible> habitacionesDisponibles = hdDAO.buscarTodo(sucursalcbx, localDateIni.toString(), localDateFin.toString());

                // Recorriendo el ArrayList y agregando las filas a la tabla
                for (HabitacionDisponible habitacion : habitacionesDisponibles) {
                    Object[] row = {habitacion.getId_habitacion(),
                                    habitacion.getTipo(),
                                    habitacion.getCapacidad(),
                                    habitacion.getDescripcion(),
                                    habitacion.getHabitacion(),
                                    habitacion.getPrecio(),
                                    habitacion.getReservasSinPagar(),
                                    habitacion.getMaxDuracionAfectada() };
                    model.addRow(row);
                }
            }
        } else {
            String mensaje = "Seleccione tanto la Fecha de Inicio como la Fecha de Fin.";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.WARNING_MESSAGE);
        }

    }//GEN-LAST:event_btnBuscarHabitacionesActionPerformed

    private void cbxTipoHabitacionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxTipoHabitacionItemStateChanged
        if (evt.getStateChange() == ItemEvent.SELECTED) {
            String selectedItem = cbxTipoHabitacion.getSelectedItem().toString();

            if (selectedItem.isEmpty()) {
                // Si el primer item es vacío, se muestra toda la tabla
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblDisponibles.getModel());
                tblDisponibles.setRowSorter(sorter);
                sorter.setRowFilter(null);
            } else {
                // Se filtra la tabla por la columna 'Descripción' usando el item seleccionado
                TableRowSorter<TableModel> sorter = new TableRowSorter<>(tblDisponibles.getModel());
                tblDisponibles.setRowSorter(sorter);
                RowFilter<TableModel, Object> filter = RowFilter.regexFilter(selectedItem, 1); // '1' indica la columna 'Descripción'
                sorter.setRowFilter(filter);
            }
        }
    }//GEN-LAST:event_cbxTipoHabitacionItemStateChanged


    private void txtDNIClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIClienteKeyPressed
        
        validarCaracteres(evt);
        DefaultTableModel modelo = (DefaultTableModel) tblDatosCliente.getModel(); //tabla de datos del cliente
        DefaultTableModel model = (DefaultTableModel) tblDetalleReserva.getModel(); //tabla de detalle de reserva
        //borrar datos de las tablas
        modelo.setRowCount(0);
        model.setRowCount(0);

        //borro contenido de txtRes
        txtRes.setText("");

        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String dni = txtDNICliente.getText();

            if (dni.matches("\\d{8}")) {
                int dniCliente = Integer.parseInt(dni);
                Cliente cliente = new Cliente();
                cliente.setDNI(dniCliente);

                ClienteDAO clienteDAO = new ClienteDAO();
                cliente = clienteDAO.buscarUno(cliente);

                if (cliente.getNombre().equals("")) {
                    // Mostrar aviso de cliente no encontrado
                    modelo.setRowCount(0);
                    JOptionPane.showMessageDialog(this, "Cliente no encontrado. Debe registrarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                    pnlRegistrarCliente.setVisible(true);
                } else {
                    // Mostrar datos del cliente en la tabla
                    modelo.setRowCount(0);
                    Object[] row = {cliente.getNombre(),
                                    cliente.getApellido(),
                                    cliente.getCelular()};
                    modelo.addRow(row);
                    pnlRegistrarCliente.setVisible(false);


                    BuscarReservaDAO brDAO = new BuscarReservaDAO();
                    ArrayList<BuscarReserva> brs = brDAO.buscarTodo(Integer.parseInt(txtDNICliente.getText()));

                    // Recorriendo el ArrayList y agregando las filas a la tabla
                    for (BuscarReserva buscar : brs) {
                        Object[] rowbr = {buscar.getIdReserva(),
                                        buscar.getEstado(),
                                        buscar.getSucursal(),
                                        buscar.getDniEmpleado(),
                                        buscar.getIdHabitacion(),
                                        buscar.getPuerta(),
                                        buscar.getTipo(),
                                        buscar.getPrecioxNoche(),
                                        buscar.getInicio(),
                                        buscar.getFin(),
                                        buscar.getNroNoche(),
                                        buscar.getTotal(),
                        };
                        model.addRow(rowbr);
                    }
                    

                }


            } else {
                // Mostrar aviso de formato de DNI incorrecto
                modelo.setRowCount(0);
                JOptionPane.showMessageDialog(this, "Formato de DNI incorrecto. Debe ser un número de 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);
                pnlRegistrarCliente.setVisible(false);
            }
            
            
        }  
    }//GEN-LAST:event_txtDNIClienteKeyPressed

    private void txtDNIClienteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIClienteKeyTyped
        if(txtDNICliente.getText().length()>7)
            evt.consume();
    }//GEN-LAST:event_txtDNIClienteKeyTyped

    private void btnRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarActionPerformed

        String DNI = txtDNICliente.getText();
        String nombre = txtNombreCliente.getText();
        String apellido = txtApellidoCliente.getText();
        String celular = txtCelularCliente.getText();
        if (nombre.equals("") || apellido.equals("") ||
            DNI.equals("")|| celular.equals("")) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben de estar completos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
        else
        {
            if (DNI.matches("\\d{8}")&&celular.matches("\\d{9}")){
                ClienteDAO cDAO = new ClienteDAO();
                cDAO.crearCliente(Integer.parseInt(DNI), nombre, apellido, celular);
                JOptionPane.showMessageDialog(null, "Registro exitoso");
            }
            else {
                // Mostrar aviso de formato de DNI incorrecto
                
                JOptionPane.showMessageDialog(this, "Formato de celular incorrecto. Debe ser un número de 9 dígitos.", 
                        "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnRegistrarActionPerformed

    private void tblDisponiblesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDisponiblesMouseClicked
        txtIDHabitacion.setText("");
        int filaSeleccionada = tblDisponibles.getSelectedRow(); 
        Object id_habitacion = tblDisponibles.getValueAt(filaSeleccionada, 0); 
        txtIDHabitacion.setText(id_habitacion.toString()); 
    }//GEN-LAST:event_tblDisponiblesMouseClicked

    private void cbxSucursalItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_cbxSucursalItemStateChanged
        limpiarDisponibles();
    }//GEN-LAST:event_cbxSucursalItemStateChanged

    private void calFechaFinMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calFechaFinMouseClicked
        
    }//GEN-LAST:event_calFechaFinMouseClicked

    private void calFechaIniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_calFechaIniMouseClicked

    }//GEN-LAST:event_calFechaIniMouseClicked

    private void calFechaIniPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calFechaIniPropertyChange
        limpiarDisponibles();
    }//GEN-LAST:event_calFechaIniPropertyChange

    private void calFechaFinPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_calFechaFinPropertyChange
        limpiarDisponibles();
    }//GEN-LAST:event_calFechaFinPropertyChange

    private void btnReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnReservarActionPerformed

        String IDHabitacion = txtIDHabitacion.getText();
        String dniEmpleado = usuarioLogueado.getDNI()+"";
        String dniCliente =  txtDNICliente.getText();
        String fechaIngreso = fmtFechaIngreso.getText();
        String fechaSalida = fmtFechaSalida.getText();

        //si la tabla con la informacion del cliente esta vacia eviar que se haga la reserva
        DefaultTableModel modelo = (DefaultTableModel) tblDatosCliente.getModel(); //tabla de datos del cliente
        if (modelo.getRowCount()==0){
            JOptionPane.showMessageDialog(this, "El cliente debe aparecer el la tabla.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } else if (fechaIngreso.equals("") || fechaSalida.equals("") || dniCliente.equals("") || IDHabitacion.equals("")) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben de estar completos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            return;
        } else {
            ReservaDAO reservaDAO = new ReservaDAO();
            if(reservaDAO.crearReserva(Integer.parseInt(IDHabitacion), dniEmpleado, dniCliente, fechaIngreso, fechaSalida)){


                DefaultTableModel model = (DefaultTableModel) tblDetalleReserva.getModel(); //tabla de detalle de reserva
                model.setRowCount(0);

                BuscarReservaDAO brDAO = new BuscarReservaDAO();
                ArrayList<BuscarReserva> brs = brDAO.buscarTodo(Integer.parseInt(txtDNICliente.getText()));

                // Recorriendo el ArrayList y agregando las filas a la tabla
                for (BuscarReserva buscar : brs) {
                    Object[] rowbr = {buscar.getIdReserva(),
                            buscar.getEstado(),
                            buscar.getSucursal(),
                            buscar.getDniEmpleado(),
                            buscar.getIdHabitacion(),
                            buscar.getPuerta(),
                            buscar.getTipo(),
                            buscar.getPrecioxNoche(),
                            buscar.getInicio(),
                            buscar.getFin(),
                            buscar.getNroNoche(),
                            buscar.getTotal(),
                    };
                    model.addRow(rowbr);
                }

                JOptionPane.showMessageDialog(null, "Reserva exitosa");
            } else {
                JOptionPane.showMessageDialog(null, "Error al reservar");
            }

            limpiarDisponibles();
        }
    }//GEN-LAST:event_btnReservarActionPerformed

    private void btnConfirmarPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnConfirmarPagoActionPerformed

        if (!txtIDReserva.getText().equals("")){
            int id_reserva = Integer.parseInt(txtIDReserva.getText());
            String fechaPago = txtFechaPago.getText();

            String descuento = txtCuponSoles.getText();
            descuento = descuento.replace(",", "."); //realizo el cambio de coma por punto para evitar error de formato
            double descuentoDouble = Double.parseDouble(descuento);

            BoletaDAO boletaDAO = new BoletaDAO();
            if(boletaDAO.confirmarPago(id_reserva, fechaPago, descuentoDouble)){

                // pasar la instancia de boletaDAO en txtRes que es un text area
                Boleta boleta = boletaDAO.reporteBoleta(id_reserva);
                String reporteBoleta = boleta.toString();
                txtRes.setText(reporteBoleta);

                DefaultTableModel model = (DefaultTableModel) tblDetalleReserva.getModel(); //tabla de detalle de reserva
                //se elimina de la tabla la fila que tenga el id de reserva que se acaba de pagar
                for (int i = 0; i < model.getRowCount(); i++) {
                    if (model.getValueAt(i, 0).equals(id_reserva)) {
                        model.removeRow(i);
                    }
                }

                txtIDReserva.setText("");
                txtCuponSoles.setText("0");
                JOptionPane.showMessageDialog(null, "Pago exitoso");

                
                /* --- Generando el ticket pdf----   */

                String nombreArchivo = "Boleta_Res-" + id_reserva + "_" + fechaPago;
                // Crear el documento PDF
                CustomDocument document = new CustomDocument();
                document.generatePDF(reporteBoleta, nombreArchivo);

            } else {
                JOptionPane.showMessageDialog(null, "Error al pagar");
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una reserva de la tabla.");
        }
    }//GEN-LAST:event_btnConfirmarPagoActionPerformed

    private void txtTotalSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTotalSolesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTotalSolesActionPerformed

    double montoSemiFinal;
    private void tblDetalleReservaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblDetalleReservaMouseClicked

        txtCuponSoles.setText("0");

        int filaSeleccionada = tblDetalleReserva.getSelectedRow();

        //obtenemos el valor de la columna ID
        Object idReserva = tblDetalleReserva.getValueAt(filaSeleccionada, 0);
        txtIDReserva.setText(idReserva.toString());

        //el monto semi final
        Object montoSemiFinaLocal = tblDetalleReserva.getValueAt(filaSeleccionada, 11);
        //paso el valor a la variable global
        montoSemiFinal = Double.parseDouble(montoSemiFinaLocal.toString());


        //Calculando el monto total con el descuento
        String descuento = txtCuponSoles.getText();
        if (descuento.equals("")){
            descuento = "0";
        }
        double montoTotal = montoSemiFinal - Double.parseDouble(descuento);
        txtTotalSoles.setText(montoTotal+"");

        //borro el contenido de el txtRes donde está el reporte de la boleta
        txtRes.setText("");

    }//GEN-LAST:event_tblDetalleReservaMouseClicked

    private void txtCuponSolesPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_txtCuponSolesPropertyChange
        
    }//GEN-LAST:event_txtCuponSolesPropertyChange


    private void txtCuponSolesKeyPressed(java.awt.event.KeyEvent evt) {                                         

        /*
        Calculando el monto total con el descuento,
        el numero ingresado puede ser decimal con punto o coma que luego se reemplaza por punto,
        si es vacio se reemplaza por 0, si es mayor al monto semi final se reemplaza por 0 y se muestra un mensaje de error
        */

        if(evt.getKeyCode() == KeyEvent.VK_ENTER ){
            String descuento = txtCuponSoles.getText();
            //las comas convertirlas en puntos
            descuento = descuento.replace(",", ".");
            //si es vacio reemplazar por 0
            if (descuento.equals("")){
                descuento = "0";
            }
            //si es mayor al monto semi final reemplazar por 0
            if (Double.parseDouble(descuento) > montoSemiFinal){
                descuento = "0";
                JOptionPane.showMessageDialog(null, "El descuento no puede ser mayor al monto semi final");
            } else {
                //si es menor o igual al monto semi final calcular el monto total
                double montoTotal = montoSemiFinal - Double.parseDouble(descuento);
                txtTotalSoles.setText(montoTotal+"");
            }

        }
    }


    private void txtCuponSolesKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCuponSolesKeyTyped

    }//GEN-LAST:event_txtCuponSolesKeyTyped

    private void btnCrear_creHabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrear_creHabActionPerformed
        // cbxSucursal_creHab Sucursal sucursales
        int idSucursal = 0;
        for (Sucursal suc : sucursales) {
            if (suc.getNombre().equals(cbxSucursal_creHab.getSelectedItem().toString())) {
                idSucursal = suc.getIdSucursal();
                System.out.println("Sucursal: "+idSucursal);
            }
        }

        //mediante el nombre del combo tipoHabitacion, obtenemos su id
        int idTipoHab_creHab = 0;
        for (TipoHabitacion th : tiposHabitacion) {
            if (th.getTipo().equals(cbxTipo_creHab.getSelectedItem().toString())) {
                idTipoHab_creHab = th.getIdTipoHabitacion();
                System.out.println("Tipo: "+idTipoHab_creHab);
            }
        }

        // IMPRIMIR EL NUMERO DEL SPINNER spnrPiso_creHab
        System.out.println("Piso: "+spnrPiso_creHab.getValue());
        // spnrPuerta_creHab
        System.out.println("Piso: "+spnrPuerta_creHab.getValue());

        HabitacionDAO hbDAO = new HabitacionDAO();
        hbDAO.crearHabitacion_creHab(idSucursal, Integer.parseInt(spnrPiso_creHab.getValue().toString()), Integer.parseInt(spnrPuerta_creHab.getValue().toString()), idTipoHab_creHab);

    }//GEN-LAST:event_btnCrear_creHabActionPerformed

    private void btnListarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarHabitacionActionPerformed
        // TODO add your handling code here:

        // Limpiar el modelo de la tabla
        DefaultTableModel tableModel = (DefaultTableModel) tblHabitacionesCreadas.getModel();
        tableModel.setRowCount(0);
        HabitacionDAO habitacionDAO = new HabitacionDAO();

        // Obtener la lista de todas las habitaciones existentes
        ArrayList<Habitacion> habitaciones = habitacionDAO.buscarTodo();

        // Llenar la tabla con los datos de las habitaciones
        for (Habitacion habitacion : habitaciones) {
            Object[] rowData = {
                habitacion.getIdHabitacion(),
                habitacion.getSucursalId(),

                habitacion.getPiso(),
                habitacion.getPuerta(),
                habitacion.getTipoHabitacionId()
            };
            tableModel.addRow(rowData);
        }
    }//GEN-LAST:event_btnListarHabitacionActionPerformed

    private void btnGuardarHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarHabitacionActionPerformed
        // Obtener el modelo de la tabla
        DefaultTableModel modelo = (DefaultTableModel) tblHabitacionesCreadas.getModel();
        int rowCount = modelo.getRowCount();

        HabitacionDAO habitacion = new HabitacionDAO();
        if (tblHabitacionesCreadas.getCellEditor() != null) {
            tblHabitacionesCreadas.getCellEditor().stopCellEditing();
        }

        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            int id_habitacion = Integer.parseInt(modelo.getValueAt(rowIndex, 0).toString());
            int id_sucursal = Integer.parseInt(modelo.getValueAt(rowIndex, 1).toString());
            int piso = Integer.parseInt(modelo.getValueAt(rowIndex, 2).toString());
            int puerta = Integer.parseInt(modelo.getValueAt(rowIndex, 3).toString());
            int id_tipo_habitacion = Integer.parseInt(modelo.getValueAt(rowIndex, 4).toString());

            boolean exito = habitacion.actualizarHabitacion(id_habitacion, id_sucursal, piso, puerta, id_tipo_habitacion);

            if (!exito) {
                mostrarMensaje("Error al actualizar la habitación en la fila " + (rowIndex + 1));
                return; // Salir del método en caso de error
            }
        }

        mostrarMensaje("Cambios guardados exitosamente.");
    }//GEN-LAST:event_btnGuardarHabitacionActionPerformed

    private void btnGuardarTipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarTipoHabitacionActionPerformed
        // TODO add your handling code here:
        DefaultTableModel tableModel = (DefaultTableModel) tblTipoHabitacionesCreadas.getModel();
        int rowCount = tableModel.getRowCount();

        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

        // Detener la edición actual
        if (tblTipoHabitacionesCreadas.getCellEditor() != null) {
            tblTipoHabitacionesCreadas.getCellEditor().stopCellEditing();
        }

        // Actualizar los cambios en la base de datos
        for (int rowIndex = 0; rowIndex < rowCount; rowIndex++) {
            String tipo = tableModel.getValueAt(rowIndex, 1).toString();
            int capacidad = Integer.parseInt(tableModel.getValueAt(rowIndex, 2).toString());
            String descripcion = tableModel.getValueAt(rowIndex, 3).toString();
            double precio = Double.parseDouble(tableModel.getValueAt(rowIndex, 4).toString());

            tipoHabitacionDAO.actualizarTipoHabitacion(tipo, capacidad, descripcion, precio);
        }
        mostrarMensaje("Cambios guardados exitosamente.");
    }//GEN-LAST:event_btnGuardarTipoHabitacionActionPerformed

    private void btnLimpiarTipoHabitacion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarTipoHabitacion1ActionPerformed
        // TODO add your handling code here:
        txtTipoHabitacion.setText("");
        txtCapHabiAdmin.setText("");
        txtDescTipoHabitacion.setText("");
        txtPrecioTipoHabitacionAdmin.setText("");
    }//GEN-LAST:event_btnLimpiarTipoHabitacion1ActionPerformed

    private void btnListarTipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarTipoHabitacionActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelo = (DefaultTableModel) tblTipoHabitacionesCreadas.getModel();
        modelo.setRowCount(0); // Limpiar filas existentes en la tabla

        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();
        ArrayList<TipoHabitacion> tiposHabitaciones = tipoHabitacionDAO.buscarTodo();

        for (TipoHabitacion tipoHabitacion : tiposHabitaciones) {
            Object[] fila = {
                tipoHabitacion.getIdTipoHabitacion(),
                tipoHabitacion.getTipo(),
                tipoHabitacion.getCapacidad(),
                tipoHabitacion.getDescripcion(),
                tipoHabitacion.getPrecio()
            };
            modelo.addRow(fila);
        }
    }//GEN-LAST:event_btnListarTipoHabitacionActionPerformed

    private void btnCrearTipoHabitacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearTipoHabitacionActionPerformed
        // TODO add your handling code here:
        String tipoHabitacion = txtTipoHabitacion.getText();
        String capacidadStr = txtCapHabiAdmin.getText();
        String descripcion = txtDescTipoHabitacion.getText();
        String precioStr = txtPrecioTipoHabitacionAdmin.getText();

        // Verificar que los campos no estén vacíos
        if (tipoHabitacion.isEmpty() || capacidadStr.isEmpty() || descripcion.isEmpty() || precioStr.isEmpty()) {
            mostrarMensaje("Por favor, llene todos los campos.");
            return; // Salir del método sin continuar con la creación del tipo de habitación
        }
        if (!esNumeroEntero(capacidadStr) ||!esNumeroDecimal(precioStr)) {
            mostrarMensaje("Ingrese solo números en los campos numéricos.");
            return;
        }

        // Convertir los campos numéricos a valores enteros o dobles
        int capacidad = Integer.parseInt(capacidadStr);
        double precio = Double.parseDouble(precioStr);

        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();
        int idTipoHabitacion = tipoHabitacionDAO.crearTipoHabitacion(tipoHabitacion, capacidad, descripcion, precio);

        if (idTipoHabitacion != -1) {
            mostrarMensaje("Tipo de habitación creado exitosamente. ID: " + idTipoHabitacion);
            // Realiza otras acciones necesarias después de crear el tipo de habitación, utilizando el ID obtenido
        } else {
            mostrarMensaje("Error al crear el tipo de habitación.");
            // Realiza acciones alternativas en caso de error
        }

    }//GEN-LAST:event_btnCrearTipoHabitacionActionPerformed

    private void btnEditarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarPersonalActionPerformed
        // TODO add your handling code here:
        txtNombrePersonalAgregar.setEnabled(true);
        txtApePersonalAgregar.setEnabled(true);
        txtFonoPersonalAgregar.setEnabled(true);
        txtContraseñaPersonal.setEnabled(true);
        cbxSucursalPersonalAgregar.setEnabled(true);
        cbxCargoPersonalAgregar.setEnabled(true);
    }//GEN-LAST:event_btnEditarPersonalActionPerformed

    private void btnModificarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarPersonalActionPerformed
        // TODO add your handling code here:
        String dniText = txtDNIPersonalAgregar.getText();
        int dni;
        try {
            dni = Integer.parseInt(dniText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = txtNombrePersonalAgregar.getText();
        String apellido = txtApePersonalAgregar.getText();
        String celular = txtFonoPersonalAgregar.getText();
        String contraseña = txtContraseñaPersonal.getText();
        int idSucursal = cbxSucursalPersonalAgregar.getSelectedIndex()+1;
        int idCargo = cbxCargoPersonalAgregar.getSelectedIndex()+1;

        if (nombre.isEmpty() || apellido.isEmpty() || celular.isEmpty() || contraseña.isEmpty() || dni == -1) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            UsuarioLogueadoDAO empleadoDAO = new UsuarioLogueadoDAO();
            boolean actualizarEmpleado = empleadoDAO.actualizarEmpleado(idSucursal, idCargo, dni, nombre, apellido, celular, contraseña);
            if (actualizarEmpleado) {
                JOptionPane.showMessageDialog(null, "Actualización exitosa");
                txtNombrePersonalAgregar.setEnabled(false);
                txtApePersonalAgregar.setEnabled(false);
                txtFonoPersonalAgregar.setEnabled(false);
                txtContraseñaPersonal.setEnabled(false);
                cbxSucursalPersonalAgregar.setEnabled(false);
                cbxCargoPersonalAgregar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al actualizar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnModificarPersonalActionPerformed

    private void btnAgregarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarPersonalActionPerformed
        // TODO add your handling code here:
        String dniText = txtDNIPersonalAgregar.getText();
        int dni;
        try {
            dni = Integer.parseInt(dniText);
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = txtNombrePersonalAgregar.getText();
        String apellido = txtApePersonalAgregar.getText();
        String celular = txtFonoPersonalAgregar.getText();
        String contraseña = txtContraseñaPersonal.getText();
        int idSucursal = cbxSucursalPersonalAgregar.getSelectedIndex()+1;
        int idCargo = cbxCargoPersonalAgregar.getSelectedIndex()+1;
        if (nombre.isEmpty() || apellido.isEmpty() || celular.isEmpty() || contraseña.isEmpty() || dni == -1 || idSucursal == -1 || idCargo == -1) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            UsuarioLogueadoDAO empleadoDAO = new UsuarioLogueadoDAO();
            boolean crearEmpleado = empleadoDAO.crearEmpleado(idSucursal, idCargo,dni, nombre, apellido, celular,  contraseña);
            if (crearEmpleado) {
                JOptionPane.showMessageDialog(null, "Creación exitosa");
                txtNombrePersonalAgregar.setEnabled(false);
                txtApePersonalAgregar.setEnabled(false);
                txtFonoPersonalAgregar.setEnabled(false);
                txtContraseñaPersonal.setEnabled(false);
                cbxSucursalPersonalAgregar.setEnabled(false);
                cbxCargoPersonalAgregar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al crear el empleado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }//GEN-LAST:event_btnAgregarPersonalActionPerformed

    private void btnBuscarPersonalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarPersonalActionPerformed
        // TODO add your handling code here:
        int dni;
        try {
            dni = Integer.parseInt(txtDNIPersonalAgregar.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "El DNI debe ser un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String nombre = txtNombrePersonalAgregar.getText();
        String apellido = txtApePersonalAgregar.getText();
        String celular = txtFonoPersonalAgregar.getText();
        String contraseña = txtContraseñaPersonal.getText();
        int idSucursal = cbxSucursalPersonalAgregar.getSelectedIndex()+1;
        int idCargo = cbxCargoPersonalAgregar.getSelectedIndex()+1;
        if (String.valueOf(dni).matches("\\d{8}")) {
            UsuarioLogueado empleado = new UsuarioLogueado();
            empleado.setDNI(dni);

            UsuarioLogueadoDAO empleadoDAO = new UsuarioLogueadoDAO();
            empleado = empleadoDAO.buscarUno(empleado);
            if (empleado.getNombre().equals("")) {
                JOptionPane.showMessageDialog(this, "Empleado no encontrado. Debe registrarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                pnlPersonalNoRegistrado.setVisible(true);
                btnModificarPersonal.setVisible(false);
                btnEditarPersonal.setVisible(false);
                btnAgregarPersonal.setVisible(true);
                lblPersonalNoRegistrado.setVisible(true);

                txtNombrePersonalAgregar.setEnabled(true);
                txtApePersonalAgregar.setEnabled(true);
                txtFonoPersonalAgregar.setEnabled(true);
                txtContraseñaPersonal.setEnabled(true);
                cbxSucursalPersonalAgregar.setEnabled(true);
                cbxCargoPersonalAgregar.setEnabled(true);

                txtNombrePersonalAgregar.setText("");
                txtApePersonalAgregar.setText("");
                txtFonoPersonalAgregar.setText("");
                txtContraseñaPersonal.setText("");
                cbxSucursalPersonalAgregar.setSelectedIndex(0);
                cbxCargoPersonalAgregar.setSelectedIndex(0);
            } else {
                JOptionPane.showMessageDialog(this, "Empleado encontrado. Puede realizar acciones adicionales si es necesario.", "Aviso", JOptionPane.INFORMATION_MESSAGE);

                txtNombrePersonalAgregar.setText(empleado.getNombre());
                txtApePersonalAgregar.setText(empleado.getApellido());
                txtFonoPersonalAgregar.setText(empleado.getCelular());
                txtContraseñaPersonal.setText(empleado.getContraseña());

                cbxSucursalPersonalAgregar.setSelectedItem(empleado.getId_sucursal());
                cbxCargoPersonalAgregar.setSelectedItem(empleado.getId_cargo());

                pnlPersonalNoRegistrado.setVisible(true);
                lblPersonalNoRegistrado.setVisible(false);
                txtNombrePersonalAgregar.setEnabled(false);
                txtApePersonalAgregar.setEnabled(false);
                txtFonoPersonalAgregar.setEnabled(false);
                txtContraseñaPersonal.setEnabled(false);
                cbxSucursalPersonalAgregar.setEnabled(false);
                cbxCargoPersonalAgregar.setEnabled(false);
                btnModificarPersonal.setVisible(true);
                btnEditarPersonal.setVisible(true);
                btnAgregarPersonal.setVisible(false);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Formato de DNI incorrecto. Debe ser un número de 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_btnBuscarPersonalActionPerformed

    private void btnLimpiarModifClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarModifClientesActionPerformed
        // TODO add your handling code here:
        txtNameClienteModificar.setText("");
        txtApeClienteModificar.setText("");
        txtFonoClienteModificar.setText("");
        txtDNIClienteMod.setText("");
        txtNameClienteModificar.setEditable(false);
        txtApeClienteModificar.setEditable(false);
        txtFonoClienteModificar.setEditable(false);
        txtNameClienteModificar.setEnabled(false);
        txtApeClienteModificar.setEnabled(false);
        txtFonoClienteModificar.setEnabled(false);
    }//GEN-LAST:event_btnLimpiarModifClientesActionPerformed

    private void btnEditarClientesModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarClientesModificarActionPerformed
        // TODO add your handling code here:
        txtNameClienteModificar.setEditable(true);
        txtApeClienteModificar.setEditable(true);
        txtFonoClienteModificar.setEditable(true);
        txtNameClienteModificar.setEnabled(true);
        txtApeClienteModificar.setEnabled(true);
        txtFonoClienteModificar.setEnabled(true);
    }//GEN-LAST:event_btnEditarClientesModificarActionPerformed

    private void btnGuardarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarClienteActionPerformed
        // TODO add your handling code here:
        String dni = txtDNIClienteMod.getText();
        String nombre = txtNameClienteModificar.getText();
        String apellido = txtApeClienteModificar.getText();
        String celular = txtFonoClienteModificar.getText();

        if (nombre.equals("") || apellido.equals("") || celular.equals("")) {
            JOptionPane.showMessageDialog(this, "Todos los campos deben estar completos.", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            if (celular.matches("\\d{9}")) {
                ClienteDAO cDAO = new ClienteDAO();
                boolean actualizacionExitosa = cDAO.actualizarCliente( dni,nombre, apellido, celular);
                if (actualizacionExitosa) {
                    JOptionPane.showMessageDialog(null, "Actualización exitosa");
                    txtNameClienteModificar.setEnabled(false);
                    txtApeClienteModificar.setEnabled(false);
                    txtFonoClienteModificar.setEnabled(false);
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el cliente", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Mostrar aviso de formato de Celular incorrecto
                JOptionPane.showMessageDialog(this, "Formato de celular o DNI incorrecto. Número celular debe ser de 9 dígitos y DNI de 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnGuardarClienteActionPerformed

    private void btnEliminarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarClienteActionPerformed
        // TODO add your handling code here:
        // Obtener los valores del formulario de entrada
        int dniCliente = Integer.parseInt(txtDNIClienteMod.getText());
        String nombre = txtNameClienteModificar.getText();
        String apellido = txtApeClienteModificar.getText();
        String celular = txtFonoClienteModificar.getText();

        // Mostrar un mensaje de confirmación al usuario
        int respuesta = JOptionPane.showOptionDialog(null, "Si ejecuta la eliminación del cliente perderá todos los datos relacionados al contacto así como las reservas realizadas. ¿Desea continuar?"
            , "Confirmar eliminación", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, new String[]{"Sí", "No"}, "No");

        // Verificar la respuesta del usuario
        if (respuesta == JOptionPane.YES_OPTION) {
            // Crear una instancia del CLIENTEDAO
            ClienteDAO cDAO = new ClienteDAO();

            // Llamar al método eliminarCliente y almacenar el resultado
            boolean eliminado = cDAO.eliminarCliente(dniCliente, nombre, apellido, celular);

            // Verificar si el cliente fue eliminado exitosamente
            if (eliminado) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente");
                txtNameClienteModificar.setText("");
                txtApeClienteModificar.setText("");
                txtFonoClienteModificar.setText("");
                txtDNIClienteMod.setText("");
                txtNameClienteModificar.setEditable(false);
                txtApeClienteModificar.setEditable(false);
                txtFonoClienteModificar.setEditable(false);
                txtNameClienteModificar.setEnabled(false);
                txtApeClienteModificar.setEnabled(false);
                txtFonoClienteModificar.setEnabled(false);
            } else {
                JOptionPane.showMessageDialog(null, "Error al eliminar cliente");
            }
        }
    }//GEN-LAST:event_btnEliminarClienteActionPerformed

    private void btnBuscarClienteModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuscarClienteModificarActionPerformed
        // TODO add your handling code here:

        String dni = txtDNIClienteMod.getText();

        if (dni.matches("\\d{8}")) {
            int dniEmpleado = Integer.parseInt(dni);
            Cliente cliente = new Cliente();
            cliente.setDNI(dniEmpleado);

            ClienteDAO clienteDAO = new ClienteDAO();
            cliente = clienteDAO.buscarUno(cliente);
            if (cliente.getNombre().equals("")) {
                // Mostrar aviso de cliente no encontrado

                JOptionPane.showMessageDialog(this, "Cliente no encontrado. Debe registrarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
            }
            else {
                txtNameClienteModificar.setText(cliente.getNombre());
                txtApeClienteModificar.setText(cliente.getApellido());
                txtFonoClienteModificar.setText(cliente.getCelular());

            }
        }

        else {
            // Mostrar aviso de formato de DNI incorrecto

            JOptionPane.showMessageDialog(this, "Formato de DNI incorrecto. Debe ser un número de 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);

        }
    }//GEN-LAST:event_btnBuscarClienteModificarActionPerformed

    private void txtDNIClienteModKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIClienteModKeyPressed
        // TODO add your handling code here:
        validarCaracteres(evt);
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String dni = txtDNIClienteMod.getText();

            if (dni.matches("\\d{8}")) {
                int dniCliente = Integer.parseInt(dni);
                Cliente cliente = new Cliente();
                cliente.setDNI(dniCliente);

                ClienteDAO clienteDAO = new ClienteDAO();
                cliente = clienteDAO.buscarUno(cliente);
                if (cliente.getNombre().equals("")) {
                    // Mostrar aviso de cliente no encontrado

                    JOptionPane.showMessageDialog(this, "Cliente no encontrado. Debe registrarlo.", "Aviso", JOptionPane.WARNING_MESSAGE);
                }
                else {
                    txtNameClienteModificar.setText(cliente.getNombre());
                    txtApeClienteModificar.setText(cliente.getApellido());
                    txtFonoClienteModificar.setText(cliente.getCelular());

                }
            }

            else {
                // Mostrar aviso de formato de DNI incorrecto

                JOptionPane.showMessageDialog(this, "Formato de DNI incorrecto. Debe ser un número de 8 dígitos.", "Aviso", JOptionPane.WARNING_MESSAGE);

            }
        }

    }//GEN-LAST:event_txtDNIClienteModKeyPressed
    private void mostrarMensaje(String mensaje) {
        // Aquí va el código para mostrar un mensaje en la interfaz gráfica
        // Puedes utilizar un cuadro de diálogo, un componente de texto o cualquier otro método apropiado para mostrar el mensaje

        // Ejemplo de muestra de mensaje en un cuadro de diálogo
        JOptionPane.showMessageDialog(this, mensaje);
    }
    private boolean esNumeroDecimal(String texto) {
        try {
            Double.parseDouble(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    private boolean esNumeroEntero(String texto) {
        try {
            Integer.parseInt(texto);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }     private ArrayList<Integer> obtenerIdsTipoHabitacion() {
        ArrayList<Integer> idsTipoHabitacion = new ArrayList<>();

        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();
        ArrayList<TipoHabitacion> tiposHabitaciones = tipoHabitacionDAO.buscarTodo();

        for (TipoHabitacion tipoHabitacion : tiposHabitaciones) {
            idsTipoHabitacion.add(tipoHabitacion.getIdTipoHabitacion());
        }

        return idsTipoHabitacion;
    }    
    private ArrayList<Integer> obtenerTiposHabitacionesDisponibles() {
        // Crear una instancia de la clase TipoHabitacionDAO
        TipoHabitacionDAO tipoHabitacionDAO = new TipoHabitacionDAO();

        // Obtener la lista de todos los tipos de habitación
        ArrayList<TipoHabitacion> tiposHabitaciones = tipoHabitacionDAO.buscarTodo();

        // Crear una lista para almacenar los id_tipo_habitacion disponibles
        ArrayList<Integer> tiposHabitacionesDisponibles = new ArrayList<>();

        // Recorrer la lista de tipos de habitación y agregar los id_tipo_habitacion a la lista
        for (TipoHabitacion tipoHabitacion : tiposHabitaciones) {
            tiposHabitacionesDisponibles.add(tipoHabitacion.getIdTipoHabitacion());
        }

        return tiposHabitacionesDisponibles;
    }

    @Override
    public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
        if(pageIndex==0)
        {
            Graphics2D graphics2d  = (Graphics2D) graphics;
            graphics2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            printAll(graphics2d);
            return PAGE_EXISTS;
        }
        else
        {
            return NO_SUCH_PAGE;
        }
    }

    public void limpiarDisponibles(){
        DefaultTableModel modelo = (DefaultTableModel) tblDisponibles.getModel();
        modelo.setRowCount(0);
        
        txtIDHabitacion.setText("");       
        fmtFechaIngreso.setText("");
        fmtFechaSalida.setText("");
    }
    /**
     * System.out.print(localDateIni +" " + localDateFin);
     *
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MultiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MultiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MultiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MultiVentana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MultiVentana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTabbedPane JTabbedPaneAdministrar;
    private javax.swing.JButton btnAgregarPersonal;
    private javax.swing.JButton btnBuscarClienteModificar;
    private javax.swing.JButton btnBuscarHabitaciones;
    private javax.swing.JButton btnBuscarPersonal;
    private javax.swing.JButton btnConfirmarPago;
    private javax.swing.JButton btnCrearTipoHabitacion;
    private javax.swing.JButton btnCrear_creHab;
    private javax.swing.JButton btnEditarClientesModificar;
    private javax.swing.JButton btnEditarPersonal;
    private javax.swing.JButton btnEliminarCliente;
    private javax.swing.JButton btnGuardarCliente;
    private javax.swing.JButton btnGuardarHabitacion;
    private javax.swing.JButton btnGuardarTipoHabitacion;
    private javax.swing.JButton btnLimpiarModifClientes;
    private javax.swing.JButton btnLimpiarTipoHabitacion1;
    private javax.swing.JButton btnListarHabitacion;
    private javax.swing.JButton btnListarTipoHabitacion;
    private javax.swing.JButton btnModificarPersonal;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReservar;
    private com.toedter.calendar.JCalendar calFechaFin;
    private com.toedter.calendar.JCalendar calFechaIni;
    private javax.swing.JComboBox<String> cbxCargoPersonalAgregar;
    private javax.swing.JComboBox<String> cbxSucursal;
    private javax.swing.JComboBox<String> cbxSucursalPersonalAgregar;
    private javax.swing.JComboBox<String> cbxSucursal_creHab;
    private javax.swing.JComboBox<String> cbxTipoHabitacion;
    private javax.swing.JComboBox<String> cbxTipo_creHab;
    private javax.swing.JFormattedTextField fmtFechaIngreso;
    private javax.swing.JFormattedTextField fmtFechaSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JLabel lblApeMod;
    private javax.swing.JLabel lblApePersonal;
    private javax.swing.JLabel lblCargoPersonal;
    private javax.swing.JLabel lblClienteNoRegistrado;
    private javax.swing.JLabel lblDNIMod;
    private javax.swing.JLabel lblDNIPersonal;
    private javax.swing.JLabel lblFechaEntrada;
    private javax.swing.JLabel lblFechaSalida;
    private javax.swing.JLabel lblFonoMod;
    private javax.swing.JLabel lblFonoPersonal;
    private javax.swing.JLabel lblFonoPersonal1;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblNombreMod;
    private javax.swing.JLabel lblNombreTipoHabitacion;
    private javax.swing.JLabel lblPersonalNoRegistrado;
    private javax.swing.JLabel lblPrecioTipoHabitacion;
    private javax.swing.JLabel lblPrecioTipoHabitacion1;
    private javax.swing.JLabel lblPrecioTipoHabitacion2;
    private javax.swing.JLabel lblSelectSucursal;
    private javax.swing.JLabel lblSelectTipoHabitacion;
    private javax.swing.JLabel lblSucursalPersonal;
    private javax.swing.JPanel panelBusquedaHabitacion;
    private javax.swing.JPanel pnlAgregarPersonal;
    private javax.swing.JPanel pnlBoleta;
    private javax.swing.JPanel pnlBuscarCliente;
    private javax.swing.JPanel pnlEditarPersonal;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlPersonalNoRegistrado;
    private javax.swing.JPanel pnlRegistrarCliente;
    private javax.swing.JPanel pnlReservarHabitacion;
    private javax.swing.JSpinner spnrPiso_creHab;
    private javax.swing.JSpinner spnrPuerta_creHab;
    private javax.swing.JTable tblDatosCliente;
    private javax.swing.JTable tblDetalleReserva;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTable tblHabitacionesCreadas;
    private javax.swing.JTable tblTipoHabitacionesCreadas;
    private javax.swing.JTextField txtApeClienteModificar;
    private javax.swing.JTextField txtApePersonalAgregar;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtCapHabiAdmin;
    private javax.swing.JTextField txtCelularCliente;
    private javax.swing.JPasswordField txtContraseñaPersonal;
    private javax.swing.JTextField txtCuponSoles;
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtDNIClienteMod;
    private javax.swing.JTextField txtDNIPersonalAgregar;
    private javax.swing.JTextField txtDescTipoHabitacion;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtFonoClienteModificar;
    private javax.swing.JTextField txtFonoPersonalAgregar;
    private javax.swing.JTextField txtIDHabitacion;
    private javax.swing.JTextField txtIDReserva;
    private javax.swing.JTextField txtNameClienteModificar;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextField txtNombrePersonalAgregar;
    private javax.swing.JTextField txtPrecioTipoHabitacionAdmin;
    private javax.swing.JTextArea txtRes;
    private javax.swing.JTextField txtTipoHabitacion;
    private javax.swing.JTextField txtTotalSoles;
    private javax.swing.JPanel ventanaReservar;
    // End of variables declaration//GEN-END:variables

    
}
