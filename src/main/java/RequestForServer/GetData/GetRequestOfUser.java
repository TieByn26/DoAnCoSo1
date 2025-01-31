package RequestForServer.GetData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_Request;
import ObjectGson.GsonForServer.SV_ListComicOfUser;
import ObjectGson.GsonForServer.SV_ListStatistic;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class GetRequestOfUser {
    public static SV_ListComicOfUser getRequestOfUser(){
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        SV_ListComicOfUser sv_listComicOfUser = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/get/comic/of/user");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();

            // Lay du lieu tu server gui ve
            String read = fromServer.readLine();
            sv_listComicOfUser = gson.fromJson(read, SV_ListComicOfUser.class);
            System.out.println(sv_listComicOfUser);
        } catch (Exception e){
            e.printStackTrace();
        }
        return sv_listComicOfUser;
    }
}
