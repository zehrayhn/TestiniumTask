package org.example;

import org.junit.jupiter.api.*;

//@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class HomePageTest extends BaseTest{

    HomePage homePage;


    CSVHelper csvHelper;

    @Test
    @Order(1)
    public void allow_Cookies(){
        homePage=new HomePage(driver);

        homePage.allowCookie();
        homePage.acceptCookies();

        csvHelper=new CSVHelper("C:\\Users\\Zehra\\Desktop\\seturData.csv");
        try{
            String destination = csvHelper.getCellValue(1,1);
            homePage.fillOut(destination);


        }
        catch (Exception e){
            System.err.println("hata"+e.getMessage());
        }

    }





}
