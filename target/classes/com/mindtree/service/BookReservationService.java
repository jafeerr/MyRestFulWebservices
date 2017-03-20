package com.mindtree.service;

import java.sql.Date;
import java.util.List;

import com.mindtree.bean.Book;
import com.mindtree.bean.BookReservation;

public interface BookReservationService {
	public Book addBook(String title);

	public BookReservation reserveBook(int visitorId, int bookId);

	public List<BookReservation> getReservedBooksBasedOnRangeOfDate(String dateParam, Date fromDate, Date toDate);

	public int removeBook(int bookId);
}
