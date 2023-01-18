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
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Asus
 */
public class FormPembelian extends javax.swing.JFrame {
    DefaultTableModel model = new DefaultTableModel();
    public long totalh;
    public long bayar;
    public long kembali;
    /**
     * Creates new form FormPembelian
     */
    public FormPembelian() {
        initComponents();
        
        koneksi.getKoneksi();
        totalnya();
        tanggal();

        tabelBeli.setModel(model);
        model.addColumn("ID");
        model.addColumn("Kode_Saham");
        model.addColumn("Nama_Saham");
        model.addColumn("Harga/Lot");
        model.addColumn("Jumlah");
        model.addColumn("Total_Harga");
        
       noid();
        loadData();
        
        
    }
    
     public final void loadData() {
       int row = tabelBeli.getRowCount();
        for(int a = 0 ; a < row ; a++){
            model.removeRow(0);
        }
        
        String sql = "SELECT * FROM `pembelian` ";
        String procedures = "CALL `total_transaksi2`()";

        try{
            Connection connect = koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            ResultSet rslt = sttmnt.executeQuery(sql);
            
            while (rslt.next()){
                String id = rslt.getString("ID");
                String kode = rslt.getString("Kode_Saham");
                String nama = rslt.getString("Nama_Saham");
                String harga = rslt.getString("Harga_perLot");
                String jumlah = rslt.getString("Jumlah");
                
                String total = rslt.getString("Total_Harga");
                
               
                String[] data = {id,kode,nama,harga,jumlah,total};
                
                model.addRow(data);
            }
             tabelBeli.setModel(model);
             
        } catch (SQLException e) {
            System.out.println("Terjadi Error");
        }
     }
     
       private void clear(){
        kodeTextField.setText(null);
        namaTextField.setText(null);
        beliTextField.setText(null);
        hargaTextField.setText(null);
        totalTextField.setText(null);
       
    }
       
       private void clearplus(){
          
           
        labelTotal.setText(null);
        bayarTextField.setText(null);
        kembalianTextField.setText(null);
        
        
    }
        public void tanggal(){
        Date now = new Date();  
        jDateChooser.setDate(now);    
    }
       
         
        public void cart(){
         String id = labelid.getText();
            String kode = kodeTextField.getText();
            String nama = namaTextField.getText();
            String harga = hargaTextField.getText();
            String jumlah = beliTextField.getText();
            String total = totalTextField.getText();
         
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = String.valueOf(date.format(jDateChooser.getDate()));
            
               Connection connect = koneksi.getKoneksi();

                String sql = "INSERT INTO `pembelian` (`Tanggal`, `ID`, `Kode_Saham`, `Nama_Saham`, `Harga_perLot`, `Jumlah`, `Total_Harga`) "
                + "VALUES ('"+tanggal+"','"+id+"'  , '"+kode+"', '"+nama+"', '"+harga+"', '"+jumlah+"', '"+total+"')";
               
            try{
           
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(sql);
            ps.executeUpdate(sql);
            JOptionPane.showMessageDialog(null,"Data Telah Masuk di Keranjang");
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Data Gagal Disimpan");
            
        }finally{
            loadData();
            clear();
            noid();
        }
        totalnya();
}
        
