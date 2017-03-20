package com.mindtree.bean;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "book_reservation")
public class BookReservation {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int reservationId;
	@JoinColumn(name="book_id")
	@OneToOne
	private Book book;
	public BookReservation(Book book, int visitorId, Date reservationDate, Date expirationDate) {
		super();
		this.book = book;
		this.visitorId = visitorId;
		this.reservationDate = reservationDate;
		this.expirationDate = expirationDate;
	}
	public BookReservation() {
		// TODO Auto-generated constructor stub
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setId(int id) {
		this.reservationId = id;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getVisitorId() {
		return visitorId;
	}
	public void setVisitorId(int visitorId) {
		this.visitorId = visitorId;
	}
	public Date getReservationDate() {
		return reservationDate;
	}
	public void setReservationDate(Date reservationDate) {
		this.reservationDate = reservationDate;
	}
	public Date getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}
	@Column(name="visitor_id")
	private int visitorId;
	@Column(name="reservation_date")
	private Date reservationDate;
	@Column(name="expiration_date")
	private Date  expirationDate;

}
