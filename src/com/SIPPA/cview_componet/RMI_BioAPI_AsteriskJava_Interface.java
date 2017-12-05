package com.SIPPA.cview_componet;


//
import java.rmi.RemoteException;
public interface RMI_BioAPI_AsteriskJava_Interface extends java.rmi.Remote{
	static public String local_ip=null;
	public void RPC_FileRead(String Service_UID, String srcFileName, String socket_ip, int socket_port, String remote_fileName,String first,String last) throws RemoteException;
	public boolean check(String first, String last, String src)throws RemoteException;
    public String[] list(String s)throws RemoteException;
}
