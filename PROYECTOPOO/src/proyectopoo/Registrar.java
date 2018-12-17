
package proyectopoo;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Registrar {      //Clase para logueo
    String newUser,newPass1,newPass2;
     public String       user,password;
    Statement sentencia = null;
    boolean ingresar;
    ResultSet res=null;
    
public void realizaConexion() throws SQLException{  //Metodo para verificar conexion a la db
            Connection conexion = null;
            String urlDatabase =  "jdbc:postgresql://localhost:5432/postgres"; 
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(urlDatabase, "postgres", "jossdoming");
                 } catch (Exception e) {
                System.out.println("Ocurrio un error : "+e.getMessage());
                                        }
                                          System.out.println("La conexión se realizo sin problemas! =) ");
            
}
    
    public void Registrar() throws SQLException{ // Registrar
               
                Connection conexion = null;
            String urlDatabase =  "jdbc:postgresql://localhost:5432/postgres"; 
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(urlDatabase, "postgres", "jossdoming");
                JOptionPane.showMessageDialog(null, "SE CONECTÓ SATISFACTORIAMENTE A LA BASE DE DATOS");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            
            sentencia = conexion.createStatement();
            
                 
                 newUser=JOptionPane.showInputDialog("INGRESE NOMBRE DE USUARIO A USAR"); //Ingresa un usuario
       
                 newPass1=JOptionPane.showInputDialog("INGRESE SU CONTRASEÑA"); //ingresa y repite pass
                 newPass2=JOptionPane.showInputDialog("REPITA SU CONTRASEÑA");
                     
                         
                     
                     if(newUser!=null&&newPass1 == null ? newPass2 == null : newPass1.equals(newPass2)){ //verifica que las pass sean iguales
                        if(newUser.isEmpty() || newUser.length()>10){
                           JOptionPane.showMessageDialog(null, "EL USUARIO DEBE CONTENER CARACTERES VÁLIDOS Ó NO TENER MÁS DE 10");
                        }else{
                         try{   
                        String sentenciaSql = new String();
                        sentenciaSql = "INSERT INTO registro (usuario,password)";
                        sentenciaSql=sentenciaSql+" VALUES ('"+newUser+"','"+newPass1+"')";
                         
                        sentencia.execute(sentenciaSql);
                        JOptionPane.showMessageDialog(null, "GUARDADO CON ÉXITO");
           
       }catch(SQLException e){
           JOptionPane.showMessageDialog(null, e);
       }
       }  }         else{
                        JOptionPane.showMessageDialog(null, "LAS CONTRASEÑAS SON DIFERENTES");
            //si las contraseñas son distintas no graba en db
                     
                         }
                     
            
    
    }
    public void VerificarCuenta(String user,String password) throws SQLException{
        this.user=user;
        this.password=password;
      Connection conexion = null;
      
      
      
            String urlDatabase =  "jdbc:postgresql://localhost:5432/postgres"; 
            try {
                Class.forName("org.postgresql.Driver");
                conexion = DriverManager.getConnection(urlDatabase, "postgres", "jossdoming");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
            JOptionPane.showMessageDialog(null, "LA CONEXIÓN A LA BASE DE DATOS SE REALIZÓ CORRECTAMENTE");
             
             try{
                 
                 sentencia = conexion.createStatement();
                 String sentenciaSQL = "select*from registro where usuario = '"+user+"' and password = '"+password+"'";
                 sentencia.execute(sentenciaSQL);
                 res = sentencia.executeQuery(sentenciaSQL);
                 if(res.next()){
                     JOptionPane.showMessageDialog(null, "BIENVENIDO "+user);
                     
                  }else{
                     JOptionPane.showMessageDialog(null,"NO SE ENCONTRÓ "+user+" EN LA BASE DE DATOS");
                     
                     
                
                
                 }
                 
                 
                 
            }catch(SQLException e){
                
              JOptionPane.showMessageDialog(null, e);
             }
    }}