        private void totalnya(){
        String procedures = "CALL `total_transaksi2`()";
        
        try{
            Connection connect = koneksi.getKoneksi();
            Statement sttmnt = connect.createStatement();
            ResultSet rslt = sttmnt.executeQuery(procedures);
                while(rslt.next()){
                    labelTotal.setText(rslt.getString(1));
                }
                
        }catch(Exception e){
            System.out.println(e);
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
     
 private void total(){
        String harga = hargaTextField.getText();
        String jumlah = beliTextField.getText();
        
        int hargaa = Integer.parseInt(harga);
        try{
        int jumlahh = Integer.parseInt(jumlah);
        
        int total = hargaa * jumlahh;
        String total_harga = Integer.toString(total);
        
        totalTextField.setText(total_harga);
        }catch(Exception e){
           
            beliTextField.setText(null);
        }
    }
 
 private void resett(){
     try{
            String clear = "TRUNCATE `pembelian`";
            Connection connect = koneksi.getKoneksi();
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(clear);
            ps.execute();
//            keranjang();
            
            
        }catch(Exception e){
            System.out.println(e);
        }finally{
            loadData();
            totalnya();
           
        }
 }
 
      private void hapusData (){
          int i = tabelBeli.getSelectedRow();
        
        String kode = model.getValueAt(i, 0).toString();
        
        Connection connect = koneksi.getKoneksi();
        
        String query = "DELETE FROM `pembelian` WHERE `pembelian`.`ID` = '"+kode+"' ";
        try{
            PreparedStatement ps = (PreparedStatement) connect.prepareStatement(query);
            ps.execute();
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "Data Gagal Dihapus");
        }finally{
           
            loadData();
            clear();
        }
        totalnya();
      }
    
      private void noid() {
        try {
            Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();
           

            String sql = "SELECT * FROM transaksi_beli ORDER by ID desc";
            ResultSet r = s.executeQuery(sql);
          
            if (r.next()) {
                String nofak = r.getString("ID").substring(1);
                String AN = "" + (Integer.parseInt(nofak) + 1);
                String Nol = "";

                if (AN.length() == 1) {
                    Nol = "000";
                } else if (AN.length() == 2) {
                    Nol = "00";
                } else if (AN.length() == 3) {
                    Nol = "0";
                } else if (AN.length() == 4) {
                    Nol = "";
                }

                labelid.setText("4" + Nol + AN);
            } else {
                labelid.setText("40001");
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
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

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        namaTextField = new javax.swing.JTextField();
        hargaTextField = new javax.swing.JTextField();
        tambahButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        bayarTextField = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        kembalianTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        bayarButton = new javax.swing.JButton();
        cetakButton = new javax.swing.JButton();
        kodeTextField = new javax.swing.JTextField();
        totalTextField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        beliTextField = new javax.swing.JTextField();
        hapusButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabelBeli = new javax.swing.JTable();
        resetButton = new javax.swing.JButton();
        labelid = new javax.swing.JLabel();
        tambahButton2 = new javax.swing.JButton();
        tambahButton1 = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(51, 102, 204));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shopping-basket-icon.png"))); // NOI18N
        jLabel2.setText("Beli Saham");

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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 544, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(23, 23, 23))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addContainerGap(32, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel14)
                .addGap(19, 87, Short.MAX_VALUE))
        );

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 204));
        jLabel3.setText("Kode Saham");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(51, 102, 204));
        jLabel4.setText("Nama Saham");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(51, 102, 204));
        jLabel5.setText("Harga/Lot");

        namaTextField.setEditable(false);
        namaTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        namaTextField.setForeground(new java.awt.Color(51, 102, 204));
        namaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namaTextFieldActionPerformed(evt);
            }
        });

        hargaTextField.setEditable(false);
        hargaTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        hargaTextField.setForeground(new java.awt.Color(51, 102, 204));
        hargaTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hargaTextFieldActionPerformed(evt);
            }
        });

        tambahButton.setBackground(new java.awt.Color(51, 102, 204));
        tambahButton.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/button-round-plus-icon.png"))); // NOI18N
        tambahButton.setText("TAMBAH");
        tambahButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButtonActionPerformed(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(51, 102, 204));

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bayar");

        bayarTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        bayarTextField.setForeground(new java.awt.Color(51, 102, 204));
        bayarTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarTextFieldActionPerformed(evt);
            }
        });
        bayarTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                bayarTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                bayarTextFieldKeyTyped(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Kembalian");

        kembalianTextField.setEditable(false);
        kembalianTextField.setBackground(new java.awt.Color(255, 255, 255));
        kembalianTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kembalianTextField.setForeground(new java.awt.Color(51, 102, 204));
        kembalianTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kembalianTextFieldActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("Rp.");

        labelTotal.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        labelTotal.setForeground(new java.awt.Color(255, 255, 255));
        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        labelTotal.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shopping-bag-icon.png"))); // NOI18N

        bayarButton.setBackground(new java.awt.Color(51, 102, 204));
        bayarButton.setForeground(new java.awt.Color(255, 255, 255));
        bayarButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/shopping-tag-icon24.png"))); // NOI18N
        bayarButton.setText("BELI");
        bayarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bayarButtonActionPerformed(evt);
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

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(192, 192, 192)
                            .addComponent(jLabel1))
                        .addGroup(jPanel3Layout.createSequentialGroup()
                            .addGap(78, 78, 78)
                            .addComponent(jLabel11)
                            .addGap(18, 18, 18)
                            .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bayarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(bayarButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(cetakButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(kembalianTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addContainerGap(73, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addGap(16, 16, 16)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11)
                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bayarTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(kembalianTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bayarButton)
                    .addComponent(cetakButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        kodeTextField.setEditable(false);
        kodeTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        kodeTextField.setForeground(new java.awt.Color(51, 102, 204));
        kodeTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                kodeTextFieldActionPerformed(evt);
            }
        });

        totalTextField.setEditable(false);
        totalTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        totalTextField.setForeground(new java.awt.Color(51, 102, 204));
        totalTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                totalTextFieldActionPerformed(evt);
            }
        });

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(51, 102, 204));
        jLabel9.setText("Total");

        jLabel13.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(51, 102, 204));
        jLabel13.setText("Jumlah");

        beliTextField.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        beliTextField.setForeground(new java.awt.Color(51, 102, 204));
        beliTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                beliTextFieldActionPerformed(evt);
            }
        });
        beliTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                beliTextFieldKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                beliTextFieldKeyTyped(evt);
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

        tabelBeli.setModel(new javax.swing.table.DefaultTableModel(
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
        tabelBeli.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelBeliMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabelBeli);

        resetButton.setBackground(new java.awt.Color(51, 102, 204));
        resetButton.setForeground(new java.awt.Color(255, 255, 255));
        resetButton.setText("RESET");
        resetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetButtonActionPerformed(evt);
            }
        });

        labelid.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelid.setForeground(new java.awt.Color(255, 255, 255));
        labelid.setText("ID");

        tambahButton2.setBackground(new java.awt.Color(51, 102, 204));
        tambahButton2.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton2.setText("HITUNG");
        tambahButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButton2ActionPerformed(evt);
            }
        });

        tambahButton1.setBackground(new java.awt.Color(51, 102, 204));
        tambahButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tambahButton1.setForeground(new java.awt.Color(255, 255, 255));
        tambahButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/image/database-icon 48px.png"))); // NOI18N
        tambahButton1.setText("PILIH SAHAM");
        tambahButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tambahButton1MouseClicked(evt);
            }
        });
        tambahButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tambahButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(totalTextField)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(tambahButton2))
                        .addComponent(beliTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(hargaTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(namaTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(kodeTextField, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9)
                        .addComponent(jLabel13)
                        .addComponent(jLabel5)
                        .addComponent(jLabel4)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel3)
                            .addGap(68, 68, 68)
                            .addComponent(labelid)))
                    .addComponent(tambahButton)
                    .addComponent(tambahButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 488, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 457, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tambahButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(labelid)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(kodeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(namaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hargaTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(beliTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(totalTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tambahButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tambahButton))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(resetButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(hapusButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(27, 27, 27))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void namaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namaTextFieldActionPerformed

    private void hargaTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hargaTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_hargaTextFieldActionPerformed

    private void tambahButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButtonActionPerformed
        // TODO add your handling code here:
            cart();
    }//GEN-LAST:event_tambahButtonActionPerformed

    private void bayarTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_bayarTextFieldActionPerformed

    private void bayarTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarTextFieldKeyReleased
        // TODO add your handling code here:
        
            bayar = Integer.parseInt(String.valueOf(bayarTextField.getText()));
            totalh = Integer.parseInt(String.valueOf(labelTotal.getText()));
            kembali = bayar - totalh;
            
            kembalianTextField.setText(Long.toString(kembali));
        
    }//GEN-LAST:event_bayarTextFieldKeyReleased

    private void kembalianTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kembalianTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kembalianTextFieldActionPerformed

    private void bayarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bayarButtonActionPerformed
        // TODO add your handling code here:
         if(bayarTextField.getText().equals("") ||kembalianTextField.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Masukkan Uang Anda!", "Toko Saham", JOptionPane.INFORMATION_MESSAGE);
        
        }else{
            String a = kembalianTextField.getText();
            int ab = Integer.parseInt(String.valueOf(kembalianTextField.getText()));
              if(ab < 0){
                JOptionPane.showMessageDialog(null, "Uang Anda kurang", "Toko Saham", JOptionPane.INFORMATION_MESSAGE);
                bayarTextField.setText("");
            kembalianTextField.setText("");
              }else{
      
        try{
           
           Connection c = koneksi.getKoneksi();
            Statement s = c.createStatement();

            String sql = "SELECT * FROM pembelian";
            ResultSet rslt = s.executeQuery(sql);
            
             while (rslt.next()){
             
                String id = rslt.getString("ID");
                String kode = rslt.getString("Kode_Saham");
                String nama = rslt.getString("Nama_Saham");
                String harga = rslt.getString("Harga_perLot");
                String jumlah = rslt.getString("Jumlah");
                
                String total = rslt.getString("Total_Harga");
         
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            String tanggal = String.valueOf(date.format(jDateChooser.getDate()));
                

                String sqla = "INSERT INTO `transaksi_beli` (`ID`, `Kode_Saham`, `Nama_Saham`, `Harga_perLot`, `Jumlah`, `Total_Harga`,`Tanggal`) "
                + "VALUES ('"+id+"' , '"+kode+"', '"+nama+"', '"+harga+"', '"+jumlah+"', '"+total+"','"+tanggal+"')";
                
         
            PreparedStatement ps = (PreparedStatement) c.prepareStatement(sqla);
            ps.executeUpdate(sqla);
            JOptionPane.showMessageDialog(null,"Transaksi Berhasil");
            }
            
        }catch(SQLException | HeadlessException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null,"Mohon Reset Terlebih Dahulu");
            
        }finally{
     
            loadData();
            clear();
           
        }    
        }
        }
    }//GEN-LAST:event_bayarButtonActionPerformed

    private void totalTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_totalTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_totalTextFieldActionPerformed

    private void beliTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_beliTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_beliTextFieldActionPerformed

    private void beliTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beliTextFieldKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_beliTextFieldKeyReleased

    private void kodeTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_kodeTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_kodeTextFieldActionPerformed

    private void hapusButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hapusButtonActionPerformed
        // TODO add your handling code here:
        hapusData();
        bayarTextField.setText(null);
        kembalianTextField.setText(null);
    }//GEN-LAST:event_hapusButtonActionPerformed

    private void resetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetButtonActionPerformed
        // TODO add your handling code here:
        clearplus();
        resett();
        clear();
    }//GEN-LAST:event_resetButtonActionPerformed

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        // TODO add your handling code here:
         // TODO add your handling code here:
        MenuPenjualan au = new MenuPenjualan();
        au.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel14MouseClicked

    private void cetakButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cetakButtonActionPerformed
        // TODO add your handling code here:
       try{
            String file = "/Report/StrukPembelian.jasper";
            
            Class.forName("com.mysql.jdbc.Driver").newInstance();
            HashMap param = new HashMap();
            
            param.put("total",labelTotal.getText());
            param.put("uang",bayarTextField.getText());
            param.put("kembalian",kembalianTextField.getText());
            
            JasperPrint print = JasperFillManager.fillReport(getClass().getResourceAsStream(file),param,koneksi.getKoneksi());
            JasperViewer.viewReport(print, false);
            
        }catch(ClassNotFoundException | InstantiationException | IllegalAccessException | JRException e){
            System.out.println(e);
        }
    }//GEN-LAST:event_cetakButtonActionPerformed

    private void tabelBeliMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelBeliMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_tabelBeliMouseClicked

    private void beliTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_beliTextFieldKeyTyped
        // TODO add your handling code here:
    
        FilterAngka(evt);
    }//GEN-LAST:event_beliTextFieldKeyTyped

    private void tambahButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButton2ActionPerformed
        // TODO add your handling code here:
        total();
    }//GEN-LAST:event_tambahButton2ActionPerformed

    private void tambahButton1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tambahButton1MouseClicked
        // TODO add your handling code here:
        new FormBarangBeli().setVisible(true);
        //        this.setVisible(false);
        dispose();
    }//GEN-LAST:event_tambahButton1MouseClicked

    private void tambahButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tambahButton1ActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_tambahButton1ActionPerformed

    private void bayarTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bayarTextFieldKeyTyped
        // TODO add your handling code here:
        FilterAngka(evt);
    }//GEN-LAST:event_bayarTextFieldKeyTyped

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
            java.util.logging.Logger.getLogger(FormPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPembelian.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new FormPembelian().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bayarButton;
    private javax.swing.JTextField bayarTextField;
    public javax.swing.JTextField beliTextField;
    private javax.swing.JButton cetakButton;
    private javax.swing.JButton hapusButton;
    public javax.swing.JTextField hargaTextField;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField kembalianTextField;
    public javax.swing.JTextField kodeTextField;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JLabel labelid;
    public javax.swing.JTextField namaTextField;
    private javax.swing.JButton resetButton;
    private javax.swing.JTable tabelBeli;
    private javax.swing.JButton tambahButton;
    private javax.swing.JButton tambahButton1;
    private javax.swing.JButton tambahButton2;
    private javax.swing.JTextField totalTextField;
    // End of variables declaration//GEN-END:variables
}
