package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_CheckLogin;
import ObjectGson.GsonForServer.SV_ListComicsInformations;
import ObjectGson.GsonForServer.SV_ListStatistic;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GetAllComic {
    public static SV_ListComicsInformations getAllComic(){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_ListComicsInformations svListComicsInformations = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/get/all/comics");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            Connect.receiveStatus(socket);

            // Lay du lieu tu server gui ve
            String read = fromServer.readLine();
            svListComicsInformations = gson.fromJson(read, SV_ListComicsInformations.class);
            System.out.println(svListComicsInformations);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return svListComicsInformations;
    }
    public static SV_ListStatistic getAllView(){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_ListStatistic sv_listStatistic = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/get/all/view/comics");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            Connect.receiveStatus(socket);

            // Lay du lieu tu server gui ve
            String read = fromServer.readLine();
            sv_listStatistic = gson.fromJson(read, SV_ListStatistic.class);
            System.out.println(sv_listStatistic);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sv_listStatistic;
    }
}
