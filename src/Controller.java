import java.io.DataOutputStream;
import java.net.Socket;

import java.text.SimpleDateFormat;  
import java.util.Date;

import org.json.JSONObject;
import org.json.JSONException;

import org.apache.commons.io.IOUtils;

public class Controller {
    public String getCurrentDate(){
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
        Date date = new Date();
        return formatter.format(date);
    }

    public JSONObject generateJson(String name, String message){
        String date = getCurrentDate();
        JSONObject messageObject = new JSONObject();
        try{
            messageObject.put("identificador", name);
            messageObject.put("mensagem", message);
            messageObject.put("data", date);
            return messageObject;
        } catch (JSONException error) {
            View.showError(error);
            return messageObject;
        }
    }

    public Socket serverStart(String ip, int port) throws Exception{
        Socket server = new Socket(ip, port);
        return server;
    }

    public DataOutputStream getDataOutPutStream(Socket server) throws Exception{
        DataOutputStream connection = new DataOutputStream(server.getOutputStream());
        return connection;
    }

    public void sendMessage(JSONObject message, DataOutputStream connection) throws Exception{
        connection.writeUTF(message.toString());
        connection.flush();
    }

    public boolean serverConnected(Socket server) throws Exception{
        return server.isConnected();
    }

    public void serverDisconnect(Socket server) throws Exception{
        server.close();
    }

    public JSONObject getMessages(Socket server) throws Exception{
        String messagesString = IOUtils.toString(server.getInputStream(), "UTF-8");
        JSONObject messagesObject = new JSONObject(messagesString);
        return messagesObject;
    }
}
