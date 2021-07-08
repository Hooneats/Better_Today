package sample;


import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class Service {

    FXMLExampleController controller;



    Service(FXMLExampleController controller) {
        this.controller = controller;
    }


    public void googleWeb(String search){
        try {
            search = search.replaceAll(" ", "+");
            Desktop.getDesktop().browse(new URI("https://www.google.com/search?q="+search));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }
    public void naverWeb(String search){
        try {
            search = search.replaceAll(" ", "+");
            Desktop.getDesktop().browse(new URI("https://search.naver.com/search.naver?sm=tab_sug.top&where=nexearch&query="+search));

        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }
    public void daumWeb(String search){
        try {
            search = search.replaceAll(" ", "+");
            Desktop.getDesktop().browse(new URI("https://search.daum.net/search?nil_suggest=btn&w=tot&DA=SBC&q="+search));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }

    public void youtubeWeb(String search) {
        try {
            search = search.replaceAll(" ", "+");
            Desktop.getDesktop().browse(new URI("https://www.youtube.com/results?search_query="+search));
        } catch (IOException e) {
            System.out.println("주소를 잘못 입력했습니다");
        } catch (URISyntaxException ue) {
            System.out.println("인터넷을 띄울수 없습니다.");
        }
    }

    public void allWeb(String search) {
        daumWeb(search);
        naverWeb(search);
        googleWeb(search);
        youtubeWeb(search);
    }

}
