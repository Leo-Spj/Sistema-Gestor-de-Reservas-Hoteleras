package Vistas;

import Configuracion.DatabaseConfig;
import Modelo.UsuarioLogueado;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Login extends javax.swing.JFrame {

    DatabaseConfig databaseConfig = new DatabaseConfig();

    public Login() {
        initComponents();
        this.setLocationRelativeTo(this);
        this.ojoOcultar.setVisible(true);
        btnIngresar.setEnabled(false);
        rsCargaLogin.setVisible(false);
    }

    public void validarCaracteres(java.awt.event.KeyEvent evento) {
        if (evento.getKeyChar() >= 32 && evento.getKeyChar() <= 47 || evento.getKeyChar() >= 58 && evento.getKeyChar() <= 8482) {
            evento.consume();
            String mensaje = "No se permiten caracteres normales, especiales ni espacios. SOLO NÚMEROS";
            JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void habilitarBoton() {
        if (txtUsuario.getText().isEmpty() || pwdContraseña.getPassword().length == 0) {
            btnIngresar.setEnabled(false);
        } else {
            btnIngresar.setEnabled(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        logoApp = new javax.swing.JLabel();
        lblNombreEmpresa = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txtUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        pwdContraseña = new javax.swing.JPasswordField();
        rsCargaLogin = new rojerusan.componentes.RSProgressMaterial();
        ojoVer = new javax.swing.JLabel();
        ojoOcultar = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setBackground(new java.awt.Color(221, 214, 206));
        pnlLogin.setForeground(new java.awt.Color(221, 214, 206));

        logoApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/Portada_Hotel.png"))); // NOI18N

        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblNombreEmpresa.setForeground(new java.awt.Color(45, 42, 37));
        lblNombreEmpresa.setText("TravelEasy");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUsuario.setText("Usuario");

        txtUsuario.setBackground(new java.awt.Color(250, 248, 235));
        txtUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txtUsuario.setForeground(new java.awt.Color(45, 42, 37));
        txtUsuario.setSelectionColor(new java.awt.Color(171, 76, 89));
        txtUsuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtUsuarioKeyReleased(evt);
            }
        });

        lblContraseña.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblContraseña.setText("Contraseña");

        btnIngresar.setBackground(new java.awt.Color(171, 76, 89));
        btnIngresar.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        btnIngresar.setForeground(new java.awt.Color(45, 42, 37));
        btnIngresar.setText("Ingresar");
        btnIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIngresarActionPerformed(evt);
            }
        });

        pwdContraseña.setBackground(new java.awt.Color(250, 248, 235));
        pwdContraseña.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pwdContraseña.setForeground(new java.awt.Color(45, 42, 37));
        pwdContraseña.setSelectionColor(new java.awt.Color(171, 76, 89));
        pwdContraseña.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                pwdContraseñaKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pwdContraseñaKeyReleased(evt);
            }
        });

        rsCargaLogin.setAnchoProgress(5);

        ojoVer.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/ver_32px.png"))); // NOI18N
        ojoVer.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ojoVerMouseClicked(evt);
            }
        });

        ojoOcultar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/ocultar_32px.png"))); // NOI18N
        ojoOcultar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ojoOcultarMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                .addGap(0, 59, Short.MAX_VALUE)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblContraseña, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rsCargaLogin, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(txtUsuario))
                            .addGroup(pnlLoginLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pwdContraseña)
                                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 186, Short.MAX_VALUE)))))
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(logoApp, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblNombreEmpresa)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ojoOcultar)
                    .addComponent(ojoVer))
                .addGap(77, 77, 77))
        );
        pnlLoginLayout.setVerticalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addComponent(logoApp)
                .addGap(18, 18, 18)
                .addComponent(lblNombreEmpresa)
                .addGap(58, 58, 58)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblUsuario))
                .addGap(45, 45, 45)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(pwdContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lblContraseña))
                    .addComponent(ojoOcultar)
                    .addComponent(ojoVer))
                .addGap(64, 64, 64)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(rsCargaLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
                .addContainerGap(68, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private String getConnectionString() {
        String serverName = databaseConfig.getServer();
        String databaseName = databaseConfig.getDatabaseName();
        return String.format("jdbc:sqlserver://%s:1433;databaseName=%s;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;",
                serverName, databaseName);
    }
    private void btnIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIngresarActionPerformed

        // hilos
        Thread hilo1 = new Thread(new Runnable() {
            public void run() {

                rsCargaLogin.setVisible(true);

            }
        });

        Thread hilo2 = new Thread(new Runnable() {
            public void run() {
                // Código del segundo hilo
                String usuario = txtUsuario.getText();
                String contraseña = new String(pwdContraseña.getPassword());

                String query = "SELECT * FROM aptos_login()";

                try ( Connection connection = DriverManager.getConnection(getConnectionString(),
                        databaseConfig.getUsername(), databaseConfig.getPassword());  PreparedStatement statement = connection.prepareStatement(query);  ResultSet resultSet = statement.executeQuery()) {

                    boolean accesoConcedido = false;

                    while (resultSet.next()) {
                        String dniEmpleado = resultSet.getString("dni_empleado");
                        String pwdEmpleado = resultSet.getString("contrasena");
                        if (usuario.equals(dniEmpleado) && contraseña.equals(pwdEmpleado)) {
                            accesoConcedido = true;
                            break;
                        }
                    }

                    if (accesoConcedido) {

                        // Obtener información del usuario logueado
                        String queryEmpleado = "SELECT * FROM empleados WHERE dni_empleado = ?";
                        try ( Connection connectionLogeo = DriverManager.getConnection(getConnectionString(),
                                databaseConfig.getUsername(), databaseConfig.getPassword());  PreparedStatement statementEmpleado = connectionLogeo.prepareStatement(queryEmpleado)) {

                            statementEmpleado.setString(1, usuario); // Parámetro para la consulta
                            ResultSet resultSetEmpleado = statementEmpleado.executeQuery();

                            UsuarioLogueado usuarioLogueado = new UsuarioLogueado();
                            if (resultSetEmpleado.next()) {

                                usuarioLogueado.setId_empleado(resultSetEmpleado.getInt("id_empleado"));
                                usuarioLogueado.setId_sucursal(resultSetEmpleado.getInt("id_sucursal"));
                                usuarioLogueado.setId_cargo(resultSetEmpleado.getInt("id_cargo"));
                                usuarioLogueado.setDNI(resultSetEmpleado.getInt("dni_empleado"));
                                usuarioLogueado.setNombre(resultSetEmpleado.getString("nombre"));
                                usuarioLogueado.setApellido(resultSetEmpleado.getString("apellido"));
                                usuarioLogueado.setCelular(resultSetEmpleado.getString("celular"));

                            }
                            resultSetEmpleado.close();

                            System.out.println("Acceso Aprobado");
                            System.out.println("Trabajador: " + usuarioLogueado.getNombre() + " " + usuarioLogueado.getApellido());

                            MultiVentana mv = new MultiVentana(usuarioLogueado); // Pasar la instancia
                            mv.setVisible(true);

                            Login.this.dispose();

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        rsCargaLogin.setVisible(false);
                        String mensaje = "Usuario o Contraseña inválida";
                        JOptionPane.showMessageDialog(null, mensaje, "Error Login", JOptionPane.ERROR_MESSAGE);
                    }

                    rsCargaLogin.setVisible(false);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        // Iniciar los hilos
        hilo1.start();
        hilo2.start();

    }//GEN-LAST:event_btnIngresarActionPerformed

    private void pwdContraseñaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdContraseñaKeyPressed
        if ((evt.getKeyCode() == KeyEvent.VK_ENTER) && (pwdContraseña.getPassword().length > 0 && !txtUsuario.getText().isEmpty())) {
            btnIngresarActionPerformed(null);
        }
        validarCaracteres(evt);
    }//GEN-LAST:event_pwdContraseñaKeyPressed

    private void txtUsuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyPressed
        validarCaracteres(evt);
    }//GEN-LAST:event_txtUsuarioKeyPressed

    private void pwdContraseñaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdContraseñaKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_pwdContraseñaKeyReleased

    private void txtUsuarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtUsuarioKeyReleased
        habilitarBoton();
    }//GEN-LAST:event_txtUsuarioKeyReleased

    private void ojoVerMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ojoVerMouseClicked
        this.ojoOcultar.setVisible(true);
        this.ojoVer.setVisible(false);
        pwdContraseña.setEchoChar('*');
    }//GEN-LAST:event_ojoVerMouseClicked

    private void ojoOcultarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ojoOcultarMouseClicked
        this.ojoOcultar.setVisible(false);
        this.ojoVer.setVisible(true);
        pwdContraseña.setEchoChar((char) 0);
    }//GEN-LAST:event_ojoOcultarMouseClicked

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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnIngresar;
    private javax.swing.JLabel lblContraseña;
    private javax.swing.JLabel lblNombreEmpresa;
    private javax.swing.JLabel lblUsuario;
    private javax.swing.JLabel logoApp;
    private javax.swing.JLabel ojoOcultar;
    private javax.swing.JLabel ojoVer;
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField pwdContraseña;
    private rojerusan.componentes.RSProgressMaterial rsCargaLogin;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables
}
