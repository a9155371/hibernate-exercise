package app;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import web.emp.util.HibernateUtil;
import web.member.pojo.Member;

public class TestApp {

	public static void main(String[] args) {
		Member member = new Member();
		TestApp app = new TestApp();// Member member = new Member();

		// Member member = new Member();
		// member.setUsername("使用者名稱");
		// member.setPassword("密碼");
		// member.setNickname("暱稱");
		// app.insert(member);
		// System.out.println(member.getId());

		member.setId(1);
		member.setPass(true);
		member.setRoleId(1);
		System.out.println(app.updateById(member));

//		member.setUsername("使用者名稱");
//		member.setPassword("密碼");
//		member.setNickname("暱稱");
//		
//		TestApp app = new TestApp();
//		app.insert(member);
//		System.out.println(member.getId());
		System.out.println(app.selectById(2));
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

}
