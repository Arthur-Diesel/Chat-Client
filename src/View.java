import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class View {
    static public void showError(JSONException error){
        System.out.println("\n");
        System.out.println("--------------------------------");
        System.out.println("|" + error);
        System.out.println("--------------------------------");
        System.out.println("\n");
    }
    public void showWelcome(){
        System.out.println("--------------------------------");
        System.out.println("| Olá, Bem vindo ao Chat!      |");
        System.out.println("--------------------------------");
    }
    public void showOptions(){
        System.out.println("--------------------------------");
        System.out.println("|                              |");
        System.out.println("| Para continuar, informe o    |");
        System.out.println("| código de uma das opções     |");
        System.out.println("| abaixo.                      |");
        System.out.println("|                              |");
        System.out.println("| 1: Lista as Mensagens.       |");
        System.out.println("|                              |");
        System.out.println("| 2: Envia uma nova mensagem.  |");
        System.out.println("|                              |");
        System.out.println("| 3: Mostra as opções.         |");
        System.out.println("|                              |");
        System.out.println("| 4: Encerra o programa.       |");
        System.out.println("|                              |");
        System.out.println("--------------------------------");
    }
    public void showJSON(JSONObject messageObject){
        try{
            String name = messageObject.getString("identificador");
            String message = messageObject.getString("mensagem");
            String date = messageObject.getString("data");
            System.out.print("\n");
            System.out.println("--------------------------------");
            System.out.println("| Nome: " + name);
            System.out.println("| Mensagem: " + message);
            System.out.println("| Data: " + date);
            System.out.println("--------------------------------");
            System.out.print("\n");
        } catch (JSONException error){
            showError(error);
        }
    }
    public void showMessages(JSONObject messagesObject){
        try{
            JSONArray messagesArray = messagesObject.getJSONArray("messages");
            for (int n = 0; n < messagesArray.length(); n++) {
                JSONObject messageObject = messagesArray.getJSONObject(n);
                showJSON(messageObject);
            }
        } catch (JSONException error){
            showError(error);
        }
    }
}
