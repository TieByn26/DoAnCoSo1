package Server.ComicServer;

import Connect.StreamSocket;
import DAO.UserDAO;
import Server.Controller.*;
import Server.ObjectGson.GsonForClient.CL_Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class ClientHandler {
    public static void ExecuteClientRequest(Socket socket){
        try {
            Gson gson = new Gson();
            //doc du lieu
            BufferedReader fromClient = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            System.out.println("ggd12");
            String clientRequest = fromClient.readLine();
            System.out.println(clientRequest);
            //chuyen doi du lieu gson thanh doi tuong java va anh xa vao model
            CL_Request clRequest = gson.fromJson(clientRequest, CL_Request.class);

            //thuc hien request
            switch (clRequest.getRequest()){
                case "/get/allcomic" : {
                    try {
                        ComicController.responeALLComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/comicInformationByNameComics" : {
                    try {

                        ComicController.responeComicInformationByNameComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/viewByIdComics" : {
                    try {
                        ComicController.responeAllViews(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/IdCategoryByIdComics" : {
                    try {
                        ComicController.responeIdCategoryByIdComics(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/CategoryNameByIdCategory" : {
                    try {
                        ComicController.responeCategoryNameByIdCategory(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/AllCommentByIdComics" : {
                    try {
                        CommentController.responeAllComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/InforUserForComment" : {
                    try {
                        UserController.responeInforUserForComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/numberOfLike" : {
                    try {
                        CommentController.updateNumberOflike(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/numberOfDislike" : {
                    try {
                        CommentController.updateNumberOfDislike(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/update/newComment" : {
                    try {
                        CommentController.createNewComment(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/AllImagesOfChapter" : {
                    try {
                        ChapterController.responeAllImagesOfChapter(socket);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/register/new/account" :{
                    try{
                        RegisterController.resisterNewAcount(socket);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/login/account":{
                    try{
                        LoginController.checkLogin(socket);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/get/otp":{
                    try{
                        LoginController.sendOtp(socket);
                    }catch (Exception e){
                        throw new RuntimeException(e);
                    }
                    break;
                }
                case "/change/password":{
                    try {
                        LoginController.changePassword(socket);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/all/comics":{
                    try {
                        ListComicsController.getAllComic(socket);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case "/get/all/view/comics":{
                    try {
                        ListComicsController.getAllNumView(socket);
                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                case "/add/new/comic":{
                    try{

                    } catch (Exception e){
                        e.printStackTrace();
                    }
                    break;
                }
                default:
                    break;
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
