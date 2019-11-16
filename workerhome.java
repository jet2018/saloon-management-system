
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jet
 */
public class workerhome extends javax.swing.JFrame {
    
    Connection con;
    PreparedStatement statement;
    Statement st;
    String cs;
    boolean next;
    String password;

    String query;
    ResultSet rs;
    String user;
    /**
     * Creates new form workerhome
     */
    public workerhome() {
        con = null;
        st = null;
        statement = null;
        next = false;
        cs = "jdbc:mysql://localhost:3306/saloondb";

        password = "peacebewithyouall2020";
        user = "root";
        initComponents();
        fetchingsalooninfo();
        gettingadmins();
        addingworkers();
        selectionstylenames();
        countingsallcustomers();
//        countingsallusers();
        fetchingstyles();
        countingdiscounts();
        gettingallworkers();
       
        
        fetchingsalooninfo();
        gettingadmins();
        addingstyles();
        addingworkers();
//        fetchingusers();
        selectionstylenames();
        countingsallcustomers();
        
        fetchingstyles();
        countingdiscounts();
    }
   
    
    public final void fetchingsalooninfo() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            st = con.createStatement();
            String sr1 = "select * from saloon";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                salonlabelnamehome.setText(rs.getString("saloonname"));
                //name3.setText(rs.getString("saloonname"));
                loclbl2home.setText(rs.getString("location"));
                //loclbl1.setText(rs.getString("location"));
                phonlbl2home.setText(rs.getString("phone"));
                //phonlbl1.setText(rs.getString("phone"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    
    public void gettingallworkers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            st = con.createStatement();
            String sr1 = "select count(*)AS count1 from user";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                lblcounterhome.setText(rs.getString("count1"));
                //lbladmnis1.setText(rs.getString("count1"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
   
    public void gettingadmins() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            st = con.createStatement();
            String sr1 = "select count(*)AS count1 from user where rolename ='Admin'";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                lbladmnishome.setText(rs.getString("count1"));
                //lbladmnis1.setText(rs.getString("count1"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
     public void countingsallcustomers(){
    try{
         Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
        con=DriverManager.getConnection(cs,user,password);
        st=con.createStatement();
        String query1 = "select count(*) from customer";
        rs = st.executeQuery(query1); 
        if(rs.first()){
        //lblcounter.setText(rs.getString("count(*)"));
        lblcountingcustomershome.setText(rs.getString("count(*)"));
       lblcounteronuser.setText(rs.getString("count(*)"));
        
        }
        }
        catch(ClassNotFoundException e){
        e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
    }
    }

   private void selectionstylenames() {
       
        try {
            Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query2 = "select * from styles";
            rs = st.executeQuery(query2);

            while (rs.next()) {

                custsty.addItem(rs.getString("stylename"));
                //stytxt.addItem(rs.getString("stylename"));
            }

        } catch(Exception ex){
        //ex.printStackTrace();
        }

    }

   
    public final void countingdiscounts(){
    try{
         Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
        con=DriverManager.getConnection(cs,user,password);
        st=con.createStatement();
        String query1 = "select count(*) from discount";
        rs = st.executeQuery(query1); 
        if(rs.first()){
        counterdisc.setText(rs.getString("count(*)"));
        }
       
        }
        catch(ClassNotFoundException e){
        } catch (SQLException e) {
             e.printStackTrace();
        }
    }

     public final void fetchingdiscounts(){
    try{
        String query6 = "select discid as 'No.',stylename as Style, datefrom as'Date From', dateto as'Date To',amt2 AS 'New Amount' from discount";
        statement =con.prepareStatement(query6);
        rs = statement.executeQuery();
        
        tabdiscount.setModel(DbUtils.resultSetToTableModel(rs));
        
        
    }
    catch(Exception e){
    e.printStackTrace();
    }
    
    
    }

    public void addingworkers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            st = con.createStatement();
            String sr1 = "select count(*) AS count1 from user where rolename ='Worker'";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                lblworkcounterhome.setText(rs.getString("count1"));
                
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public void fetchingcustomers(){
    try{
        String query5 = "select custid AS ID, fname as 'First name', lname as 'Last name',email as Email,sex as Gender,style as 'Style shaved',phone as Phone,address as Address from customer";
        statement =con.prepareStatement(query5);
        rs = statement.executeQuery();
        
  tabcustomerview.setModel(DbUtils.resultSetToTableModel(rs));
        
        
    }
    catch(Exception e){
    //e.printStackTrace();
    }
    
    
    }
   
    
    
    
    
    //my functions start here
    public final void fetchingstyles() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            
            String query9;
            query9 = "select stylename AS Style, amount As 'Amount charged', sex as 'Style for',description as About from styles";
           
            rs = st.executeQuery(query9);
            
