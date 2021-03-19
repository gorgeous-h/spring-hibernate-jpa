package com.hello.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.hello.domain.User;

@Repository
@SuppressWarnings({"unchecked", "serial"})
public class UserDao {
	@PersistenceContext
	private EntityManager entityManager;

	public User getUserById(Integer id) {
		return entityManager.find(User.class, id);
	}

	public Map<String, Object> getUsers(int pageNO, int pageSize, Map<String, Object> params) {		
		String hql = "FROM User ";
		Query query = entityManager.createQuery(hql);
		int count = query.getResultList().size();
		List<User> users = query.setFirstResult((pageNO - 1)*pageSize).setMaxResults(pageSize).getResultList();
		return new HashMap<String, Object>(){
			{
				put("rows", users);
				put("total", count);
			}
		};
	}

	public void save(User user) {
		entityManager.persist(user);
	}

	public void update(User user) {
		entityManager.merge(user);
	}

	public void delete(User user) {
		entityManager.remove(user);
	}

}
