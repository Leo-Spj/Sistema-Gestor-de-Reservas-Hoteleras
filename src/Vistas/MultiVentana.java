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
    ArrayList<TipoHabitacion> tiposHabitacion;

    //no usar este MultiVentana sin parametros
    public MultiVentana() {
        initComponents();
        pnlRegistrarCliente.setVisible(false);
        
    }
    

    public MultiVentana(UsuarioLogueado usuarioLogueado) {

        this.usuarioLogueado = usuarioLogueado;

        initComponents();
        this.setLocationRelativeTo(this);
        cerrarSesion();
        iniciarFechaHoy();

        pnlRegistrarCliente.setVisible(false);

        System.out.println(usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido() + " - " + usuarioLogueado.getId_empleado());

        cargarEmpresaHotelera();

        // Llenando los ComboBox
        llenarComboBoxSucursales();
        cargarTiposHabitacion();
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
        System.out.println("Sucursal: " + eh.getRazonSocial());
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
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel2 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel28 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jTextField6 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jComboBox3 = new javax.swing.JComboBox<>();
        jComboBox4 = new javax.swing.JComboBox<>();
        jScrollPane6 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        jLabel32 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jPanel8 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jTextField3 = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jTextField4 = new javax.swing.JTextField();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel23 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jTextField5 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        jTextField7 = new javax.swing.JTextField();

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
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
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
        btnBuscarHabitaciones.setForeground(new java.awt.Color(27, 35, 42));
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
                .addGap(38, 38, 38)
                .addGroup(panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(192, Short.MAX_VALUE))
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
                .addGap(49, 49, 49)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(txtDNICliente, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 568, javax.swing.GroupLayout.PREFERRED_SIZE)
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
        btnRegistrar.setForeground(new java.awt.Color(27, 35, 42));
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
                .addGroup(pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                        .addGap(53, 53, 53)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(btnRegistrar))
                    .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                        .addGap(149, 149, 149)
                        .addComponent(lblClienteNoRegistrado)))
                .addContainerGap(285, Short.MAX_VALUE))
        );
        pnlRegistrarClienteLayout.setVerticalGroup(
            pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                .addComponent(lblClienteNoRegistrado)
                .addGap(44, 44, 44)
                .addGroup(pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtNombreCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtApellidoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCelularCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnRegistrar))
                .addGap(0, 0, Short.MAX_VALUE))
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
        btnReservar.setForeground(new java.awt.Color(27, 35, 42));
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
            .addComponent(jSeparator1)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGap(190, 190, 190)
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btnReservar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 635, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(fmtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 974, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(171, Short.MAX_VALUE))
        );
        pnlReservarHabitacionLayout.setVerticalGroup(
            pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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
                            .addComponent(fmtFechaIngreso))))
                .addGap(18, 18, 18)
                .addComponent(btnReservar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pnlBoleta.setBackground(new java.awt.Color(221, 214, 206));

        jLabel10.setBackground(new java.awt.Color(27, 35, 42));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Pago de Reserva");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("ID de Reserva:");

        txtIDReserva.setEditable(false);
        txtIDReserva.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

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
        btnConfirmarPago.setForeground(new java.awt.Color(27, 35, 42));
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
                .addComponent(jSeparator2)
                .addContainerGap())
            .addGroup(pnlBoletaLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlBoletaLayout.createSequentialGroup()
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
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
                        .addComponent(btnConfirmarPago, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(106, 106, 106)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(140, 140, 140))
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

        jPanel9.setBackground(new java.awt.Color(0, 204, 204));

        jLabel28.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel28.setText("Agregar personal");

        jLabel27.setText("DNI");

        jLabel29.setText("Nombre:");

        jLabel30.setText("Apellido:");

        jLabel31.setText("Celular");

        jLabel33.setText("Sucursal");

        jLabel34.setText("Cargo");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane6.setViewportView(jTable2);

        jButton3.setText("Modificar");

        jButton4.setText("Eliminar");

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel34, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(45, 45, 45)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addComponent(jLabel27, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(20, 20, 20))
                                    .addComponent(jLabel29, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(21, 21, 21)
                                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel9Layout.createSequentialGroup()
                                        .addGap(18, 18, 18)
                                        .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGap(126, 126, 126)
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton4)
                        .addGap(109, 109, 109))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                        .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(15, 15, 15))))
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(262, 262, 262)
                .addComponent(jLabel28, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel28)
                .addGap(38, 38, 38)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel33)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(61, 61, 61)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(jComboBox4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(219, 219, 219))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel27)
                                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel29)
                                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(34, 34, 34)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel30)))
                            .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel31)
                            .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3)
                            .addComponent(jButton4))
                        .addGap(122, 122, 122))))
        );

        jPanel10.setBackground(new java.awt.Color(153, 153, 153));

        jLabel32.setFont(new java.awt.Font("Consolas", 1, 14)); // NOI18N
        jLabel32.setText("Crear cargo para el personal");

        jLabel35.setText("Nombre");

        jLabel36.setText("Descripcion");

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane7.setViewportView(jTable3);

        jButton5.setText("Modificar");

        jButton6.setText("Eliminar");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(98, 98, 98)
                        .addComponent(jLabel35, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel36, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(50, 50, 50)
                        .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 536, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(174, 174, 174)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 110, Short.MAX_VALUE)
                            .addComponent(jButton6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(250, 250, 250)
                        .addComponent(jLabel32)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel32)
                .addGap(45, 45, 45)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 47, Short.MAX_VALUE)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36))
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(169, 169, 169)
                .addComponent(jButton5)
                .addGap(61, 61, 61)
                .addComponent(jButton6)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(85, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Personal", jPanel2);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        jTabbedPane2.addTab("Cliente", jPanel5);

        jLabel3.setText("Crear Habitaciones");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel16.setText("Sucursal");

        jLabel17.setText("Tipo");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Piso");

        jLabel19.setText("Puerta");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 116, Short.MAX_VALUE)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(65, 65, 65)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addGap(32, 32, 32)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, 43, Short.MAX_VALUE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel3)
                .addGap(33, 33, 33)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(jLabel17)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(46, Short.MAX_VALUE))
        );

        jLabel20.setText("Crear Tipos de Habitacion");

        jLabel21.setText("Nombre: ");

        jLabel22.setText("Precio:  S/.");

        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);

        jLabel23.setText("Modificar Tipo de Habitacion");

        jButton1.setText("Crear");

        jLabel24.setText("Buscar: ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Tipo", "Precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane5.setViewportView(jTable1);

        jLabel25.setText("Tipo");

        jButton2.setText("Cambiar");

        jLabel26.setText("Precio");

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel20)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel24, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel26, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(31, 31, 31)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                            .addComponent(jTextField7)
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))))
                .addContainerGap(411, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel20)
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21))
                                .addGap(15, 15, 15)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel22)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton1))
                            .addComponent(jSeparator3))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel8Layout.createSequentialGroup()
                        .addComponent(jLabel23)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jLabel24)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel8Layout.createSequentialGroup()
                                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel25))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel26)
                                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addComponent(jButton2)
                                .addGap(15, 15, 15))))))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("Habitaciones", jPanel1);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2)
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
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 823, Short.MAX_VALUE)
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
            JOptionPane.showMessageDialog(this, "Debe buscar un cliente.", "Aviso", JOptionPane.WARNING_MESSAGE);
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
    private javax.swing.JButton btnBuscarHabitaciones;
    private javax.swing.JButton btnConfirmarPago;
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReservar;
    private com.toedter.calendar.JCalendar calFechaFin;
    private com.toedter.calendar.JCalendar calFechaIni;
    private javax.swing.JComboBox<String> cbxSucursal;
    private javax.swing.JComboBox<String> cbxTipoHabitacion;
    private javax.swing.JFormattedTextField fmtFechaIngreso;
    private javax.swing.JFormattedTextField fmtFechaSalida;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox3;
    private javax.swing.JComboBox<String> jComboBox4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    private javax.swing.JLabel lblClienteNoRegistrado;
    private javax.swing.JLabel lblFechaEntrada;
    private javax.swing.JLabel lblFechaSalida;
    private javax.swing.JLabel lblSelectSucursal;
    private javax.swing.JLabel lblSelectTipoHabitacion;
    private javax.swing.JPanel panelBusquedaHabitacion;
    private javax.swing.JPanel pnlBoleta;
    private javax.swing.JPanel pnlBuscarCliente;
    private javax.swing.JPanel pnlFiltrar;
    private javax.swing.JPanel pnlRegistrarCliente;
    private javax.swing.JPanel pnlReservarHabitacion;
    private javax.swing.JTable tblDatosCliente;
    private javax.swing.JTable tblDetalleReserva;
    private javax.swing.JTable tblDisponibles;
    private javax.swing.JTextField txtApellidoCliente;
    private javax.swing.JTextField txtCelularCliente;
    private javax.swing.JTextField txtCuponSoles;
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtFechaPago;
    private javax.swing.JTextField txtIDHabitacion;
    private javax.swing.JTextField txtIDReserva;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JTextArea txtRes;
    private javax.swing.JTextField txtTotalSoles;
    private javax.swing.JPanel ventanaReservar;
    // End of variables declaration//GEN-END:variables

    
}
