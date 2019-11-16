
//import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import java.sql.Date;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
//import javax.swing.text.Document;
import net.proteanit.sql.DbUtils;
//import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author jet
 */
public class adminhome extends javax.swing.JFrame {

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
     * Creates new form adminhome
     */
    public adminhome() {
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
        addingstyles();
        addingworkers();
        fetchingusers();
        selectionstylenames();
        countingsallcustomers();
        countingsallusers();
        fetchingstyles();
        countingdiscounts();
        fetchingdebts();
        gettingtotaldebts();

    }

    public final void fetchingdebts() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();

            String query9;
            query9 = "select defid AS 'Debt no', fname As 'First name', lname as 'Last name',phone as 'Tel.', date AS 'Date of payment', amount AS Amount from defaults";

            rs = st.executeQuery(query9);

            tabviewdebts.setModel(DbUtils.resultSetToTableModel(rs));

        } catch (SQLException e) {
            //e.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

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

    public void addingstyles() {
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

    public void fetchingsalooninfo() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);

            st = con.createStatement();
            String sr1 = "select * from saloon";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                name1.setText(rs.getString("saloonname"));
                name3.setText(rs.getString("saloonname"));
                loclbl2.setText(rs.getString("location"));
                loclbl1.setText(rs.getString("location"));
                phonlbl2.setText(rs.getString("phone"));
                phonlbl1.setText(rs.getString("phone"));
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
            String sr1 = "select count(*)AS count1 from user where rolename ='Admin'";
            rs = st.executeQuery(sr1);
            if (rs.first()) {
                lbladmnis.setText(rs.getString("count1"));
//                lbladmnis1.setText(rs.getString("count1"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void countingsallusers() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); //registers drivers we configured
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query1 = "select count(*) from user";
            rs = st.executeQuery(query1);
            if (rs.first()) {
                lblcounterviews.setText(rs.getString("count(*)"));
//       lblcounter3.setText(rs.getString("count(*)"));

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
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
                lbladmnis.setText(rs.getString("count1"));
                lbladmnisview.setText(rs.getString("count1"));
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void countingsallcustomers() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query1 = "select count(*) from customer";
            rs = st.executeQuery(query1);
            if (rs.first()) {
                lblcounteronuser.setText(rs.getString("count(*)"));
                // lblcounter3.setText(rs.getString("count(*)"));

            }
        } catch (ClassNotFoundException e) {
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
                stydiscounttxt.addItem(rs.getString("stylename"));
            }

        } catch (Exception ex) {
            //ex.printStackTrace();
        }

    }

    private void gettingtotaldebts() {

        try {
            Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query2 = " select sum(amount) as total from defaults";
            rs = st.executeQuery(query2);

            lblcountingdebts.setText(rs.getString("total"));

        } catch (Exception ex) {
            //ex.printStackTrace();
        }

    }

    public final void countingdiscounts() {
        try {
            Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query1 = "select count(*) from discount";
            rs = st.executeQuery(query1);
            if (rs.first()) {
                counterdisc.setText(rs.getString("count(*)"));
            }

        } catch (ClassNotFoundException e) {
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public final void fetchingdiscounts() {
        try {
            String query6 = "select discid as 'No.',stylename as Style, datefrom as'Date From', dateto as'Date To',amt2 AS 'New Amount' from discount";
            statement = con.prepareStatement(query6);
            rs = statement.executeQuery();

            tabdiscount.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
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
                lblwork.setText(rs.getString("count1"));
                lblworkview.setText(rs.getString("count1"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void fetchingcustomers() {
        try {
            String query5 = "select custid AS ID, fname as 'First name', lname as 'Last name',email as Email,sex as Gender,style as 'Style shaved',phone as Phone,address as Address from customer";
            statement = con.prepareStatement(query5);
            rs = statement.executeQuery();

            tabcustomerview.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            //e.printStackTrace();
        }

    }

    public void fetchingusers() {
        try {
            String query5 = "select fname as 'First name', lname as 'Last name',password as Password,gender as Gender,uname as 'Username',rolename as Role,phone as Phone from user";
            statement = con.prepareStatement(query5);
            rs = statement.executeQuery();

            tabviewallusers.setModel(DbUtils.resultSetToTableModel(rs));
        } catch (Exception e) {
            //e.printStackTrace();
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
        jLabel1 = new javax.swing.JLabel();
        addabout = new javax.swing.JLabel();
        addmembers = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        addcustomers = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        logsname = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        gohome1 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel75 = new javax.swing.JLabel();
        panadddefault = new keeptoo.KGradientPanel();
        jLabel49 = new javax.swing.JLabel();
        lblerrdefaults = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        def_fname = new javax.swing.JTextField();
        jLabel69 = new javax.swing.JLabel();
        def_lname = new javax.swing.JTextField();
        jLabel70 = new javax.swing.JLabel();
        def_pay = new com.toedter.calendar.JDateChooser();
        jLabel54 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        def_fon = new javax.swing.JTextField();
        def_amount = new javax.swing.JTextField();
        def_cancel = new javax.swing.JButton();
        def_add = new javax.swing.JButton();
        panreports = new keeptoo.KGradientPanel();
        jLabel37 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jSeparator8 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton6 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        panaddingdiscounts = new keeptoo.KGradientPanel();
        jLabel62 = new javax.swing.JLabel();
        lblerrdiscount = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        stydiscounttxt = new javax.swing.JComboBox();
        jLabel64 = new javax.swing.JLabel();
        discnewamt = new javax.swing.JTextField();
        cancelbtn = new javax.swing.JLabel();
        adddicountbtn = new javax.swing.JLabel();
        discdateto = new com.toedter.calendar.JDateChooser();
        jLabel66 = new javax.swing.JLabel();
        discdatefro = new com.toedter.calendar.JDateChooser();
        jLabel65 = new javax.swing.JLabel();
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
        panviewdefs = new keeptoo.KGradientPanel();
        jLabel71 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabviewdebts = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();
        lblerrdebts = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel72 = new javax.swing.JLabel();
        lblcountingdebts = new javax.swing.JLabel();
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
        userbtn = new javax.swing.JButton();
        customerbtn = new javax.swing.JButton();
        btndefault = new javax.swing.JButton();
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
        panviewusers = new keeptoo.KGradientPanel();
        kGradientPanel3 = new keeptoo.KGradientPanel();
        jLabel47 = new javax.swing.JLabel();
        lbladmnisview = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        lblcounterviews = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        lblworkview = new javax.swing.JLabel();
        searchallusers = new javax.swing.JTextField();
        jLabel52 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabviewallusers = new javax.swing.JTable();
        lblerrusers = new javax.swing.JLabel();
        labdeleteusers = new javax.swing.JLabel();
        gohome = new javax.swing.JLabel();
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
        panaddinguser = new keeptoo.KGradientPanel();
        labreg = new javax.swing.JLabel();
        lblerraddiinguser = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        useraddingfname = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        useraddinguname = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        useraddingrole = new javax.swing.JComboBox();
        jLabel22 = new javax.swing.JLabel();
        useraddingsex1 = new javax.swing.JComboBox();
        jLabel23 = new javax.swing.JLabel();
        useraddingfon = new javax.swing.JTextField();
        jSeparator6 = new javax.swing.JSeparator();
        useraddinglname = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        useraddingpass1 = new javax.swing.JPasswordField();
        jLabel26 = new javax.swing.JLabel();
        addinguserpass2 = new javax.swing.JPasswordField();
        cancel = new javax.swing.JButton();
        btnadd = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        panaaboutsaloon = new keeptoo.KGradientPanel();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        lblerraboutsaloon = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        salname1 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        saloonloc = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        saloonpho = new javax.swing.JTextField();
        btncancelsaloon = new javax.swing.JButton();
        saloonbtnedit = new javax.swing.JButton();
        name1 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        loclbl2 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        phonlbl2 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        lbladmnis = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        jLabel18 = new javax.swing.JLabel();
        lblwork = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        panhome = new keeptoo.KGradientPanel();
        name3 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        loclbl1 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        phonlbl1 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();

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

        jLabel1.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(110, 174, 80));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-edit-row-40.png"))); // NOI18N
        jLabel1.setToolTipText("Add defaulter");
        jLabel1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });
        pantitles.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 0, 60, 50));

        addabout.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        addabout.setForeground(new java.awt.Color(110, 174, 80));
        addabout.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addabout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-about-30.png"))); // NOI18N
        addabout.setToolTipText("About Saloon");
        addabout.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        addabout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addabout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addaboutMouseClicked(evt);
            }
        });
        pantitles.add(addabout, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 0, 60, 50));

        addmembers.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        addmembers.setForeground(new java.awt.Color(110, 174, 80));
        addmembers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addmembers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-add-user-group-man-man-48.png"))); // NOI18N
        addmembers.setToolTipText("Add Workers/members");
        addmembers.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        addmembers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addmembers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addmembersMouseClicked(evt);
            }
        });
        pantitles.add(addmembers, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 0, 60, 50));

        jLabel5.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(110, 174, 80));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jLabel5.setToolTipText("Reports");
        jLabel5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        pantitles.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 0, 60, 50));

        addcustomers.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        addcustomers.setForeground(new java.awt.Color(110, 174, 80));
        addcustomers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        addcustomers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-reception-40.png"))); // NOI18N
        addcustomers.setToolTipText("Add customers");
        addcustomers.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        addcustomers.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addcustomers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addcustomersMouseClicked(evt);
            }
        });
        pantitles.add(addcustomers, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 0, 60, 50));

        jLabel7.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(110, 174, 80));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-stubble-40.png"))); // NOI18N
        jLabel7.setToolTipText("Hair-styles");
        jLabel7.setBorder(null);
        jLabel7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel7MouseClicked(evt);
            }
        });
        pantitles.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 10, 60, 40));

        jLabel9.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        jLabel9.setForeground(java.awt.Color.green);
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-shutdown-30.png"))); // NOI18N
        jLabel9.setText("Logout");
        jLabel9.setToolTipText("Qiut the system");
        jLabel9.setBorder(new javax.swing.border.LineBorder(java.awt.Color.green, 1, true));
        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
        });
        pantitles.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 0, 110, 50));

        jLabel27.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(110, 174, 80));
        jLabel27.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel27.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-eye-checked-48.png"))); // NOI18N
        jLabel27.setToolTipText("View");
        jLabel27.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel27.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel27.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel27MouseClicked(evt);
            }
        });
        pantitles.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 0, 60, 50));

        logsname.setFont(new java.awt.Font("Ubuntu", 0, 17)); // NOI18N
        logsname.setForeground(java.awt.Color.green);
        logsname.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pantitles.add(logsname, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 0, 240, 50));

        jLabel56.setForeground(java.awt.Color.green);
        jLabel56.setText("Logged in as:");
        pantitles.add(jLabel56, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 0, 100, 50));

        gohome1.setBackground(java.awt.Color.green);
        gohome1.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        gohome1.setForeground(java.awt.Color.white);
        gohome1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gohome1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-google-home-48.png"))); // NOI18N
        gohome1.setToolTipText("Home");
        gohome1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        gohome1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        gohome1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gohome1MouseClicked(evt);
            }
        });
        pantitles.add(gohome1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 50));

        jScrollPane6.setViewportView(jEditorPane1);

        pantitles.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 310, -1, -1));

        jLabel75.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(110, 174, 80));
        jLabel75.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel75.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-download-graph-report-50.png"))); // NOI18N
        jLabel75.setToolTipText("Discounts");
        jLabel75.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        jLabel75.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel75.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel75MouseClicked(evt);
            }
        });
        pantitles.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(750, 0, 60, 50));

        panparent.add(pantitles, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 50));

        panadddefault.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel49.setFont(new java.awt.Font("Phetsarath OT", 0, 30)); // NOI18N
        jLabel49.setForeground(java.awt.Color.white);
        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setText("Add defaulters");
        panadddefault.add(jLabel49, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 90, 550, 50));

        lblerrdefaults.setFont(new java.awt.Font("SansSerif", 0, 25)); // NOI18N
        lblerrdefaults.setForeground(java.awt.Color.red);
        lblerrdefaults.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panadddefault.add(lblerrdefaults, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 800, 40));

        jLabel67.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jLabel67.setForeground(java.awt.Color.white);
        jLabel67.setText("First name");
        panadddefault.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 180, 140, 40));
        panadddefault.add(def_fname, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 220, 360, 50));

        jLabel69.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jLabel69.setForeground(java.awt.Color.white);
        jLabel69.setText("Last name");
        panadddefault.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 180, 140, 40));
        panadddefault.add(def_lname, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 220, 360, 50));

        jLabel70.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jLabel70.setForeground(java.awt.Color.white);
        jLabel70.setText("Amount defaulted");
        panadddefault.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 250, 40));

        def_pay.setDateFormatString("yyy-MM-dd");
        panadddefault.add(def_pay, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 440, 360, 50));

        jLabel54.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jLabel54.setForeground(java.awt.Color.white);
        jLabel54.setText("Estimated date of payment");
        panadddefault.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 320, 40));

        jLabel68.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jLabel68.setForeground(java.awt.Color.white);
        jLabel68.setText("Phone");
        panadddefault.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 290, 140, 40));

        def_fon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                def_fonKeyTyped(evt);
            }
        });
        panadddefault.add(def_fon, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 330, 360, 50));

        def_amount.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                def_amountKeyTyped(evt);
            }
        });
        panadddefault.add(def_amount, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 330, 360, 50));

        def_cancel.setForeground(java.awt.Color.lightGray);
        def_cancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-undo-50.png"))); // NOI18N
        def_cancel.setText("Cancel");
        def_cancel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        def_cancel.setContentAreaFilled(false);
        def_cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                def_cancelActionPerformed(evt);
            }
        });
        panadddefault.add(def_cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 410, 120, 40));

        def_add.setForeground(java.awt.Color.green);
        def_add.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-add-user-group-man-man-48.png"))); // NOI18N
        def_add.setText("Add");
        def_add.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(36, 180, 67)));
        def_add.setContentAreaFilled(false);
        def_add.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                def_addActionPerformed(evt);
            }
        });
        panadddefault.add(def_add, new org.netbeans.lib.awtextra.AbsoluteConstraints(810, 410, 110, 40));

        panparent.add(panadddefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 550));

        panreports.setForeground(java.awt.Color.white);
        panreports.setkEndColor(java.awt.Color.pink);
        panreports.setkStartColor(new java.awt.Color(80, 164, 80));
        panreports.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel37.setFont(new java.awt.Font("Ubuntu Light", 0, 24)); // NOI18N
        jLabel37.setForeground(java.awt.Color.cyan);
        jLabel37.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel37.setText("Click on any button below to generate the corresponding report ");
        panreports.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 90, 810, 80));

        jLabel44.setFont(new java.awt.Font("Ubuntu Light", 0, 24)); // NOI18N
        jLabel44.setForeground(java.awt.Color.white);
        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setText("GENERATE REPORTS");
        panreports.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 60, 520, 80));
        panreports.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 150, 710, 40));

        jButton1.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton1.setForeground(new java.awt.Color(17, 155, 154));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton1.setText("Customers");
        jButton1.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton1.setContentAreaFilled(false);
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        panreports.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 300, 220, 80));

        jButton2.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton2.setForeground(new java.awt.Color(17, 155, 154));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton2.setText("Workers");
        jButton2.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton2.setContentAreaFilled(false);
        jButton2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        panreports.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 190, 200, 80));

        jButton4.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton4.setForeground(new java.awt.Color(17, 155, 154));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton4.setText("Defaulters");
        jButton4.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton4.setContentAreaFilled(false);
        jButton4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        panreports.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 300, 210, 80));

        jButton5.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton5.setForeground(new java.awt.Color(17, 155, 154));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton5.setText("Styles");
        jButton5.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton5.setContentAreaFilled(false);
        jButton5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        panreports.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 190, 200, 80));

        jPanel1.setBorder(new javax.swing.border.LineBorder(java.awt.Color.cyan, 1, true));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton6.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton6.setForeground(new java.awt.Color(17, 155, 154));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton6.setText("Discounts");
        jButton6.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton6.setContentAreaFilled(false);
        jButton6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton6, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 140, 210, 80));

        jButton3.setFont(new java.awt.Font("SansSerif", 0, 30)); // NOI18N
        jButton3.setForeground(new java.awt.Color(17, 155, 154));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-pdf-48.png"))); // NOI18N
        jButton3.setText("Admins");
        jButton3.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        jButton3.setContentAreaFilled(false);
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        jPanel1.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 30, 200, 80));

        panreports.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 160, 860, 310));

        panparent.add(panreports, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        panaddingdiscounts.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel62.setFont(new java.awt.Font("Cantarell", 1, 36)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(234, 242, 245));
        jLabel62.setText("Add a discount below");
        panaddingdiscounts.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 440, 50));

        lblerrdiscount.setFont(new java.awt.Font("Cantarell", 3, 24)); // NOI18N
        lblerrdiscount.setForeground(new java.awt.Color(238, 9, 20));
        lblerrdiscount.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panaddingdiscounts.add(lblerrdiscount, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 170, 770, 40));

        jLabel63.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(234, 242, 245));
        jLabel63.setText("Style name");
        panaddingdiscounts.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 220, 140, 50));

        stydiscounttxt.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select style" }));
        stydiscounttxt.setSelectedItem("Select style");
        stydiscounttxt.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stydiscounttxtActionPerformed(evt);
            }
        });
        panaddingdiscounts.add(stydiscounttxt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 260, 320, 40));

        jLabel64.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(234, 242, 245));
        jLabel64.setText("New Amount");
        panaddingdiscounts.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 300, 140, 50));

        discnewamt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                discnewamtKeyTyped(evt);
            }
        });
        panaddingdiscounts.add(discnewamt, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 340, 320, 40));

        cancelbtn.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        cancelbtn.setForeground(java.awt.Color.white);
        cancelbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-undo-50.png"))); // NOI18N
        cancelbtn.setText("Cancel");
        cancelbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(213, 34, 34)));
        cancelbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cancelbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cancelbtnMouseClicked(evt);
            }
        });
        panaddingdiscounts.add(cancelbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 410, 140, 50));

        adddicountbtn.setFont(new java.awt.Font("Bitstream Vera Serif", 0, 17)); // NOI18N
        adddicountbtn.setForeground(java.awt.Color.white);
        adddicountbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-add-user-male-48.png"))); // NOI18N
        adddicountbtn.setText("Add Discount");
        adddicountbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 221, 39)));
        adddicountbtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        adddicountbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                adddicountbtnMouseClicked(evt);
            }
        });
        adddicountbtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                adddicountbtnKeyPressed(evt);
            }
        });
        panaddingdiscounts.add(adddicountbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 410, 180, 50));
        panaddingdiscounts.add(discdateto, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 320, 40));

        jLabel66.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(234, 242, 245));
        jLabel66.setText("To");
        panaddingdiscounts.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 310, 140, 30));
        panaddingdiscounts.add(discdatefro, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 260, 320, 40));

        jLabel65.setFont(new java.awt.Font("Cantarell", 1, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(234, 242, 245));
        jLabel65.setText("From");
        panaddingdiscounts.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 220, 140, 50));

        panparent.add(panaddingdiscounts, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

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

        panviewdefs.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel71.setFont(new java.awt.Font("Sawasdee", 0, 30)); // NOI18N
        jLabel71.setForeground(java.awt.Color.white);
        jLabel71.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel71.setText("Currently running debts");
        panviewdefs.add(jLabel71, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 10, 530, 40));

        tabviewdebts.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane7.setViewportView(tabviewdebts);

        panviewdefs.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 830, 310));

        jButton7.setFont(new java.awt.Font("Ubuntu", 0, 25)); // NOI18N
        jButton7.setForeground(java.awt.Color.red);
        jButton7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-trash-48.png"))); // NOI18N
        jButton7.setText("Delete a debt");
        jButton7.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        jButton7.setContentAreaFilled(false);
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        panviewdefs.add(jButton7, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 470, 240, 50));

        lblerrdebts.setFont(new java.awt.Font("Ubuntu", 0, 20)); // NOI18N
        lblerrdebts.setForeground(java.awt.Color.green);
        lblerrdebts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panviewdefs.add(lblerrdebts, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 470, 580, 50));

        jPanel3.setBackground(java.awt.Color.black);
        jPanel3.setForeground(java.awt.Color.white);
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel72.setFont(new java.awt.Font("Ubuntu Condensed", 0, 24)); // NOI18N
        jLabel72.setForeground(java.awt.Color.white);
        jLabel72.setText("Total amount from defaulters:");
        jPanel3.add(jLabel72, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 300, 50));

        lblcountingdebts.setFont(new java.awt.Font("Tibetan Machine Uni", 0, 20)); // NOI18N
        lblcountingdebts.setForeground(java.awt.Color.red);
        lblcountingdebts.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcountingdebts.setText("0");
        jPanel3.add(lblcountingdebts, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 10, 180, 50));

        panviewdefs.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 60, 490, 70));

        panviewingall.add(panviewdefs, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 840, 540));

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
        jPanel2.add(dicbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 140, 50));

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
        jPanel2.add(stylebtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 140, 50));

        userbtn.setBackground(java.awt.Color.black);
        userbtn.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        userbtn.setForeground(java.awt.Color.green);
        userbtn.setText("Users");
        userbtn.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 198, 60)));
        userbtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userbtnMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                userbtnMouseEntered(evt);
            }
        });
        userbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                userbtnActionPerformed(evt);
            }
        });
        jPanel2.add(userbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 140, 50));

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
        jPanel2.add(customerbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 140, 50));

        btndefault.setBackground(java.awt.Color.black);
        btndefault.setFont(new java.awt.Font("Ubuntu Mono", 0, 24)); // NOI18N
        btndefault.setForeground(java.awt.Color.green);
        btndefault.setText("Defaulters");
        btndefault.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(102, 198, 60)));
        btndefault.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btndefaultMouseClicked(evt);
            }
        });
        btndefault.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btndefaultActionPerformed(evt);
            }
        });
        jPanel2.add(btndefault, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 50));

        panviewingall.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, 140, 250));

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

        panviewusers.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        kGradientPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel47.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel47.setForeground(new java.awt.Color(240, 233, 68));
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-microsoft-admin-50.png"))); // NOI18N
        jLabel47.setText("Admins");
        kGradientPanel3.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -2, 170, 70));

        lbladmnisview.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lbladmnisview.setForeground(java.awt.Color.white);
        lbladmnisview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbladmnisview.setText("0");
        kGradientPanel3.add(lbladmnisview, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 0, 100, 70));

        panviewusers.add(kGradientPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 50, 280, 70));

        jPanel6.setBackground(java.awt.Color.black);
        jPanel6.setForeground(java.awt.Color.white);
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-cross-country-skiing-48.png"))); // NOI18N
        jPanel6.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 50, 60));

        jLabel46.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        jLabel46.setForeground(java.awt.Color.white);
        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel46.setText("Total");
        jPanel6.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 10, 70, 40));

        lblcounterviews.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        lblcounterviews.setForeground(java.awt.Color.white);
        lblcounterviews.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblcounterviews.setText("0");
        jPanel6.add(lblcounterviews, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 10, 70, 40));

        panviewusers.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 230, 70));

        jLabel48.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel48.setForeground(new java.awt.Color(240, 233, 68));
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-workers-40.png"))); // NOI18N
        jLabel48.setText("Workers");
        panviewusers.add(jLabel48, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 50, 240, 70));

        lblworkview.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lblworkview.setForeground(java.awt.Color.white);
        lblworkview.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblworkview.setText("0");
        panviewusers.add(lblworkview, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 50, 130, 70));

        searchallusers.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                searchallusersKeyPressed(evt);
            }
        });
        panviewusers.add(searchallusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 130, 770, 40));

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-search-48.png"))); // NOI18N
        panviewusers.add(jLabel52, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 120, 60, 50));

        tabviewallusers.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(tabviewallusers);

        panviewusers.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 180, 820, 300));

        lblerrusers.setFont(new java.awt.Font("Century Schoolbook L", 0, 24)); // NOI18N
        lblerrusers.setForeground(new java.awt.Color(221, 27, 25));
        lblerrusers.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        panviewusers.add(lblerrusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 480, 680, 50));

        labdeleteusers.setFont(new java.awt.Font("Cantarell", 0, 24)); // NOI18N
        labdeleteusers.setForeground(java.awt.Color.white);
        labdeleteusers.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labdeleteusers.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-trash-48.png"))); // NOI18N
        labdeleteusers.setText("Delete");
        labdeleteusers.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(24, 102, 24), 2, true));
        labdeleteusers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labdeleteusersMouseClicked(evt);
            }
        });
        panviewusers.add(labdeleteusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 480, 140, 50));

        panviewingall.add(panviewusers, new org.netbeans.lib.awtextra.AbsoluteConstraints(140, 0, 840, 540));

        gohome.setBackground(java.awt.Color.green);
        gohome.setFont(new java.awt.Font("Manjari Regular", 0, 24)); // NOI18N
        gohome.setForeground(java.awt.Color.white);
        gohome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gohome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-google-home-48.png"))); // NOI18N
        gohome.setToolTipText("Home");
        gohome.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.darkGray));
        gohome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                gohomeMouseClicked(evt);
            }
        });
        panviewingall.add(gohome, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 90, -1, -1));

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

        panaddinguser.setkEndColor(java.awt.Color.gray);
        panaddinguser.setkStartColor(java.awt.Color.black);
        panaddinguser.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        labreg.setFont(new java.awt.Font("Ubuntu", 1, 18)); // NOI18N
        labreg.setForeground(java.awt.Color.white);
        labreg.setText("Add member");
        panaddinguser.add(labreg, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 170, 40));

        lblerraddiinguser.setFont(new java.awt.Font("Ubuntu Light", 3, 18)); // NOI18N
        lblerraddiinguser.setForeground(new java.awt.Color(75, 183, 49));
        panaddinguser.add(lblerraddiinguser, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 60, 760, 40));

        jLabel19.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel19.setForeground(java.awt.Color.white);
        jLabel19.setText("First name");
        panaddinguser.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 290, 30));

        useraddingfname.setBackground(new java.awt.Color(166, 121, 121));
        useraddingfname.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddingfname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingfnameKeyPressed(evt);
            }
        });
        panaddinguser.add(useraddingfname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 170, 400, 40));

        jLabel20.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel20.setForeground(java.awt.Color.white);
        jLabel20.setText("Create username");
        panaddinguser.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 210, 290, 30));

        useraddinguname.setBackground(new java.awt.Color(166, 121, 121));
        useraddinguname.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddinguname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingunameKeyPressed(evt);
            }
        });
        panaddinguser.add(useraddinguname, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 400, 40));

        jLabel21.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel21.setForeground(java.awt.Color.white);
        jLabel21.setText("Role");
        panaddinguser.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 290, 300, 30));

        useraddingrole.setBackground(new java.awt.Color(166, 121, 121));
        useraddingrole.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddingrole.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select role", "Admin", "Worker" }));
        useraddingrole.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                useraddingroleActionPerformed(evt);
            }
        });
        useraddingrole.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingroleKeyPressed(evt);
            }
        });
        panaddinguser.add(useraddingrole, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 320, 410, 40));

        jLabel22.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel22.setForeground(java.awt.Color.white);
        jLabel22.setText("Sex");
        panaddinguser.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, 290, 30));

        useraddingsex1.setBackground(new java.awt.Color(166, 121, 121));
        useraddingsex1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddingsex1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select sex", "Female", "Male", "Rather not say" }));
        useraddingsex1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingsex1KeyPressed(evt);
            }
        });
        panaddinguser.add(useraddingsex1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 400, 40));

        jLabel23.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel23.setForeground(java.awt.Color.white);
        jLabel23.setText("Phone");
        panaddinguser.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 430, 290, 30));

        useraddingfon.setBackground(new java.awt.Color(166, 121, 121));
        useraddingfon.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddingfon.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                useraddingfonKeyTyped(evt);
            }
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingfonKeyPressed(evt);
            }
        });
        panaddinguser.add(useraddingfon, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 460, 400, 40));

        jSeparator6.setForeground(java.awt.Color.cyan);
        jSeparator6.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panaddinguser.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 150, 40, 360));

        useraddinglname.setBackground(new java.awt.Color(166, 121, 121));
        useraddinglname.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddinglname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddinglnameKeyPressed(evt);
            }
        });
        panaddinguser.add(useraddinglname, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 180, 410, 40));

        jLabel24.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel24.setForeground(java.awt.Color.white);
        jLabel24.setText("Last name");
        panaddinguser.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 150, 310, 30));

        jLabel25.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel25.setForeground(java.awt.Color.white);
        jLabel25.setText("Create password");
        panaddinguser.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 220, 310, 30));

        useraddingpass1.setBackground(new java.awt.Color(166, 121, 121));
        useraddingpass1.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        useraddingpass1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                useraddingpass1KeyPressed(evt);
            }
        });
        panaddinguser.add(useraddingpass1, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 250, 410, 40));

        jLabel26.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        jLabel26.setForeground(java.awt.Color.white);
        jLabel26.setText("Comfirm password");
        panaddinguser.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, 310, 30));

        addinguserpass2.setBackground(new java.awt.Color(166, 121, 121));
        addinguserpass2.setFont(new java.awt.Font("Bitstream Vera Sans", 0, 18)); // NOI18N
        addinguserpass2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addinguserpass2ActionPerformed(evt);
            }
        });
        addinguserpass2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                addinguserpass2KeyPressed(evt);
            }
        });
        panaddinguser.add(addinguserpass2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 320, 400, 40));

        cancel.setFont(new java.awt.Font("Ubuntu", 0, 19)); // NOI18N
        cancel.setForeground(java.awt.Color.red);
        cancel.setText("Cancel");
        cancel.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        cancel.setContentAreaFilled(false);
        cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelActionPerformed(evt);
            }
        });
        panaddinguser.add(cancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 450, 150, 40));

        btnadd.setBackground(java.awt.Color.green);
        btnadd.setFont(new java.awt.Font("Ubuntu", 0, 19)); // NOI18N
        btnadd.setForeground(java.awt.Color.green);
        btnadd.setText("Add+");
        btnadd.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        btnadd.setContentAreaFilled(false);
        btnadd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnaddActionPerformed(evt);
            }
        });
        panaddinguser.add(btnadd, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 450, 150, 40));

        jLabel2.setForeground(java.awt.Color.orange);
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(" above is final, WE ADVISE YOU TO FIRST CHECK THROUGH");
        jLabel2.setBorder(null);
        panaddinguser.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 410, 450, 40));

        jLabel4.setForeground(java.awt.Color.orange);
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("By clicking ADD below, you comfirm that what you have entered");
        jLabel4.setBorder(null);
        panaddinguser.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 370, 450, 40));

        panparent.add(panaddinguser, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        panaaboutsaloon.setkStartColor(java.awt.Color.black);
        panaaboutsaloon.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        panaaboutsaloon.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 290, -1));

        jLabel11.setFont(new java.awt.Font("Cantarell", 0, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(134, 203, 227));
        jLabel11.setText("ADD THIS SALOON's INFO");
        panaaboutsaloon.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 150, 250, 30));

        lblerraboutsaloon.setFont(new java.awt.Font("Cantarell", 0, 16)); // NOI18N
        lblerraboutsaloon.setForeground(new java.awt.Color(89, 200, 237));
        panaaboutsaloon.add(lblerraboutsaloon, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 200, 380, 30));

        jLabel10.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(240, 243, 243));
        jLabel10.setText("Saloon name");
        panaaboutsaloon.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 120, 30));

        salname1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                salname1KeyPressed(evt);
            }
        });
        panaaboutsaloon.add(salname1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 370, 40));

        jLabel12.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(240, 243, 243));
        jLabel12.setText("Location");
        panaaboutsaloon.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 120, 30));

        saloonloc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                saloonlocKeyPressed(evt);
            }
        });
        panaaboutsaloon.add(saloonloc, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 340, 370, 40));

        jLabel13.setFont(new java.awt.Font("Cantarell", 0, 18)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(240, 243, 243));
        jLabel13.setText("Official phone");
        panaaboutsaloon.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 190, 30));

        saloonpho.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                saloonphoKeyTyped(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                saloonphoKeyReleased(evt);
            }
        });
        panaaboutsaloon.add(saloonpho, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 370, 40));

        btncancelsaloon.setForeground(java.awt.Color.red);
        btncancelsaloon.setText("Cancel");
        btncancelsaloon.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.red));
        btncancelsaloon.setContentAreaFilled(false);
        btncancelsaloon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelsaloonActionPerformed(evt);
            }
        });
        panaaboutsaloon.add(btncancelsaloon, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 460, 90, 30));

        saloonbtnedit.setBackground(java.awt.Color.green);
        saloonbtnedit.setForeground(java.awt.Color.white);
        saloonbtnedit.setText("Edit");
        saloonbtnedit.setBorder(javax.swing.BorderFactory.createLineBorder(java.awt.Color.green));
        saloonbtnedit.setContentAreaFilled(false);
        saloonbtnedit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saloonbtneditActionPerformed(evt);
            }
        });
        panaaboutsaloon.add(saloonbtnedit, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 460, 90, 30));

        name1.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        name1.setForeground(new java.awt.Color(81, 204, 245));
        name1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        name1.setText("SALOON ONE");
        panaaboutsaloon.add(name1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 60, 840, 50));

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/naming.png"))); // NOI18N
        panaaboutsaloon.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 130, 50));

        jLabel16.setFont(new java.awt.Font("Noto Sans Mono CJK KR Bold", 0, 36)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(127, 31, 43));
        jLabel16.setIcon(new javax.swing.ImageIcon(getClass().getResource("/location.png"))); // NOI18N
        jLabel16.setText("Location");
        panaaboutsaloon.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 150, 270, 50));

        loclbl2.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        loclbl2.setForeground(java.awt.Color.white);
        loclbl2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loclbl2.setText("KAKYEEKA");
        panaaboutsaloon.add(loclbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 210, 530, 50));

        jLabel15.setFont(new java.awt.Font("Abyssinica SIL", 0, 24)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(236, 227, 19));
        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-office-phone-64.png"))); // NOI18N
        jLabel15.setText("Official phone");
        panaaboutsaloon.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 290, 270, 60));

        phonlbl2.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        phonlbl2.setForeground(java.awt.Color.white);
        phonlbl2.setText("0781109107");
        panaaboutsaloon.add(phonlbl2, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 340, 240, 50));

        jLabel17.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(240, 233, 68));
        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        jLabel17.setText("ADMINS");
        panaaboutsaloon.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 420, 210, 50));

        lbladmnis.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lbladmnis.setForeground(java.awt.Color.white);
        lbladmnis.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbladmnis.setText("0");
        panaaboutsaloon.add(lbladmnis, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 460, 200, 60));

        jSeparator3.setBackground(new java.awt.Color(49, 192, 196));
        jSeparator3.setForeground(new java.awt.Color(65, 185, 225));
        jSeparator3.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panaaboutsaloon.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 430, 40, 80));

        jLabel18.setFont(new java.awt.Font("STIXGeneral", 0, 30)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(240, 233, 68));
        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/admin.png"))); // NOI18N
        jLabel18.setText("WORKERS");
        panaaboutsaloon.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 420, 210, 50));

        lblwork.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        lblwork.setForeground(java.awt.Color.white);
        lblwork.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblwork.setText("0");
        panaaboutsaloon.add(lblwork, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 450, 200, 70));
        panaaboutsaloon.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 290, 30));

        jSeparator5.setOrientation(javax.swing.SwingConstants.VERTICAL);
        panaaboutsaloon.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 140, 30, 380));

        panparent.add(panaaboutsaloon, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 970, 540));

        panhome.setkStartColor(java.awt.Color.black);
        panhome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        name3.setFont(new java.awt.Font("Cantarell", 0, 36)); // NOI18N
        name3.setForeground(new java.awt.Color(81, 204, 245));
        name3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        name3.setText("Welcome here");
        panhome.add(name3, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 50, 590, 110));

        jLabel38.setFont(new java.awt.Font("Noto Sans Mono CJK KR Bold", 0, 36)); // NOI18N
        jLabel38.setForeground(new java.awt.Color(127, 31, 43));
        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/location.png"))); // NOI18N
        jLabel38.setText("Location");
        panhome.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 180, 270, 100));

        loclbl1.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        loclbl1.setForeground(java.awt.Color.white);
        loclbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        loclbl1.setText("KAKYEEKA");
        panhome.add(loclbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 240, 560, 70));

        jLabel39.setFont(new java.awt.Font("Abyssinica SIL", 0, 24)); // NOI18N
        jLabel39.setForeground(new java.awt.Color(236, 227, 19));
        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icons8-office-phone-64.png"))); // NOI18N
        jLabel39.setText("Official phone");
        panhome.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 270, 280, 110));

        phonlbl1.setFont(new java.awt.Font("DejaVu Serif", 1, 30)); // NOI18N
        phonlbl1.setForeground(java.awt.Color.white);
        phonlbl1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        phonlbl1.setText("0781109107");
        panhome.add(phonlbl1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 350, 590, 50));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/saloonhome1.jpg"))); // NOI18N
        panhome.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 370, 540));

        jLabel3.setForeground(java.awt.Color.gray);
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Designed by Tumusiime Ezra Junior");
        panhome.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 448, 580, 30));
        panhome.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 440, 550, 20));

        panparent.add(panhome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 980, 490));

        getContentPane().add(panparent, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 540));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        panparent.setVisible(true);
        panhome.setVisible(true);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panaddingstyles.setVisible(false);
        panreports.setVisible(false);
        panadddefault.setVisible(false);
        
        panaaboutsaloon.setVisible(false);
        panadddefault.setVisible(false);
        panviewingall.setVisible(false);
        fetchingsalooninfo();
        gettingadmins();
        addingworkers();
       
        countingsallcustomers();
        countingsallusers();
    }//GEN-LAST:event_formWindowOpened

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        panparent.setVisible(true);
        panreports.setVisible(false);
        //panadddefault.setVisible(false);
        panhome.setVisible(false);
        panadddefault.setVisible(true);
        panviewingall.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void gohomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gohomeMouseClicked
        // TODO add your handling code here:
        panparent.setVisible(true);
        panhome.setVisible(true);
        pantitles.setVisible(true);
        panadddefault.setVisible(false);
        panviewingall.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_gohomeMouseClicked

    private void addaboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addaboutMouseClicked
        // TODO add your handling code here:
        panreports.setVisible(false);
        panadddefault.setVisible(false);
        panparent.setVisible(true);
        panhome.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        // panviews.setVisible(false);
        //panaddstyles.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panaaboutsaloon.setVisible(true);
        //panadddefault.setVisible(false);
        panviewingallcustomers.setVisible(false);
        gettingadmins();
        addingworkers();
    }//GEN-LAST:event_addaboutMouseClicked

    private void addmembersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addmembersMouseClicked
        // TODO add your handling code here:
        panreports.setVisible(false);
        panparent.setVisible(true);
        panhome.setVisible(false);
        panaddinguser.setVisible(true);
        panaddingcustomers.setVisible(false);
        // panviews.setVisible(false);
        //panaddstyles.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panviewingall.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panadddefault.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_addmembersMouseClicked

    private void addcustomersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addcustomersMouseClicked
        // TODO add your handling code here:

        panparent.setVisible(true);
        panhome.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(true);
        //selectionstylenames();
        // panviews.setVisible(false);
        panaddingstyles.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panviewingall.setVisible(false);
        panreports.setVisible(false);
        panadddefault.setVisible(false);
        panviewingallcustomers.setVisible(false);
        panaddingdiscounts.setVisible(false);
    }//GEN-LAST:event_addcustomersMouseClicked

    private void jLabel7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel7MouseClicked
        panreports.setVisible(false);
        panparent.setVisible(true);
        panhome.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        // panviews.setVisible(false);
        panaddingstyles.setVisible(true);
        panaaboutsaloon.setVisible(false);
        panviewingall.setVisible(false);
        panadddefault.setVisible(false);
        panviewingallcustomers.setVisible(false);
        panaddingdiscounts.setVisible(false);

    }//GEN-LAST:event_jLabel7MouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        // TODO add your handling code here:
        int resp = JOptionPane.showConfirmDialog(this, "Are you sure you want to logout?", "CONFIRM", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
        if (resp == JOptionPane.NO_OPTION) {

        } else {
            login lg = new login();
            this.dispose();
            lg.setVisible(true);
            lg.setLocationRelativeTo(null);
        }
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel27MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel27MouseClicked
        panreports.setVisible(false);
        panparent.setVisible(true);
        panhome.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panviewingstyles.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panadddefault.setVisible(false);
        panviewdefs.setVisible(false);

        pantitles.setVisible(false);

        panaaboutsaloon.setVisible(false);
        panviewingall.setVisible(true);
        panviewingallcustomers.setVisible(false);
        addingstyles();
        countingsallcustomers();
        fetchingcustomers();
        fetchingsalooninfo();
        fetchingusers();
        countingdiscounts();
        panviewingalldiscounts.setVisible(false);
        //panviewingall.setVisible(true);
        panviewusers.setVisible(true);
        //gettingadmins();
        //addingworkers();
        //selectionstylenames();
        countingsallcustomers();
    }//GEN-LAST:event_jLabel27MouseClicked

    private void salname1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_salname1KeyPressed
        // TODO add your handling code here:
        lblerraboutsaloon.setText("");
    }//GEN-LAST:event_salname1KeyPressed

    private void saloonlocKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saloonlocKeyPressed
        lblerraboutsaloon.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_saloonlocKeyPressed

    private void saloonphoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saloonphoKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_saloonphoKeyTyped

    private void saloonphoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_saloonphoKeyReleased
        lblerraboutsaloon.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_saloonphoKeyReleased

    private void saloonbtneditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saloonbtneditActionPerformed
        String s1 = salname1.getText().toUpperCase();
        String s2 = saloonloc.getText().toUpperCase();
        String s3 = saloonpho.getText().trim();

        if (s1.isEmpty() && s2.isEmpty() && s3.isEmpty()) {

            lblerraboutsaloon.setText("Fields are still empty");
            salname1.requestFocus();
            return;
        } else if (s1.isEmpty()) {
            lblerraboutsaloon.setText("Saloon has no name yet");
            salname1.requestFocus();
            return;
        } else if (s2.isEmpty()) {
            lblerraboutsaloon.setText("The location is not defined yet");
            saloonloc.requestFocus();
            return;
        } else if (s3.isEmpty()) {
            lblerraboutsaloon.setText("Phone is still empty");
            saloonpho.requestFocus();
            return;
        } else if ((s3.charAt(0) != 0) && s3.length() != 10) {
            lblerraboutsaloon.setText("The phone number is invalid please");
            saloonpho.requestFocus();
            return;
        } else {

            try {
                // TODO add your handling code here:

                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);

                st = con.createStatement();

                String query1 = "Select * from saloon";

                rs = st.executeQuery(query1);
                if (rs.first()) {

                    String query2 = "UPDATE saloon SET saloonname = '" + s1 + "',location = '" + s2 + "',phone = '" + s3 + "' where id = 1";
                    Statement stat1 = con.createStatement();
                    stat1.executeUpdate(query2);
                    lblerraboutsaloon.setForeground(Color.green);
                    lblerraboutsaloon.setText("Edit successfull");
                    salname1.setText("");
                    saloonloc.setText("");
                    saloonpho.setText("");

                } else {
                    String query3 = "INSERT into saloon (location,phone,saloonname) VALUES ('" + s2 + "','" + s3 + "','" + s1 + "')";
                    Statement stat2 = con.createStatement();
                    stat2.executeUpdate(query3);
                    lblerraboutsaloon.setForeground(Color.green);
                    lblerraboutsaloon.setText("Saloon info added successfully");
                    salname1.setText("");
                    saloonloc.setText("");
                    saloonpho.setText("");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        fetchingsalooninfo();
        //fetch1();
    }//GEN-LAST:event_saloonbtneditActionPerformed

    private void btncancelsaloonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelsaloonActionPerformed
        // TODO add your handling code here:
        salname1.setText("");
        saloonloc.setText("");
        saloonpho.setText("");
        lblerraboutsaloon.setText("");
    }//GEN-LAST:event_btncancelsaloonActionPerformed

    private void useraddingfnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingfnameKeyPressed
        // TODO add your handling code here:
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_useraddingfnameKeyPressed

    private void useraddingunameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingunameKeyPressed
        // TODO add your handling code here:
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_useraddingunameKeyPressed

    private void useraddingroleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_useraddingroleActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_useraddingroleActionPerformed

    private void useraddingroleKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingroleKeyPressed
        // TODO add your handling code here:
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_useraddingroleKeyPressed

    private void useraddingsex1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingsex1KeyPressed
        lblerraddiinguser.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_useraddingsex1KeyPressed

    private void useraddingfonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingfonKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_useraddingfonKeyTyped

    private void useraddingfonKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingfonKeyPressed
        lblerraddiinguser.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_useraddingfonKeyPressed

    private void useraddinglnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddinglnameKeyPressed
        // TODO add your handling code here:
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_useraddinglnameKeyPressed

    private void useraddingpass1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_useraddingpass1KeyPressed
        // TODO add your handling code here:
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_useraddingpass1KeyPressed

    private void addinguserpass2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addinguserpass2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addinguserpass2ActionPerformed

    private void addinguserpass2KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_addinguserpass2KeyPressed

        lblerraddiinguser.setText("");        // TODO add your handling code here:
    }//GEN-LAST:event_addinguserpass2KeyPressed

    private void cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelActionPerformed
        // TODO add your handling code here:
        useraddingfname.setText("");
        useraddinglname.setText("");
        useraddinguname.setText("");
        useraddingpass1.setText("");
        useraddingrole.setSelectedItem("Select role");
        useraddingsex1.setSelectedItem("Select sex");
        addinguserpass2.setText("");
        useraddingfon.setText("");
        lblerraddiinguser.setText("");
    }//GEN-LAST:event_cancelActionPerformed

    private void btnaddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnaddActionPerformed
        // TODO add your handling code here:
        String name1 = useraddingfname.getText().toUpperCase();
        String name2 = useraddinglname.getText().toUpperCase();
        String name3 = useraddinguname.getText();
        String fon1 = useraddingfon.getText().trim();
        String sex = useraddingsex1.getSelectedItem().toString();
        String rol = useraddingrole.getSelectedItem().toString();
        String p1 = useraddingpass1.getText();
        String p2 = addinguserpass2.getText();
        lblerraddiinguser.setForeground(Color.cyan);
        if (name1.isEmpty() && name2.isEmpty() && name3.isEmpty() && fon1.isEmpty() && sex.equals("Select sex") && rol.equals("Select role") && p1.isEmpty() && p2.isEmpty()) {
            lblerraddiinguser.setText("Make sure every field is filled please");
        } else if (name1.isEmpty()) {
            lblerraddiinguser.setText("First name required please");
        } else if (name2.isEmpty()) {
            lblerraddiinguser.setText("Last name required please");
        } else if (name3.isEmpty()) {
            lblerraddiinguser.setText("Please create username, that's what you will login with ");
        } else if (p1.isEmpty() || p1.length() < 7) {
            lblerraddiinguser.setText("Please create a strong password");
        } else if (rol.equals("Select role")) {
            lblerraddiinguser.setText("You have not selected the role yet!");
        } else if (!p2.matches(p1)) {
            lblerraddiinguser.setText("The two passwords are not matching");
        } else if (sex.equals("Select sex")) {
            lblerraddiinguser.setText("You have no sex!?");
        } else if (fon1.indexOf(0) != 0 && fon1.indexOf(7) != 1 && fon1.length() != 10) {
            lblerraddiinguser.setText("Please insert a valid phone number");
        } else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver"); //registers drivers we configured
                con = DriverManager.getConnection(cs, user, password);
                st = con.createStatement();
                query = "INSERT INTO user(uname,password,gender,phone,fname,lname,rolename)  VALUES('" + name3 + "', '" + p2 + "','" + sex + "','" + fon1 + "','" + name1 + "','" + name2 + "','" + rol + "')";
                st.executeUpdate(query);//executes the activities
                lblerraddiinguser.setForeground(Color.green);
                lblerraddiinguser.setText("New " + rol + ", " + name3 + " has been registered successfully ");
                useraddingfname.setText("");
                useraddinglname.setText("");
                useraddinguname.setText("");
                useraddingpass1.setText("");
                useraddingrole.setSelectedItem("Select role");
                useraddingsex1.setSelectedItem("Select sex");
                addinguserpass2.setText("");
                useraddingfon.setText("");

            } catch (ClassNotFoundException ex) {
            } catch (SQLException ex) {
            }

        }
    }//GEN-LAST:event_btnaddActionPerformed

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

        String ph2 = "^[a-zA-Z0-9]{1,15}[@][a-zA-Z]{1,10}[.][a-zA-Z]{2,3}$";
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

    private void custfiraddingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_custfiraddingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_custfiraddingActionPerformed

    private void userbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_userbtnActionPerformed
        // TODO add your handling code here:
        countingsallusers();
        gettingadmins();
        addingworkers();
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
        panviewusers.setVisible(true);
        panviewingstyles.setVisible(false);
        panviewingallcustomers.setVisible(false);
        pantitles.setVisible(true);
        panviewdefs.setVisible(false);
    }//GEN-LAST:event_userbtnActionPerformed

    private void dicbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dicbtnMouseClicked
        // TODO add your handling code here:
        countingdiscounts();
        btndefault.setBackground(Color.black);
        dicbtn.setBackground(Color.gray);
        userbtn.setBackground(Color.black);
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.black);
    }//GEN-LAST:event_dicbtnMouseClicked

    private void dicbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dicbtnActionPerformed
        // TODO add your handling code here:
        pantitles.setVisible(true);
        countingdiscounts();
        panviewdefs.setVisible(false);
        panviewingalldiscounts.setVisible(true);
        panviewingall.setVisible(true);
        panviewusers.setVisible(false);
        panviewingstyles.setVisible(false);
        panviewingallcustomers.setVisible(false);
        fetchingdiscounts();
    }//GEN-LAST:event_dicbtnActionPerformed

    private void userbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseClicked
        // TODO add your handling code here:
        btndefault.setBackground(Color.black);
        dicbtn.setBackground(Color.black);
        userbtn.setBackground(Color.gray);
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.black);
    }//GEN-LAST:event_userbtnMouseClicked

    private void userbtnMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userbtnMouseEntered
        // TODO add your handling code here:

    }//GEN-LAST:event_userbtnMouseEntered

    private void stylebtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_stylebtnMouseClicked
        // TODO add your handling code here:
        dicbtn.setBackground(Color.black);
        userbtn.setBackground(Color.black);
        stylebtn.setBackground(Color.gray);
        customerbtn.setBackground(Color.black);
        btndefault.setBackground(Color.black);
    }//GEN-LAST:event_stylebtnMouseClicked

    private void customerbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_customerbtnMouseClicked
        // TODO add your handling code here:
        dicbtn.setBackground(Color.black);
        userbtn.setBackground(Color.black);
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.gray);
        btndefault.setBackground(Color.black);
    }//GEN-LAST:event_customerbtnMouseClicked

    private void deletingcustomerlblMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletingcustomerlblMouseClicked

        int row = tabdiscount.getSelectedRow();
        String cell = tabdiscount.getModel().getValueAt(row, 0).toString();
        //System.out.println(cell);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String sql1 = "delete from discount  where  discid= '" + cell + "'";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            lblerrdiscounts.setText("Discount " + cell + " has araedy been removed succeefully");
            countingdiscounts();
            fetchingdiscounts();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_deletingcustomerlblMouseClicked

    private void labdeleteusersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labdeleteusersMouseClicked
        // TODO add your handling code here:

        int row = tabviewallusers.getSelectedRow();
        String cell = tabviewallusers.getModel().getValueAt(row, 0).toString();
        //System.out.println(cell);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String sql1 = "delete from user  where  fname= '" + cell + "'";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            lblerrusers.setText("" + cell + " has  been removed succeefully");
            countingsallusers();
            fetchingusers();
            gettingadmins();
            addingworkers();
        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_labdeleteusersMouseClicked

    private void stylebtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stylebtnActionPerformed
        // TODO add your handling code here:
        panviewingstyles.setVisible(true);
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
        panviewusers.setVisible(false);
        panviewingallcustomers.setVisible(false);
        pantitles.setVisible(true);
        panviewdefs.setVisible(false);
        addingstyles();
        fetchingstyles();
    }//GEN-LAST:event_stylebtnActionPerformed

    private void lbldeletiingstyleMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbldeletiingstyleMouseClicked
        // TODO add your handling code here:
        int row = tabviewstyle.getSelectedRow();
        String cell = tabviewstyle.getModel().getValueAt(row, 0).toString();

        try {
            String sql = "delete from styles where stylename = '" + cell + "'";
            statement = con.prepareStatement(sql);
            statement.executeUpdate();
            lblerrstyles.setText("" + cell + " has been removed succeefully");
            fetchingstyles();
            addingstyles();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lbldeletiingstyleMouseClicked

    private void searchstyletxtKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchstyletxtKeyPressed
        // TODO add your handling code here:
        if (searchstyletxt.getText().equals("")) {
            fetchingstyles();
            lblerrstyles.setText("");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);
                PreparedStatement ps = con.prepareStatement("select * from styles where stylename like '%" + searchstyletxt.getText() + "%' or sex like '%" + searchstyletxt.getText() + "%' or amount like '%" + searchstyletxt.getText() + "%'");

                rs = ps.executeQuery();
                DefaultTableModel dt = (DefaultTableModel) tabviewstyle.getModel();
                dt.setRowCount(0);

                if (rs.first()) {
                    Object o[] = {rs.getString("stylename"), rs.getString("amount"), rs.getString("sex"), rs.getString("description")};
                    dt.addRow(o);
                    lblerrstyles.setText("");

                } else {
                    if (searchstyletxt.getText().isEmpty()) {
                        fetchingstyles();
                        addingstyles();
                        lblerrstyles.setText("");
                    } else {
                        lblerrstyles.setText("No matching record found");
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_searchstyletxtKeyPressed

    private void customerbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerbtnActionPerformed
        // TODO add your handling code here:
        pantitles.setVisible(true);
        panviewingallcustomers.setVisible(true);
        panviewingstyles.setVisible(false);
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
        panviewusers.setVisible(false);
        panviewdefs.setVisible(false);

    }//GEN-LAST:event_customerbtnActionPerformed

    private void gohome1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_gohome1MouseClicked
        // TODO add your handling code here:
        panaddingdiscounts.setVisible(false);

        panreports.setVisible(false);
        panadddefault.setVisible(false);
        panparent.setVisible(true);
        panhome.setVisible(true);
        panviewingall.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        // panviews.setVisible(false);
        //panaddstyles.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_gohome1MouseClicked

    private void searchcustomersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchcustomersActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchcustomersActionPerformed

    private void searchcustomersKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchcustomersKeyTyped
        // TODO add your handling code here:

        if (searchcustomers.getText().equals("")) {
            fetchingcustomers();
            lblerrviewingcustomers.setText("");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);
                PreparedStatement ps = con.prepareStatement("select * from customer where fname like '%" + searchcustomers.getText() + "%' or lname like '%" + searchcustomers.getText() + "%' or address like '%" + searchcustomers.getText() + "%'");

                rs = ps.executeQuery();
                DefaultTableModel dt = (DefaultTableModel) tabcustomerview.getModel();
                dt.setRowCount(0);

                if (rs.first()) {
                    Object o[] = {rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("sex"), rs.getString("style"), rs.getString("phone"), rs.getString("address")};
                    dt.addRow(o);
                    lblerrviewingcustomers.setText("");

                } else {
                    if (searchcustomers.getText().isEmpty()) {
                        fetchingcustomers();
                        countingsallcustomers();
                        lblerrviewingcustomers.setText("");
                    } else {
                        lblerrviewingcustomers.setText("No matching record found");
                    }
                }

            } catch (Exception ex) {
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
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String sql1 = "delete from customer  where custid = '" + cell + "' LIMIT 1";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            fetchingcustomers();
            countingsallcustomers();
            lblerrviewingcustomers.setText(" Customer  " + cell + " has been removed succeefully");

        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_jLabel32MouseClicked

    private void styledescKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_styledescKeyPressed
        // TODO add your handling code here:
        lblerr.setText("");
    }//GEN-LAST:event_styledescKeyPressed

    private void styleamtchargedKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_styleamtchargedKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
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

        if (sname.isEmpty() && sex2.equals("Select sex") && d.isEmpty()) {
            lblerr.setText("Hey! no field is filled yet");
            stlname.requestFocus();
        } else if (sname.isEmpty()) {
            lblerr.setText("The style has no name yet, baptize it!");
            stlname.requestFocus();
        } else if (am.length() < 3 || am.isEmpty()) {
            lblerr.setText("Amount is either empty or invalid in ugx");
            styleamtcharged.requestFocus();
        } else if (d.isEmpty()) {
            lblerr.setText("No description given yet");
            styledesc.requestFocus();

        } else if (d.length() > 200) {
            lblerr.setText("Description should not be more than 200 characters");
            styledesc.requestFocus();
        } else {

            try {
                Class.forName("com.mysql.jdbc.Driver"); //registers drivers we configured
                con = DriverManager.getConnection(cs, user, password);
                st = con.createStatement();

                query = "INSERT INTO styles(stylename,amount,sex,description)  VALUES('" + sname + "', '" + am + "','" + sex2 + "','" + d + "')";
                int resp = JOptionPane.showConfirmDialog(this, "Is whatever you entered final? click no to edit ", "CONFIRM THIS SUBMISSION", JOptionPane.YES_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (resp == JOptionPane.NO_OPTION) {

                } else {
                    st.executeUpdate(query);//executes the activities
                    lblerr.setBackground(Color.green);
                    lblerr.setText("New style " + sname + " has been added successfully");
                    stlname.setText("");
                    stylesexselection.setSelectedItem("Select sex");
                    styledesc.setText("");
                    styleamtcharged.setText("");

                }
            } catch (ClassNotFoundException ex) {
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

    private void stydiscounttxtActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stydiscounttxtActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_stydiscounttxtActionPerformed

    private void discnewamtKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discnewamtKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_discnewamtKeyTyped

    private void cancelbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cancelbtnMouseClicked
        // TODO add your handling code here:

        stydiscounttxt.setSelectedItem("Select style");
        lblerrdiscount.setText("");
        discnewamt.setText("");
        discdatefro.setDate(Date.valueOf(LocalDate.MIN));
        discdateto.setDate(Date.valueOf(LocalDate.MIN));

    }//GEN-LAST:event_cancelbtnMouseClicked

    private void adddicountbtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_adddicountbtnMouseClicked
        // TODO add your handling code here:

        String discn2 = discnewamt.getText();
        String discs2 = stydiscounttxt.getSelectedItem().toString();
       
        
        lblerrdiscount.setForeground(Color.red);
            
        
         DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar calobj = Calendar.getInstance(discdatefro.getLocale());
        String date1 = df.format(calobj.getTime());
       
        Calendar calobj2 = Calendar.getInstance(discdateto.getLocale());
        String date2 = df.format(calobj2.getTime());
        
        //validations
        
         if(date2.isEmpty() && date2.equals(Date.valueOf(LocalDate.MIN)) && discn2.isEmpty() && discs2.equals("Select style") && date2.isEmpty() && date2.equals(Date.valueOf(LocalDate.MIN))){ 
         lblerrdiscount.setText("Oops! no field filled yet"); 
         stydiscounttxt.requestFocus(); 
         return;
        }
         
        if (discs2.equals("Select style")) {
            lblerrdiscount.setText("Oops! No style is chosen yet");
            stydiscounttxt.requestFocus();
            return;
        } else if (discn2.isEmpty()) {
            lblerrdiscount.setText("Oops!that amount is still empty");
            discnewamt.requestFocus();
            return;
        } else if (date1.equals("") && date1.equals(Date.valueOf(LocalDate.MIN))) {
            lblerrdiscount.setText("Oops! date fields still empty");
            discdatefro.requestFocus();
            return;
        } else if (date2.equals("") && date2.equals(Date.valueOf(LocalDate.MIN))) {
            lblerrdiscount.setText("Oops! date fields still empty");
            discdateto.requestFocus();
            return;
        } else {

            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);

                st = con.createStatement();

                query = "INSERT INTO discount(datefrom ,dateto ,amt2,stylename) VALUES('" + date1 + "', '" + date2 + "','" + discn2 + "','" + discs2 + "')";
                st.executeUpdate(query);

                lblerrdiscount.setForeground(Color.green);
                lblerrdiscount.setText("New discount on " + discs2 + " is now running");
                stydiscounttxt.setSelectedItem("Select style");
                  discdatefro.setDate(Date.valueOf(LocalDate.MIN));
                  discdateto.setDate(Date.valueOf(LocalDate.MIN));
                discnewamt.setText("");

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_adddicountbtnMouseClicked

    private void adddicountbtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_adddicountbtnKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_adddicountbtnKeyPressed

    private void searchallusersKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchallusersKeyPressed

        if (searchallusers.getText().equals("")) {
            fetchingusers();
            lblerrusers.setText("");
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);
                PreparedStatement ps = con.prepareStatement("select * from customer where fname like '%" + searchallusers.getText() + "%' or lname like '%" + searchallusers.getText() + "%' or address like '%" + searchallusers.getText() + "%'");

                rs = ps.executeQuery();
                DefaultTableModel dt = (DefaultTableModel) tabviewallusers.getModel();
                dt.setRowCount(0);

                if (rs.first()) {
                    Object o[] = {rs.getString("fname"), rs.getString("lname"), rs.getString("email"), rs.getString("sex"), rs.getString("style"), rs.getString("phone"), rs.getString("address")};
                    dt.addRow(o);
                    lblerrusers.setText("");

                } else {
                    if (searchallusers.getText().isEmpty()) {
                        fetchingusers();
                        countingsallusers();
                        gettingadmins();
                        addingworkers();
                        lblerrusers.setText("");
                    } else {
                        lblerrusers.setText("No matching record found");
                    }
                }

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }


    }//GEN-LAST:event_searchallusersKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed

        String path1 = "workers1.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                String query12 = "select count(*) AS count1 from user where rolename ='Worker'";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL WORKERS:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from user where rolename = 'Worker'";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(5);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("First name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Last name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" Username:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph(" Gender:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                        PdfPCell ro = new PdfPCell(new Paragraph(" Role:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro);
                    while (rs.next()) {
                       
                        table1.addCell(rs.getString("fname"));

                        
                        table1.addCell(rs.getString("lname"));

                        
                        table1.addCell(rs.getString("uname"));

                       
                        table1.addCell(rs.getString("gender"));

                        
                        table1.addCell(rs.getString("rolename"));

                       
                    }
                     document.add(table1);
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }


    }//GEN-LAST:event_jButton2ActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        // TODO add your handling code here:
        panreports.setVisible(true);
        panparent.setVisible(true);
        panadddefault.setVisible(false);
        panhome.setVisible(false);
        panviewingall.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        // panviews.setVisible(false);
        //panaddstyles.setVisible(false);
        panaaboutsaloon.setVisible(false);
        panaddingdiscounts.setVisible(false);
        panviewingallcustomers.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel75MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel75MouseClicked
        // TODO add your handling code here:
        panadddefault.setVisible(false);
        panreports.setVisible(false);
        // TODO add your handling code here:
        //panadddiscount.setVisible(true);
        //panaddstyles.setVisible(true);
        //panviews.setVisible(false);
        //panaddusers.setVisible(false);
        // TODO add your handling code here:
        panparent.setVisible(true);
        panhome.setVisible(false);
        panviewingall.setVisible(false);
        panaddinguser.setVisible(false);
        panaddingcustomers.setVisible(false);
        panaddingstyles.setVisible(false);
        panaddingdiscounts.setVisible(true);
        panaaboutsaloon.setVisible(false);
        panviewingallcustomers.setVisible(false);

    }//GEN-LAST:event_jLabel75MouseClicked

    private void btndefaultMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btndefaultMouseClicked
        // TODO add your handling code here:
        dicbtn.setBackground(Color.black);
        userbtn.setBackground(Color.black);
        stylebtn.setBackground(Color.black);
        customerbtn.setBackground(Color.black);
        btndefault.setBackground(Color.gray);
    }//GEN-LAST:event_btndefaultMouseClicked

    private void btndefaultActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btndefaultActionPerformed
        // TODO add your handling code here:
       fetchingdebts();
       gettingtotaldebts();

        panviewdefs.setVisible(true);
        panviewingall.setVisible(true);
        pantitles.setVisible(true);
        panviewingallcustomers.setVisible(false);
        panviewingstyles.setVisible(false);
        panviewingalldiscounts.setVisible(false);
        panviewingall.setVisible(true);
        panviewusers.setVisible(false);
    }//GEN-LAST:event_btndefaultActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:

        int row = tabviewdebts.getSelectedRow();
        String cell = tabviewdebts.getModel().getValueAt(row, 0).toString();
        //System.out.println(cell);
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String sql1 = "delete from customer  where defid = '" + cell + "' LIMIT 1";

            st = con.prepareStatement(sql1);
            st.executeUpdate(sql1);
            fetchingdebts();
            gettingtotaldebts();
            lblerrdebts.setText(" User  " + cell + " has been removed from defaulters succeefully");

        } catch (ClassNotFoundException ex) {
        } catch (SQLException ex) {
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void def_addActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_def_addActionPerformed
        String nam1 = def_fname.getText();
        String nam2 = def_lname.getText();
        String nam3 = def_fon.getText();
        String nam4 = def_amount.getText();
        DateFormat df = new SimpleDateFormat("dd/MM/yy");
        Calendar calobj = Calendar.getInstance(def_pay.getLocale());
        String cal1 = df.format(calobj.getTime());

        if (nam1.isEmpty() && nam2.isEmpty() && nam3.isEmpty() && nam4.isEmpty() && cal1.isEmpty()) {
            lblerrdefaults.setText("You have not filled any fields yet");
            def_fname.requestFocus();
            return;
        } else if (nam1.isEmpty()) {
            lblerrdefaults.setText("First name is still empty");
            def_fname.requestFocus();
            return;
        } else if (nam2.isEmpty()) {
            lblerrdefaults.setText("Last name is still empty");
            def_lname.requestFocus();
            return;
        } else if (nam3.isEmpty()) {
            lblerrdefaults.setText("Phone field is still empty");
            def_fon.requestFocus();
            return;
        } else if (nam3.length() != 10) {
            lblerrdefaults.setText("Please insert a valid phone number");
            def_fon.requestFocus();
            return;
        } else if (nam4.isEmpty()) {
            lblerrdefaults.setText("Indicate the amount you owe this user");
            def_amount.requestFocus();
            return;
        } else if (nam4.length() < 3) {
            lblerrdefaults.setText("This is not valid Ugandan shillings");
            def_amount.requestFocus();
            return;
        } else if (cal1.isEmpty() ||cal1.equals(Date.valueOf(LocalDate.MIN))) {
            lblerrdefaults.setText("When do you expect this person to pay back!");
            def_pay.requestFocus();
            return;
        } else {
            try {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection(cs, user, password);
                st = con.createStatement();

                String sql3 = "insert into defaults (fname, lname,phone, date, amount) values('" + nam1 + "','" + nam2 + "','" + nam3 + "','" + cal1 + "','" + nam4 + "')";
                st.executeUpdate(sql3);
                lblerrdefaults.setForeground(Color.green);
                lblerrdefaults.setText("Defaulter added successfully");
                def_fname.setText("");
                def_lname.setText("");
                def_fon.setText("");
                def_amount.setText("");
                def_pay.setDate(Date.valueOf(LocalDate.MIN));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }//GEN-LAST:event_def_addActionPerformed

    private void def_cancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_def_cancelActionPerformed
        // TODO add your handling code here:
        lblerrdefaults.setText("");
        def_fname.setText("");
        def_lname.setText("");
        def_fon.setText("");
        def_amount.setText("");
         def_pay.setDate(Date.valueOf(LocalDate.MIN));
        

    }//GEN-LAST:event_def_cancelActionPerformed

    private void def_amountKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_def_amountKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_def_amountKeyTyped

    private void def_fonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_def_fonKeyTyped
        // TODO add your handling code here:
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            evt.consume();
        }
    }//GEN-LAST:event_def_fonKeyTyped

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
         String path1 = "styles.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                /**Paragraph para4 = new Paragraph("Issued on: " + cal1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para4.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para4);

                Paragraph para5 = new Paragraph("Issued by:," + logsname.getText().toUpperCase() + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para5.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para5);*/

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                String query12 = "select count(*) AS count1 from styles";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL STYLES:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from styles";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(4);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("Style name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Style for:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" Amount charged:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph(" Short notes:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                        
                    while (rs.next()) {
                       
                        table1.addCell(rs.getString("stylename"));

                        
                        table1.addCell(rs.getString("sex"));

                        
                        table1.addCell(rs.getString("amount"));

                       
                        table1.addCell(rs.getString("description"));

                        
                        
                       
                    }
                     document.add(table1);
                     
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);

                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
         String path1 = "admins.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                String query12 = "select count(*) AS count1 from user where rolename ='Admin'";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL ADMINISTRATORS:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from user where rolename = 'Admin'";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(5);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("First name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Last name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" Username:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph(" Gender:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                        PdfPCell ro = new PdfPCell(new Paragraph(" Role:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro);
                    while (rs.next()) {
                       
                        table1.addCell(rs.getString("fname"));

                        
                        table1.addCell(rs.getString("lname"));

                        
                        table1.addCell(rs.getString("uname"));

                       
                        table1.addCell(rs.getString("gender"));

                        
                        table1.addCell(rs.getString("rolename"));

                       
                    }
                     document.add(table1);
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        
         String path1 = "discounts.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                String query12 = "select count(*) AS count1 from discount";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL RUNNING DISCOUNTS:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from discount";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(4);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("Style name:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("New amount:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" From:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph(" To:", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                       
                    while (rs.next()) {
                       
                        table1.addCell(rs.getString("stylename"));

                        
                        table1.addCell(rs.getString("amt2"));

                        
                        table1.addCell(rs.getString("datefrom"));

                       
                        table1.addCell(rs.getString("dateto"));

                       
                    }
                     document.add(table1);
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
            }

        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        
         String path1 = "customers.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                String query12 = "select count(*) AS count1 from customer";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL CUSTOMERS:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from customer";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(7);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("First name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Last name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" Gender", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph(" Style used", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                        PdfPCell ro = new PdfPCell(new Paragraph(" Phone", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro);
                        
                         PdfPCell ro2 = new PdfPCell(new Paragraph(" Residence", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro2.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro2);
                        
                        PdfPCell ro1 = new PdfPCell(new Paragraph(" Date of visit", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro1.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro1);
                    while (rs.next()) {
                       
                        table1.addCell(rs.getString("fname"));

                        
                        table1.addCell(rs.getString("lname"));

                        table1.addCell(rs.getString("sex"));
                        
                        table1.addCell(rs.getString("style"));

                       table1.addCell(rs.getString("address"));
                        table1.addCell(rs.getString("phone"));

                        
                        table1.addCell(rs.getString("dupdated"));

                       
                    }
                     document.add(table1);
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        
         String path1 = "default.pdf";

        try {

            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(cs, user, password);
            st = con.createStatement();
            String query11 = "select * from saloon";
            rs = st.executeQuery(query11);
            if (rs.first()) {
                String saloonname1 = rs.getString("saloonname");
                String fon1 = rs.getString("phone");
                String loc1 = rs.getString("location");

                Document document = new Document(PageSize.A4);
                PdfWriter.getInstance(document, new FileOutputStream(path1));

                document.open();

                Paragraph para1 = new Paragraph(saloonname1, FontFactory.getFont(FontFactory.TIMES_BOLD, 20, Font.BOLD, BaseColor.CYAN));
                para1.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para1);

                Paragraph para0 = new Paragraph("---------------------------------------");
                para0.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para0);

                Paragraph para2 = new Paragraph("Tel: " + fon1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para2.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para2);

                Paragraph para3 = new Paragraph("Loc: " + loc1 + "", FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                para3.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para3);

                DateFormat df = new SimpleDateFormat("dd/MM/yy");
                Calendar calobj = Calendar.getInstance();
                String cal1 = df.format(calobj.getTime()).toString();

                Paragraph para01 = new Paragraph("______________________________________________________________________________");
                para01.setAlignment(Paragraph.ALIGN_CENTER);
                document.add(para01);
                
                String query12 = "select sum(Amount) AS count1 from defaults";
                rs = st.executeQuery(query12);
                if (rs.first()) {
                    String count11 = rs.getString("count1");
                    Chunk glue = new Chunk(new VerticalPositionMark());
                    Paragraph ptot = new Paragraph("REPORT SHOWING ALL DEFAULTERS:", FontFactory.getFont(FontFactory.TIMES_BOLD, 17, Font.TYPE1_FONT, BaseColor.BLUE));
                    ptot.add(new Chunk(glue));
                    ptot.add("Total Amount: " + count11 + "");
                    document.add(ptot);

                    Paragraph para02 = new Paragraph("______________________________________________________________________________");
                    para02.setAlignment(Paragraph.ALIGN_CENTER);
                    document.add(para02);

                    String query13 = "select * from defaults";
                    rs = st.executeQuery(query13);
                    
                    
                     PdfPTable table1 = new PdfPTable(5);
                        table1.setWidthPercentage(100);
                        PdfPCell fn = new PdfPCell(new Paragraph("First name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        fn.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(fn);
                        
                        PdfPCell ln = new PdfPCell(new Paragraph("Last name", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ln.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ln);
                        
                        PdfPCell un = new PdfPCell(new Paragraph(" Phone", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        un.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(un);
                        
                        PdfPCell gen = new PdfPCell(new Paragraph("Date of payment", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        gen.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(gen);
                        
                        PdfPCell ro = new PdfPCell(new Paragraph(" Amount", FontFactory.getFont(FontFactory.TIMES_BOLD, 12, Font.BOLD, BaseColor.BLUE)));
                        ro.setBackgroundColor(BaseColor.GRAY);
                        table1.addCell(ro);
                        
                        
                        while (rs.next()) {
                       
                        table1.addCell(rs.getString("fname"));

                        
                        table1.addCell(rs.getString("lname"));

                        table1.addCell(rs.getString("phone"));
                        
                        table1.addCell(rs.getString("date"));

                       table1.addCell(rs.getString("amount"));
                    
                    }
                        
                        
                     document.add(table1);
                     
                     Chunk glue1 = new Chunk(new VerticalPositionMark());
                    Paragraph ptot1 = new Paragraph("Issued by: " + logsname.getText().toUpperCase() + "",FontFactory.getFont(FontFactory.TIMES_BOLD, 15, Font.TYPE1_FONT, BaseColor.GREEN));
                    ptot1.add(new Chunk(glue1));
                    ptot1.add("Issued on: " + cal1 + "");
                    document.add(ptot1);
                }
                document.close();
            } else {
                JOptionPane.showMessageDialog(null, "Some information can't be fetched");
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DocumentException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(adminhome.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (Desktop.isDesktopSupported()) {
            try {
                File myFile = new File(path1);
                Desktop.getDesktop().open(myFile);

            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(adminhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(adminhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(adminhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(adminhome.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                adminhome adm = new adminhome();
                adm.setVisible(true);
                adm.setLocationRelativeTo(null);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel addabout;
    private javax.swing.JLabel addcustomers;
    private javax.swing.JLabel adddicountbtn;
    private javax.swing.JPasswordField addinguserpass2;
    private javax.swing.JLabel addmembers;
    private javax.swing.JButton btnadd;
    private javax.swing.JButton btnadd1;
    private javax.swing.JButton btncancel;
    private javax.swing.JButton btncancelsaloon;
    private javax.swing.JButton btndefault;
    private javax.swing.JButton cancel;
    private javax.swing.JLabel cancelbtn;
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
    private javax.swing.JButton def_add;
    private javax.swing.JTextField def_amount;
    private javax.swing.JButton def_cancel;
    private javax.swing.JTextField def_fname;
    private javax.swing.JTextField def_fon;
    private javax.swing.JTextField def_lname;
    private com.toedter.calendar.JDateChooser def_pay;
    private javax.swing.JLabel deletingcustomerlbl;
    private javax.swing.JButton dicbtn;
    private com.toedter.calendar.JDateChooser discdatefro;
    private com.toedter.calendar.JDateChooser discdateto;
    private javax.swing.JTextField discnewamt;
    private javax.swing.JLabel gohome;
    private javax.swing.JLabel gohome1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JEditorPane jEditorPane1;
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
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
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
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private keeptoo.KGradientPanel kGradientPanel3;
    private keeptoo.KGradientPanel kGradientPanel4;
    private javax.swing.JLabel labdeleteusers;
    private javax.swing.JLabel label2foraddinganother;
    private javax.swing.JLabel labreg;
    private javax.swing.JLabel lbladmnis;
    private javax.swing.JLabel lbladmnisview;
    private javax.swing.JLabel lblcounteronuser;
    private javax.swing.JLabel lblcounterstyles;
    private javax.swing.JLabel lblcounterviews;
    private javax.swing.JLabel lblcountingdebts;
    private javax.swing.JLabel lbldeletiingstyle;
    private javax.swing.JLabel lblerr;
    private javax.swing.JLabel lblerraboutsaloon;
    private javax.swing.JLabel lblerraddiinguser;
    private javax.swing.JLabel lblerrcustomers;
    private javax.swing.JLabel lblerrdebts;
    private javax.swing.JLabel lblerrdefaults;
    private javax.swing.JLabel lblerrdiscount;
    private javax.swing.JLabel lblerrdiscounts;
    private javax.swing.JLabel lblerrstyles;
    private javax.swing.JLabel lblerrusers;
    private javax.swing.JLabel lblerrviewingcustomers;
    private javax.swing.JLabel lblwork;
    private javax.swing.JLabel lblworkview;
    private javax.swing.JLabel loclbl1;
    private javax.swing.JLabel loclbl2;
    public static javax.swing.JLabel logsname;
    private javax.swing.JLabel name1;
    private javax.swing.JLabel name3;
    private keeptoo.KGradientPanel panaaboutsaloon;
    private keeptoo.KGradientPanel panadddefault;
    private keeptoo.KGradientPanel panaddingcustomers;
    private keeptoo.KGradientPanel panaddingdiscounts;
    private keeptoo.KGradientPanel panaddingstyles;
    private keeptoo.KGradientPanel panaddinguser;
    private keeptoo.KGradientPanel panhome;
    private keeptoo.KGradientPanel panparent;
    private keeptoo.KGradientPanel panreports;
    private javax.swing.JPanel pantitles;
    private keeptoo.KGradientPanel panviewdefs;
    private keeptoo.KGradientPanel panviewingall;
    private keeptoo.KGradientPanel panviewingallcustomers;
    private keeptoo.KGradientPanel panviewingalldiscounts;
    private keeptoo.KGradientPanel panviewingstyles;
    private keeptoo.KGradientPanel panviewusers;
    private javax.swing.JLabel phonlbl1;
    private javax.swing.JLabel phonlbl2;
    private javax.swing.JTextField salname1;
    private javax.swing.JButton saloonbtnedit;
    private javax.swing.JTextField saloonloc;
    private javax.swing.JTextField saloonpho;
    private javax.swing.JTextField searchallusers;
    private javax.swing.JTextField searchcustomers;
    private javax.swing.JTextField searchstyletxt;
    private javax.swing.JTextField stlname;
    private javax.swing.JComboBox<String> stydiscounttxt;
    private javax.swing.JTextField styleamtcharged;
    private javax.swing.JButton stylebtn;
    private javax.swing.JTextArea styledesc;
    private javax.swing.JComboBox<String> stylesexselection;
    private javax.swing.JTable tabcustomerview;
    private javax.swing.JTable tabdiscount;
    private javax.swing.JTable tabviewallusers;
    private javax.swing.JTable tabviewdebts;
    private javax.swing.JTable tabviewstyle;
    private javax.swing.JTextField useraddingfname;
    private javax.swing.JTextField useraddingfon;
    private javax.swing.JTextField useraddinglname;
    private javax.swing.JPasswordField useraddingpass1;
    private javax.swing.JComboBox<String> useraddingrole;
    private javax.swing.JComboBox<String> useraddingsex1;
    private javax.swing.JTextField useraddinguname;
    private javax.swing.JButton userbtn;
    // End of variables declaration//GEN-END:variables
}
