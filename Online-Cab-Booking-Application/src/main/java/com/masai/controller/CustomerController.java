package com.masai.controller;

import java.util.List; 

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.masai.exceptions.CustomerException;
import com.masai.exceptions.TripBookingException;
import com.masai.model.BillDetails;
import com.masai.model.Customer;
import com.masai.model.TripBooking;
import com.masai.model.TripBookingDTO;
import com.masai.service.CustomerService;
import com.masai.service.TripBookingService;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

	@Autowired
	private CustomerService cService;
	
	@Autowired
	private TripBookingService tService;
	
	@Autowired
	 public TripBookingService tripBookingService;
	
	@PostMapping("/create")
	public ResponseEntity<Customer> saveCustomer(@Valid @RequestBody Customer customer) throws CustomerException {
		
		Customer savedCustomer= cService.createCustomer(customer);
		
		
		return new ResponseEntity<Customer>(savedCustomer,HttpStatus.CREATED);
	}
	
	@PutMapping("/update")
	public  ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer,@RequestParam(required = false) String key ) throws CustomerException {
		
		
		Customer updatedCustomer= cService.updateCustomer(customer, key);
				
		return new ResponseEntity<Customer>(updatedCustomer,HttpStatus.OK);
		
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) throws CustomerException{
		
		String DeleteCustomer = cService.deleteCustomer(customer);
		
		return new ResponseEntity<String>(DeleteCustomer,HttpStatus.OK);
	}
	
	@PostMapping("/tripbooking")
	public ResponseEntity<TripBooking> registerTripBooking(@RequestBody TripBookingDTO tripBooking) throws TripBookingException{
		
		TripBooking savedBooking=tripBookingService.insertTripBooking(tripBooking);
		return new ResponseEntity<TripBooking>(savedBooking,HttpStatus.OK);	
		
	}
	@DeleteMapping("/canceltrip/{customerId}")
	public ResponseEntity<String> deleteTripBooking(@PathVariable Integer customerId) throws TripBookingException{
		
	   String  deleteBooking=tripBookingService.deleteTripBooking(customerId);		
		return new ResponseEntity<String>(deleteBooking,HttpStatus.OK);	
	
	}
	
	@GetMapping("/tripbooking/{customerid}")
	public ResponseEntity<List<TripBooking>> allTripBooking(@PathVariable("customerid") Integer customerId) throws TripBookingException{
		
		List<TripBooking> savedBooking=tripBookingService.viewAllTripsCustomer(customerId);		
		return new ResponseEntity<List<TripBooking>>(savedBooking,HttpStatus.OK);	
	
	}
	
	@GetMapping("/viewAll")
	public ResponseEntity<List<Customer>> findAllCustomer() throws CustomerException{
		
		List<Customer> customers = cService.viewCustomer();
		
		return new ResponseEntity<List<Customer>>(customers,HttpStatus.OK);
	}
	@GetMapping("/viewById/{customerId}")
	public ResponseEntity<Customer> findCustomerById(@PathVariable("customerId") Integer customerId) throws CustomerException{
		Customer customer = cService.viewCustomer(customerId);
		
		return new ResponseEntity<Customer>(customer,HttpStatus.OK);
		
	}
	@GetMapping("/generateBill/{customerId}/{tripBookingId}")
	public ResponseEntity<BillDetails> generateBillHandler(@PathVariable("customerId") Integer customerId, @PathVariable("tripBookingId") Integer tripBookingId) throws TripBookingException{
		BillDetails billDetails = tService.generateBill(customerId,tripBookingId);
		return new ResponseEntity<BillDetails>(billDetails,HttpStatus.OK);
	}
	
	@GetMapping("/Alltrips")
	public ResponseEntity<List<TripBooking>>  allTrips() throws TripBookingException{
		
		List<TripBooking> tripBooking = tripBookingService.getAllTrips();
		
		return new ResponseEntity<List<TripBooking>>(tripBooking,HttpStatus.OK);
	}
	


	
	
	
	
	
	
	
	
	
}
