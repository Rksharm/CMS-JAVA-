package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
    public List<Customer> getCustomers() {
		
		Session currentSession=sessionFactory.getCurrentSession();
		
		Query<Customer> theQuery=currentSession.createQuery("from Customer order by lastName",Customer.class);
		
		List<Customer> customers=theQuery.getResultList();
		
		return customers;
	}


	@Transactional
	public void saveCustomer(Customer theCustometr) {
		
		// get the Current hibernate session
		
		Session cureentSession=sessionFactory.getCurrentSession();
		
		
		

		// save the customer .... finallu
		
	cureentSession.saveOrUpdate(theCustometr);
		
	}
	
	


	@Override
	@Transactional
	public Customer getCustomer(int theId) {
		Session currentsession=sessionFactory.getCurrentSession();
		
		Customer theCustomer=currentsession.get(Customer.class,theId);
		
		return theCustomer;
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {
		
		//get the current status of hibernate session
		Session currentsession=sessionFactory.getCurrentSession();
		
		
		//then delete the object with the primary key 
		Query theQuery=
				currentsession.createQuery("delete from Customer where id=:customerId ");
		
		theQuery.setParameter("customerId", theId);
		
		theQuery.executeUpdate();
		
	}
;
}
