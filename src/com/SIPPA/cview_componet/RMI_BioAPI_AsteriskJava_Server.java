package com.SIPPA.cview_componet;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.net.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.rmi.server.*;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.util.UUID;
import java.util.Vector;

import org.openhealthtools.mdht.uml.cda.AssignedAuthor;
import org.openhealthtools.mdht.uml.cda.AssignedCustodian;
import org.openhealthtools.mdht.uml.cda.AssignedEntity;
import org.openhealthtools.mdht.uml.cda.Authenticator;
import org.openhealthtools.mdht.uml.cda.Author;
import org.openhealthtools.mdht.uml.cda.AuthoringDevice;
import org.openhealthtools.mdht.uml.cda.Authorization;
import org.openhealthtools.mdht.uml.cda.Birthplace;
import org.openhealthtools.mdht.uml.cda.CDAFactory;
import org.openhealthtools.mdht.uml.cda.ClinicalDocument;
import org.openhealthtools.mdht.uml.cda.Component1;
import org.openhealthtools.mdht.uml.cda.Consent;
import org.openhealthtools.mdht.uml.cda.Consumable;
import org.openhealthtools.mdht.uml.cda.Custodian;
import org.openhealthtools.mdht.uml.cda.CustodianOrganization;
import org.openhealthtools.mdht.uml.cda.DocumentationOf;
import org.openhealthtools.mdht.uml.cda.EncompassingEncounter;
import org.openhealthtools.mdht.uml.cda.Entry;
import org.openhealthtools.mdht.uml.cda.EntryRelationship;
import org.openhealthtools.mdht.uml.cda.Guardian;
import org.openhealthtools.mdht.uml.cda.InFulfillmentOf;
import org.openhealthtools.mdht.uml.cda.Informant12;
import org.openhealthtools.mdht.uml.cda.InformationRecipient;
import org.openhealthtools.mdht.uml.cda.InfrastructureRootTypeId;
import org.openhealthtools.mdht.uml.cda.IntendedRecipient;
import org.openhealthtools.mdht.uml.cda.LabeledDrug;
import org.openhealthtools.mdht.uml.cda.LanguageCommunication;
import org.openhealthtools.mdht.uml.cda.LegalAuthenticator;
import org.openhealthtools.mdht.uml.cda.ManufacturedProduct;
import org.openhealthtools.mdht.uml.cda.Order;
import org.openhealthtools.mdht.uml.cda.Organization;
import org.openhealthtools.mdht.uml.cda.Organizer;
import org.openhealthtools.mdht.uml.cda.Participant1;
import org.openhealthtools.mdht.uml.cda.Participant2;
import org.openhealthtools.mdht.uml.cda.ParticipantRole;
import org.openhealthtools.mdht.uml.cda.Patient;
import org.openhealthtools.mdht.uml.cda.PatientRole;
import org.openhealthtools.mdht.uml.cda.Performer1;
import org.openhealthtools.mdht.uml.cda.Person;
import org.openhealthtools.mdht.uml.cda.Place;
import org.openhealthtools.mdht.uml.cda.PlayingEntity;
import org.openhealthtools.mdht.uml.cda.RecordTarget;
import org.openhealthtools.mdht.uml.cda.RelatedEntity;
import org.openhealthtools.mdht.uml.cda.RelatedSubject;
import org.openhealthtools.mdht.uml.cda.Section;
import org.openhealthtools.mdht.uml.cda.ServiceEvent;
import org.openhealthtools.mdht.uml.cda.Specimen;
import org.openhealthtools.mdht.uml.cda.SpecimenRole;
import org.openhealthtools.mdht.uml.cda.StrucDocText;
import org.openhealthtools.mdht.uml.cda.Subject;
import org.openhealthtools.mdht.uml.cda.SubstanceAdministration;
import org.openhealthtools.mdht.uml.cda.ccd.AlertObservation;
import org.openhealthtools.mdht.uml.cda.ccd.AlertsSection;
import org.openhealthtools.mdht.uml.cda.ccd.CCDFactory;
import org.openhealthtools.mdht.uml.cda.ccd.CCDPackage;
import org.openhealthtools.mdht.uml.cda.ccd.ContinuityOfCareDocument;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationActivity;
import org.openhealthtools.mdht.uml.cda.ccd.MedicationsSection;
import org.openhealthtools.mdht.uml.cda.ccd.ProblemAct;
import org.openhealthtools.mdht.uml.cda.ccd.VitalSignsSection;

