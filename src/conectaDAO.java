
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class conectaDAO {
    
        Connection conn = null;
         
        String url;
        String user;
        String password;
    
    public boolean connectDB(){
       
        
        url = "jdbc:mysql://localhost:3306/uc11";
        user = "root";
        password = "88664897";
        
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url,user,password);
            return true;
            
        } catch (ClassNotFoundException | SQLException ex){
            JOptionPane.showMessageDialog(null, "Erro ConectaDAO" + ex.getMessage());
            return false;
        }
        
    }
    public void desconectar(){
        try{
            conn.close();;
        }catch(SQLException ex){
            
        }
    }
    
}
