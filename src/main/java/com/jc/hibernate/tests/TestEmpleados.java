package com.jc.hibernate.tests;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.jc.hibernate.modelo.Empleado;

@SuppressWarnings("unchecked")
public class TestEmpleados {
	
	
	private static EntityManager manager;
	private static EntityManagerFactory emf;
	
	public static void main(String[] args){
		
		/**
		 * Crear el gestor de persistencia (EM).
		 */
		emf = Persistence.createEntityManagerFactory("aplicacion");
		manager = emf.createEntityManager();

		List<Empleado> empleados =  manager.createQuery("FROM Empleado").getResultList();
		System.out.println("En esta base de datos hay " + empleados.size() + " empleados");
	}
	
}
