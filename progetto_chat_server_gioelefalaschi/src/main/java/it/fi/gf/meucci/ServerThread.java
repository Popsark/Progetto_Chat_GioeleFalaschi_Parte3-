package it.fi.gf.meucci;
import java.net.Socket;
import javax.swing.event.SwingPropertyChangeSupport;

import java.net.*;
import java.io.*;

public class ServerThread extends Thread{
    ServerSocket server = null;
    Socket client = null;
    String stringaRicevuta= null;
    String stringaModificata = null;
    BufferedReader inDalClient;
    DataOutputStream OutVersoClient;

    public ServerThread (Socket socket){
        this.client = socket;
    }

    public void comunica ()throws Exception{
        inDalClient = new BufferedReader(new InputStreamReader(client.getInputStream()));
        OutVersoClient = new DataOutputStream(client.getOutputStream());
        for(;;){
            stringaRicevuta = inDalClient.readLine();
            if(stringaRicevuta == null || stringaRicevuta.equals("FINE")){
                OutVersoClient.writeBytes(stringaRicevuta+ "(=>servfer in chiusura...)" + '\n');
                System.out.println("Echo sul server in chiusura :"+ stringaRicevuta);
                break;
            }
            else{
                OutVersoClient.writeBytes(stringaRicevuta+ "(ricevuta e ritrasmessa)"+ '\n');
                System.out.println("Echo sul server :"+ stringaRicevuta);
            }
        }
        OutVersoClient.close();
        inDalClient.close();
        System.out.println("Chiusura socket "+ client);
        client.close();
    }
    
    public void run (){
        try{
            comunica();
        }
        catch(Exception e){
            e.printStackTrace(System.out);
        }
    }
}
