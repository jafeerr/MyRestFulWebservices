package com.mindtree.controller;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.util.StringUtils;

import com.mindtree.bean.Book;
import com.mindtree.bean.BookReservation;
import com.mindtree.config.AppConfig;
import com.mindtree.service.BookReservationService;
import com.mindtree.util.DateUtil;

@Path("/")
public class BookReservationController {
	AbstractApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	private BookReservationService bookReservationService = (BookReservationService) context
			.getBean("bookReservationService");

	@POST
	@Path("AddBook")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces({ MediaType.APPLICATION_JSON })
	public Response addBook(@QueryParam("bookName") String booktitle) {
		Book book = null;
		if (!StringUtils.isEmpty(booktitle)) {
			book = bookReservationService.addBook(booktitle);
			if (book.getId() != 0) {
				return Response.ok().entity(book).build();
			}
		}
		return Response.status(304).entity("Book Entry is already present in the DB").build();
	}

	@POST
	@Path("ReserveBook")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response reserveBook(@QueryParam("visitorId") int visitorId, @QueryParam("bookId") int bookId) {
		BookReservation bookReservation = null;
		if (visitorId != 0 && bookId != 0) {
			bookReservation = bookReservationService.reserveBook(visitorId, bookId);
			if (bookReservation != null) {
				return Response.accepted().entity(bookReservation).build();
			} else {
				return Response.noContent().entity("Book is already Reserved").build();
			}
		}
		return Response.notModified().entity(bookReservation).build();
	}

	@DELETE
	@Path("DeleteBook")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteBook(@QueryParam("bookId") int bookId) {
		if (bookId != 0) {
			bookId = bookReservationService.removeBook(bookId);
		}
		if (bookId == 0) {
			return Response.notModified().entity("Something Went wrong! check book Id").build();
		}
		return Response.status(202).entity("Book " + bookId + " deleted").build();
	}

	@GET
	@Path("getAllReservedBooksBasedOnRangeOfDate/{dateParam}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservedBooksBasedOnRangeOfDate(@PathParam("dateParam") String dateParam,
			@QueryParam("fromDate") String fromDateString, @QueryParam("toDate") String toDateString) {
		Date fromDate = DateUtil.parseDate(fromDateString);
		Date toDate = DateUtil.parseDate(toDateString);

		if (fromDate != null && toDate != null) {
			List<BookReservation> bookList = bookReservationService.getReservedBooksBasedOnRangeOfDate(dateParam,
					fromDate, toDate);
			GenericEntity<List<BookReservation>> entity = new GenericEntity<List<BookReservation>>(bookList) {
			};
			if (bookList.size() == 0) {
				return Response.noContent().entity("No records Matched").build();
			} else {
				return Response.ok(entity).build();
			}
		}
		return Response.status(400).entity("Dates are Not Valid.! (MM/DD/YYYY)").build();
	}

	@GET
	@Path("getAllReservedBooksBasedOnDate/{dateParam}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getReservedBooksBasedOnDate(@PathParam("dateParam") String dateParam,
			@QueryParam("date") String dateString) {
		Date date = DateUtil.parseDate(dateString);

		if (date != null) {
			List<BookReservation> bookList = bookReservationService.getReservedBooksBasedOnRangeOfDate(dateParam, date,
					date);
			GenericEntity<List<BookReservation>> entity = new GenericEntity<List<BookReservation>>(bookList) {
			};
			if (bookList.size() == 0) {
				return Response.noContent().entity("No records Matched").build();
			} else {
				return Response.ok(entity).build();
			}
		}
		return Response.status(400).entity("Date is Not Valid.! (MM/DD/YYYY)").build();
	}
}