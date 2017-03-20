package com.mindtree.dao;

import org.hibernate.Session;

import com.mindtree.util.HibernateUtil;

public class TestClass {
	public static void main(String[] args)
	{
		Session session=HibernateUtil.getSessionFactory().openSession();
		session.close();
		System.out.print("Test");
	}
}
