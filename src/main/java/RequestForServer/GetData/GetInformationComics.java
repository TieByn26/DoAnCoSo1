package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_IdComics;
import ObjectGson.GsonForClient.CL_NameComics;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_ComicsInformation;
import ObjectGson.GsonForServer.SV_ListComicsInformations;
import ObjectGson.GsonForServer.SV_Statistic;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class GetInformationComics {

    public static ArrayList<SV_ComicsInformation> getAllComics() {
       // tạo một đối tượng gson đẻ truyền thông tin giữa server và client
        Gson gson = new Gson();

        //tạo 1 kết nối đến server
        Socket socket = Connect.getSocket();
        // tạo ArrayList để lưu dữ liệu từ server
        ArrayList<SV_ComicsInformation> listComicsInformation = new ArrayList<>();

        //tao request yêu cầu server thực hiện cái gì
        CL_Request req = new CL_Request("/get/allcomic");

        // chuyen req thanh json
        String rpJson = gson.toJson(req);

        try {
            //gửi dữ liệu JSon từ client cho Server
            DataOutputStream sendReqtoServer = new DataOutputStream(socket.getOutputStream());
            sendReqtoServer.writeBytes(rpJson + "\n");

            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
            String comicsInformations = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ListComicsInformations dataConvertFromServer = gson.fromJson(comicsInformations, SV_ListComicsInformations.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                for (SV_ComicsInformation data : dataConvertFromServer.getListComicsInfomations()) {
                    listComicsInformation.add(data);
                }
            }
            else {
                System.out.println("/get/allcomic");
            }


            sendReqtoServer.close();
            receive.close();
            socket.close();
    }
        catch (Exception e) {
            e.printStackTrace();
        }
        return listComicsInformation;

    }

    public static SV_ComicsInformation getComicsInformationByNameComic(String nameComics) {
        // tạo một đối tượng gson đẻ truyền thông tin giữa server và client
        Gson gson = new Gson();

        //tạo 1 kết nối đến server
        Socket socket = Connect.getSocket();
        // tạo doi tuong để lưu dữ liệu từ server
        SV_ComicsInformation sv_comicsInformation = null;

        //tao request yêu cầu server thực hiện cái gì
        CL_Request req = new CL_Request("/get/ComicInformationByNameComics");

        // tao doi tuong de luu du lieu server can de thuc hien query
        CL_NameComics cl_nameComics = new CL_NameComics(nameComics);

        // chuyen req thanh json
        String rpJson = gson.toJson(req);
        String cl_nameComicsJson = gson.toJson(cl_nameComics);

        try {
            //gửi dữ liệu JSon từ client cho Server
            DataOutputStream sendReqtoServer = new DataOutputStream(socket.getOutputStream());
            sendReqtoServer.writeBytes(rpJson + "\n");

            // ktra server đã nhận đc yêu cầu chưa?
            Connect.receiveStatus(socket);

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.writeBytes(cl_nameComicsJson + "\n");


            //nhận và đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String comicsInformations = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ComicsInformation dataConvertFromServer = gson.fromJson(comicsInformations, SV_ComicsInformation.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
               sv_comicsInformation = dataConvertFromServer;
            }
            else {
                System.out.println("/get/ComicInformationByNameComics is null");
            }

            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sv_comicsInformation;
    }

    public static SV_Statistic getAllViewByIdComics(String idComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_Statistic allView = null;
        CL_Request req = new CL_Request("/get/viewByidComics");

        // data de server thuc hien query
        CL_IdComics cl_idComics = new CL_IdComics(idComics);

        // chuyen req thanh json
        String rpJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_idComics);

        try {
            //gửi dữ liệu JSon từ client cho Server
            DataOutputStream sendReqtoServer = new DataOutputStream(socket.getOutputStream());
            sendReqtoServer.writeBytes(rpJson + "\n");


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            // gui data de server thuc hien query
            sendReqtoServer.writeBytes(cl_idComicsJson + "\n");


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String allViewsJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_Statistic dataConvertFromServer = gson.fromJson(allViewsJson, SV_Statistic.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                allView = dataConvertFromServer;
            }
            else {
                System.out.println("/get/viewByidComics is null");
            }


            sendReqtoServer.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return allView;
    }

    public static SV_ComicsInformation getFullComicsInformationByNameComics(String nameComics) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_ComicsInformation sv_comicsInformation = null;
        CL_Request req = new CL_Request("/get/comicInformationByNameComics");

        // data de server thuc hien query
        CL_NameComics cl_nameComics = new CL_NameComics(nameComics);

        // chuyen req thanh json
        String rpJson = gson.toJson(req);
        String cl_idComicsJson = gson.toJson(cl_nameComics);

        try {

            OutputStream output = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(output, "UTF-8");
            BufferedWriter sendReqtoServer = new BufferedWriter(outputStreamWriter);

            //gửi dữ liệu JSon từ client cho Server
            sendReqtoServer.write(rpJson + "\n");
            sendReqtoServer.flush();


            // ktra server đã nhận đc yêu cầu chưa
            Connect.receiveStatus(socket);

            // gui data de server thuc hien query
            sendReqtoServer.write(cl_idComicsJson + "\n");
            sendReqtoServer.flush();


            //đọc dữ liệu json từ server
            BufferedReader receive = new BufferedReader(new InputStreamReader(socket.getInputStream(),"UTF-8"));
            String comicsInformationJson = receive.readLine();

            // chuyển đổi từ json sang đối tượng
            SV_ComicsInformation dataConvertFromServer = gson.fromJson(comicsInformationJson, SV_ComicsInformation.class);

            // truyền dữ liệu từ server vào arrayList đã tạo sẵn
            if(dataConvertFromServer != null) {
                sv_comicsInformation = dataConvertFromServer;
            }
            else {
                System.out.println("/get/comicInformationByNameComics is null");
            }

            sendReqtoServer.close();
            outputStreamWriter.close();
            output.close();
            receive.close();
            socket.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return sv_comicsInformation;
    }
}
