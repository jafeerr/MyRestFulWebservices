package com.mindtree.dao;

import java.sql.Date;
import java.util.List;

import com.mindtree.bean.Book;
import com.mindtree.bean.BookReservation;

public interface BookReservationDao {
	public Book addBook(Book book);

	public BookReservation reserveBook(int visitorId, int bookId);

	public int deleteBook(int bookId);

	public List<BookReservation> getAllReservedBooks(Date fromDate, Date toDate, boolean isBasedOnExpirationDate);
	public Book getBook(int bookId);
}
