import java.util.Scanner;

import java.net.Socket;
import java.io.DataOutputStream;
import org.json.JSONObject;

public class App {
    public static void main(String[] args) throws Exception {
        Controller controller = new Controller();
        View view = new View();
        view.showWelcome();
        view.showOptions();
        
        Scanner nameSc = new Scanner(System.in);
        Scanner optionSc = new Scanner(System.in);
        Scanner ipSc = new Scanner(System.in);
        Scanner portSc = new Scanner(System.in);
        Scanner messageSc = new Scanner(System.in);
        int option = 0;

        System.out.print("Primeiro, informe seu nome: ");
        String name = nameSc.next();
        System.out.print("\n");

        System.out.print("Informe o IP do servidor que você deseja se conectar: ");
        String ip = ipSc.next();
        System.out.print("\n");
        
        System.out.print("Informe a porta do servidor que você deseja se conectar: ");
        int port = portSc.nextInt();
        System.out.print("\n");

        Socket server = controller.serverStart(ip, port);
        DataOutputStream connection = controller.getDataOutPutStream(server);

        String message = "";
                
        while(option != 4){
            System.out.print("Digite a opção: ");
            option = optionSc.nextInt();
            System.out.print("\n");
            switch(option){
                case 1:
                    JSONObject messagesObject = controller.getMessages(server);
                    view.showMessages(messagesObject);
                    break;
                case 2:
                    System.out.print("Informe a mensagem: ");
                    message = messageSc.next();
                    System.out.print("\n");
                    JSONObject messageObject = controller.generateJson(name, message);
                    controller.sendMessage(messageObject, connection);
                    break;
                case 3:
                    view.showOptions();
                    break;
                case 4: 
                    controller.serverDisconnect(server);
                    messageSc.close();
                    optionSc.close();
                    ipSc.close();
                    nameSc.close();
                    portSc.close();
                    break;
            }
        }

    }
}
