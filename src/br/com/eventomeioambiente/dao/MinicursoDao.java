package br.com.eventomeioambiente.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import br.com.eventomeioambiente.conexao.Conexao;
import br.com.eventomeioambiente.modelo.Minicurso;


public class MinicursoDao {
	
	public List<Minicurso> buscarTodosMinicursos() throws SQLException{
		
		Conexao c = new Conexao();
		Connection cc = c.conectar();
		List<Minicurso> minicursos = new ArrayList<Minicurso>();
		String consulta = "SELECT * FROM palestra_minicurso";
		
		Statement stm = (Statement) cc.createStatement();
		ResultSet result = stm.executeQuery(consulta);
		PalestranteDao palestranteDao = new PalestranteDao();
		while (result.next()){
			Minicurso m = new Minicurso();
			m.setIdPalestra(result.getInt("id_palestra_minicurso"));
			m.setPalestrante(palestranteDao.buscarPalestrantePorId(result.getInt("id_palestrante")));
			m.setTipo(result.getInt("id_tipo"));
			m.setTema(result.getString("tema"));
			m.setData(result.getDate("data"));
			m.setQntVagas(result.getInt("qnt_vagas"));
			m.setDescricao(result.getString("descricao"));
		    
			
			 minicursos.add(m);
		}
		c.desconectar();
		
	return minicursos;	
	}

}
