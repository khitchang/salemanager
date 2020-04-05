package net.khi.salemanagerbackend.daoimpl;

import java.util.List;

import net.khi.salemanagerbackend.dao.CategoryDAO;
import net.khi.salemanagerbackend.dto.Category;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("categoryDAO")
@Transactional
public class CategoryDAOImpl implements CategoryDAO {
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Category> list() {

		String selectActiveCategory = "FROM Category WHERE 	active = :active";

		return sessionFactory.getCurrentSession()
				.createQuery(selectActiveCategory, Category.class)
				.setParameter("active", true).getResultList();
	}

	/**
	 * getting category basing on id
	 */
	@Override
	public Category get(int id) {
		return sessionFactory.getCurrentSession().get(Category.class,
				Integer.valueOf(id));
	}

	@Override
	public boolean add(Category category) {
		System.out.println("Daolmpl ------- :: " + category.toString());

		try {// add category to the database table
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		category.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(category);

			return true;
		} catch (Exception e) {

			e.printStackTrace();
			return false;
		}
	}

}
