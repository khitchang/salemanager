package net.khi.salemanagerbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import net.khi.salemanagerbackend.dao.ProductDAO;
import net.khi.salemanagerbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDAOImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productId) {

		return sessionFactory.getCurrentSession().get(Product.class,
				Integer.valueOf(productId));

	}

	@Override
	public List<Product> list() {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product", Product.class).getResultList();

	}

	@Override
	public boolean add(Product product) {

		System.out.println("Daolmpl create product ------- :: "
				+ product.toString());

		try {// add category to the database table
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		System.out.println("Daolmpl update product ------- :: "
				+ product.toString());
		try {
			sessionFactory.getCurrentSession().update(product);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(Product product) {

		product.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(product);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public List<Product> listActiveProducts() {

		String selectActiveProducts = "FROM Product WHERE active =:active";
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProducts, Product.class)
				.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {

		String selectActiveProductsByCategory = "FROM Product WHERE active =:active AND categoryId =:categoryId";
		System.out.println("------------- " + selectActiveProductsByCategory);
		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveProductsByCategory, Product.class)
				.setParameter("active", true)
				.setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory
				.getCurrentSession()
				.createQuery("FROM Product WHERE active =:active ORDER BY id",
						Product.class).setParameter("active", true)
				.setFirstResult(0).setMaxResults(count).getResultList();
	}

	@Override
	public List<Product> getProductsByParam(String param, int count) {
		String query = "FROM Product WHERE active = true ORDER BY " + param
				+ " DESC";

		return sessionFactory.getCurrentSession()
				.createQuery(query, Product.class).setFirstResult(0)
				.setMaxResults(count).getResultList();
	}

}