import org.openhealthtools.mdht.uml.cda.util.CDAUtil;
import org.openhealthtools.mdht.uml.hl7.datatypes.AD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CD;
import org.openhealthtools.mdht.uml.hl7.datatypes.CE;
import org.openhealthtools.mdht.uml.hl7.datatypes.CS;
import org.openhealthtools.mdht.uml.hl7.datatypes.DatatypesFactory;
import org.openhealthtools.mdht.uml.hl7.datatypes.ED;
import org.openhealthtools.mdht.uml.hl7.datatypes.II;
import org.openhealthtools.mdht.uml.hl7.datatypes.INT;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_INT;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVL_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.IVXB_TS;
import org.openhealthtools.mdht.uml.hl7.datatypes.ON;
import org.openhealthtools.mdht.uml.hl7.datatypes.PN;
import org.openhealthtools.mdht.uml.hl7.datatypes.PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.RTO_PQ_PQ;
import org.openhealthtools.mdht.uml.hl7.datatypes.SC;
import org.openhealthtools.mdht.uml.hl7.datatypes.ST;
import org.openhealthtools.mdht.uml.hl7.datatypes.TEL;
import org.openhealthtools.mdht.uml.hl7.datatypes.TS;
import org.openhealthtools.mdht.uml.hl7.vocab.EntityClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.NullFlavor;
import org.openhealthtools.mdht.uml.hl7.vocab.ParticipationType;
import org.openhealthtools.mdht.uml.hl7.vocab.PostalAddressUse;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassMutualRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.RoleClassRoot;
import org.openhealthtools.mdht.uml.hl7.vocab.VocabFactory;
import org.openhealthtools.mdht.uml.hl7.vocab.VocabPackage;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ActRelationshipEntryRelationship;
import org.openhealthtools.mdht.uml.hl7.vocab.x_ServiceEventPerformer;



/**
 * 
 */

/**
 * @author bon
 *
 */
