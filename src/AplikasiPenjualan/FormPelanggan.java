
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AplikasiPenjualan;

import java.awt.HeadlessException;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class FormPelanggan extends javax.swing.JFrame {
      DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form FormBarang
     */
    public FormPelanggan() {
        initComponents();
        koneksi c = new koneksi();
        koneksi.getKoneksi();
        
        tabelPelanggan.setModel(model);
        model.addColumn("Username");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelamin");
        model.addColumn("Tempat Lahir");
        model.addColumn("Tanggal Lahir");
        model.addColumn("Agama");
        model.addColumn("Alamat");
        model.addColumn("Email");
        model.addColumn("No HP");
        loadData();
    }

     public void loadData() {
       int row = tabelPelanggan.getRowCount();
        for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        
        String sql = "SELECT * FROM `pelanggan` ";

        try{
            Connection connect = koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            ResultSet rslt = sttmnt.executeQuery(sql);

            while (rslt.next()) {
               
                String usernam = rslt.getString("Username");
                String nama = rslt.getString("Nama");
                String jk = rslt.getString("Jenis Kelamin");
                String tlahire = rslt.getString("Tempat Lahir");
                String tanggal = rslt.getString("Tanggal Lahir");
                String agama = rslt.getString("Agama");
                String alamat = rslt.getString("Alamat");
                String email = rslt.getString("Email");
                String notelp = rslt.getString("No HP");
                
                String[] data = {usernam,nama,jk,tlahire,tanggal,agama,alamat,email,notelp};
                
                model.addRow(data);
            }
             tabelPelanggan.setModel(model);
             
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
    }
     
     private void cari(){
        int row = tabelPelanggan.getRowCount();
        for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        
        String cari = cariTextField.getText();
        
        String sql = "SELECT * FROM `pelanggan` WHERE `Username`  LIKE '%"+cari+"%' OR `Nama` LIKE '%"+cari+"%' ";
                
       try{
           Connection connect = koneksi.getKoneksi();
           Statement sttmnt = connect.createStatement();
           ResultSet rslt = sttmnt.executeQuery(sql);
           
           while (rslt.next()){
                
                String usernam = rslt.getString("Username");
                String nama = rslt.getString("Nama");
                String jk = rslt.getString("Jenis Kelamin");
                String tlahir = rslt.getString("Tempat Lahir");
                String tgl = rslt.getString("Tanggal Lahir");
                String agama = rslt.getString("Agama");
                String alamat = rslt.getString("Alamat");
                String email = rslt.getString("Email");
                String notelp = rslt.getString("No HP");
                
                String[] data = {usernam,nama,tlahir,tgl,agama,alamat,notelp};
                
                model.addRow(data);
            }
                
                tabelPelanggan.setModel(model);
           
        
    }catch(SQLException e){
           System.out.println(e);
    }
    }
     
      private void clear(){
            
            usernameTextField.setText(null);
            namaTextField.setText(null);
            tlahirTextField.setText(null);
            jDateChooser.setDate(null);
            buttonGroup1.clearSelection();
            alamatTextField.setText(null);
            emailTextField.setText(null);
            nohpTextField.setText(null);
            ComboBox.setSelectedItem(null);
           
        
    }
      
      public void FilterHuruf(KeyEvent a){
       if(Character.isDigit(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukkan Huruf Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
       }
   }
    public void FilterAngka(KeyEvent a){
       if(Character.isAlphabetic(a.getKeyChar())){
           a.consume();
           JOptionPane.showMessageDialog(null, "Masukkan Angka Saja!", "Peringatan", JOptionPane.WARNING_MESSAGE);
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

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        emailTextField = new javax.swing.JTextField();
        namaTextField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tlahirTextField = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        usernameTextField = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelPelanggan = new javax.swing.JTable();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        alamatTextField = new javax.swing.JTextField();
        lakiRadioButton = new javax.swing.JRadioButton();
        perempuanRadioButton = new javax.swing.JRadioButton();
        ComboBox = new javax.swing.JComboBox<>();
        hapusButton = new javax.swing.JButton();
        simpanButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        nohpTextField = new javax.swing.JTextField();
        cetakButton = new javax.swing.JButton();
        cariTextField = new javax.swing.JTextField();
        cariButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/identity-card-icon.png"))); // NOI18N
        jLabel2.setText("Data Pelanggan");

        jLabel14.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-icon (1).png"))); // NOI18N
        jLabel14.setText("Menu");
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(22, 22, 22))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel14)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 204));
        jLabel7.setText("Tanggal Lahir");

        emailTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        emailTextField.setForeground(new java.awt.Color(51, 102, 204));
        emailTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTextFieldActionPerformed(evt);
            }
        });
        emailTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                emailTextFieldKeyReleased(evt);
            }
        });

        namaTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        namaTextField.setForeground(new java.awt.Color(51, 102, 204));
        namaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTextFieldActionPerformed(evt);
            }
        });
        namaTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                namaTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namaTextFieldKeyTyped(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(51, 102, 204));
        jLabel8.setText("Agama");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 204));
        jLabel3.setText("Username");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 102, 204));
        jLabel9.setText("Alamat");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 204));
        jLabel4.setText("Nama ");

        tlahirTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        tlahirTextField.setForeground(new java.awt.Color(51, 102, 204));
        tlahirTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tlahirTextFieldActionPerformed(evt);
            }
        });
        tlahirTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tlahirTextFieldKeyReleased(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 204));
        jLabel5.setText("Jenis Kelamin");

        usernameTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        usernameTextField.setForeground(new java.awt.Color(51, 102, 204));
        usernameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usernameTextFieldActionPerformed(evt);
            }
        });
        usernameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                usernameTextFieldKeyTyped(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(51, 102, 204));
        jLabel6.setText("Tempat Lahir");

        tabelPelanggan.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelPelanggan.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelPelangganMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelPelanggan);

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(51, 102, 204));
        jLabel10.setText("Email");

        jLabel11.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(51, 102, 204));
        jLabel11.setText("No HP");

        alamatTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        alamatTextField.setForeground(new java.awt.Color(51, 102, 204));
        alamatTextField.setHorizontalAlignment(javax.swing.JTextField.LEFT);
        alamatTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                alamatTextFieldActionPerformed(evt);
            }
        });
        alamatTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                alamatTextFieldKeyReleased(evt);
            }
        });

        buttonGroup1.add(lakiRadioButton);
        lakiRadioButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lakiRadioButton.setForeground(new java.awt.Color(51, 102, 204));
        lakiRadioButton.setText("Laki-Laki");

        buttonGroup1.add(perempuanRadioButton);
        perempuanRadioButton.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        perempuanRadioButton.setForeground(new java.awt.Color(51, 102, 204));
        perempuanRadioButton.setText("Perempuan");

        ComboBox.setBackground(new java.awt.Color(51, 102, 204));
        ComboBox.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        ComboBox.setForeground(new java.awt.Color(255, 255, 255));
        ComboBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Islam", "Kristen", "Katolik", "Hindu", "Buddha", "Konghucu" }));

        hapusButton.setBackground(new java.awt.Color(51, 102, 204));
        hapusButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash-icon24.png"))); // NOI18N
        hapusButton.setText("HAPUS");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        simpanButton.setBackground(new java.awt.Color(51, 102, 204));
        simpanButton.setForeground(new java.awt.Color(255, 255, 255));
        simpanButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-round-plus-icon.png"))); // NOI18N
        simpanButton.setText("SIMPAN");
        simpanButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simpanButtonActionPerformed(evt);
            }
        });

        editButton.setBackground(new java.awt.Color(51, 102, 204));
        editButton.setForeground(new java.awt.Color(255, 255, 255));
        editButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/preferences-icon.png"))); // NOI18N
        editButton.setText("UBAH");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        nohpTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        nohpTextField.setForeground(new java.awt.Color(51, 102, 204));
        nohpTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nohpTextFieldActionPerformed(evt);
            }
        });
        nohpTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nohpTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                nohpTextFieldKeyTyped(evt);
            }
        });

        cetakButton.setBackground(new java.awt.Color(51, 102, 204));
        cetakButton.setForeground(new java.awt.Color(255, 255, 255));
        cetakButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/printer-icon.png"))); // NOI18N
        cetakButton.setText("CETAK");
        cetakButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cetakButtonActionPerformed(evt);
            }
        });

        cariTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        cariTextField.setForeground(new java.awt.Color(51, 102, 204));
        cariTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariTextFieldActionPerformed(evt);
            }
        });

        cariButton.setBackground(new java.awt.Color(51, 102, 204));
        cariButton.setForeground(new java.awt.Color(255, 255, 255));
        cariButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/search-icon.png"))); // NOI18N
        cariButton.setText("CARI");
        cariButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cariButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(61, 61, 61)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(22, 22, 22)
                                .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lakiRadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(perempuanRadioButton))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(tlahirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(simpanButton)
                                .addGap(118, 367, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(cariTextField)
                                        .addGap(18, 18, 18)
                                        .addComponent(cariButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(29, 29, 29)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(alamatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(cetakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(nohpTextField, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(148, 148, 148)
                                        .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariButton))
                .addGap(37, 37, 37)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(usernameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(perempuanRadioButton)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(lakiRadioButton)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(9, 9, 9)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(tlahirTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(12, 12, 12)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(alamatTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(58, 58, 58)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(emailTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(6, 6, 6)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(nohpTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(hapusButton)
                            .addComponent(simpanButton)
                            .addComponent(editButton))))
                .addGap(18, 18, 18)
                .addComponent(cetakButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void emailTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTextFieldActionPerformed

    private void namaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTextFieldActionPerformed

    private void tlahirTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tlahirTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tlahirTextFieldActionPerformed

    private void usernameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usernameTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldActionPerformed

    private void alamatTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_alamatTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_alamatTextFieldActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
        if (usernameTextField.getText().equals("") ||namaTextField.getText().equals("") || lakiRadioButton.getText().equals("")|| tlahirTextField.getText().equals("")|| ComboBox.getSelectedItem().equals("")|| alamatTextField.getText().equals("")|| emailTextField.getText().equals("")|| nohpTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Toko Saham", JOptionPane.INFORMATION_MESSAGE);
             }else{
        try {
            String sql ="delete from pelanggan where username='"+usernameTextField.getText()+"'";
            java.sql.Connection conn=(Connection)koneksi.getKoneksi();
            java.sql.PreparedStatement pst=conn.prepareStatement(sql);
            pst.execute();
            JOptionPane.showMessageDialog(this, "Berhasil di Hapus");
            
            } catch (Exception e) {
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
        loadData();
        clear();
        }
       
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void simpanButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simpanButtonActionPerformed
        // TODO add your handling code here:
            String usernam = usernameTextField.getText();
            String nama = namaTextField.getText();
            String tlahir = tlahirTextField.getText();
            String agama = (String) ComboBox.getSelectedItem();
            String alamat = alamatTextField.getText();
            String email = emailTextField.getText();
            String notelp = nohpTextField.getText();
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = date.format(jDateChooser.getDate());
            
            String jk ="";
             if(lakiRadioButton.isSelected()){
                jk = lakiRadioButton.getText();
            }else{
                jk = perempuanRadioButton.getText();
            }
             
                Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO `pelanggan` (`Username`, `Nama`, `Jenis Kelamin`,`Tempat Lahir`, `Tanggal Lahir`, `Agama`, `Alamat`, `Email`, `No HP`) "
                     + "VALUES ('"+usernam+"', '"+nama+"','"+jk+"', '"+tlahir+"', '"+tanggal+"','"+agama+"','"+alamat+"','"+email+"','"+notelp+"')";
               try{
            
            PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
            ps.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data Berhasil Disimpan");
            
               }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
            }finally{
            loadData();
            clear();
            
        }
    
         
               
    }//GEN-LAST:event_simpanButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
          int i = tabelPelanggan.getSelectedRow();
          
            String usernam = usernameTextField.getText();
            String nama = namaTextField.getText();
            String tlahir = tlahirTextField.getText();
            String agama = ComboBox.getSelectedItem().toString();
            String alamat = alamatTextField.getText();
            String email = emailTextField.getText();
            String notelp = nohpTextField.getText();
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = String.valueOf(date.format(jDateChooser.getDate()));
            
            String jk ="";
             if(lakiRadioButton.isSelected()){
                jk = lakiRadioButton.getText();
            }else{
                jk = perempuanRadioButton.getText();
            }
        
        
            Connection c = koneksi.getKoneksi();
        
            String sql = "UPDATE `pelanggan` SET `Nama` = '"+nama+"', `Jenis Kelamin` = '"+jk+"', `Tempat Lahir` = '"+tlahir+"', `Tanggal Lahir` = '"+tanggal+"', `Agama` = '"+agama+"', `Alamat` = '"+alamat+"', `Email` = '"+email+"', `No HP` = '"+notelp+"' "
                + "WHERE `pelanggan`.`Username` = '"+usernam+"';";
            
           try{
            PreparedStatement ps = (PreparedStatement) c.prepareStatement(sql);
            ps.executeUpdate(sql);
            JOptionPane.showMessageDialog(null , "Data Update");
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Gagal Update");
        }finally{
            loadData();
            clear();
        }
    
             
    }//GEN-LAST:event_editButtonActionPerformed

    private void nohpTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nohpTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_nohpTextFieldActionPerformed

    private void tabelPelangganMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelPelangganMouseClicked
        // TODO add your handling code here:
        int i = tabelPelanggan.getSelectedRow();
        
        String user =  model.getValueAt(i, 0).toString();
        usernameTextField.setText(user);

        String nama = model.getValueAt(i, 1).toString();
        namaTextField.setText(nama);

        String jk =  model.getValueAt(i, 2).toString();
        String l ="Laki-Laki";
        if(jk.equals(l)){
            lakiRadioButton.setSelected(true);
        }else{
            perempuanRadioButton.setSelected(true);
        }
        
        String tlahir =  model.getValueAt(i, 3).toString();
        tlahirTextField.setText(tlahir);
        
        String tanggal =  model.getValueAt(i, 4).toString();
        
        Date convert = null;
        try{
            convert = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);   
        }catch(Exception e){
            System.out.println(e);
        }
        jDateChooser.setDate(convert);
        
        String agama = model.getValueAt(i, 5).toString();
        ComboBox.setSelectedItem(agama);
        
        String alamat = model.getValueAt(i, 6).toString();
        alamatTextField.setText(alamat);

        String email =  model.getValueAt(i, 7).toString();
        emailTextField.setText(email);

        String nohp =  model.getValueAt(i, 8).toString();
        nohpTextField.setText(nohp);     
    }//GEN-LAST:event_tabelPelangganMouseClicked

    private void cariTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTextFieldActionPerformed

    private void cariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariButtonActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_cariButtonActionPerformed

    private void nohpTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nohpTextFieldKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_nohpTextFieldKeyTyped

    private void cetakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakButtonActionPerformed
        // TODO add your handling code here:
        try{
            String file = "/Report/DataPelanggan.jasper";
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);

        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);

        }
    }//GEN-LAST:event_cetakButtonActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
     MenuPenjualan au = new MenuPenjualan();
        au.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel14MouseClicked
         
    
    private void usernameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyReleased
        // TODO add your handling code here:
         String input = usernameTextField.getText();
        if(input.length()>16){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 16");
            
        }
    }//GEN-LAST:event_usernameTextFieldKeyReleased

    private void usernameTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usernameTextFieldKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_usernameTextFieldKeyTyped

    private void namaTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTextFieldKeyTyped
        // TODO add your handling code here:
        FilterHuruf(evt);
    }//GEN-LAST:event_namaTextFieldKeyTyped

    private void namaTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTextFieldKeyReleased
        // TODO add your handling code here:
        String input = namaTextField.getText();
        if(input.length()>50){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 50");
            
        }
    }//GEN-LAST:event_namaTextFieldKeyReleased

    private void tlahirTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tlahirTextFieldKeyReleased
        // TODO add your handling code here:
        String input = tlahirTextField.getText();
        if(input.length()>20){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 20");
            
        }
    }//GEN-LAST:event_tlahirTextFieldKeyReleased

    private void alamatTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_alamatTextFieldKeyReleased
        // TODO add your handling code here:
        String input = alamatTextField.getText();
        if(input.length()>500){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 500");
            
        }
    }//GEN-LAST:event_alamatTextFieldKeyReleased

    private void emailTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_emailTextFieldKeyReleased
        // TODO add your handling code here:
        String input = emailTextField.getText();
        if(input.length()>40){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 40");
            
        }
    }//GEN-LAST:event_emailTextFieldKeyReleased

    private void nohpTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nohpTextFieldKeyReleased
        // TODO add your handling code here:
        String input = nohpTextField.getText();
        if(input.length()>20){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 20");
            
        }
    }//GEN-LAST:event_nohpTextFieldKeyReleased

    /**
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
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPelanggan.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPelanggan().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboBox;
    private javax.swing.JTextField alamatTextField;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cariButton;
    private javax.swing.JTextField cariTextField;
    private javax.swing.JButton cetakButton;
    private javax.swing.JButton editButton;
    private javax.swing.JTextField emailTextField;
    private javax.swing.JButton hapusButton;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton lakiRadioButton;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JTextField nohpTextField;
    private javax.swing.JRadioButton perempuanRadioButton;
    private javax.swing.JButton simpanButton;
    private javax.swing.JTable tabelPelanggan;
    private javax.swing.JTextField tlahirTextField;
    private javax.swing.JTextField usernameTextField;
    // End of variables declaration//GEN-END:variables
}
