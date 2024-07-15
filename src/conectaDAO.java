
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;



public class conectaDAO {
    
        Connection conn;
    
    public boolean connectDB(){
       
        
        String url = "jdbc:mysql://localhost:3306/uc11";
        String user = "root";
        String password = "88664897";
        
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
