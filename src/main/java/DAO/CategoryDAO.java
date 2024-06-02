package DAO;

import Connect.DatabaseConnect;
import Server.ObjectGson.GsonForServer.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class CategoryDAO {
    public static SV_CategoryManager selectIdCategoryByIdComics(String idComics) {  // ham tra ve id the loai
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_CategoryManager idCategory = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idCategory` FROM `managercategorycomics` WHERE idComics = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idComics);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String idCategoryDatabase = rs.getString(1);
                idCategory = new SV_CategoryManager(idCategoryDatabase);
            }
            else {
                System.out.println("select idcategory is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);


        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("database tra ve: " + idCategory);

        return idCategory;
    }

    public static SV_CategoryName selectCategoryNameByIdCategory(String idCategory) {  // ham tra ve ten the loai theo id
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_CategoryName categoryName = null;
        try {
            // tao ra cau query sql
            String querySQL = "SELECT  `categoryInformation` FROM `categorycomics` WHERE idCategory =  ?";
            PreparedStatement st = connection.prepareStatement(querySQL);

            st.setString(1,idCategory);

            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            // load du lieu sau khi thuc hien cau query
            if(rs.next()) {  // kiem tra xem ket qua tra ve co null khong
                String categoryInfotmation = rs.getString(1);
                categoryName = new SV_CategoryName(categoryInfotmation);
            }
            else {
                System.out.println("selectCategoryNameByIdCategory is null");
            }

            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + categoryName);

        return categoryName;
    }

    public static SV_listCategory selecAllCategory() {  // ham tra ve thong tin tat ca the loai
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_listCategory listCategory = new SV_listCategory();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idCategory`, `categoryInformation` FROM `categorycomics` ";
            PreparedStatement st = connection.prepareStatement(querySQL);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String idCategory = rs.getString(1);
                String categoryName = rs.getString(2);

                SV_CategoryComics newCategory = new SV_CategoryComics(idCategory,categoryName);

                listCategory.getListCategory().add(newCategory);
            }
            // dong connect database
            DatabaseConnect.closeConnect(connection);

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + listCategory);
        return listCategory;
    }
    public static SV_ListComicsInformations selectALlComicsByCategory(String idCategory) {  // ham tra ve thong tin truyen theo 1 the loai nhat dinh
        //tao connect toi server
        Connection connection = DatabaseConnect.getConnect();
        // tao doi tuong de nhan du lieu tu database
        SV_ListComicsInformations listComics = new SV_ListComicsInformations();
        ArrayList<String> listIdComics = new ArrayList<>();
        try {
            // tao ra cau query sql
            String querySQL = "SELECT `idComics` FROM `managercategorycomics` WHERE idCategory = ?";
            PreparedStatement st = connection.prepareStatement(querySQL);
            st.setString(1,idCategory);
            // thuc thi cau query
            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                String idComics = rs.getString(1);
                listIdComics.add(idComics);
            }
            // dong connect database
            DatabaseConnect.closeConnect(connection);

            // lay tat ca thong tin cua tung idComics
             for (String id : listIdComics) {
                 SV_ComicsInformation comicsInformation = ComicsDAO.selectComicsInformationByIdComics(id);
                 listComics.getListComicsInfomations().add(comicsInformation);
             }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("database tra ve: " + listComics);
        return listComics;
    }

}
