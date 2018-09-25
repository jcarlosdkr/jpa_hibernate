package com.jc.hibernate.tests;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jc.hibernate.modelo.Empleado;

public class TestEmpleados {
		
	private static EntityManagerFactory emf;
	
	public static void main(String[] args){
		
		/**
		 * Crear el gestor de persistencia (EM).
		 */
		emf = Persistence.createEntityManagerFactory("aplicacion");
		EntityManager manager = emf.createEntityManager();
				
		Empleado e = new Empleado(042210050L, "Alicia", "Hernández", new GregorianCalendar(1986,8,10).getTime());
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
		
		imprimirTodo();
		
		manager = emf.createEntityManager();
		manager.getTransaction().begin();
		e.setNombre("Romina");
		manager.merge(e);
		manager.getTransaction().commit();
		imprimirTodo();
		
	}

	private static void insertInicial() {
		EntityManager manager = emf.createEntityManager();
		Empleado e = new Empleado(042210500L, "Juan Carlos", "Higuera Sánchez", new GregorianCalendar(1986,9,18).getTime());
		
		manager.getTransaction().begin();
		manager.persist(e);
		manager.getTransaction().commit();
		manager.close();
	}

	@SuppressWarnings("unchecked")
	public static void imprimirTodo(){
		EntityManager manager = emf.createEntityManager();
		List<Empleado> emps = manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay " + emps.size() + " Empleados en el sistema.");
		for(Empleado emp : emps){
			System.out.println(emp.toString());
		}
		manager.close();
	}
}
