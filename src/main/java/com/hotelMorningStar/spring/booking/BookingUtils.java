package com.hotelMorningStar.spring.booking;

import java.io.File;
import java.io.FileWriter;

import org.springframework.stereotype.Component;
import java.util.Properties;
import java.util.Base64;  
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import net.minidev.json.JSONObject;
import java.io.BufferedWriter;


@Component
public class BookingUtils {
	   public File writeToCsv(JSONObject obj){
	 	  File file=new File(obj.get("id")+".csv");
		      try {	    	  
		         BufferedWriter fileWriter = new BufferedWriter(new FileWriter(file));     
		         fileWriter.write("BOOKING DETAILS:");
	          fileWriter.newLine();
		         fileWriter.write(obj.keySet().toString().substring(1,obj.keySet().toString().length()-1));
	          fileWriter.newLine();
	          fileWriter.write(obj.values().toString().substring(1, obj.values().toString().length()-1)); 
	          fileWriter.close();
		      }
		      catch(Exception e) {
		         e.printStackTrace();
		      }   
		      return file;
	}
	   
       public void sendMail(JSONObject obj) {
             Base64.Decoder decoder = Base64.getDecoder();
           String encpwd="";//your gmail password
           String pwd=new String(decoder.decode(encpwd));
           String to = obj.getAsString("uId");
           String from = "";//your gmail in empty string
           String host = "smtp.gmail.com";
           Properties properties = System.getProperties();
           properties.put("mail.smtp.host", host);
           properties.put("mail.smtp.port", "465");
           properties.put("mail.smtp.ssl.enable", "true");
           properties.put("mail.smtp.auth", "true");
           Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication("",pwd );//your gmail in empty string
               }
           });
           session.setDebug(true);
           try {
               MimeMessage message = new MimeMessage(session);
               message.setFrom(new InternetAddress(from));
               message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
               message.setSubject("Your Booking Details For Booking Id "+obj.getAsString("id"));
               StringBuffer sb=new StringBuffer();
               int totalAmt= Integer.parseInt(obj.getAsString("costPerDay")) * Integer.parseInt(obj.getAsString("bookedDays"));
               sb.append("<table border=\"1\" >"
                       +"<thead >"
                       + "<tr >"
                       + "<th >Booking Id</th>"
                       + "<th >Room No.</th>"
                       + "<th>From</th>"
                       + "<th>To</th>"
                       + "<th>Total Amount</th>"
                       + "</tr>"
                       +"</thead>"
                       + "<tbody >"
                       +"<tr >"
                       + "<td>"+obj.getAsString("id")+"</td>"
                       + "<td>"+obj.getAsString("rId")+"</td>"
                       + "<td>"+obj.getAsString("bookedFrom")+"</td>"
                       + "<td>"+obj.getAsString("bookedTo")+"</td>"
                       + "<td>"+totalAmt+"</td>"
                       + "</tr>"
                       +"</tbody>"
                       + "</table>");
               message.setContent(sb.toString(),"text/html");
               Transport.send(message);
           } catch (MessagingException mex) {
               mex.printStackTrace();
           }
	    }   
}
