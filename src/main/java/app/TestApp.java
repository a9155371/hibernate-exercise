package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
//import javax.persistence.criteria.CriteriaBuilder;
//import javax.persistence.criteria.CriteriaQuery;
//import javax.persistence.criteria.Root;
//
//import org.hibernate.query.Query;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
//
//import web.emp.entity.Dept;
//import web.emp.entity.Emp;
//import web.emp.util.HibernateUtil;
//import web.member.entity.Member;
import org.springframework.context.support.GenericApplicationContext;

import web.member.dao.MemberDao;
import web.member.entity.Member;

public class TestApp {

	public static void main(String args[]) {
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		new XmlBeanDefinitionReader(applicationContext).loadBeanDefinitions("applicationContext.xml");
		applicationContext.refresh();
		
		MemberDao memberDao = applicationContext.getBean(MemberDao.class);
		System.out.println(memberDao.selectById(1).getNickname());
		
//		((ConfigurableApplicationContext) applicationContext).close();
//		
	}
}

//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//
//		Emp emp = session.get(Emp.class, 7369);
//		Dept dept = emp.getDept();
//		List<Emp> emps = dept.getEmps();
//		for (Emp tmp : emps) {
//			System.out.println(tmp.getEname());
//		}
//	}
//
//	public Integer insert(Member member) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			session.persist(member);
//			transaction.commit();
//			return member.getId();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//	}
//
//	public Integer deleteById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member member = session.get(Member.class, id);
//			session.remove(member);
//			transaction.commit();
//			return member.getId();
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;
//		}
//	}
//
//	public int updateById(Member newMember) {
//
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member oldMember = session.get(Member.class, newMember.getId());
//			// 如果有傳pass,就將此pass設定給oldMember
//			final Boolean pass = newMember.getPass();
//			if (pass != null) {
//				oldMember.setPass(pass);
//			}
//			// 若有傳roleId,就將此roleId設定給oldMember
//			final Integer roleId = newMember.getRoleId();
//			if (roleId != null) {
//				oldMember.setRoleId(roleId);
//			}
//
//			transaction.commit();
//			return 1;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return -1;
//		}
//	}
//
//	public Member selectById(Integer id) {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Member member = session.get(Member.class, id);
//			session.remove(member);
//			transaction.commit();
//			return member;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//	}
//
//	public List<Member> selectAll() {
//		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
//		Session session = sessionFactory.openSession();
//		try {
//			Transaction transaction = session.beginTransaction();
//			Query<web.member.entity.Member> query = session.createQuery(
//					"SELECT new web.member.pojo.Member(username, nickName) FROM Member", web.member.entity.Member.class);
//			List<Member> list = query.getResultList();
//
//			transaction.commit();
//			return list;
//		} catch (Exception e) {
//			e.printStackTrace();
//			session.getTransaction().rollback();
//			return null;
//		}
//	}
