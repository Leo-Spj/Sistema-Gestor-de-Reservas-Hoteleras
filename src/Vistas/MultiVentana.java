/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vistas;

import Configuracion.DatabaseConfig;

import Modelo.Cliente;
import Modelo.UsuarioLogueado;

import java.awt.event.ItemEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import java.time.LocalDate;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JFrame;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Leo
 */
public final class MultiVentana extends javax.swing.JFrame {

    /**
     * Creates new form MultiVentana
     */
    private UsuarioLogueado usuarioLogueado;
    private ArrayList<Cliente> clientes;

    public MultiVentana(UsuarioLogueado usuarioLogueado) {

        this.usuarioLogueado = usuarioLogueado;

        initComponents();
        this.setLocationRelativeTo(this);
        cerrarSesion();

        pnlRegistrarCliente.setVisible(false);

        System.out.println("Codigo empleado: " + usuarioLogueado.getId_empleado());

        // Llenando los ComboBox
        llenarComboBoxSucursales();
        cargarTiposHabitacion();
        
        
        cargarClientes(); 
    }

    public MultiVentana() {
        initComponents();
        pnlRegistrarCliente.setVisible(false);
        cargarClientes(); //Probando la carga de cliente en un arrayList
    }
    
    public void validarCaracteres(java.awt.event.KeyEvent evento) {
        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 47 || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 8482) {
            evento.consume();
            String mensaje = "No se permiten caracteres normales, especiales ni espacios. SOLO NÚMEROS";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void cargarClientes(){
        clientes = new ArrayList<>();

        String query = "SELECT * FROM clientes;";

        try (Connection connection = DriverManager.getConnection(getConnectionString(),
            databaseConfig.getUsername(), databaseConfig.getPassword());
                
            PreparedStatement statement = connection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int dni = resultSet.getInt("dni_cliente");
                String nombre = resultSet.getString("nombre");
                String apellido = resultSet.getString("apellido");
                String celular = resultSet.getString("celular");

                Cliente cliente = new Cliente(dni, nombre, apellido, celular);
                clientes.add(cliente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        for (Cliente c : clientes) {
            System.out.println(c.getNombre());
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

    DatabaseConfig databaseConfig = new DatabaseConfig();

    private String getConnectionString() {
        String serverName = databaseConfig.getServer();
        String databaseName = databaseConfig.getDatabaseName();
        return String.format("jdbc:sqlserver://%s:1433;databaseName=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                serverName, databaseName);
    }

    public void llenarComboBoxSucursales() {
        // Consulta SQL para obtener los nombres de las sucursales
        // Antes de agregar los elementos desde la base de datos, asegúrate de que el combobox esté vacío
        cbxSucursal.removeAllItems();

        // Agregar el elemento "Seleccionar" como primer elemento del combobox
        cbxSucursal.addItem("Seleccionar");

        // Realizar la consulta a la base de datos y agregar los resultados al combobox
        String querySucursal = "SELECT nombre FROM sucursal";

        try ( Connection connection = DriverManager.getConnection(getConnectionString(),
                databaseConfig.getUsername(), databaseConfig.getPassword());  
                PreparedStatement statementSucursal = connection.prepareStatement(querySucursal);
                ResultSet resultSetSucursal = statementSucursal.executeQuery()) {

            while (resultSetSucursal.next()) {
                String nombreSucursal = resultSetSucursal.getString("nombre");
                cbxSucursal.addItem(nombreSucursal);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        cbxSucursal.setSelectedIndex(usuarioLogueado.getId_sucursal());

    }

    private void cargarTiposHabitacion() {
        // Antes de agregar los elementos desde la base de datos, asegúrate de que el combobox esté vacío
        cbxTipoHabitacion.removeAllItems();

        // Agregar el elemento "Seleccionar" como primer elemento del combobox
        cbxTipoHabitacion.addItem("");

        // Realizar la consulta a la base de datos y agregar los resultados al combobox
        String queryTipoHabitacion = "SELECT tipo FROM tipo_habitacion";
        try ( Connection connection = DriverManager.getConnection(getConnectionString(),
                databaseConfig.getUsername(), databaseConfig.getPassword());  PreparedStatement statementTipoHabitacion = connection.prepareStatement(queryTipoHabitacion);  ResultSet resultSetTipoHabitacion = statementTipoHabitacion.executeQuery()) {

            while (resultSetTipoHabitacion.next()) {
                String tipoHabitacion = resultSetTipoHabitacion.getString("tipo");
                cbxTipoHabitacion.addItem(tipoHabitacion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
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
        pnlFiltrar = new javax.swing.JPanel();
        lblSelectSucursal = new javax.swing.JLabel();
        cbxSucursal = new javax.swing.JComboBox<>();
        lblSelectTipoHabitacion = new javax.swing.JLabel();
        cbxTipoHabitacion = new javax.swing.JComboBox<>();
        btnBuscarHabitaciones = new javax.swing.JButton();
        jPanel6 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblDisponibles = new javax.swing.JTable();
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
        jTextField8 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jButton3 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

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
                .addComponent(lblSelectSucursal)
                .addGap(18, 18, 18)
                .addComponent(cbxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(33, 33, 33)
                .addComponent(lblSelectTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbxTipoHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBuscarHabitaciones, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(48, 48, 48))
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
                .addGap(0, 9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblFechaEntrada)
                            .addComponent(calFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(calFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblFechaSalida))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(pnlFiltrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblFechaEntrada)
                    .addComponent(lblFechaSalida))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(calFechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, 305, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(calFechaIni, javax.swing.GroupLayout.PREFERRED_SIZE, 299, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(12, 12, 12)
                .addComponent(pnlFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel6.setBackground(new java.awt.Color(221, 214, 206));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jLabel1.setText("Habitaciones Disponibles:");

        tblDisponibles.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        tblDisponibles.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID habitación", "Tipo", "Capacidad", "Descripción", "Habitación", "S/."
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.Double.class
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
            tblDisponibles.getColumnModel().getColumn(0).setMinWidth(85);
            tblDisponibles.getColumnModel().getColumn(0).setMaxWidth(85);
            tblDisponibles.getColumnModel().getColumn(1).setMinWidth(100);
            tblDisponibles.getColumnModel().getColumn(1).setMaxWidth(130);
            tblDisponibles.getColumnModel().getColumn(2).setMaxWidth(100);
            tblDisponibles.getColumnModel().getColumn(3).setMinWidth(370);
            tblDisponibles.getColumnModel().getColumn(4).setMaxWidth(100);
            tblDisponibles.getColumnModel().getColumn(5).setMaxWidth(100);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jScrollPane1)
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout panelBusquedaHabitacionLayout = new javax.swing.GroupLayout(panelBusquedaHabitacion);
        panelBusquedaHabitacion.setLayout(panelBusquedaHabitacionLayout);
        panelBusquedaHabitacionLayout.setHorizontalGroup(
            panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaHabitacionLayout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 931, Short.MAX_VALUE)
                    .addComponent(jPanel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 13, Short.MAX_VALUE))
        );
        panelBusquedaHabitacionLayout.setVerticalGroup(
            panelBusquedaHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBusquedaHabitacionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(56, Short.MAX_VALUE))
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
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        pnlRegistrarClienteLayout.setVerticalGroup(
            pnlRegistrarClienteLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlRegistrarClienteLayout.createSequentialGroup()
                .addComponent(lblClienteNoRegistrado)
                .addGap(26, 26, 26)
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

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel8.setText("Fecha de Entrada");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel9.setText("Fecha de Salida");

        btnReservar.setBackground(new java.awt.Color(171, 76, 89));
        btnReservar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnReservar.setForeground(new java.awt.Color(27, 35, 42));
        btnReservar.setText("Reservar");

        tblDetalleReserva.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(tblDetalleReserva);

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

        javax.swing.GroupLayout pnlReservarHabitacionLayout = new javax.swing.GroupLayout(pnlReservarHabitacion);
        pnlReservarHabitacion.setLayout(pnlReservarHabitacionLayout);
        pnlReservarHabitacionLayout.setHorizontalGroup(
            pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addComponent(btnReservar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(184, 184, 184))
                    .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel7)
                            .addComponent(txtIDHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(fmtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                                .addGap(117, 117, 117)
                                .addComponent(jLabel9))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlReservarHabitacionLayout.createSequentialGroup()
                                .addGap(109, 109, 109)
                                .addComponent(fmtFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(176, 176, 176))))
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 919, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addComponent(jSeparator1)
        );
        pnlReservarHabitacionLayout.setVerticalGroup(
            pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlReservarHabitacionLayout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlReservarHabitacionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtIDHabitacion)
                    .addComponent(fmtFechaSalida)
                    .addComponent(fmtFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addComponent(btnReservar)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnlBoleta.setBackground(new java.awt.Color(221, 214, 206));

        jLabel10.setBackground(new java.awt.Color(27, 35, 42));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Pago de Reserva");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel11.setText("ID de Reserva:");

        jTextField8.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel12.setText("Fecha de pago:");

        jTextField9.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel13.setText("Cupon en soles:");

        jTextField10.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setRows(5);
        jScrollPane4.setViewportView(jTextArea1);

        jButton3.setBackground(new java.awt.Color(171, 76, 89));
        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton3.setForeground(new java.awt.Color(27, 35, 42));
        jButton3.setText("Confirmar Pago");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel14.setText("S/.");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel15.setText("Monto final:");

        javax.swing.GroupLayout pnlBoletaLayout = new javax.swing.GroupLayout(pnlBoleta);
        pnlBoleta.setLayout(pnlBoletaLayout);
        pnlBoletaLayout.setHorizontalGroup(
            pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBoletaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 646, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(pnlBoletaLayout.createSequentialGroup()
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel12)
                            .addComponent(jLabel13)
                            .addComponent(jLabel11))
                        .addGap(4, 4, 4)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9)
                            .addComponent(jTextField10)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(353, 353, 353)
                        .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(147, 147, 147))
            .addGroup(pnlBoletaLayout.createSequentialGroup()
                .addGap(394, 394, 394)
                .addComponent(jLabel10)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlBoletaLayout.createSequentialGroup()
                .addComponent(jSeparator2)
                .addContainerGap())
        );
        pnlBoletaLayout.setVerticalGroup(
            pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlBoletaLayout.createSequentialGroup()
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(pnlBoletaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23))
        );

        javax.swing.GroupLayout ventanaReservarLayout = new javax.swing.GroupLayout(ventanaReservar);
        ventanaReservar.setLayout(ventanaReservarLayout);
        ventanaReservarLayout.setHorizontalGroup(
            ventanaReservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlBuscarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlRegistrarCliente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlReservarHabitacion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(pnlBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ventanaReservarLayout.setVerticalGroup(
            ventanaReservarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ventanaReservarLayout.createSequentialGroup()
                .addComponent(pnlBuscarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlRegistrarCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pnlReservarHabitacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(pnlBoleta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jTabbedPane1.addTab("Reservar", ventanaReservar);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 961, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 731, Short.MAX_VALUE)
        );

        jTabbedPane1.addTab("Settings", jPanel3);

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
                int indiceSucursal = cbxSucursal.getSelectedIndex();
                System.out.println("Sucursal: " + indiceSucursal + "; Inicio: " + localDateIni + "; Final: " + localDateFin);
                
                txtIDHabitacion.setText("");
                
                fmtFechaIngreso.setText("");
                fmtFechaIngreso.setText(localDateIni.toString());
                
                fmtFechaSalida.setText("");
                fmtFechaSalida.setText(localDateFin.toString());
                
                String queryTipoHabitacion = "EXEC sp_buscar_habitaciones_disponibles ?, ?, ?";

                try ( Connection connection = DriverManager.getConnection(getConnectionString(),
                        databaseConfig.getUsername(), databaseConfig.getPassword() );   
                    PreparedStatement statementTipoHabitacion = connection.prepareStatement(queryTipoHabitacion)) {

                    // Borrar resultados anteriores de la tabla
                    DefaultTableModel model = (DefaultTableModel) tblDisponibles.getModel();
                    model.setRowCount(0);

                    // Establecer los valores de los parámetros
                    statementTipoHabitacion.setString(1, indiceSucursal + "");
                    statementTipoHabitacion.setString(2, localDateIni + "");
                    statementTipoHabitacion.setString(3, localDateFin + "");

                    try ( ResultSet resultSetTipoHabitacion = statementTipoHabitacion.executeQuery()) {
                        while (resultSetTipoHabitacion.next()) {
                            Object[] row = new Object[6];
                            row[0] = resultSetTipoHabitacion.getInt("id_habitacion");
                            row[1] = resultSetTipoHabitacion.getString("tipo");
                            row[2] = resultSetTipoHabitacion.getInt("capacidad");
                            row[3] = resultSetTipoHabitacion.getString("descripcion");
                            row[4] = resultSetTipoHabitacion.getString("habitacion");
                            row[5] = resultSetTipoHabitacion.getDouble("Precio por noche");
                            model.addRow(row);
                        }
                    }
                } catch (SQLException e) {
                    // Manejo de excepciones
                    e.printStackTrace();
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

    public boolean buscarClientePorDNI(int dni) {
        Cliente clienteEncontrado = null;
        
        // Buscar el cliente por DNI en el ArrayList
        for (Cliente c : clientes) {
            if (c.getDNI() == dni) {
                clienteEncontrado = c;
                break; // Se encontró el cliente, salir del bucle
            }
        }

        // Imprimir los campos en la tabla tblDatosCliente
        DefaultTableModel modelo = (DefaultTableModel) tblDatosCliente.getModel();
        modelo.setRowCount(0); // Limpiar filas existentes en la tabla

        if (clienteEncontrado != null) {
            // Obtener los campos del cliente encontrado
            String nombre = clienteEncontrado.getNombre();
            String apellido = clienteEncontrado.getApellido();
            String celular = clienteEncontrado.getCelular();

            // Agregar una nueva fila con los campos en la tabla
            modelo.addRow(new Object[]{nombre, apellido, celular});
            pnlRegistrarCliente.setVisible(false);
            return true;
        } else {
            System.out.println("Cliente no encontrado.");
            pnlRegistrarCliente.setVisible(true);
            return false;
        }
        
        
    }


    private void txtDNIClienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDNIClienteKeyPressed
        
        validarCaracteres(evt);
        DefaultTableModel modelo = (DefaultTableModel) tblDatosCliente.getModel();
        
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            String dni = txtDNICliente.getText();

            if (dni.matches("\\d{8}")) {
                int dniCliente = Integer.parseInt(dni);
                boolean clienteEncontrado = buscarClientePorDNI(dniCliente);

                if (!clienteEncontrado) {
                    pnlRegistrarCliente.setVisible(true);
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
        // TODO add your handling code here:
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
    private javax.swing.JButton btnRegistrar;
    private javax.swing.JButton btnReservar;
    private com.toedter.calendar.JCalendar calFechaFin;
    private com.toedter.calendar.JCalendar calFechaIni;
    private javax.swing.JComboBox<String> cbxSucursal;
    private javax.swing.JComboBox<String> cbxTipoHabitacion;
    private javax.swing.JFormattedTextField fmtFechaIngreso;
    private javax.swing.JFormattedTextField fmtFechaSalida;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextField jTextField10;
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
    private javax.swing.JTextField txtDNICliente;
    private javax.swing.JTextField txtIDHabitacion;
    private javax.swing.JTextField txtNombreCliente;
    private javax.swing.JPanel ventanaReservar;
    // End of variables declaration//GEN-END:variables
}
