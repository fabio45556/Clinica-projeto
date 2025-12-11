package controllers;

import play.mvc.Controller;
import java.util.List;

import models.Paciente;

public class Pacientes extends Controller {
	
	public static void form() {
		render();	
	}

	public static void listar(String termo) {
		List<Paciente> pacientes = null;
		if (termo == null) {
			pacientes = Paciente.findAll();
		} else {
			pacientes = Paciente.find("lower(nome) like ?1 or email like ?1", "%" + termo.toLowerCase() + "%").fetch();
		}
		render(pacientes, termo);
	}

	public static void salvar(Paciente p) {
		String mensagem = "Cadastrado com sucesso!";
		if (p.id != null) {
	        mensagem = "Editado com sucesso!";
		}
		p.save();
	    flash.success(mensagem);
	    listar(null);
	}


    public static void editar(Long id){
	    Paciente p = Paciente.findById(id);
	    renderTemplate("Pacientes/form.html", p);
    }
    
    public static void remover(Long id) {
	    Paciente p = Paciente.findById(id);
	    p.delete();
	    flash.success("removido com sucesso");
	    listar(null);
    }

}