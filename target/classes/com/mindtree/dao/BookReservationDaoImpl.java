package com.mindtree.dao;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.mindtree.bean.Book;
import com.mindtree.bean.BookReservation;
import com.mindtree.util.DateUtil;
import com.mindtree.util.HibernateUtil;
@Repository("bookReservationDao")
public class BookReservationDaoImpl implements BookReservationDao{
	public Book addBook(Book book) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Integer id=(Integer)session.save(book);
			book.setId(id);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return book;
		}

		return book;

	}

	public BookReservation reserveBook(int visitorId, int bookId) {
		BookReservation reserveBook=null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Book book=(Book)session.get(Book.class, new Integer(bookId));
			reserveBook = new BookReservation(book, visitorId, DateUtil.getCurrentDate(),
					DateUtil.getExpirationDate());

			session.save(reserveBook);
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return reserveBook;
		}

		return reserveBook;

	}

	public int deleteBook(int bookId) {
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Serializable id = new Integer(bookId);
			Object book = session.get(Book.class, id);
			if (book != null) {
				session.delete(book);
			}
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return 0;
		}

		return bookId;

	}

	public List<BookReservation> getAllReservedBooks(Date fromDate, Date toDate,boolean isBasedOnExpirationDate) {
		List<BookReservation> bookList = null;
		StringBuilder queryString=new StringBuilder("from BookReservation where ");
		if(isBasedOnExpirationDate)
			queryString.append("expiration_date >= ? and expiration_date <= ?");
		else
			queryString.append("reservation_date >= ? and reservation_date <= ?");
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			Query query = session.createQuery(queryString.toString());
			query.setDate(0, fromDate);
			query.setDate(1,toDate);
			bookList = (List<BookReservation>)query.list(); 
			transaction.commit();
		} catch (Exception e) {
			e.printStackTrace();
			return bookList;
		}
		return bookList;
	}
	public Book getBook(int bookId)
	{
		Book book=null;
		try {
			Session session = HibernateUtil.getSessionFactory().openSession();
			Transaction transaction = session.beginTransaction();
			
			transaction.commit();
			session.close();
		} catch (Exception e) {
			return book;
		}

		return book;
	}
}
