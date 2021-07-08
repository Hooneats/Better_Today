package sample;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Vector;


public class FXMLExampleController implements Initializable {


    public Service service;

    public Pane homePaneButton;
    public Pane searchPaneButton;
    public Pane newsPaneButton;
    public Pane toDoPaneButton;
    public AnchorPane mainBoard;
    public Pane loading;
    public Pane firstHome;
    public Pane secondSearch;
    public Pane thirdNews;
    public Pane errorPane;
    public ImageView loadingImageView;
    public ImageView homeImageView;
    public javafx.scene.control.TextField gTF;
    public javafx.scene.control.TextField nTF;
    public javafx.scene.control.TextField dTF;
    public TextField yTF;
    public TextField allGND;
    public ImageView googleSearchImageView;
    public ImageView naverSearchImageView;
    public ImageView daumSearchImageView;
    public ImageView youtubeSearchImageView;
    public ImageView allsearchImageView;
    public ImageView googleLofo;
    public ImageView naverLogo;
    public ImageView daumLogo;
    public ImageView youtubeLogo;

    public Label news1_num1;
    public Label news1_num2;
    public Label news1_num3;
    public Label news1_num4;
    public Label news1_num5;
    public Label news1_num6;
    public Label news1_num7;
    public Label news1_num8;
    public Label news1_num9;
    public Label news1_num10;
    public Button news1_but1;
    public Button news1_but2;
    public Button news1_but3;
    public Button news1_but4;
    public Button news1_but5;
    public Button news1_but6;
    public Button news1_but7;
    public Button news1_but8;
    public Button news1_but9;
    public Button news1_but10;
    public Label news1_com1;
    public Label news1_com2;
    public Label news1_com3;
    public Label news1_com4;
    public Label news1_com5;
    public Label news1_com6;
    public Label news1_com7;
    public Label news1_com8;
    public Label news1_com9;
    public Label news1_com10;
    public ImageView news1_image1;
    public ImageView news1_image2;
    public ImageView news1_image3;
    public ImageView news1_image4;
    public ImageView news1_image5;
    public ImageView news1_image6;
    public ImageView news1_image7;
    public ImageView news1_image8;
    public ImageView news1_image9;
    public ImageView news1_image10;

    public Label news2_num1;
    public Label news2_num2;
    public Label news2_num3;
    public Label news2_num4;
    public Label news2_num5;
    public Label news2_num6;
    public Label news2_num7;
    public Label news2_num8;
    public Label news2_num9;
    public Label news2_num10;
    public Button news2_but1;
    public Button news2_but2;
    public Button news2_but3;
    public Button news2_but4;
    public Button news2_but5;
    public Button news2_but6;
    public Button news2_but7;
    public Button news2_but8;
    public Button news2_but9;
    public Button news2_but10;
    public Label news2_com1;
    public Label news2_com2;
    public Label news2_com3;
    public Label news2_com4;
    public Label news2_com5;
    public Label news2_com6;
    public Label news2_com7;
    public Label news2_com8;
    public Label news2_com9;
    public Label news2_com10;
    public ImageView news2_image1;
    public ImageView news2_image2;
    public ImageView news2_image3;
    public ImageView news2_image4;
    public ImageView news2_image5;
    public ImageView news2_image6;
    public ImageView news2_image7;
    public ImageView news2_image8;
    public ImageView news2_image9;
    public ImageView news2_image10;

    public ListView<String> todoListView = new ListView<>();
    public ObservableList<String> loadList = FXCollections.observableArrayList();
    public ListView<String> toDoCompleteView = new ListView<>();
    public ObservableList<String> loadCompleteList = FXCollections.observableArrayList();
    public TextField addToDoTextField;
    public Pane toDoListPane;

    //    ======================================

