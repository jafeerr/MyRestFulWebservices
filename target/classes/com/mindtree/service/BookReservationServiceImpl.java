package com.mindtree.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mindtree.bean.Book;
import com.mindtree.bean.BookReservation;
import com.mindtree.dao.BookReservationDao;
import com.mindtree.util.DateUtil;

@Service("bookReservationService")
public class BookReservationServiceImpl implements BookReservationService {
	@Autowired
	private BookReservationDao bookReservationDao;
	public static final String PATH_EXPIRATION_DATE = "ExpirationDate";
	public static final String PATH_RESERVATION_DATE = "ReservationDate";

	public Book addBook(String title) {
		Book book = new Book();
		book.setTitle(title);
		return bookReservationDao.addBook(book);
	}

	public BookReservation reserveBook(int visitorId, int bookId) {
		boolean isAlreadyBooked = false;
		List<BookReservation> bookReservationList = bookReservationDao.getAllReservedBooks(DateUtil.getCurrentDate(),
				DateUtil.getExpirationDate(), true);
		for (BookReservation bookReservation : bookReservationList) {
			if (bookId == bookReservation.getBook().getId()) {
				isAlreadyBooked = true;
				break;
			}
		}
		if (isAlreadyBooked)
			return null;
		else
			return bookReservationDao.reserveBook(visitorId, bookId);
	}

	public List<BookReservation> getReservedBooksBasedOnRangeOfDate(String dateParam, Date fromDate, Date toDate) {
		return bookReservationDao.getAllReservedBooks(fromDate, toDate,(PATH_EXPIRATION_DATE.equals(dateParam))?true:false );
	}
	public int removeBook(int bookId)
	{
		return bookReservationDao.deleteBook(bookId);
	}
}
