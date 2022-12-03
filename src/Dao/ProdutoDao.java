/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import jdbc.ConnectionFactory;
import model.Produto;

/**
 *
 * @author allan
 */
public class ProdutoDao {
    
    private Connection con;

    public ProdutoDao() {
        this.con = new ConnectionFactory().getConnection();
    }
    
    public void cadastrarProduto(Produto obj){
        
        try {

            //Criar comando sql            
            String sql = "insert into tb_produto(codigo, produto, valorCompra, valorVenda, marca, quantidade) "
                    + "value(?,?,?,?,?,?)";

            //conectar o banco de dados e organizar o comando sql
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCodigo());
            stmt.setString(2, obj.getProduto());
            stmt.setDouble(3, obj.getValorCompra());
            stmt.setDouble(4, obj.getValorVenda());
            stmt.setString(5, obj.getMarca());
            stmt.setInt(6, obj.getQuantidade());

            //Executar o comando sql
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Cadastrado com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro " + erro);
        }
        
    }
    
    public void editarProduto(Produto obj){
        
        try {

            // 1° passo - Criar o comando sql.
            String sql = "UPDATE tb_produto SET codigo = ? ,produto = ? ,valorCompra = ?, valorVenda = ?, marca = ? ,quantidade = ? WHERE id = ?";

            // 2° passo - Conectar o banco de dados e organizar o comando sql.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCodigo());
            stmt.setString(2, obj.getProduto());
            stmt.setDouble(3, obj.getValorCompra());
            stmt.setDouble(4, obj.getValorVenda());
            stmt.setString(5, obj.getMarca());
            stmt.setInt(6, obj.getQuantidade());
            stmt.setInt(7, obj.getId());

            // 3° passo - executor o comando sql.
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Alterdo com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        
    }
    
    public void excluirProduto(Produto obj){
        
        try {

            // 1° passo - Criar o comando sql.
            String sql = "delete from tb_produto where id = ?";

            // 2° passo - Conectar o banco de dados e organizar o comando sql.
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId());

            // 3° passo - executor o comando sql.
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Excluído com sucesso!");

        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro: " + erro);
        }
        
    }
    
    public List<Produto> listarProduto(){
        
        try {
            //criar a lista
            List<Produto> listar = new ArrayList<>();

            //Criar o comando sql
            String sql = "select * from tb_produto";
            PreparedStatement stmt = con.prepareStatement(sql);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();

                obj.setId(rs.getInt("id"));
                obj.setCodigo(rs.getInt("codigo"));
                obj.setProduto(rs.getString("produto"));
                obj.setValorCompra(rs.getDouble("valorCompra"));
                obj.setValorVenda(rs.getDouble("valorVenda"));
                obj.setMarca(rs.getString("marca"));
                obj.setQuantidade(rs.getInt("quantidade"));

                listar.add(obj);

            }
            
            return listar;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

            return null;
        }
        
    }
    
    public List<Produto> buscarClientePorNome(String produto){
        
        try {
            //criar a lista
            List<Produto> listar = new ArrayList<>();

            //Criar o comando sql
            String sql = "select * from tb_produto where produto like ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, produto);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produto obj = new Produto();

                obj.setId(rs.getInt("id"));
                obj.setCodigo(rs.getInt("codigo"));
                obj.setProduto(rs.getString("produto"));
                obj.setValorCompra(rs.getDouble("valorCompra"));
                obj.setValorVenda(rs.getDouble("valorVenda"));
                obj.setMarca(rs.getString("marca"));
                obj.setQuantidade(rs.getInt("quantidade"));

                listar.add(obj);

            }
            
            return listar;

        } catch (SQLException erro) {

            JOptionPane.showMessageDialog(null, "Erro: " + erro);

            return null;
        }
    }
    
}