    public void toDoSuccess(){
        if (!todoListView.getItems().isEmpty()) {
            try {
                String sel = todoListView.getSelectionModel().getSelectedItem().trim();
                Object selO = (Object) sel;
                loadList.remove(selO);
                loadCompleteList.add(sel);
            } catch (NullPointerException ne) {
//                System.out.println("잡았다 NULL");
            }
        }

    }
    public void todoCancel(){
        if (!todoListView.getItems().isEmpty()) {
            try {
                String sel = todoListView.getSelectionModel().getSelectedItem().trim();
                Object selO = (Object) sel;
                loadList.remove(selO);
            } catch (NullPointerException ne) {
//                System.out.println("잡았다 NULL");
            }
        }

    }
    public void comCancel(){
        if (!toDoCompleteView.getItems().isEmpty()) {
            try {
                String sel = toDoCompleteView.getSelectionModel().getSelectedItem().trim();
                Object selO = (Object) sel;
                loadCompleteList.remove(selO);
                loadList.add(sel);
            }catch (NullPointerException ne) {
//                System.out.println("잡았다 NULL");
            }

        }

    }
    public void toDoListClearB(){
        if (!loadList.isEmpty()) {
            loadList.clear();
        }
    }
    public void succeedListClearB(){
        if (!loadCompleteList.isEmpty()) {
            loadCompleteList.clear();
        }
    }
    public void enterToDoAdd(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            toDoAdd();
        }
    }
    public void toDoAdd(){
        if (addToDoTextField.getText().trim().length() != 0) {
            loadList.add(addToDoTextField.getText().trim());
            addToDoTextField.setText("");
        }
    }
    public void save(){
        String sql1 = "truncate table TODOLIST";
        String sql2 = "truncate table COMPLETELIST";
        String sql3 = "insert into TODOLIST values(?,?)";
        String sql4 = "insert into COMPLETELIST values(?,?)";

        try {
            stm = conn.createStatement();
            stm.execute(sql1);
            stm.execute(sql2);
//            System.out.println("trunc 완료");
            stm.close();
            PreparedStatement pstm1 = conn.prepareStatement(sql3);
            PreparedStatement pstm2 = conn.prepareStatement(sql4);
            int o = 1;
            for (String stodo : loadList) {
//                System.out.println(stodo);
                pstm1.setInt(1, o);
                pstm1.setString(2, stodo);
                int t = pstm1.executeUpdate();
//                if(t>0){System.out.println(t+"개의 row 입력 성공");}
//                else {System.out.println(t+"개의 row 입력 실패");}
                o++;
            }
            int u = 1;
            for (String sCom : loadCompleteList) {
//                System.out.println(sCom);
                pstm2.setInt(1, u);
                pstm2.setString(2, sCom);
                int w = pstm2.executeUpdate();
//                if(w>0){System.out.println(w+"개의 row 입력 성공");}
//                else {System.out.println(w+"개의 row 입력 실패");}
                u++;
            }
//            System.out.println("모두 저장 완료");
            pstm1.close();
            pstm2.close();
        } catch (SQLException e) {
            System.out.println("trunc 실패"+ e.getMessage());
            try {
                stm.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }

    }


    public void homeOnM() {
        homePaneButton.styleProperty().set("-fx-background-color: #0078FF");
    }

    public void homeOutM() {
        homePaneButton.styleProperty().set("-fx-background-color: #0064CD");
    }

    public void serachOnM() {
        searchPaneButton.styleProperty().set("-fx-background-color: #0078FF");
    }

    public void searchOutM() {
        searchPaneButton.styleProperty().set("-fx-background-color: #0064CD");
    }

    public void newsOnM() {
        newsPaneButton.styleProperty().set("-fx-background-color: #0078FF");
    }

    public void newsOutM() {
        newsPaneButton.styleProperty().set("-fx-background-color: #0064CD");
    }
    public void toDoOnM() {
        toDoPaneButton.styleProperty().set("-fx-background-color: #0078FF");
    }

    public void toDoOutM() {
        toDoPaneButton.styleProperty().set("-fx-background-color: #0064CD");
    }

    public void homeAction() {
        firstHome.toFront();
    }

    public void searchAction() {
        secondSearch.toFront();
    }

//    ===============================================================



    //        ================================================================
    public void newsAction() {
        loading.toFront();
//        System.out.println("you clecked news");
        try {
            Thread.sleep(500);
            Desktop.getDesktop().open(
                    new File("C:\\Users\\MyDev\\Desktop\\pyjava_project\\java\\pyJAVAFX0.2\\pyJAVAFX6.0\\pyJAVAFX\\src\\newsP_crawling.exe"));
            Thread.sleep(2500);

        } catch (IOException e) {
            System.out.println("파이썬 경로 확인바람");
        } catch (InterruptedException e) {
            System.out.println("쓰레드 휴식문제발생");
        }
    }

    public void toDoAction() {
        toDoListPane.toFront();
    }

    public void bringInfoNews() throws InterruptedException {
        newsLoad();

        factoryNews();
        thirdNews.toFront();
    }

    //    ==================================================================
    public void searchGoogle() {
        if (gTF.getText().length() != 0) {
            String search = gTF.getText();
            gTF.setText("");
            service.googleWeb(search);
        }
    }

    public void searchNaver() {
        if (nTF.getText().length() != 0) {
            String search = nTF.getText();
            nTF.setText("");
            service.naverWeb(search);
        }
    }

    public void searchDaum() {
        if (dTF.getText().length() != 0) {
            String search = dTF.getText();
            dTF.setText("");
            service.daumWeb(search);
        }
    }
    public void searchYoutube() {
        if (yTF.getText().length() != 0) {
            String search = yTF.getText();
            yTF.setText("");
            service.youtubeWeb(search);
        }
    }
    public void searchAll() {
        if (allGND.getText().length() != 0) {
            String search = allGND.getText();
            allGND.setText("");
            service.allWeb(search);
        }
    }
    public void enterG(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            if (gTF.getText().length() != 0) {
                searchGoogle();
            }
        }
    }

    public void enterN(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            if (nTF.getText().length() != 0) {
                searchNaver();
            }
        }
    }

    public void enterD(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            if (dTF.getText().length() != 0) {
                searchDaum();
            }
        }
    }

    public void enterY(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            if (yTF.getText().length() != 0) {
                searchYoutube();
            }
        }
    }
    public void enterAll(KeyEvent keyEvent) {
        if (keyEvent.getCode() == keyEvent.getCode().ENTER) {
            if (allGND.getText().length() != 0) {
                searchAll();
            }
        }
    }
    public void gGO() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.google.com"));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }

    public void nGo() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.naver.com"));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }

    public void dGo() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.daum.net"));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }
    public void yGo() {
        try {
            Desktop.getDesktop().browse(new URI("https://www.youtube.com"));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }
    public void allGo() {
        gGO();
        nGo();
        dGo();
        yGo();
    }
//=============================================================================

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstHome.toFront();
        todoListView.setItems(loadList);
        toDoCompleteView.setItems(loadCompleteList);
        service = new Service(this);
        DBCON();
        todoLoad();

    }

    //=======================================================================================
    Connection conn;
    Statement stm;
    String urlDb = "jdbc:oracle:thin:@localhost:1521:JAVA";
    String usr = "pyjava";
    String pwd = "tiger";

    ArrayList<String> newsNumList1 = new ArrayList<>();
    ArrayList<String> newsTitleList1 = new ArrayList<>();
    ArrayList<String> newsCompanyList1 = new ArrayList<>();
    ArrayList<String> newsImgList1 = new ArrayList<>();
    Vector<String> newsLinkList1 = new Vector<>();

    ArrayList<String> newsNumList2 = new ArrayList<>();
    ArrayList<String> newsTitleList2 = new ArrayList<>();
    ArrayList<String> newsCompanyList2 = new ArrayList<>();
    ArrayList<String> newsImgList2 = new ArrayList<>();
    Vector<String> newsLinkList2 = new Vector<>();

    public void DBCON() {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
//            System.out.println("드라이버 로딩 성공");
            conn = DriverManager.getConnection(urlDb, usr, pwd);
//            System.out.println("DB연결성공");
        } catch (ClassNotFoundException classNotFoundException) {
            System.out.println("드라이버 로딩 실패");
        } catch (SQLException sqlException) {
            System.out.println("DB연결 실패");
        }
    }
    public void todoLoad() {
        try {
            stm = conn.createStatement();
            String sql1 = "select * from TODOLIST order by ID";
            String sql2 = "select * from COMPLETELIST order by ID";
            ResultSet st1 = stm.executeQuery(sql1);
            while (st1.next()) {
                Optional<String> todo = Optional.ofNullable(st1.getString(2));
                if (todo.isPresent()) {
                    String valueTodo = String.valueOf(todo);
                    loadList.add(valueTodo.substring(valueTodo.indexOf("[")+1,valueTodo.lastIndexOf("]")));
                } else {
                    loadList.add(String.valueOf(todo));
                }
            }
            st1.close();
            ResultSet st2 = stm.executeQuery(sql2);
            while (st2.next()) {
                Optional<String> com = Optional.ofNullable(st2.getString(2));
                if (com.isPresent()) {
                    String valuecom = String.valueOf(com);
                    loadCompleteList.add(valuecom.substring(valuecom.indexOf("[")+1,valuecom.lastIndexOf("]")));
                } else {
                    loadCompleteList.add(String.valueOf(com));
                }
            }

            st2.close();
            stm.close();
        } catch (SQLException e) {
            System.out.println("toDoList를 불러오지 못했습니다." + e.getMessage());
            try {
                stm.close();
            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            }
        }
    }

    public void newsLoad() {
        try {
            stm = conn.createStatement();
            String sql1 = "select * from NEWS1 where ROWNUM<11 order by ID";
            ResultSet rs = stm.executeQuery(sql1);

            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String company = rs.getString(3);
                String imag = rs.getString(4);
//                String ko = rs.getString(5);
                String link = rs.getString(6);
                newsNumList1.add(id);
                newsTitleList1.add(title);
                newsCompanyList1.add(company);
                newsImgList1.add(imag);
                newsLinkList1.add(link);
            }
            String sql2 = "select * from NEWS3 where ROWNUM<11 order by ID";
            rs = stm.executeQuery(sql2);
            while (rs.next()) {
                String id = rs.getString(1);
                String title = rs.getString(2);
                String company = rs.getString(3);
                String imag = rs.getString(4);
                String link = rs.getString(6);
                newsNumList2.add(id);
                newsTitleList2.add(title);
                newsCompanyList2.add(company);
                newsImgList2.add(imag);
                newsLinkList2.add(link);
            }
            if (newsNumList1.isEmpty()) {
                errorPane.toFront();
//                System.out.println("정보를 못가져왔지...?");
            }
            rs.close();
            stm.close();
        } catch (SQLException e) {
            try {
                stm.close();
                errorPane.toFront();
                System.out.println("DB정보 가져오기 실패"+e.getSQLState()+e.getErrorCode());
            } catch (SQLException sqlException) {
                errorPane.toFront();
                sqlException.printStackTrace();
            }

        }
    }

    public void errorNews() {
        try {
            Desktop.getDesktop().browse(new URI("https://news.daum.net/ranking/popular"));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }

    public void factoryNews(){

        try {
            news1_num1.setText(newsNumList1.get(0));
            news1_com1.setText(newsCompanyList1.get(0));
            news1_but1.setText(newsTitleList1.get(0));
            news1_image1.setImage(new Image(newsImgList1.get(0)));
            news1_num2.setText(newsNumList1.get(1));
            news1_com2.setText(newsCompanyList1.get(1));
            news1_but2.setText(newsTitleList1.get(1));
            news1_image2.setImage(new Image(newsImgList1.get(1)));
            news1_num3.setText(newsNumList1.get(2));
            news1_com3.setText(newsCompanyList1.get(2));
            news1_but3.setText(newsTitleList1.get(2));
            news1_image3.setImage(new Image(newsImgList1.get(2)));
            news1_num4.setText(newsNumList1.get(3));
            news1_com4.setText(newsCompanyList1.get(3));
            news1_but4.setText(newsTitleList1.get(3));
            news1_image4.setImage(new Image(newsImgList1.get(3)));
//            ---------------------------------------------------------
            news1_num5.setText(newsNumList1.get(4));
            news1_com5.setText(newsCompanyList1.get(4));
            news1_but5.setText(newsTitleList1.get(4));
            news1_image5.setImage(new Image(newsImgList1.get(4)));
            news1_num6.setText(newsNumList1.get(5));
            news1_com6.setText(newsCompanyList1.get(5));
            news1_but6.setText(newsTitleList1.get(5));
            news1_image6.setImage(new Image(newsImgList1.get(5)));
            news1_num7.setText(newsNumList1.get(6));
            news1_com7.setText(newsCompanyList1.get(6));
            news1_but7.setText(newsTitleList1.get(6));
            news1_image7.setImage(new Image(newsImgList1.get(6)));
            news1_num8.setText(newsNumList1.get(7));
            news1_com8.setText(newsCompanyList1.get(7));
            news1_but8.setText(newsTitleList1.get(7));
            news1_image8.setImage(new Image(newsImgList1.get(7)));
            news1_num9.setText(newsNumList1.get(8));
            news1_com9.setText(newsCompanyList1.get(8));
            news1_but9.setText(newsTitleList1.get(8));
            news1_image9.setImage(new Image(newsImgList1.get(8)));
            news1_num10.setText(newsNumList1.get(9));
            news1_com10.setText(newsCompanyList1.get(9));
            news1_but10.setText(newsTitleList1.get(9));
            news1_image10.setImage(new Image(newsImgList1.get(9)));


//


//                --------------------------------------------------------------------
            news2_num1.setText(newsNumList2.get(0));
            news2_com1.setText(newsCompanyList2.get(0));
            news2_but1.setText(newsTitleList2.get(0));
            news2_image1.setImage(new Image(newsImgList2.get(0)));
            news2_num2.setText(newsNumList2.get(1));
            news2_com2.setText(newsCompanyList2.get(1));
            news2_but2.setText(newsTitleList2.get(1));
            news2_image2.setImage(new Image(newsImgList2.get(1)));
            news2_num3.setText(newsNumList2.get(2));
            news2_com3.setText(newsCompanyList2.get(2));
            news2_but3.setText(newsTitleList2.get(2));
            news2_image3.setImage(new Image(newsImgList2.get(2)));
            news2_num4.setText(newsNumList2.get(3));
            news2_com4.setText(newsCompanyList2.get(3));
            news2_but4.setText(newsTitleList2.get(3));
            news2_image4.setImage(new Image(newsImgList2.get(3)));
//          ----------------------------------------------------------
            news2_num5.setText(newsNumList2.get(4));
            news2_com5.setText(newsCompanyList2.get(4));
            news2_but5.setText(newsTitleList2.get(4));
            news2_image5.setImage(new Image(newsImgList2.get(4)));
            news2_num6.setText(newsNumList2.get(5));
            news2_com6.setText(newsCompanyList2.get(5));
            news2_but6.setText(newsTitleList2.get(5));
            news2_image6.setImage(new Image(newsImgList2.get(5)));
            news2_num7.setText(newsNumList2.get(6));
            news2_com7.setText(newsCompanyList2.get(6));
            news2_but7.setText(newsTitleList2.get(6));
            news2_image7.setImage(new Image(newsImgList2.get(6)));
            news2_num8.setText(newsNumList2.get(7));
            news2_com8.setText(newsCompanyList2.get(7));
            news2_but8.setText(newsTitleList2.get(7));
            news2_image8.setImage(new Image(newsImgList2.get(7)));
            news2_num9.setText(newsNumList2.get(8));
            news2_com9.setText(newsCompanyList2.get(8));
            news2_but9.setText(newsTitleList2.get(8));
            news2_image9.setImage(new Image(newsImgList2.get(8)));
            news2_num10.setText(newsNumList2.get(9));
            news2_com10.setText(newsCompanyList2.get(9));
            news2_but10.setText(newsTitleList2.get(9));
            news2_image10.setImage(new Image(newsImgList2.get(9)));

        } catch (NullPointerException e) {
            errorPane.toFront();
            System.out.println("NullPointerException + 업데이트 중입니다. 가져올려는 페이지 주소 : \n\thttps://news.daum.net/ranking/popular");
        } catch (IndexOutOfBoundsException indexOutOfBoundsException) {
            errorPane.toFront();
            System.out.println("IndexOutOfBoundsException+ 업데이트 중입니다. 가져올려는 페이지 주소 : \n\thttps://news.daum.net/ranking/popular");
            errorPane.toFront();
        } catch (Exception ee) {
            errorPane.toFront();
            System.out.println("Exception + 업데이트 중입니다. 가져올려는 페이지 주소 : \n\thttps://news.daum.net/ranking/popular");
        }

    }

    public void webToon(){
        String webtToonURL = "";
        LocalDate localDate = LocalDate.now();
        int dayNum = localDate.getDayOfWeek().getValue();
        switch (dayNum) {
            case 1 : webtToonURL = "mon"; break;
            case 2 : webtToonURL = "tue"; break;
            case 3 : webtToonURL = "wed"; break;
            case 4 : webtToonURL = "tue"; break;
            case 5 : webtToonURL = "fri"; break;
            case 6 : webtToonURL = "sat"; break;
            case 7 : webtToonURL = "sun"; break;
        }
        try {
            Desktop.getDesktop().browse(new URI("https://comic.naver.com/webtoon/weekdayList.nhn?week=" + webtToonURL));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }

    public void n1B1() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B2() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(1)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B3() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(2)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B4() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(3)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    //  ------------------------------------------------------------------
    public void n1B5() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(4)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B6() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(5)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B7() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(6)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B8() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(7)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B9() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(8)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n1B10() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList1.get(9)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    //  ----------------------------------------------------------------
    public void n2B1() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(0)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B2() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(1)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B3() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(2)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B4() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(3)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }

    //    --------------------------------------------------------
    public void n2B5() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(4)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B6() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(5)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B7() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(6)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B8() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(7)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B9() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(8)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
    public void n2B10() {
        try {
            Desktop.getDesktop().browse(new URI(newsLinkList2.get(9)));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException uriSyntaxException) {
            uriSyntaxException.printStackTrace();
        }
    }
//  -------------------------------------------------------------------


}
