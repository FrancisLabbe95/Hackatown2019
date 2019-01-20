/* 
 * Hibernate, Relational Persistence for Idiomatic Java
 * 
 * JBoss, Home of Professional Open Source
 * Copyright 2013 Red Hat Inc. and/or its affiliates and other contributors
 * as indicated by the @authors tag. All rights reserved.
 * See the copyright.txt in the distribution for a
 * full listing of individual contributors.
 *
 * This copyrighted material is made available to anyone wishing to use,
 * modify, copy, or redistribute it subject to the terms and conditions
 * of the GNU Lesser General Public License, v. 2.1.
 * This program is distributed in the hope that it will be useful, but WITHOUT A
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE.  See the GNU Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public License,
 * v.2.1 along with this distribution; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston,
 * MA  02110-1301, USA.
 */
package hello;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Table;

/**
 * @author Brett Meyer
 */
public class UserService {
	
	private HibernateUtil hibernateUtil = new HibernateUtil();

	public UserService() {
		add(new User(1015, "Paul", false, "3000 rue des Paquerettes", 8, 0));
		add(new User(1016, "Brendon", true, "3001 rue des Paquerettes", 96, 0));
		add(new User(1017, "Jacob", false, "450 rue des Baccalaureats", 8, 0));
		add(new User(1018, "Bernard", true, "1806 rue des Professeurs", 96, 0));
		add(new User(1019, "Bruno", true, "781 rue du Curé", 8, 0));
		add(new User(1020, "Pitbull", true, "666 rue du Diable", 96, 0));
		add(new User(1021, "Toto", true, "4545 rue des Addresses Fake", 8, 0));
		add(new User(1022, "CNN", true, "233 rue des Paquerettes", 96, 0));
		add(new User(1023, "Fox News", true, "56 rue des Paquerettes", 8, 0));
		add(new User(1024, "Wololololo", true, "1791 rue des Rues", 96, 0));
	}

	public void add(User user) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist( user );
		em.getTransaction().commit();
		em.close();
	}

	public void update(User user) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge( user );
		em.getTransaction().commit();
		em.close();
	}

	public User get(long id) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		User user = (User) em.createQuery( "from User user where user.id=" + id ).getSingleResult();
		em.getTransaction().commit();
		em.close();
		return user;
	}

	public User get(String name) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		User user = (User) em.createQuery( "from User user where user.name=\'" + name + "\'" ).getSingleResult();
		em.getTransaction().commit();
		em.close();
		return user;
	}

	public List<User> getAll() {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		List list = em.createQuery( "from User user", User.class ).getResultList();
		em.getTransaction().commit();
		em.close();
		return list;
	}

	public List<User> getAllWhere(int stock) {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		List list = em.createQuery( "from User user where user.stock>" + stock + " and user.availability=TRUE", User.class ).getResultList();
		em.getTransaction().commit();
		em.close();
		return list;
	}

	public void deleteAll() {
		EntityManager em = hibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.createQuery( "delete from User" ).executeUpdate();
		em.getTransaction().commit();
		em.close();
	}

}