            tabviewstyle.setModel(DbUtils.resultSetToTableModel(rs));
            
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void addingstyles(){
    try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            String sr1 = "select count(*) AS count1 from styles";
             rs = st.executeQuery(sr1);
            if (rs.first()) {
                lblcounterstyles.setText(rs.getString("count1"));
//                lbladmnis1.setText(rs.getString("count1"));
            }
            
        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
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

        panparent = new keeptoo.KGradientPanel();
        pantitles = new javax.swing.JPanel();
        addcustomers2 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        unameworkerhome = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        gohome1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        panaddingstyles = new keeptoo.KGradientPanel();
        jLabel36 = new javax.swing.JLabel();
        jLabel58 = new javax.swing.JLabel();
        jLabel59 = new javax.swing.JLabel();
        jLabel60 = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        styledesc = new javax.swing.JTextArea();
        styleamtcharged = new javax.swing.JTextField();
        jSeparator7 = new javax.swing.JSeparator();
        lblerr = new javax.swing.JLabel();
        stlname = new javax.swing.JTextField();
        stylesexselection = new javax.swing.JComboBox();
        btnadd1 = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        panviewingall = new keeptoo.KGradientPanel();
        panviewingallcustomers = new keeptoo.KGradientPanel();
        jLabel31 = new javax.swing.JLabel();
        searchcustomers = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        lblcounteronuser = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabcustomerview = new javax.swing.JTable();
        jLabel32 = new javax.swing.JLabel();
        lblerrviewingcustomers = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dicbtn = new javax.swing.JButton();
        stylebtn = new javax.swing.JButton();
        customerbtn = new javax.swing.JButton();
        panviewingstyles = new keeptoo.KGradientPanel();
        searchstyletxt = new javax.swing.JTextField();
        jLabel53 = new javax.swing.JLabel();
        kGradientPanel4 = new keeptoo.KGradientPanel();
        jLabel50 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        lblcounterstyles = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tabviewstyle = new javax.swing.JTable();
        lbldeletiingstyle = new javax.swing.JLabel();
        lblerrstyles = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        panviewingalldiscounts = new keeptoo.KGradientPanel();
        jPanel7 = new javax.swing.JPanel();
        jLabel55 = new javax.swing.JLabel();
        counterdisc = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabdiscount = new javax.swing.JTable();
        deletingcustomerlbl = new javax.swing.JLabel();
        lblerrdiscounts = new javax.swing.JLabel();
        gohome3 = new javax.swing.JLabel();
        panaddingcustomers = new keeptoo.KGradientPanel();
        jLabel30 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        lblerrcustomers = new javax.swing.JLabel();
        custfiradding = new javax.swing.JTextField();
        jLabel29 = new javax.swing.JLabel();
        custpho = new javax.swing.JTextField();
        jLabel35 = new javax.swing.JLabel();
        custgen = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        custsty = new javax.swing.JComboBox();
        jLabel28 = new javax.swing.JLabel();
        custema = new javax.swing.JTextField();
        jLabel40 = new javax.swing.JLabel();
        custlas = new javax.swing.JTextField();
        jLabel33 = new javax.swing.JLabel();
        custaddress = new javax.swing.JTextField();
        custcancelbtn = new javax.swing.JLabel();
        custsubmit = new javax.swing.JLabel();
        label2foraddinganother = new javax.swing.JLabel();
        panhome = new keeptoo.KGradientPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel15 = new javax.swing.JLabel();
        phonlbl2home = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        loclbl2home = new javax.swing.JLabel();
        salonlabelnamehome = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lblcountingcustomershome = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        lblworkcounterhome = new javax.swing.JLabel();
        lbladmnishome = new javax.swing.JLabel();
        lblcounterhome = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panparent.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pantitles.setBackground(java.awt.Color.black);
        pantitles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        addcustomers2.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        addcustomers2.setForeground(new java.awt.Color(110, 174, 80));
        addcustomers2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addcustomers2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-reception-40.png"))); // NOI18N
        addcustomers2.setToolTipText("Add customers");
        addcustomers2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        addcustomers2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addcustomers2MouseClicked(evt);
            }
        });
        pantitles.add(addcustomers2, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 60, 50));

        jLabel71.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(110, 174, 80));
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-stubble-40.png"))); // NOI18N
        jLabel71.setToolTipText("Hair-styles");
        jLabel71.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel71.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel71MouseClicked(evt);
            }
        });
        pantitles.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 60, 50));

        jLabel72.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel72.setForeground(java.awt.Color.green);
        jLabel72.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel72.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-shutdown-30.png"))); // NOI18N
        jLabel72.setText("Logout");
        jLabel72.setToolTipText("Qiut the system");
        jLabel72.setBorder(new javax.swing.border.LineBorder(java.awt.Color.green, 1, true));
        jLabel72.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel72MouseClicked(evt);
            }
        });
        pantitles.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 110, 50));

        jLabel73.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(110, 174, 80));
        jLabel73.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel73.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-eye-checked-48.png"))); // NOI18N
        jLabel73.setToolTipText("View");
        jLabel73.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel73.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel73MouseClicked(evt);
            }
        });
        pantitles.add(jLabel73, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 60, 50));

        unameworkerhome.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        unameworkerhome.setForeground(java.awt.Color.green);
        unameworkerhome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pantitles.add(unameworkerhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 400, 50));

        jLabel74.setForeground(java.awt.Color.green);
        jLabel74.setText("Logged in as:");
        pantitles.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 100, 50));

        gohome1.setBackground(java.awt.Color.green);
        gohome1.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        gohome1.setForeground(java.awt.Color.white);
        gohome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gohome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-google-home-48.png"))); // NOI18N
        gohome1.setToolTipText("Home");
        gohome1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        gohome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gohome1MouseClicked(evt);
            }
        });
        pantitles.add(gohome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jScrollPane6.setViewportView(jEditorPane1);

        pantitles.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, -1, -1));

        panparent.add(pantitles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        panaddingstyles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel36.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel36.setForeground(java.awt.Color.white);
        jLabel36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel36.setText("Style name");
        panaddingstyles.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 190, 120, 40));

        jLabel58.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel58.setForeground(java.awt.Color.white);
        jLabel58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel58.setText("Style for");
        panaddingstyles.add(jLabel58, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 260, 100, 40));

        jLabel59.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel59.setForeground(java.awt.Color.white);
        jLabel59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel59.setText("Amount charged ");
        panaddingstyles.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 330, 160, 30));

        jLabel60.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel60.setForeground(java.awt.Color.white);
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel60.setText("Short description about this style");
        panaddingstyles.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 380, 330, 30));

        jLabel61.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel61.setForeground(java.awt.Color.white);
        jLabel61.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel61.setText("Let's register a new style!");
        panaddingstyles.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 100, 360, 40));

        styledesc.setColumns(20);
        styledesc.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        styledesc.setRows(5);
        styledesc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                styledescKeyPressed(evt);
            }
        });
        jScrollPane5.setViewportView(styledesc);

        panaddingstyles.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 410, 460, 80));

        styleamtcharged.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        styleamtcharged.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                styleamtchargedKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                styleamtchargedKeyPressed(evt);
            }
        });
        panaddingstyles.add(styleamtcharged, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 330, 310, 40));

        jSeparator7.setBackground(java.awt.Color.white);
        jSeparator7.setForeground(java.awt.Color.white);
        jSeparator7.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panaddingstyles.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 180, 10, 340));

        lblerr.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        lblerr.setForeground(java.awt.Color.green);
        lblerr.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panaddingstyles.add(lblerr, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 150, 560, 30));

        stlname.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        stlname.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stlnameMouseClicked(evt);
            }
        });
        stlname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stlnameKeyPressed(evt);
            }
        });
        panaddingstyles.add(stlname, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 190, 340, 40));

        stylesexselection.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17));
        stylesexselection.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select sex", "Female", "Male", "Both" }));
        stylesexselection.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                stylesexselectionKeyPressed(evt);
            }
        });
        panaddingstyles.add(stylesexselection, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 260, 340, 40));

        btnadd1.setBackground(new java.awt.Color(54, 120, 31));
        btnadd1.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        btnadd1.setForeground(java.awt.Color.white);
        btnadd1.setText("Add style");
        btnadd1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(81, 179, 58)));
        btnadd1.setContentAreaFilled(false);
        btnadd1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnadd1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnadd1ActionPerformed(evt);
            }
        });
        panaddingstyles.add(btnadd1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 190, 120, 40));

        btncancel.setBackground(new java.awt.Color(211, 109, 109));
        btncancel.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        btncancel.setForeground(java.awt.Color.lightGray);
        btncancel.setText("Cancel");
        btncancel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        btncancel.setContentAreaFilled(false);
        btncancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        panaddingstyles.add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 450, 120, 40));

        panparent.add(panaddingstyles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        panviewingall.setkEndColor(new java.awt.Color(76, 76, 123));
        panviewingall.setkGradientFocus(1000);
        panviewingall.setkStartColor(java.awt.Color.blue);
        panviewingall.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panviewingallcustomers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-48.png"))); // NOI18N
        panviewingallcustomers.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, -1, 40));

        searchcustomers.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchcustomersActionPerformed(evt);
            }
        });
        searchcustomers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchcustomersKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchcustomersKeyPressed(evt);
            }
        });
        panviewingallcustomers.add(searchcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 60, 590, 40));

        jPanel5.setBackground(java.awt.Color.black);
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cross-country-skiing-48.png"))); // NOI18N
        jPanel5.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(-10, 0, 60, 60));

        jLabel43.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        jLabel43.setForeground(java.awt.Color.white);
        jLabel43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel43.setText("Total");
        jPanel5.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 10, 80, 40));

        lblcounteronuser.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        lblcounteronuser.setForeground(java.awt.Color.white);
        lblcounteronuser.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcounteronuser.setText("0");
        jPanel5.add(lblcounteronuser, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 0, 70, 60));

        panviewingallcustomers.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 50, 180, 60));

        tabcustomerview.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(tabcustomerview);

        panviewingallcustomers.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 830, 270));

        jLabel32.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel32.setForeground(java.awt.Color.white);
        jLabel32.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel32.setText("Delete");
        jLabel32.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 102, 24), 2, true));
        jLabel32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel32MouseClicked(evt);
            }
        });
        panviewingallcustomers.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 380, 120, 50));

        lblerrviewingcustomers.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        lblerrviewingcustomers.setForeground(new java.awt.Color(221, 27, 25));
        lblerrviewingcustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panviewingallcustomers.add(lblerrviewingcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 380, 700, 50));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-trash-48.png"))); // NOI18N
        panviewingallcustomers.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 380, 70, 50));

        panviewingall.add(panviewingallcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 840, 540));

        jPanel2.setBackground(java.awt.Color.black);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dicbtn.setBackground(java.awt.Color.black);
        dicbtn.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        dicbtn.setForeground(java.awt.Color.green);
        dicbtn.setText("Discounts");
        dicbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 198, 60)));
        dicbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                dicbtnMouseClicked(evt);
            }
        });
        dicbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dicbtnActionPerformed(evt);
            }
        });
        jPanel2.add(dicbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 20, 140, 50));

        stylebtn.setBackground(java.awt.Color.black);
        stylebtn.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        stylebtn.setForeground(java.awt.Color.green);
        stylebtn.setText("Styles");
        stylebtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 198, 60)));
        stylebtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                stylebtnMouseClicked(evt);
            }
        });
        stylebtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stylebtnActionPerformed(evt);
            }
        });
        jPanel2.add(stylebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 140, 50));

        customerbtn.setBackground(java.awt.Color.black);
        customerbtn.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        customerbtn.setForeground(java.awt.Color.green);
        customerbtn.setText("Customers");
        customerbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 198, 60)));
        customerbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                customerbtnMouseClicked(evt);
            }
        });
        customerbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerbtnActionPerformed(evt);
            }
        });
        jPanel2.add(customerbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 140, 140, 50));

        panviewingall.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 200));

        panviewingstyles.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchstyletxt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchstyletxtKeyPressed(evt);
            }
        });
        panviewingstyles.add(searchstyletxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 50, 600, 50));

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-48.png"))); // NOI18N
        panviewingstyles.add(jLabel53, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 60, 50));

        kGradientPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cross-country-skiing-48.png"))); // NOI18N
        kGradientPanel4.add(jLabel50, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        jLabel51.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        jLabel51.setForeground(java.awt.Color.white);
        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel51.setText("Total");
        kGradientPanel4.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, 40));

        lblcounterstyles.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        lblcounterstyles.setForeground(java.awt.Color.white);
        lblcounterstyles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcounterstyles.setText("0");
        kGradientPanel4.add(lblcounterstyles, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 0, 70, 50));

        panviewingstyles.add(kGradientPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 50, 190, 50));

        tabviewstyle.setAutoCreateRowSorter(true);
        tabviewstyle.setBackground(new java.awt.Color(15, 15, 87));
        tabviewstyle.setBorder(null);
        tabviewstyle.setForeground(java.awt.Color.white);
        tabviewstyle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null}
            },
            new String [] {
                "Style name", "Amount", "Sex", "Description"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane3.setViewportView(tabviewstyle);

        panviewingstyles.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 820, 340));

        lbldeletiingstyle.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        lbldeletiingstyle.setForeground(java.awt.Color.white);
        lbldeletiingstyle.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbldeletiingstyle.setText("Delete");
        lbldeletiingstyle.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 102, 24), 2, true));
        lbldeletiingstyle.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbldeletiingstyleMouseClicked(evt);
            }
        });
        panviewingstyles.add(lbldeletiingstyle, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 480, 120, 60));

        lblerrstyles.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 14)); // NOI18N
        lblerrstyles.setForeground(java.awt.Color.white);
        lblerrstyles.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panviewingstyles.add(lblerrstyles, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 480, 700, 60));

        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-trash-48.png"))); // NOI18N
        panviewingstyles.add(jLabel57, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 490, 50, 40));

        panviewingall.add(panviewingstyles, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 840, 540));

        panviewingalldiscounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel7.setBackground(java.awt.Color.black);
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel55.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        jLabel55.setForeground(java.awt.Color.white);
        jLabel55.setText("TOTAL DISCOUNTS:");
        jPanel7.add(jLabel55, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 250, 50));

        counterdisc.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        counterdisc.setForeground(java.awt.Color.white);
        counterdisc.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        counterdisc.setText("0");
        jPanel7.add(counterdisc, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 10, 70, 50));

        panviewingalldiscounts.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 330, 70));

        tabdiscount.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(tabdiscount);

        panviewingalldiscounts.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 840, 310));

        deletingcustomerlbl.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        deletingcustomerlbl.setForeground(java.awt.Color.white);
        deletingcustomerlbl.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        deletingcustomerlbl.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-trash-48.png"))); // NOI18N
        deletingcustomerlbl.setText("Delete ");
        deletingcustomerlbl.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 102, 24), 2, true));
        deletingcustomerlbl.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletingcustomerlblMouseClicked(evt);
            }
        });
        panviewingalldiscounts.add(deletingcustomerlbl, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 430, 160, 50));

        lblerrdiscounts.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        lblerrdiscounts.setForeground(java.awt.Color.green);
        lblerrdiscounts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panviewingalldiscounts.add(lblerrdiscounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 680, 50));

        panviewingall.add(panviewingalldiscounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 840, 540));

        gohome3.setBackground(java.awt.Color.green);
        gohome3.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        gohome3.setForeground(java.awt.Color.white);
        gohome3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gohome3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-google-home-48.png"))); // NOI18N
        gohome3.setToolTipText("Home");
        gohome3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        gohome3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gohome3MouseClicked(evt);
            }
        });
        panviewingall.add(gohome3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

        panparent.add(panviewingall, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        panaddingcustomers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel30.setFont(new java.awt.Font("Ubuntu Condensed", 0, 18)); // NOI18N
        jLabel30.setForeground(java.awt.Color.white);
        jLabel30.setText("Add customer");
        panaddingcustomers.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 120, 120, 40));

        jLabel34.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel34.setForeground(java.awt.Color.white);
        jLabel34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel34.setText("First name");
        panaddingcustomers.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 120, 30));

        lblerrcustomers.setFont(new java.awt.Font("Noto Sans CJK JP Bold", 0, 18)); // NOI18N
        lblerrcustomers.setForeground(java.awt.Color.white);
        lblerrcustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panaddingcustomers.add(lblerrcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, 830, 40));

        custfiradding.setBackground(new java.awt.Color(27, 8, 8));
        custfiradding.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17)); // NOI18N
        custfiradding.setForeground(java.awt.Color.white);
        custfiradding.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custfiradding.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        custfiradding.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                custfiraddingActionPerformed(evt);
            }
        });
        custfiradding.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custfiraddingKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custfiradding, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 230, 400, 40));

        jLabel29.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel29.setForeground(java.awt.Color.white);
        jLabel29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel29.setText("Phone");
        panaddingcustomers.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 150, 30));

        custpho.setBackground(new java.awt.Color(27, 8, 8));
        custpho.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17)); // NOI18N
        custpho.setForeground(java.awt.Color.white);
        custpho.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custpho.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        custpho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                custphoKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custphoKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custpho, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 300, 400, 40));

        jLabel35.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel35.setForeground(java.awt.Color.white);
        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel35.setText("Gender");
        panaddingcustomers.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 340, 120, 30));

        custgen.setBackground(java.awt.Color.lightGray);
        custgen.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17));
        custgen.setForeground(java.awt.Color.blue);
        custgen.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select sex", "Male", "Female", "Rather not say" }));
        custgen.setBorder(null);
        custgen.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custgenKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custgen, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 370, 190, 40));

        jLabel6.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel6.setForeground(java.awt.Color.white);
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel6.setText("Style used");
        panaddingcustomers.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 340, 100, 30));

        custsty.setBackground(java.awt.Color.lightGray);
        custsty.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17));
        custsty.setForeground(java.awt.Color.blue);
        custsty.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select style" }));
        custsty.setBorder(null);
        custsty.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custstyKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custsty, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 370, 190, 40));

        jLabel28.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel28.setForeground(java.awt.Color.white);
        jLabel28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel28.setText("Email address");
        panaddingcustomers.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 410, 120, 30));

        custema.setBackground(new java.awt.Color(27, 8, 8));
        custema.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17)); // NOI18N
        custema.setForeground(java.awt.Color.white);
        custema.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custema.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        custema.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custemaKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custema, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 440, 400, 40));

        jLabel40.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel40.setForeground(java.awt.Color.white);
        jLabel40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel40.setText("Last name");
        panaddingcustomers.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 200, 90, 30));

        custlas.setBackground(new java.awt.Color(27, 8, 8));
        custlas.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17)); // NOI18N
        custlas.setForeground(java.awt.Color.white);
        custlas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custlas.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        custlas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custlasKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custlas, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 370, 40));

        jLabel33.setFont(new java.awt.Font("DejaVu Serif Condensed", 0, 18)); // NOI18N
        jLabel33.setForeground(java.awt.Color.white);
        jLabel33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel33.setText("Address");
        panaddingcustomers.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 270, 120, 30));

        custaddress.setBackground(new java.awt.Color(27, 8, 8));
        custaddress.setFont(new java.awt.Font("Bitstream Vera Sans Mono", 0, 17)); // NOI18N
        custaddress.setForeground(java.awt.Color.white);
        custaddress.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        custaddress.setBorder(new javax.swing.border.LineBorder(java.awt.Color.lightGray, 1, true));
        custaddress.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custaddressKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custaddress, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 300, 370, 40));

        custcancelbtn.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        custcancelbtn.setForeground(java.awt.Color.white);
        custcancelbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-undo-50.png"))); // NOI18N
        custcancelbtn.setText("Cancel");
        custcancelbtn.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        custcancelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custcancelbtnMouseClicked(evt);
            }
        });
        panaddingcustomers.add(custcancelbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 380, 150, 50));

        custsubmit.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        custsubmit.setForeground(java.awt.Color.white);
        custsubmit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-add-user-male-48.png"))); // NOI18N
        custsubmit.setText("Add customer");
        custsubmit.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        custsubmit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                custsubmitMouseClicked(evt);
            }
        });
        custsubmit.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                custsubmitKeyPressed(evt);
            }
        });
        panaddingcustomers.add(custsubmit, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 380, 190, 50));

        label2foraddinganother.setFont(new java.awt.Font("Ubuntu", 1, 24)); // NOI18N
        label2foraddinganother.setForeground(new java.awt.Color(67, 186, 36));
        label2foraddinganother.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panaddingcustomers.add(label2foraddinganother, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 50, 600, 40));

        panparent.add(panaddingcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        panhome.setkStartColor(java.awt.Color.black);
        panhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel3.setForeground(new java.awt.Color(212, 51, 51));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Designed by Tumusiime Ezra Junior");
        panhome.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 448, 580, 30));
        panhome.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, 550, 20));

        jLabel15.setFont(new java.awt.Font("Abyssinica SIL", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(236, 227, 19));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-office-phone-64.png"))); // NOI18N
        jLabel15.setText("Official Saloon phone");
        panhome.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 320, 60));

        phonlbl2home.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        phonlbl2home.setForeground(java.awt.Color.white);
        phonlbl2home.setText("0781109107");
        panhome.add(phonlbl2home, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 240, 50));

        jLabel16.setFont(new java.awt.Font("Noto Sans Mono CJK KR Bold", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(236, 227, 19));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/location.png"))); // NOI18N
        jLabel16.setText("Saloon Location");
        panhome.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 20, 370, 50));

        loclbl2home.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        loclbl2home.setForeground(java.awt.Color.white);
        loclbl2home.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loclbl2home.setText("KAKYEEKA");
        panhome.add(loclbl2home, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 60, 540, 50));

        salonlabelnamehome.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        salonlabelnamehome.setForeground(java.awt.Color.yellow);
        salonlabelnamehome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        salonlabelnamehome.setText("SALOON ONE");
        panhome.add(salonlabelnamehome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 1000, 70));

        jLabel5.setFont(new java.awt.Font("Ubuntu Light", 0, 20)); // NOI18N
        jLabel5.setForeground(java.awt.Color.white);
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("TOTAL");
        panhome.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 150, 40));

        jLabel4.setFont(new java.awt.Font("Ubuntu Light", 0, 20)); // NOI18N
        jLabel4.setForeground(java.awt.Color.white);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("CUSTOMERS");
        panhome.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 150, 40));

        lblcountingcustomershome.setFont(new java.awt.Font("Ubuntu Light", 0, 20)); // NOI18N
        lblcountingcustomershome.setForeground(java.awt.Color.white);
        lblcountingcustomershome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcountingcustomershome.setText("0");
        panhome.add(lblcountingcustomershome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 360, 150, 40));

        jLabel18.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(240, 233, 68));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        jLabel18.setText("WORKERS");
        panhome.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 260, -1, 50));

        jLabel17.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(240, 233, 68));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        jLabel17.setText("ADMINS");
        panhome.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 320, 190, 50));

        jLabel19.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(240, 233, 68));
        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        jLabel19.setText("TOTAL");
        panhome.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 380, -1, 50));

        lblworkcounterhome.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lblworkcounterhome.setForeground(java.awt.Color.white);
        lblworkcounterhome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblworkcounterhome.setText("0");
        panhome.add(lblworkcounterhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 260, 150, 60));

        lbladmnishome.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lbladmnishome.setForeground(java.awt.Color.white);
        lbladmnishome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbladmnishome.setText("0");
        panhome.add(lbladmnishome, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 320, 170, 50));

        lblcounterhome.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lblcounterhome.setForeground(java.awt.Color.white);
        lblcounterhome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcounterhome.setText("0");
        panhome.add(lblcounterhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 380, 150, 60));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagestyle1.jpg"))); // NOI18N
        panhome.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 490));

        panparent.add(panhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 490));

        getContentPane().add(panparent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        // TODO add your handling code here:
         panparent.setVisible(true);
        panhome.setVisible(true);
        panviewingall.setVisible(false);
       
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_formWindowOpened

    private void styledescKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_styledescKeyPressed
        // TODO add your handling code here:
        lblerr.setText("");
    }//GEN-LAST:event_styledescKeyPressed

    private void styleamtchargedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_styleamtchargedKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if(!Character.isDigit(c) || c==KeyEvent.VK_BACK_SPACE || c==KeyEvent.VK_DELETE){
            evt.consume();
        }
    }//GEN-LAST:event_styleamtchargedKeyTyped

    private void styleamtchargedKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_styleamtchargedKeyPressed
        // TODO add your handling code here:
        lblerr.setText("");
    }//GEN-LAST:event_styleamtchargedKeyPressed

    private void stlnameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stlnameMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_stlnameMouseClicked

    private void stlnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stlnameKeyPressed
        // TODO add your handling code here:
        lblerr.setText("");
    }//GEN-LAST:event_stlnameKeyPressed

    private void stylesexselectionKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_stylesexselectionKeyPressed
        // TODO add your handling code here:
        lblerr.setText("");
    }//GEN-LAST:event_stylesexselectionKeyPressed

    private void btnadd1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnadd1ActionPerformed
        // TODO add your handling code here:
        String sname = stlname.getText().toUpperCase();
        String sex2 = stylesexselection.getSelectedItem().toString();
        String d = styledesc.getText();
        String am = styleamtcharged.getText();

        if(sname.isEmpty() && sex2.equals("Select sex") && d.isEmpty()){
            lblerr.setText("Hey! no field is filled yet");
            stlname.requestFocus();
        }
        else if(sname.isEmpty()){
            lblerr.setText("The style has no name yet, baptize it!");
            stlname.requestFocus();
        }
        else if(am.length()<3 || am.isEmpty()){
            lblerr.setText("Amount is either empty or invalid in ugx");
            styleamtcharged.requestFocus();
        }
        else if(d.isEmpty()){
            lblerr.setText("No description given yet");
            styledesc.requestFocus();

        } else if(d.length()>200){
            lblerr.setText("Description should not be more than 200 characters");
            styledesc.requestFocus();
        }
        else{

            try{
                Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
                con=DriverManager.getConnection(cs,user,password);
                st=con.createStatement();

                query="INSERT INTO styles(stylename,amount,sex,description)  VALUES('"+sname+"', '" +am+"','" +sex2+"','" +d+"')";
                int resp = JOptionPane.showConfirmDialog(this, "Is whatever you entered final? click no to edit ", "CONFIRM THIS SUBMISSION", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.NO_OPTION) {

                } else {
                    st.executeUpdate(query);//executes the activities
                    lblerr.setBackground(Color.green);
                    lblerr.setText("New style "+sname+" has been added successfully");
                    stlname.setText("");
                    stylesexselection.setSelectedItem("Select sex");
                    styledesc.setText("");
                    styleamtcharged.setText("");

                }
            }
            catch(ClassNotFoundException ex){
            } catch (SQLException ex) {
            }
        }
        // selectionstylenames();
    }//GEN-LAST:event_btnadd1ActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "Are you sure on canceling everything", "CONFIRM THIS SUBMISSION", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.NO_OPTION) {

        } else {
            stlname.setText("");
            stylesexselection.setSelectedItem("Select sex");
            styledesc.setText("");
            styleamtcharged.setText("");
            lblerr.setText("");
        }
    }//GEN-LAST:event_btncancelActionPerformed

    private void searchcustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcustomersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchcustomersActionPerformed

    private void searchcustomersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchcustomersKeyTyped
        // TODO add your handling code here:

        if(searchcustomers.getText().equals("")){
            fetchingcustomers();
            lblerrviewingcustomers.setText("");
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(cs,user,password);
                PreparedStatement ps = con.prepareStatement("select * from customer where fname like '%"+searchcustomers.getText()+"%' or lname like '%"+searchcustomers.getText()+"%' or address like '%"+searchcustomers.getText()+"%'");

                rs = ps.executeQuery();
                DefaultTableModel dt = (DefaultTableModel)tabcustomerview.getModel();
                dt.setRowCount(0);

                if (rs.first()){
                    Object o[] = {rs.getString("fname"),rs.getString("lname"),rs.getString("email"),rs.getString("sex"),rs.getString("style"),rs.getString("phone"),rs.getString("address")};
                    dt.addRow(o);
                    lblerrviewingcustomers.setText("");

                }

                else{
                    if(searchcustomers.getText().isEmpty()){
                        fetchingcustomers();
                        countingsallcustomers();
                        lblerrviewingcustomers.setText("");
                    }else{
                        lblerrviewingcustomers.setText("No matching record found");
                    }
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_searchcustomersKeyTyped

    private void searchcustomersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchcustomersKeyPressed
        // TODO add your handling code here:
        lblerrviewingcustomers.setText("");
    }//GEN-LAST:event_searchcustomersKeyPressed

    private void jLabel32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel32MouseClicked
        // TODO add your handling code here:

        int row = tabcustomerview.getSelectedRow();
        String cell = tabcustomerview.getModel().getValueAt(row, 0).toString();
        //System.out.println(cell);
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con=DriverManager.getConnection(cs,user,password);
            st= con.createStatement();
            String sql1 = "delete from customer  where custid = '"+cell+"' LIMIT 1";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            
            lblerrviewingcustomers.setText(" Customer  "+cell+ " has been removed succeefully");
            fetchingcustomers();
            countingsallcustomers();

        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_jLabel32MouseClicked

    private void dicbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dicbtnMouseClicked
        // TODO add your handling code here:

        dicbtn.setBackground(Color.gray);
       
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.black);
    }//GEN-LAST:event_dicbtnMouseClicked

    private void dicbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dicbtnActionPerformed
        // TODO add your handling code here:
        pantitles.setVisible(true);
        countingdiscounts();
        panviewingalldiscounts.setVisible(true);
        panviewingall.setVisible(true);
      
        panviewingstyles.setVisible(false);
        panviewingallcustomers.setVisible(false);
        fetchingdiscounts();
    }//GEN-LAST:event_dicbtnActionPerformed

    private void stylebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stylebtnMouseClicked
        // TODO add your handling code here:
        dicbtn.setBackground(Color.black);
       
        stylebtn.setBackground(Color.gray);
        customerbtn.setBackground(Color.black);
    }//GEN-LAST:event_stylebtnMouseClicked

    private void stylebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stylebtnActionPerformed
        // TODO add your handling code here:
        panviewingstyles.setVisible(true);
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
       
        panviewingallcustomers.setVisible(false);
        pantitles.setVisible(true);
        addingstyles();
        fetchingstyles();
    }//GEN-LAST:event_stylebtnActionPerformed

    private void customerbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerbtnMouseClicked
        // TODO add your handling code here:
        dicbtn.setBackground(Color.black);
      
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.gray);
    }//GEN-LAST:event_customerbtnMouseClicked

    private void customerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerbtnActionPerformed
        // TODO add your handling code here:
        pantitles.setVisible(true);
        panviewingallcustomers.setVisible(true);
        panviewingstyles.setVisible(false);
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
      fetchingcustomers();

    }//GEN-LAST:event_customerbtnActionPerformed

    private void searchstyletxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchstyletxtKeyPressed
        // TODO add your handling code here:
        if(searchstyletxt.getText().equals("")){
            fetchingstyles();
            lblerrstyles.setText("");
        }
        else{
            try{
                Class.forName("com.mysql.jdbc.Driver");
                con=DriverManager.getConnection(cs,user,password);
                PreparedStatement ps = con.prepareStatement("select * from styles where stylename like '%"+searchstyletxt.getText()+"%' or sex like '%"+searchstyletxt.getText()+"%' or amount like '%"+searchstyletxt.getText()+"%'");

                rs = ps.executeQuery();
                DefaultTableModel dt = (DefaultTableModel)tabviewstyle.getModel();
                dt.setRowCount(0);

                if (rs.first()){
                    Object o[] = {rs.getString("stylename"),rs.getString("amount"),rs.getString("sex"), rs.getString("description")};
                    dt.addRow(o);
                    lblerrstyles.setText("");

                }

                else{
                    if(searchstyletxt.getText().isEmpty()){
                        fetchingstyles();
                        addingstyles();
                        lblerrstyles.setText("");
                    }else{
                        lblerrstyles.setText("No matching record found");
                    }
                }

            }
            catch(Exception ex){
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_searchstyletxtKeyPressed

    private void lbldeletiingstyleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldeletiingstyleMouseClicked
        // TODO add your handling code here:
        int row = tabviewstyle.getSelectedRow();
        String cell = tabviewstyle.getModel().getValueAt(row, 0).toString();

        try{
            String sql = "delete from styles where stylename = '"+cell+"'";
            statement = con.prepareStatement(sql);
            statement.executeUpdate();
            lblerrstyles.setText(""+cell+ " has been removed succeefully");
            fetchingstyles();
            addingstyles();
        } catch(Exception ex){
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lbldeletiingstyleMouseClicked

    private void deletingcustomerlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletingcustomerlblMouseClicked

        int row = tabdiscount.getSelectedRow();
        String cell = tabdiscount.getModel().getValueAt(row, 0).toString();
        //System.out.println(cell);
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con=DriverManager.getConnection(cs,user,password);
            st= con.createStatement();
            String sql1 = "delete from discount  where  discid= '"+cell+"'";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            lblerrdiscounts.setText("Discount "+cell+ " has araedy been removed succeefully");
            countingdiscounts();
            fetchingdiscounts();
        } catch(ClassNotFoundException ex){
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_deletingcustomerlblMouseClicked

    private void gohome3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gohome3MouseClicked
        // TODO add your handling code here:
        panparent.setVisible(true);
        panhome.setVisible(true);
        panviewingall.setVisible(false);
       
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_gohome3MouseClicked

    private void custfiraddingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custfiraddingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custfiraddingActionPerformed

    private void custfiraddingKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custfiraddingKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_custfiraddingKeyPressed

    private void custphoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custphoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_custphoKeyTyped

    private void custphoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custphoKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_custphoKeyPressed

    private void custgenKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custgenKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_custgenKeyPressed

    private void custstyKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custstyKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_custstyKeyPressed

    private void custemaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custemaKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_custemaKeyPressed

    private void custlasKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custlasKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");// TODO add your handling code here:
    }//GEN-LAST:event_custlasKeyPressed

    private void custaddressKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custaddressKeyPressed
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_custaddressKeyPressed

    private void custcancelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custcancelbtnMouseClicked
        // TODO add your handling code here:

        custsty.setSelectedItem("Select style");
        custgen.setSelectedItem("Select sex");
        custfiradding.setText("");
        custlas.setText("");
        custpho.setText("");
        custema.setText("");
        custaddress.setText("");
        lblerrcustomers.setText("");
        label2foraddinganother.setText("");
    }//GEN-LAST:event_custcancelbtnMouseClicked

    private void custsubmitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_custsubmitMouseClicked
        // TODO add your handling code here:
        String n1 = custfiradding.getText().toUpperCase();
        String n2 = custlas.getText().toUpperCase();
        String s2 = custgen.getSelectedItem().toString();
        String custfon = custpho.getText().trim();
        String sty1 = custsty.getSelectedItem().toString();
        String e1 = custema.getText().trim();
        String ad = custaddress.getText().toUpperCase();

        String ph2 = "[a-zA-Z0-9]{1,15}@[a-zA-Z0-9]{1,10}.[a-zA-Z]{2,3}";
        Pattern phone3 = Pattern.compile(ph2);
        Matcher match3 = phone3.matcher(e1);

        lblerrcustomers.setForeground(Color.red);
        if (n1.isEmpty() && n2.isEmpty() && s2.equals("Select sex") && custfon.isEmpty() && sty1.equals("Select style") && e1.isEmpty() && ad.isEmpty()) {
            lblerrcustomers.setText("Oops! no field filled yet");
            custfiradding.requestFocus();
            return;
        } else if (n1.isEmpty() && n1.length() < 3) {
            lblerrcustomers.setText("Oops! first name is still invalid");
            custfiradding.requestFocus();
            return;
        } else if ((custfon.length() != 10)) {
            lblerrcustomers.setText("Oops! phone is still invalid");
            custpho.requestFocus();
            return;
        } else if (s2.equals("Select sex")) {
            lblerrcustomers.setText("Oops! you did not select any choice on gender");
            custgen.requestFocus();
            return;
        } else if (sty1.equals("Select style")) {
            lblerrcustomers.setText("Oops! you did not select any choice on style");
            custsty.requestFocus();
            return;
        } else if (n2.isEmpty() || n2.length() < 3) {
            lblerrcustomers.setText("Oops! last name is still invalid");
            custlas.requestFocus();
            return;
        } else if (!match3.matches() && e1.isEmpty()) {
            lblerrcustomers.setText("Oops! check your email and try again");
            custema.requestFocus();
            return;
        } else if (ad.isEmpty()) {
            lblerrcustomers.setText("Oops! the address is still empty! ");
            custaddress.requestFocus();
            return;
        } else {

            try {
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                LocalDateTime now = LocalDateTime.now();

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);
                Statement s = con.createStatement();

                st = con.createStatement();
                // query = "INSERT INTO customer(fname,lname,email,sex,style,phone,addres) "
                //       + "VALUES('"+n1+"','"+n2+"','"+e1+"','"+s2+"','"+sty1+"','"+fon+"''"+ad+"',)";
                //st.executeUpdate(query);

                query = "INSERT INTO customer(fname,lname,email,sex,style,phone,address,dupdated) VALUES('" + n1 + "', '" + n2 + "','" + e1 + "','" + s2 + "','" + sty1 + "','" + custfon + "','" + ad + "','" + now + "')";

                int resp = JOptionPane.showConfirmDialog(this, "Are you sure whatever you entered is right? Please check through to comfirm first! ", "CONFIRM THIS SUBMISSION", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.NO_OPTION) {

                } else {
                    st.executeUpdate(query);

                    lblerrcustomers.setForeground(Color.green);
                    lblerrcustomers.setText("New user " + n2 + " has been added successfully");
                    label2foraddinganother.setText("REFILL TO ADD ANOTHER NEW USER");
                    custsty.setSelectedItem("Select style");
                    custgen.setSelectedItem("Select sex");
                    custfiradding.setText("");
                    custlas.setText("");
                    custpho.setText("");
                    custema.setText("");
                    custaddress.setText("");
                    custfiradding.requestFocus();
                }
            } catch (ClassNotFoundException ex) {
                // ex.printStackTrace();
            } catch (SQLException ex) {
                // ex.printStackTrace();
            }
        }
        countingsallcustomers();
    }//GEN-LAST:event_custsubmitMouseClicked

    private void custsubmitKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_custsubmitKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_custsubmitKeyPressed

    private void gohome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gohome1MouseClicked
        // TODO add your handling code here:
        panparent.setVisible(true);
        panhome.setVisible(true);
        panviewingall.setVisible(false);
        
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        // panviews.setVisible(false);
        //panaddstyles.setVisible(false);
        
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_gohome1MouseClicked

    private void jLabel73MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel73MouseClicked
        panparent.setVisible(true);
        panhome.setVisible(false);
       // fetchingusers();
        panaddingcustomers.setVisible(false);
        panviewingstyles.setVisible(false);
        panaddingstyles.setVisible(false);
        panviewingall.setVisible(true);
        panviewingallcustomers.setVisible(false);
        addingstyles();
        countingsallcustomers();
        fetchingcustomers();
        fetchingsalooninfo();
        fetchingdiscounts();
        countingdiscounts();
        panviewingalldiscounts.setVisible(true);
        
        countingsallcustomers();
    }//GEN-LAST:event_jLabel73MouseClicked

    private void jLabel72MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel72MouseClicked
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "CONFIRM", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.NO_OPTION) {

        } else {
            login lg = new login();
            this.dispose();
            lg.setVisible(true);
            lg.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jLabel72MouseClicked

    private void jLabel71MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel71MouseClicked

        panparent.setVisible(true);
        panhome.setVisible(false);
     
        panaddingcustomers.setVisible(false);
        // panviews.setVisible(false);
        panaddingstyles.setVisible(true);
        
        panviewingall.setVisible(false);
        panviewingallcustomers.setVisible(false);
      
    }//GEN-LAST:event_jLabel71MouseClicked

    private void addcustomers2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addcustomers2MouseClicked
        // TODO add your handling code here:
        panparent.setVisible(true);
        panhome.setVisible(false);
      
        panaddingcustomers.setVisible(true);
        //selectionstylenames();
        // panviews.setVisible(false);
        panaddingstyles.setVisible(false);
       
        panviewingall.setVisible(false);
        panviewingallcustomers.setVisible(false);
       
    }//GEN-LAST:event_addcustomers2MouseClicked

    /**
     * @param args the command line arguments
     */
    public void main2() {
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
            java.util.logging.Logger.getLogger(workerhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(workerhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(workerhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(workerhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                workerhome wk =new workerhome();
                wk.setVisible(true);
                wk.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addcustomers2;
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btncancel;
    private javax.swing.JLabel counterdisc;
    private javax.swing.JTextField custaddress;
    private javax.swing.JLabel custcancelbtn;
    private javax.swing.JTextField custema;
    private javax.swing.JTextField custfiradding;
    private javax.swing.JComboBox<String> custgen;
    private javax.swing.JTextField custlas;
    private javax.swing.JButton customerbtn;
    private javax.swing.JTextField custpho;
    private javax.swing.JComboBox<String> custsty;
    private javax.swing.JLabel custsubmit;
    private javax.swing.JLabel deletingcustomerlbl;
    private javax.swing.JButton dicbtn;
    private javax.swing.JLabel gohome1;
    private javax.swing.JLabel gohome3;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
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
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator7;
    private keeptoo.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel label2foraddinganother;
    private javax.swing.JLabel lbladmnishome;
    private javax.swing.JLabel lblcounterhome;
    private javax.swing.JLabel lblcounteronuser;
    private javax.swing.JLabel lblcounterstyles;
    private javax.swing.JLabel lblcountingcustomershome;
    private javax.swing.JLabel lbldeletiingstyle;
    private javax.swing.JLabel lblerr;
    private javax.swing.JLabel lblerrcustomers;
    private javax.swing.JLabel lblerrdiscounts;
    private javax.swing.JLabel lblerrstyles;
    private javax.swing.JLabel lblerrviewingcustomers;
    private javax.swing.JLabel lblworkcounterhome;
    private javax.swing.JLabel loclbl2home;
    private keeptoo.KGradientPanel panaddingcustomers;
    private keeptoo.KGradientPanel panaddingstyles;
    private keeptoo.KGradientPanel panhome;
    private keeptoo.KGradientPanel panparent;
    private javax.swing.JPanel pantitles;
    private keeptoo.KGradientPanel panviewingall;
    private keeptoo.KGradientPanel panviewingallcustomers;
    private keeptoo.KGradientPanel panviewingalldiscounts;
    private keeptoo.KGradientPanel panviewingstyles;
    private javax.swing.JLabel phonlbl2home;
    private javax.swing.JLabel salonlabelnamehome;
    private javax.swing.JTextField searchcustomers;
    private javax.swing.JTextField searchstyletxt;
    private javax.swing.JTextField stlname;
    private javax.swing.JTextField styleamtcharged;
    private javax.swing.JButton stylebtn;
    private javax.swing.JTextArea styledesc;
    private javax.swing.JComboBox<String> stylesexselection;
    private javax.swing.JTable tabcustomerview;
    private javax.swing.JTable tabdiscount;
    private javax.swing.JTable tabviewstyle;
    public static javax.swing.JLabel unameworkerhome;
    // End of variables declaration//GEN-END:variables
}
