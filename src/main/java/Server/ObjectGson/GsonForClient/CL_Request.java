package Server.ObjectGson.GsonForClient;

public class CL_Request {
    private String request;

    public CL_Request() {
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request;
    }

    @Override
    public String toString() {
        return "CL_Request{" +
                "request='" + request + '\'' +
                '}';
    }
}
