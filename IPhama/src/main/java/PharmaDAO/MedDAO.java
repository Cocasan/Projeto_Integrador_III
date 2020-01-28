/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PharmaDAO;

import Bean.Medicamento;
import Connection.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;


public class MedDAO {
    
    public List<Medicamento> read() {
        Connection con = Conexao.getConection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        List<Medicamento> medicamentos = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM tbl_medicamentos");
            rs = stmt.executeQuery();
            while (rs.next()) {
                Medicamento p = new Medicamento();
                p.setCOD_MED(rs.getInt("cod_med"));
                p.setDES(rs.getString("des"));
                p.setPRECO(rs.getFloat("preco"));
               medicamentos.add(p);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Falha ao obter registro do Banco" + e);
        } finally {
            Conexao.closeConnection(con, stmt, rs);
        }
        return medicamentos;
    }
     public void create(Medicamento p) {
        Connection con = Conexao.getConection();
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO tbl_medicamentos (cod_med,preco,des) values(?,?,?)");
            stmt.setInt(1, p.getCOD_MED());
            stmt.setFloat(2, p.getPRECO());
            stmt.setString(3, p.getDES());

            stmt.execute();
            JOptionPane.showMessageDialog(null, "Inserido com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, " Erro ao inserir: " + e);

        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void Delete(int cod_med) {
        Connection con = Conexao.getConection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement("DELETE FROM tbl_medicamentos WHERE cod_med = ?");
            stmt.setInt(1, cod_med);
            stmt.execute();
            JOptionPane.showMessageDialog(null, "REMOVIDO com sucesso!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FALHA ao remover o registro!");

        } finally {
            Conexao.closeConnection(con, stmt);
        }
    }

    public void Update(Medicamento p) {
        Connection con = Conexao.getConection();
        PreparedStatement stmt = null;

        try {
            stmt = con.prepareStatement(" UPDATE tbl_medicamentos set cod_med = ? ,"
                  + "preco = ?, des = ? where cod_med =?");
            stmt.setInt(1, p.getCOD_MED());
            stmt.setFloat(2, p.getPRECO());
            stmt.setString(3, p.getDES());
            stmt.execute();
              JOptionPane.showMessageDialog(null, "ALTERADO com Sucesso!"
                      + "");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "FALHA ao atualizar registro!");
            
        }finally{
            Conexao.closeConnection(con,stmt);
            
        }
    }
}
