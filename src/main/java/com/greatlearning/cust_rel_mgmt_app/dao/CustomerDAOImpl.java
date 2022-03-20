package com.greatlearning.cust_rel_mgmt_app.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.greatlearning.cust_rel_mgmt_app.entity.Customer;



@Repository
@Transactional
@EnableTransactionManagement
public class CustomerDAOImpl implements CustomerDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * private Session session;
	 * 
	 * @Autowired CustomerDAOImpl(SessionFactory sessionFactory){
	 * this.sessionFactory = sessionFactory;
	 * 
	 * try { session = sessionFactory.getCurrentSession(); } catch
	 * (HibernateException e) { session = sessionFactory.openSession(); }
	 * 
	 * }
	 */


	@Override
	@Transactional
	public List<Customer> getCustomers() {

		//Transaction tx = session.beginTransaction();

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();


		//create Query and sort by last name
		Query<Customer> theQuery = session.createQuery("from Customer order by lastName",Customer.class);
	
		
		
		//execute Query
		List<Customer> customers = theQuery.getResultList(); 
		//tx.commit();
		
		System.out.println(customers.size());

		return customers;
	}



	@Override
	@Transactional
	public void saveCustomer(Customer theCustomer) {

		/*
		 * Transaction tx = session.beginTransaction();
		 */

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		session.saveOrUpdate(theCustomer);
		//tx.commit();

	}

	@Override
	@Transactional
	public Customer getCustomer(int theId) {

		//Transaction tx = session.beginTransaction();

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();
		Customer theCustomer = session.get(Customer.class, theId);

		//tx.commit();

		return theCustomer;
	}


	@Override
	@Transactional
	public void deleteCustomer(int theId) {

		//Transaction tx = session.beginTransaction();

		// get the current hibernate session
		Session session = sessionFactory.getCurrentSession();

		Customer theCustomer = session.get(Customer.class, theId);

		session.delete(theCustomer);

		//tx.commit();

	}

}
