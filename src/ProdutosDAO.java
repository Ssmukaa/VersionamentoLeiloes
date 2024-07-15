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
    
    public List<ProdutosDTO> listarProdutos(String status){
            
        
        try {
            conectaDAO conector = new conectaDAO();
            conector.connectDB();
            
            String filtro = ("Select * from produtos");
            
            if(!status.isEmpty()){
                filtro = filtro + "where status = ?";
                prep = conector.conn.prepareStatement(filtro);
                prep.setString(1,status);
            }else{
            
            prep = conector.conn.prepareStatement(filtro);
            }
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
    
    public int atualizar(ProdutosDTO produto){
        conectaDAO conector = new conectaDAO();
        conector.connectDB();
       
        int status;
        try {
             prep = conector.conn.prepareStatement("update produtos set status = ? where id = ?");
             prep.setString(1,produto.getStatus());
             prep.setInt(2,produto.getId());
             status = prep.executeUpdate();
             return status;
            
        }catch(SQLException ex) {
            System.out.println("Erro "+ ex.getMessage());
            return ex.getErrorCode();
        }
    }
    
    public ProdutosDTO consultar(int id){
            conectaDAO conector = new conectaDAO();
            conector.connectDB();
            
            ProdutosDTO produto = new ProdutosDTO();
             
        try {
            
            prep = conector.conn.prepareStatement("Select * from produtos where id = ? ");
            prep.setInt(1,id);
            resultset = prep.executeQuery();
            
            if(resultset.next()){
                produto.setId(resultset.getInt("id"));
                produto.setNome(resultset.getString("Nome"));
                produto.setValor(resultset.getDouble("Valor"));
                produto.setStatus(resultset.getString("Status"));
                return produto;
            }else{
                return null;
            }

        }catch(SQLException ex) {
            System.out.println("Erro "+ ex.getMessage());
            return null;
            }
    }
    
        
}