public class RMI_BioAPI_AsteriskJava_Server extends UnicastRemoteObject
implements RMI_BioAPI_AsteriskJava_Interface {
	//String srcFileName;
public  static String first;
public static String last;
	PrintWriter p=new PrintWriter(new PrintWriter("kk"),true);
	public RMI_BioAPI_AsteriskJava_Server(int port) throws IOException
    {
        super(port);
        //LocateRegistry.createRegistry(2036);
       // ServerSocket s=new ServerSocket(port);
    //Socket sock=s.accept() ;
   
	   
    
    }
	
public boolean check(String first, String last, String src) throws RemoteException
{  String tempsrc="";

	
    try {
	System.out.println(src+" 654ff "+new java.io.File( "." ).getCanonicalPath()+File.separator);
	tempsrc=new java.io.File( "." ).getCanonicalPath()+File.separator+src;
    System.out.println(tempsrc+" ggh655");
	
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
	if(src!=null)
	{CCDPackage.eINSTANCE.eClass();
   try {
	  
	   System.out.println(tempsrc);
	   // src=new java.io.File( "." ).getCanonicalPath()+File.separator +src;
	ClinicalDocument clinicalDocument = CDAUtil.load(
	        new FileInputStream(tempsrc));
  // System.out.println(clinicalDocument.getRecordTargets().get(0).getPatientRole().getPatient().getNames().get(0).getGivens().get(0).
	// getText());//+ " " +
	
   System.out.println(clinicalDocument.getRecordTargets().get(0).getPatientRole().getPatient().getNames().
			  get(0).getFamilies().get(0).getText());
	
	// clinicalDocument.getR
	 if(!(first.equals(clinicalDocument.getRecordTargets().get(0).getPatientRole().getPatient().getNames().get(0).getGivens().get(0).
		 getText())&& last.equals(clinicalDocument.getRecordTargets().get(0).getPatientRole().getPatient().getNames().
				   get(0).getFamilies().get(0).getText())))
		{return false;
		  //JOptionPane.showMessageDialog(null, "folder is located at ");
		 //System.exit(0);
		}
	
    System.out.println(clinicalDocument.getRecordTargets().get(0).getPatientRole().getPatient().getNames().get(0).getGivens().get(0).getText());
   
   
   } catch (Exception e) {
	// TODO Auto-generated catch block
	     e.printStackTrace();
    }
   this.first=first;
   this.last=last;
   System.out.println("755");
   return true;
	}
    return false;
}
	
	/* (non-Javadoc)
	 * @see RMI_BioAPI_AsteriskJava_Interface#RPC_FileRead(java.lang.String, java.lang.String)
	 *///
	public String[]  list(String s)throws RemoteException
	{File f =null;
		try {
			s=new java.io.File( "." ).getCanonicalPath();
		
			File fileName = new File(s);
			f=fileName;
			for (  int i=0; i<fileName.list().length; i++)
			{//System.out.println(fileName.list()[i]);
			
			 
			 //System.out.println(fileName.list()[i]);
			 
			
			//line = brf.readLine();
			
			}
		
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return f.list();
		
	}
	public void RPC_FileRead(String Service_UID,  String srcFileName,String socket_ip, int socket_port, String remote_fileName,String first,String last)
			throws RemoteException {
		// TODO Auto-generated method stub

	Socket soc;
   File f= new File("");
		//PrintStream pw=null;
    	// BufferedReader br = null;
    System.out.println("Server address connected to is and port is ");
    //srcFileName=filelist();
    if(srcFileName==null)
    { System.out.println(new java.io.File("").getAbsolutePath()+"090909090");
    	//srcFileName="c:\\Users\\shelton\\work4\\P5\\src";
     try 
       {
		srcFileName=new java.io.File( "." ).getCanonicalPath();
	    } catch (IOException e1) 
     {
		// TODO Auto-generated catch block
		e1.printStackTrace();
	}
    System.out.println("oop");
		File fileName = new File(srcFileName);
		
		BufferedReader brf=null;
		BufferedReader br=null;
		/*try {
    		System.out.println("opp");
			
    		
    	//	BufferedReader 
    		brf = new BufferedReader(new InputStreamReader(
    			new FileInputStream(fileName.getAbsolutePath())));
    		System.out.println("opa");
		}catch ( IOException ioe ) {throw new RuntimeException(ioe);}*/
    	
    	try {
    		//soc = new Socket(socket_ip, socket_port);
    		//brf= new BufferedReader(new InputStreamReader(soc.getInputStream()));
    		
    		/*if ( brf == null )
    			throw new RuntimeException("Cannot read from closed file "
					                       + fileName.getAbsolutePath() + ".");*/
    		
     		try {
     			//soc = new Socket("10.0.0.21", 2001);
     			System.out.println("opa");
     		
        			soc = new Socket(socket_ip, socket_port);
        			System.out.println( " kkkjio");
        			//System.out.println(soc);
        			 PrintWriter pw=null;
        			pw=new PrintWriter(soc.getOutputStream(), true);
                       /* for(String list : fileName.list())
                        {
                        	pw.println(list);
                        	System.out.println(list);
                        }*/
                        pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
                		pw.println(remote_fileName); //Signaling message about remote file name

                		//String[] line=fileName.list(); //= brf.readLine();
                		int counter=0;
 
                		for (  int i=0; i<fileName.list().length; i++)
                			{System.out.println(fileName.list()[i]);
                			
                			 pw.println(fileName.list()[i]);
                			 System.out.println(fileName.list()[i]);
                			 
                			 counter++;
                			//line = brf.readLine();
                			
                			}
                		//pw.close();
                    //PrintWriter   p=new PrintWriter(soc.getOutputStream(), true);
                      
                        //if(counter==fileName.list().length-1)
                        //pw.close();
                        
                        
                        
                        
                        
                     
                        
                        //String name=br.readLine();
            		/*pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
            		pw.println(remote_fileName); //Signaling message about remote file name

            		String line = brf.readLine();
            		int counter=0;

            		while ( line != null){
            			System.out.println(line);
            			pw.println(line);
            			counter++;
            			line = brf.readLine();
            		}
            		
System.out.println(line);
            		pw.println("Done");*/ //Signaling message to terminate the remote socket server
                        System.out.println("over");
                        //br.close();
            		//brf.close();
            		//pw.close();
            		soc.close();
                	p=pw;		
     		} catch (UnknownHostException e) {
        			System.err.println("Don't know about host.");
                   System.exit(1);
        			e.printStackTrace();
        		} catch (IOException e) {
        			System.err.println("Couldn't get I/O for the connection to server.");
        			System.exit(1);
        			e.printStackTrace();
        		}    		
    	}catch (Exception e) {throw new RuntimeException(e);}
	
		
    }
     else	
      { p.close();
    	  String src=""; 
            try {
			  
    	     src=srcFileName;
    		  srcFileName=new java.io.File( "." ).getCanonicalPath()+File.separator +srcFileName;
		
    	  } catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
    	    System.out.println("oop");
    	    System.out.println(srcFileName);
    	    File fileName = new File(srcFileName);
    			
    			BufferedReader brf=null;
    			BufferedReader br=null;
    			/*try {
    	    		System.out.println("opp");
    				
    	    		
    	    	//BufferedReader*/ 
    			try{
    	    		brf = new BufferedReader(new InputStreamReader(
    	    			new FileInputStream(fileName.getAbsolutePath())));
    	    		System.out.println("opa");
    			}catch ( IOException ioe ) {throw new RuntimeException(ioe);}
    	    	
    	    	try {
    	    		 soc = new Socket(socket_ip, socket_port);
    	    		 
    	    		 //check("","",srcFileName);
    	    		 //brf= new BufferedReader(new InputStreamReader(soc.getInputStream()));
    	    		
    	    		/*if ( brf == null )
    	    			throw new RuntimeException("Cannot read from closed file "
    						                       + fileName.getAbsolutePath() + ".");
    	    		
    	     		try {
    	     			//soc = new Socket("10.0.0.21", 2001);
    	     			System.out.println("opa");
    	        			soc = new Socket(socket_ip, socket_port);
    	        			System.out.println( " kkkjio");
    	        			//System.out.println(soc);
    	        			*/
    	        			PrintWriter pw=null;
    	        			pw=new PrintWriter(soc.getOutputStream(), true);
    	                       /*for(String list : fileName.list())
    	                        {
    	                        	pw.println(list);
    	                        	System.out.println(list);
    	                        }
    	                        pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
    	                		pw.println(remote_fileName); //Signaling message about remote file name

    	                		//String[] line=fileName.list(); //= brf.readLine();
    	                		*/int counter=0;
    	                		System.out.println(first+" 5r55ddd "+last);
    	                		//if(check("Henry","Levin",src))
    	                		//{
    	                			String line = brf.readLine();
        	            		   while ( line != null)
        	            		   {
        	            			System.out.println(line);
        	            			pw.println(line);
        	            			counter++;
        	            			line = brf.readLine();
        	            		   }
    	                		//}
    	                		//else{
    	                			//pw.println("InvalidAccess");
    	                		//}
    	                		/*for (  int i=0; i<fileName.list().length; i++)
    	                			{System.out.println(fileName.list()[i]);
    	                			
    	                			 pw.println(fileName.list()[i]);
    	                			 System.out.println(fileName.list()[i]);
    	                			 
    	                			 counter++;
    	                			//line = brf.readLine();
    	                			
    	                			}*/
    	                		//pw.close();
    	                    //PrintWriter   p=new PrintWriter(soc.getOutputStream(), true);
    	                      
    	                        //if(counter==fileName.list().length-1)
    	                        //pw.close();
    	                        
    	                        
    	                        
    	                        
    	                        
    	                     
    	                        
    	                        //String name=br.readLine();
    	            		/*pw.println("StartXfer"); //Signaling message to start xfer to the remote socket server     		
    	            		pw.println(remote_fileName); //Signaling message about remote file name

    	            		String line = brf.readLine();
    	            		int counter=0;

    	            		while ( line != null){
    	            			System.out.println(line);
    	            			pw.println(line);
    	            			counter++;
    	            			line = brf.readLine();
    	            		}
    	            		
    	System.out.println(line);
    	            		pw.println("Done"); //Signaling message to terminate the remote socket server
    	                        System.out.println("over");
    	                        //br.close();
    	            		//brf.close();
    	            		//pw.close();*/
    	                		pw.close();
    	            		soc.close();
    	                			
    	     		} catch (UnknownHostException e) {
    	        			System.err.println("Don't know about host.");
    	                   System.exit(1);
    	        			e.printStackTrace();
    	        		} catch (IOException e) {
    	        			System.err.println("Couldn't get I/O for the connection to server.");
    	        			System.exit(1);
    	        			e.printStackTrace();
    	        		}    		
    	    	catch (Exception e) {throw new RuntimeException(e);}
    		
    		}
    	    
      
	
	}  // method RPC_FileRead()


	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
	    args = new String[1];
	    args[0]="2037";
		
	    System.setProperty("java.security.policy", "server.policy");
		  if (System.getSecurityManager() == null) {
	       
			
	            System.setSecurityManager(new RMISecurityManager());
	        }
	    
			  RMI_BioAPI_AsteriskJava_Server  sv = new RMI_BioAPI_AsteriskJava_Server(Integer.parseInt(args[0]));
			  //sv.filelist();
			  LocateRegistry.createRegistry(1095);
	  		Registry reg= LocateRegistry.getRegistry();
	  		Naming.rebind("rmi://localhost:1095/rec",sv);

			
	    
	
		
	    
	    if (args.length != 1)
	    {
	            System.out.println
	                ("Syntax - java RMI_BioAPI_AsteriskJava_Server_Package/RMI_BioAPI_AsteriskJava_Server_Impl host_port");
	            System.exit(1);
	    }
		
        // Create an instance of our service server ...
		//
		  
		  /* grant codebase "c://Users/shelton//workspace//src//P5" {
			    permission java.security.AllPermission;
			};*/
		
	   
	   // RMI_BioAPI_AsteriskJava_Interface st = (RMI_BioAPI_AsteriskJava_Interface) UnicastRemoteObject.exportObject(sve, 2035);
	    System.out.println("RmiRegistry listens at port 1095 ");
            System.out.println("AsteriskJava BSP Server is ready to listen on " + args[0]);
 //            System.out.println(InetAddress.getLocalHost().getHostName());
           
            
            
            //ServerSocket s=new ServerSocket(2035);
    	    //Socket sock;
            //Naming.rebind("rec",sv);
           
            System.out.println("BioAPI AsteriskJava RMI server starts ... ");
            //sock=s.accept() ;
////System.out.println(new File( "." ).getAbsolutePath());
        //BufferedReader    b = new BufferedReader(new InputStreamReader(sock.getInputStream()));
	
	//String m= b.readLine();
	
           
	//PrintStream p =new PrintStream(sock.getOutputStream());
	
	//p.println("lll");
	}

}

