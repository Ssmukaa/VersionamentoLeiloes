/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


public class ProdutosDAO {
    
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public int cadastrarProduto (ProdutosDTO produto){
        conectaDAO conector = new conectaDAO();
        conector.connectDB();
        
        int Status;
        try {
            prep = conector.conn.prepareStatement("insert into produtos (nome,valor,status) values (?,?,?)");
            prep.setString(1,produto.getNome());
            prep.setDouble(2,produto.getValor());
            prep.setString(3,produto.getStatus());
            Status = prep.executeUpdate();
            return Status;
            
        } catch (SQLException ex) {
            System.out.println("Erro "+ ex.getMessage());
            return ex.getErrorCode();
        }
        
        
        
        
    }
    
    public List<ProdutosDTO> listarProdutos(){
            
        try {
            conectaDAO conector = new conectaDAO();
            conector.connectDB();
            prep = conector.conn.prepareStatement("Select * from produtos");
            resultset = prep.executeQuery();
            
            List<ProdutosDTO> lista = new ArrayList<>();
            
            while(resultset.next()){
                ProdutosDTO p = new ProdutosDTO();
                p.setId(resultset.getInt("id"));
                p.setNome(resultset.getString("nome"));
                p.setValor(resultset.getDouble("valor"));
                p.setStatus(resultset.getString("status"));
                
                lista.add(p);
                
            }
            return lista;
            
        }catch(SQLException ex) {
            System.out.println("Erro " + ex.getMessage());
            return null;
        }
        
    }
    
    
    
        
}

