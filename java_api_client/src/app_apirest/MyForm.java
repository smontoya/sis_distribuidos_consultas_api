package app_apirest;
import java.awt.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import javax.swing.JTable.*;
import javax.swing.JScrollPane.*;
import javax.swing.table.DefaultTableModel;

import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

import java.io.*;
import java.net.*;
import java.util.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class MyForm {
    JDialog miDialogo;
    JPanel miPanel1;
    JLabel miLabelId,miLabelRut,miLabelNombre,miLabelApellido,miLabelSeccion,miLabelMail,miLabelPassword;
    JTextField txtId,txtRut,txtNombre,txtApellido,txtMail;
    JPasswordField txtPassword = new JPasswordField(20);
    JButton btnBuscar,btnGrabar,btnEditar,btnEliminar,btnListar;
    String[] strDepartamentos = {"", "FINANZAS Y CONTABILIDAD", "GERENCIA", "RR.HH."};
    JComboBox cmbSeccion = new JComboBox(strDepartamentos);
                      
    DefaultTableModel miTableDefaultTableModel;
    JTable miTable;

    
    public MyForm(){
        this.flowLayout();
        miDialogo=new JDialog(new Frame(),"CHUPALO");
        miDialogo.setSize(830,380);
        miDialogo.setTitle("MANTENEDOR DE USUARIOS");
        miDialogo.setVisible(true);
        miDialogo.setModal(true);
        miDialogo.setResizable(true);
        miDialogo.add(miPanel1);
        miDialogo.addWindowListener(new WindowAdapter(){
           public void windowClosing(WindowEvent evt){
               miDialogo.dispose();
           } 
        });
    }
    
    
    public void flowLayout(){
        miPanel1 = new JPanel(new FlowLayout(FlowLayout.LEFT));
        
        miLabelId = new JLabel("Id");
        txtId = new JTextField("",20);
        miLabelRut = new JLabel("Rut");
        txtRut = new JTextField("",20);
        miLabelNombre = new JLabel("Nombre");
        txtNombre = new JTextField("",20);
        miLabelApellido = new JLabel("Apellido");
        txtApellido = new JTextField("",20);
        miLabelSeccion = new JLabel("Seccion");
        miLabelMail = new JLabel("Mail");
        txtMail = new JTextField("",20);
        miLabelPassword = new JLabel("Password");
        
        btnBuscar = new JButton("Buscar");
        btnGrabar = new JButton("Grabar");
        btnEditar = new JButton("Editar");
        btnEliminar = new JButton("Eliminar");
        btnListar = new JButton("Listar");

        miPanel1.add(miLabelId);
        miPanel1.add(txtId);
        miPanel1.add(miLabelRut);
        miPanel1.add(txtRut);
        miPanel1.add(miLabelNombre);
        miPanel1.add(txtNombre);
        miPanel1.add(miLabelApellido);
        miPanel1.add(txtApellido);
        //miPanel1.add(miLabelSeccion);
        //miPanel1.add(cmbSeccion);
        miPanel1.add(miLabelMail);
        miPanel1.add(txtMail);
        miPanel1.add(miLabelPassword);
        miPanel1.add(txtPassword);


        miPanel1.add(btnBuscar);
        miPanel1.add(btnGrabar);
        miPanel1.add(btnEditar);
        miPanel1.add(btnEliminar);
        miPanel1.add(btnListar);


        miTableDefaultTableModel=new DefaultTableModel();
        miTable=new JTable(miTableDefaultTableModel);
        miTable.setPreferredScrollableViewportSize(new Dimension(700,200));
        miTableDefaultTableModel.addColumn("ID");
        miTableDefaultTableModel.addColumn("RUT");
        miTableDefaultTableModel.addColumn("NOMBRE");
        miTableDefaultTableModel.addColumn("APPELLIDO");
        //miTableDefaultTableModel.addColumn("SECCION");
        miTableDefaultTableModel.addColumn("MAIL");
        miTableDefaultTableModel.addColumn("PASSWORD");
        JScrollPane scrollpane=new JScrollPane(miTable);

        miPanel1.add(scrollpane);

        listar();

        btnBuscar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                int indiceFila= miTable.getSelectedRow();
                int indiceColumna= miTable.getSelectedColumn();
                // JOptionPane.showMessageDialog(null,miTable.getSelectedRow(),"indice seleccionado",JOptionPane.PLAIN_MESSAGE);
                //JOptionPane.showMessageDialog(null,miTableDefaultTableModel.getValueAt(indiceFila, 0),"Celda",JOptionPane.PLAIN_MESSAGE);
                //JOptionPane.showMessageDialog(null,miTableDefaultTableModel.getValueAt(indiceFila, 1),"Celda",JOptionPane.PLAIN_MESSAGE);
                //JOptionPane.showMessageDialog(null,miTableDefaultTableModel.getValueAt(indiceFila, 2),"Celda",JOptionPane.PLAIN_MESSAGE);
                //JOptionPane.showMessageDialog(null,miTableDefaultTableModel.getDataVector().elementAt(indiceFila),"Fila Completa",JOptionPane.PLAIN_MESSAGE);
                buscar(miTableDefaultTableModel.getValueAt(indiceFila, 0).toString());
            }
        });

        btnGrabar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txtRut.getText().length()==0 || txtNombre.getText().length()==0 || txtApellido.getText().length()==0 || txtMail.getText().length()==0 || txtPassword.getText().length()==0){
                    txtRut.requestFocus();
                    JOptionPane.showMessageDialog(null, "Faltan Datos...", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }else{
                    grabar();
                }
            }
        });

        btnEditar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txtId.getText().length()==0){
                    txtId.requestFocus();
                    JOptionPane.showMessageDialog(null, "Debe ingresar el Id", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }else{
                    editar(txtId.getText().toString());
                }
            }
        });

        btnEliminar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                if(txtId.getText().length()==0){
                    txtId.requestFocus();
                    JOptionPane.showMessageDialog(null, "Debe ingresar el Id", "Mensaje", JOptionPane.ERROR_MESSAGE);
                }else{
                    eliminar(txtId.getText().toString());
                }
            }
        });

        btnListar.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                listar();
            }
        });
         
    }

    public void listar(){
        try{
            System.out.println(miTableDefaultTableModel.getRowCount());
            while( miTableDefaultTableModel.getRowCount() > 0)
                miTableDefaultTableModel.removeRow(0);
        }catch(Exception e){
            //System.out.println(e);
        }

        try {
            URL url = new URL("http://158.170.198.111:8080/api/usuarios/");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output;
            //System.out.println("Output from Server .... \n");
            while ((output = br.readLine()) != null) {
                try {

                    //JSONParser jsonParser = new JSONParser();
                    //JSONObject jsonObject = (JSONObject) jsonParser.parse(output);
                    JSONArray jsonArr = (JSONArray) new JSONParser().parse(output);

                    for(int i=0; i<jsonArr.size(); i++){
                        JSONObject json = (JSONObject) jsonArr.get(i);
                        //System.out.println(json.get("_id")+" "+json.get("rut")+" "+json.get("nombres")+" "+json.get("apellidos")
                        //    +" "+json.get("email")+" "+json.get("seccion")+" "+json.get("password")+" "+json.get("estado"));

                        Object[] data3=new Object[7];
                        data3[0]=json.get("_id");
                        data3[1]=json.get("rut");
                        data3[2]=json.get("nombres");
                        data3[3]=json.get("apellidos");
                        //data3[4]=json.get("seccion");
                        data3[4]=json.get("email");
                        data3[5]=json.get("password");
                        miTableDefaultTableModel.addRow(data3);
                    }

                }catch(ParseException e) {
                    e.printStackTrace();
                }catch(Exception e) {
                    e.printStackTrace();
                }
            }

            conn.disconnect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }  
    }

    public void buscar(String value){

        try {
            URL url = new URL("http://158.170.198.111:8080/api/usuarios/"+value);

            //JOptionPane.showMessageDialog(null,url,"indice seleccionado",JOptionPane.PLAIN_MESSAGE);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }

            BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

            String output=br.readLine();
            System.out.println(output);
            try {
                //JSONArray jsonArr = (JSONArray) new JSONParser().parse(output);
                JSONObject json = (JSONObject) new JSONParser().parse(output);
                //JSONObject json = (JSONObject) jsonArr.get(0);
                //System.out.println(json.get("_id")+" "+json.get("rut")+" "+json.get("nombres")+" "+json.get("apellidos")
                //   +" "+json.get("email")+" "+json.get("seccion")+" "+json.get("password")+" "+json.get("estado"));

                txtId.setText("");
                txtRut.setText("");
                txtNombre.setText("");
                txtApellido.setText("");
                txtMail.setText("");
                txtPassword.setText("");

                txtId.setText(json.get("_id").toString());
                txtRut.setText(json.get("rut").toString());
                txtNombre.setText(json.get("nombres").toString());
                txtApellido.setText(json.get("apellidos").toString());
                txtMail.setText(json.get("email").toString());
                txtPassword.setText(json.get("password").toString());
                
            }catch(ParseException e) {
                e.printStackTrace();
            }

            conn.disconnect();
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void grabar(){
        try {
            //URL url = new URL("http://localhost:8080/api/usuarios?rut="+txtRut.getText()+"&nombres="+txtNombre.getText()+"&apellidos="+txtApellido.getText());
            URL url = new URL("http://158.170.198.111:8080/api/usuarios/");
            Map<String,Object> params = new LinkedHashMap<String,Object>();
            params.put("rut", txtRut.getText());
            params.put("nombres", txtNombre.getText());
            params.put("apellidos", txtApellido.getText());
            params.put("email", txtMail.getText());
            params.put("password", txtPassword.getText());

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }else{
                JOptionPane.showMessageDialog(null,"Ha sido grabado el usuario...","GRABAR",JOptionPane.PLAIN_MESSAGE);
            }

            conn.disconnect();
            /*

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setDoOutput(true);
            conn.setInstanceFollowRedirects( false );
            
            conn.setRequestMethod("POST");
            //conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

            conn.setRequestProperty("Accept", "application/json");
            conn.addRequestProperty("nombres", "eso sin hueso");

            String input = "\"rut\":\""+txtRut.getText()+"\",\"nombres\":\""+txtNombre.getText()+"\",\"apellidos\":\""+txtApellido.getText()+"\",\"email\":\""+txtMail.getText()+"\",\"password\":\""+txtPassword.getText()+"\"";

            //System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();
            

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }else{
                JOptionPane.showMessageDialog(null,"Ha sido grabado el usuario...","GRABAR",JOptionPane.PLAIN_MESSAGE);
            }

            conn.disconnect();

            //listar();
*/
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void editar(String value){
        try {

            URL url = new URL("http://158.170.198.111:8080/api/usuarios/"+value);
            Map<String,Object> params = new LinkedHashMap<String,Object>();
            params.put("rut", txtRut.getText());
            params.put("nombres", txtNombre.getText());
            params.put("apellidos", txtApellido.getText());
            params.put("email", txtMail.getText());
            params.put("password", txtPassword.getText());

            StringBuilder postData = new StringBuilder();
            for (Map.Entry<String,Object> param : params.entrySet()) {
                if (postData.length() != 0) postData.append('&');
                postData.append(URLEncoder.encode(param.getKey(), "UTF-8"));
                postData.append('=');
                postData.append(URLEncoder.encode(String.valueOf(param.getValue()), "UTF-8"));
            }
            byte[] postDataBytes = postData.toString().getBytes("UTF-8");

            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.getOutputStream().write(postDataBytes);

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }else{
                JOptionPane.showMessageDialog(null,"Ha sido editado el usuario...","EDITAR",JOptionPane.PLAIN_MESSAGE);
            }

            conn.disconnect();


            /*
            URL url = new URL("http://localhost:8080/api/usuarios/"+value);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setDoOutput(true);
            conn.setRequestMethod("PUT");
            conn.setRequestProperty("Accept", "application/json");

            String input = "{\"rut\":\""+txtRut.getText()+"\",\"nombres\":\""+txtNombre.getText()+"\",\"apellidos\":\""+txtApellido.getText()+"\",\"email\":\""+txtMail.getText()+"\",\"password\":\""+txtPassword.getText()+"\",\"seccion\":\"proyectos\",\"estado\":\"true\"}";
            System.out.println(input);
            OutputStream os = conn.getOutputStream();
            os.write(input.getBytes());
            os.flush();

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }else{
                JOptionPane.showMessageDialog(null,"Ha sido editado el usuario...","EDITAR",JOptionPane.PLAIN_MESSAGE);
            }

            conn.disconnect();
             * */

        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void eliminar(String value){

        try {
            URL url = new URL("http://158.170.198.111:8080/api/usuarios/"+value);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("DELETE");
            conn.setRequestProperty("Accept", "application/json");

            if (conn.getResponseCode() != 200) {
                throw new RuntimeException("Failed : HTTP error code : " + conn.getResponseCode());
            }else{
                JOptionPane.showMessageDialog(null,"Ha sido eliminado el usuario...","ELIMINAR",JOptionPane.PLAIN_MESSAGE);
            }

            conn.disconnect();

            listar();
            
        }catch(MalformedURLException e){
            e.printStackTrace();
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
    
    public void letras(JTextField a){
        a.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (Character.isDigit(c)){
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }
    
    public void numeros(JTextField a){
        a.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                char c = e.getKeyChar();
                if (Character.isLetter(c)){
                    java.awt.Toolkit.getDefaultToolkit().beep();
                    e.consume();
                }
            }
        });
    }
}
