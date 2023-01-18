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
public class FormBarang extends javax.swing.JFrame {
   DefaultTableModel model = new DefaultTableModel();

    /**
     * Creates new form FormBarang
     */
    public FormBarang() {
        initComponents();
        tanggal();
        
        
        koneksi c = new koneksi();
        koneksi.getKoneksi();
        
        tableData.setModel(model);
        model.addColumn("Kode_Saham");
        model.addColumn("Nama_Saham");
        model.addColumn("Jumlah_Lot");
        model.addColumn("Harga/Lot");
        
        model.addColumn("Tanggal");
      
            
        loadData();
    }
    
    public final void loadData() {
        int row = tableData.getRowCount();
        for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        
        String sql = "SELECT * FROM `barang` ";

        try{
            Connection connect = koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            ResultSet rslt = sttmnt.executeQuery(sql);
            
            while (rslt.next()){
                
                String kode = rslt.getString("Kode_Saham");
                String nama = rslt.getString("Nama_Saham");
                String lot = rslt.getString("Jumlah_Lot");
                String harga = rslt.getString("Harga/Lot");
                
                String tgl = rslt.getString("Tanggal");
               
                String[] data = {kode,nama,lot,harga,tgl};
                
                model.addRow(data);
            }
             tableData.setModel(model);
             
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
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
    
    public void tanggal(){
        Date now = new Date();  
        jDateChooser.setDate(now);    
    }
    
     private void clear(){
       kodeTextField.setText(null);
       namaTextField.setText(null);
       lotTextField.setText(null);
       hargaTextField.setText(null);
       jDateChooser.setDate(null);
        
    }
     
     
    
     private void cari(){
        int row = tableData.getRowCount();
        for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        
        String cari = cariTextField.getText();
        
        String sql = "SELECT * FROM `barang` WHERE `Kode_Saham`  LIKE '%"+cari+"%' OR `Nama_Saham` LIKE '%"+cari+"%' ";
                
       try{
           Connection connect = koneksi.getKoneksi();
           Statement sttmnt = connect.createStatement();
           ResultSet rslt = sttmnt.executeQuery(sql);
           
           while (rslt.next()){
                    
                String kode = rslt.getString("Kode_Saham");
                String nama = rslt.getString("Nama_Saham");
                String lot = rslt.getString("Jumlah_Lot");
                String harga = rslt.getString("Harga/Lot");
                
                
                String tanggal = rslt.getString("Tanggal");
                
                String[] data = {kode,nama,harga,lot,tanggal};
                
                model.addRow(data);
            }
                
                tableData.setModel(model);
           
        
    }catch(SQLException e){
           System.out.println(e);
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        kodeTextField = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        namaTextField = new javax.swing.JTextField();
        hargaTextField = new javax.swing.JTextField();
        lotTextField = new javax.swing.JTextField();
        jDateChooser = new com.toedter.calendar.JDateChooser();
        addButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        hapusButton = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tableData = new javax.swing.JTable();
        cetakButton = new javax.swing.JButton();
        cariTextField = new javax.swing.JTextField();
        cariButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/database-icon (1).png"))); // NOI18N
        jLabel2.setText("Data Saham");

        jLabel15.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/home-icon (1).png"))); // NOI18N
        jLabel15.setText("Menu");
        jLabel15.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel15MouseClicked(evt);
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
                .addComponent(jLabel15)
                .addGap(14, 14, 14))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel2))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel15)))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 204));
        jLabel3.setText("Kode Saham");

        kodeTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kodeTextField.setForeground(new java.awt.Color(51, 102, 204));
        kodeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeTextFieldActionPerformed(evt);
            }
        });
        kodeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                kodeTextFieldKeyReleased(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 204));
        jLabel4.setText("Nama Saham");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 204));
        jLabel5.setText("Harga/Lot");

        jLabel7.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 102, 204));
        jLabel7.setText("Jumlah Lot");

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 102, 204));
        jLabel9.setText("Tanggal");

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

        hargaTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hargaTextField.setForeground(new java.awt.Color(51, 102, 204));
        hargaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTextFieldActionPerformed(evt);
            }
        });
        hargaTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                hargaTextFieldKeyTyped(evt);
            }
        });

        lotTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        lotTextField.setForeground(new java.awt.Color(51, 102, 204));
        lotTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lotTextFieldActionPerformed(evt);
            }
        });
        lotTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                lotTextFieldKeyTyped(evt);
            }
        });

        jDateChooser.setBackground(new java.awt.Color(51, 102, 204));
        jDateChooser.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 102, 204), 1, true));
        jDateChooser.setForeground(new java.awt.Color(51, 102, 204));
        jDateChooser.setEnabled(false);
        jDateChooser.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        addButton.setBackground(new java.awt.Color(51, 102, 204));
        addButton.setForeground(new java.awt.Color(255, 255, 255));
        addButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-round-plus-icon.png"))); // NOI18N
        addButton.setText("TAMBAH");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
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

        hapusButton.setBackground(new java.awt.Color(51, 102, 204));
        hapusButton.setForeground(new java.awt.Color(255, 255, 255));
        hapusButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/trash-icon24.png"))); // NOI18N
        hapusButton.setText("HAPUS");
        hapusButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hapusButtonActionPerformed(evt);
            }
        });

        tableData.setModel(new javax.swing.table.DefaultTableModel(
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
        tableData.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableDataMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tableData);

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
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(290, 290, 290)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 191, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(namaTextField)
                                    .addComponent(hargaTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lotTextField, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(kodeTextField))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cetakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(cariTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 485, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(cariButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cariTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cariButton))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(kodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lotTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(hapusButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cetakButton)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void kodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeTextFieldActionPerformed

    private void namaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTextFieldActionPerformed

    private void hargaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaTextFieldActionPerformed

    private void lotTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lotTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lotTextFieldActionPerformed

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
       
            String kode = kodeTextField.getText();
            String nama = namaTextField.getText();
            String harga = hargaTextField.getText();
            String lot = lotTextField.getText();
            
     
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = date.format(jDateChooser.getDate());
            
            Connection c = koneksi.getKoneksi();

                String sql = "INSERT INTO `barang` (`Kode_Saham`, `Nama_Saham`, `Jumlah_Lot`,`Harga/Lot`, `Tanggal`) "
                     + "VALUES ('"+kode+"', '"+nama+"','"+lot+"', '"+harga+"', '"+tanggal+"')";
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
    
    }//GEN-LAST:event_addButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
         int i = tableData.getSelectedRow();
            String kode = kodeTextField.getText();
            String nama = namaTextField.getText();
            String harga = hargaTextField.getText();
            String lot = lotTextField.getText();
            
     
            
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = String.valueOf(date.format(jDateChooser.getDate()));
            
            Connection c = koneksi.getKoneksi();
            String sql = "UPDATE `barang` SET `Nama_Saham` = '"+nama+"', `Jumlah_Lot` = '"+lot+"', `Harga/Lot` = '"+harga+"', `Tanggal` = '"+tanggal+"' "
                + "WHERE `barang`.`Kode_Saham` = '"+kode+"';";
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

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
         if (kodeTextField.getText().equals("") ||namaTextField.getText().equals("") || lotTextField.getText().equals("")|| hargaTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "LENGKAPI DATA !", "Toko Saham", JOptionPane.INFORMATION_MESSAGE);
             }else{
        try {
            String sql ="delete from barang where Kode_Saham='"+kodeTextField.getText()+"'";
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

    private void lotTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_lotTextFieldKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_lotTextFieldKeyTyped

    private void hargaTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_hargaTextFieldKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_hargaTextFieldKeyTyped

    private void namaTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTextFieldKeyTyped
        // TODO add your handling code here:
        FilterHuruf(evt);
    }//GEN-LAST:event_namaTextFieldKeyTyped

    private void tableDataMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableDataMouseClicked
        // TODO add your handling code here:
        int i = tableData.getSelectedRow();
        
        String kode= model.getValueAt(i,0).toString();
        kodeTextField.setText(kode);
        
        String nama = model.getValueAt(i,1).toString();
        namaTextField.setText(nama);
        
        String lot = model.getValueAt(i,2).toString();
        lotTextField.setText(lot);
        
        
        String harga = model.getValueAt(i,3).toString();
        hargaTextField.setText(harga);
        
       
        
        
        String tanggal = model.getValueAt(i, 4).toString();
        
        Date convert = null;
        try{
            convert = new SimpleDateFormat("yyyy-MM-dd").parse(tanggal);   
        }catch(Exception e){
            System.out.println(e);
        }
        jDateChooser.setDate(convert);
    }//GEN-LAST:event_tableDataMouseClicked

    private void cetakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakButtonActionPerformed
        // TODO add your handling code here:
        try{
            String file = "/Report/DataBarang.jasper";
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),null,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);
            
        }catch(Exception e){
            JOptionPane.showMessageDialog(rootPane, e);
        
        }
    }//GEN-LAST:event_cetakButtonActionPerformed

    private void cariTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cariTextFieldActionPerformed

    private void cariButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cariButtonActionPerformed
        // TODO add your handling code here:
        cari();
    }//GEN-LAST:event_cariButtonActionPerformed

    private void kodeTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_kodeTextFieldKeyReleased
        // TODO add your handling code here:
        String input = kodeTextField.getText();
        if(input.length()>4){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 4");
            kodeTextField.setText(null);
        }
    }//GEN-LAST:event_kodeTextFieldKeyReleased

    private void jLabel15MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel15MouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        MenuPenjualan au = new MenuPenjualan();
        au.setVisible(true);
        this.setVisible(false);
  
    }//GEN-LAST:event_jLabel15MouseClicked

    private void namaTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namaTextFieldKeyReleased
        // TODO add your handling code here:
         String input = namaTextField.getText();
        if(input.length()>4){
            JOptionPane.showMessageDialog(rootPane,"Jumlah Input Maksimal 150");
            
        }
    }//GEN-LAST:event_namaTextFieldKeyReleased

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
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormBarang.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormBarang().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton cariButton;
    private javax.swing.JTextField cariTextField;
    private javax.swing.JButton cetakButton;
    private javax.swing.JButton editButton;
    private javax.swing.JButton hapusButton;
    private javax.swing.JTextField hargaTextField;
    private com.toedter.calendar.JDateChooser jDateChooser;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField kodeTextField;
    private javax.swing.JTextField lotTextField;
    private javax.swing.JTextField namaTextField;
    private javax.swing.JTable tableData;
    // End of variables declaration//GEN-END:variables
}