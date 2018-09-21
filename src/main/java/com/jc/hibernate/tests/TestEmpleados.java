package com.jc.hibernate.tests;

import java.util.GregorianCalendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jc.hibernate.modelo.Empleado;

@SuppressWarnings("unused")
public class TestEmpleados {
	
	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args){
		
		/**
		 * Crear el gestor de persistencia (EM).
		 */
		emf = Persistence.createEntityManagerFactory("aplicacion");
		manager = emf.createEntityManager();
		
		Empleado e = new Empleado(042210500L, "Juan Carlos", "Higuera Sánchez", new GregorianCalendar(1986,9,18).getTime());
		Empleado e2 = new Empleado(042210450L, "Sarai Monserrat", "Sánchez Zamora", new GregorianCalendar(1986,8,10).getTime());
		
		manager.getTransaction().begin();
		manager.persist(e);
		manager.persist(e2);
		manager.getTransaction().commit();
		
		imprimirTodo();
	}

	@SuppressWarnings("unchecked")
	public static void imprimirTodo(){
		List<Empleado> emps = manager.createQuery("FROM Empleado").getResultList();
		System.out.println("Hay " + emps.size() + " Empleados en el sistema.");
		for(Empleado emp : emps){
			System.out.println(emp.toString());
		}
	}
}
