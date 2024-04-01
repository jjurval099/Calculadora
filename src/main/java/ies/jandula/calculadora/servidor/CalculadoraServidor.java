package ies.jandula.calculadora.servidor;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class CalculadoraServidor
{
    public static final int PUERTO = 2000;
    
    public static void main(String[] args) 
    {
        
        ServerSocket socketServer = null;
        Socket socketCliente = null;
        DataInputStream dataInputStream = null;
        DataOutputStream dataOutputStream = null;
        
        try 
        {
            socketServer = new ServerSocket(PUERTO);            
            
            socketCliente = socketServer.accept();
            
            dataInputStream = new DataInputStream(socketCliente.getInputStream());
            int numero1 = Integer.parseInt(dataInputStream.readUTF());
            int numero2 = Integer.parseInt(dataInputStream.readUTF());
            String op = dataInputStream.readUTF();
            
            dataOutputStream = new DataOutputStream(socketCliente.getOutputStream());
            
            
            int resultado = 0;
            
            switch (op) 
            {
				case "+":
					resultado = numero1 + numero2;
					dataOutputStream.writeUTF(String.valueOf(resultado));
					break;
				case "-":
					resultado = numero1 - numero2;
					dataOutputStream.writeUTF(String.valueOf(resultado));
					break;
				case "*":
					resultado = numero1 * numero2;
					dataOutputStream.writeUTF(String.valueOf(resultado));
					break;
				case "/":
					resultado = numero1 / numero2;
					dataOutputStream.writeUTF(String.valueOf(resultado));
					break;
				default:
					System.out.println("Opcion incorrecta");
			}
            
        } 
        catch (IOException ioException) 
        {
            ioException.printStackTrace();
        }
        finally
        {
            try 
            {
                if (dataInputStream != null)
                {
                    dataInputStream.close();
                }
                if (dataOutputStream != null)
                {
                    dataOutputStream.close();
                }
                if (socketCliente != null)
                {
                    socketCliente.close();
                }
                if (socketServer != null)
                {
                    socketServer.close();
                }
            } 
            catch (IOException e) 
            {
                e.printStackTrace();
            }
        }       
    }
}
