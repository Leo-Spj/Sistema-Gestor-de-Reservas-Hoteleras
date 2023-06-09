
package Vistas;
import Configuracion.DatabaseConfig;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends javax.swing.JFrame  {
    DatabaseConfig databaseConfig = new DatabaseConfig();

    public Login() {
        initComponents();
        
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnlLogin = new javax.swing.JPanel();
        logoApp = new javax.swing.JLabel();
        lblNombreEmpresa = new javax.swing.JLabel();
        lblUsuario = new javax.swing.JLabel();
        txfUsuario = new javax.swing.JTextField();
        lblContraseña = new javax.swing.JLabel();
        btnIngresar = new javax.swing.JButton();
        pwdContraseña = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        pnlLogin.setBackground(new java.awt.Color(221, 214, 206));
        pnlLogin.setForeground(new java.awt.Color(221, 214, 206));

        logoApp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Vistas/Imagenes/Portada_Hotel.png"))); // NOI18N

        lblNombreEmpresa.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblNombreEmpresa.setForeground(new java.awt.Color(45, 42, 37));
        lblNombreEmpresa.setText("TravelEasy");

        lblUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lblUsuario.setText("Usuario");

        txfUsuario.setBackground(new java.awt.Color(250, 248, 235));
        txfUsuario.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        txfUsuario.setForeground(new java.awt.Color(45, 42, 37));
        txfUsuario.setSelectionColor(new java.awt.Color(171, 76, 89));

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

        javax.swing.GroupLayout pnlLoginLayout = new javax.swing.GroupLayout(pnlLogin);
        pnlLogin.setLayout(pnlLoginLayout);
        pnlLoginLayout.setHorizontalGroup(
            pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnlLoginLayout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(logoApp, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(145, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnlLoginLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lblNombreEmpresa)
                    .addGroup(pnlLoginLayout.createSequentialGroup()
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblUsuario, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lblContraseña, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnIngresar, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(txfUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, 159, Short.MAX_VALUE)
                            .addComponent(pwdContraseña))))
                .addGap(135, 135, 135))
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
                    .addComponent(lblUsuario)
                    .addComponent(txfUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(pnlLoginLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lblContraseña)
                    .addComponent(pwdContraseña, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(49, 49, 49)
                .addComponent(btnIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(pnlLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
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
        String usuario = txfUsuario.getText();
        String contraseña = new String(pwdContraseña.getPassword());

        String query = "SELECT * FROM aptos_login()";
        try (Connection connection = DriverManager.getConnection(getConnectionString(),
                databaseConfig.getUsername(), databaseConfig.getPassword());
             PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

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
                System.out.println("Acceso Aprobado");
                MultiVentana mv = new MultiVentana();
                mv.setVisible(true);
                this.setVisible(false);
            } else {
                System.out.println("Denegado");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_btnIngresarActionPerformed

    
    
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
    private javax.swing.JPanel pnlLogin;
    private javax.swing.JPasswordField pwdContraseña;
    private javax.swing.JTextField txfUsuario;
    // End of variables declaration//GEN-END:variables
}
