package com.masai.service;

import java.util.List;

import com.masai.exceptions.AdminException;
import com.masai.exceptions.DriverException;
import com.masai.model.Admin;
import com.masai.model.Driver;
import com.masai.model.TripBooking;

public interface AdminService {

	public Admin createAdmin(Admin Admin) throws AdminException;

	public Admin updateAdmin(Admin Admin, String key) throws AdminException;

	public Admin deleteAdmin(Admin admin) throws AdminException;

	public List<TripBooking> getAllTripsByCab(Integer cabId) throws AdminException;

}
