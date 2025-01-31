package RequestForServer.PostData;

import ConnectServer.Connect;
import ObjectGson.GsonForClient.CL_RegisterInformation;
import ObjectGson.GsonForClient.CL_Request;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class RequestRegister {
    public static String requestRegister(CL_RegisterInformation cl_registerInformation) {
        Gson gson = new Gson();
        Socket socket = Connect.getSocket();
        String confirmation = null;
        try (BufferedWriter fromClient = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader fromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {
            // Gửi yêu cầu tới server
            CL_Request request = new CL_Request("/register/new/account");
            String jsonRequest = gson.toJson(request);
            fromClient.write(jsonRequest);
            fromClient.newLine();
            fromClient.flush();
            Connect.receiveStatus(socket);

            // Gửi dữ liệu đăng ký lên server
            String jsonRegisterInfo = gson.toJson(cl_registerInformation);
            fromClient.write(jsonRegisterInfo);
            fromClient.newLine();
            fromClient.flush();

            // Đọc dữ liệu xác nhận đăng ký từ server
            confirmation = fromServer.readLine();
            System.out.println("Server response: " + confirmation);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return confirmation;
    }
}
