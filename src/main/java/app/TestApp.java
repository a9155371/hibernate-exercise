package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.query.Query;

import web.emp.util.HibernateUtil;
import web.member.pojo.Member;


public class TestApp {

	public static void main(String[] args) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();	
		
		//select USERNAME, NICKNAME
		//from MEMBER
		//where USERNAME = ? and PASSWORD = ?
		CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
		CriteriaQuery<Member> criteriaQuery = criteriaBuilder.createQuery(Member.class);
		Root<Member> root = criteriaQuery.from(Member.class);
		//where USERNAME =? and PASSWORD = ?
		criteriaQuery.where(criteriaBuilder.and(
			criteriaBuilder.equal(root.get("username"),"admin"),
			criteriaBuilder.equal(root.get("password"),"P@ssw0rd")
				));
		
		//select USERNAME, NICKNAME
		criteriaQuery.multiselect(root.get("username"),	 root.get("nickname"));
		
		//order by ID
		criteriaQuery.orderBy(criteriaBuilder.asc(root.get("id")));
		
		Member member = session.createQuery(criteriaQuery).uniqueResult();
		System.out.println(member);
	}

	public Integer insert(Member member) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			session.persist(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

	public Integer deleteById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return member.getId();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return -1;
		}
	}

	public int updateById(Member newMember) {

		  SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		  Session session = sessionFactory.openSession();
		  try {
		   Transaction transaction = session.beginTransaction();
		   Member oldMember = session.get(Member.class, newMember.getId());
		   // 如果有傳pass,就將此pass設定給oldMember
		   final Boolean pass = newMember.getPass();
		   if (pass != null) {
		    oldMember.setPass(pass);
		   }
		   // 若有傳roleId,就將此roleId設定給oldMember
		   final Integer roleId = newMember.getRoleId();
		   if (roleId != null) {
		    oldMember.setRoleId(roleId);
		   }

		   transaction.commit();
		   return 1;
		  } catch (Exception e) {
		   e.printStackTrace();
		   session.getTransaction().rollback();
		   return -1;
		  }
		 }

	public Member selectById(Integer id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			Member member = session.get(Member.class, id);
			session.remove(member);
			transaction.commit();
			return member;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}
	public List<Member> selectAll() {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		try {
			Transaction transaction = session.beginTransaction();
			 Query<web.member.pojo.Member> query = session.createQuery(
					 "SELECT new web.member.pojo.Member(username, nickName) FROM Member", web.member.pojo.Member.class);
			List<Member> list = query.getResultList();
			
			transaction.commit();
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			return null;
		}
	}

}
