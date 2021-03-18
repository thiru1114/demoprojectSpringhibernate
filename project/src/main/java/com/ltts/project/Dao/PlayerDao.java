package com.ltts.project.Dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ltts.project.model.Member;
import com.ltts.project.model.Player;

@Repository
public class PlayerDao {
	
	@Autowired
	private SessionFactory sf;
	
	public boolean insertPlayer(Player p)
	{
		boolean b=false;
		Session s=null;
		try {
			s=sf.openSession();
			s.beginTransaction();
			
			s.save(p);
			//System.out.println(st);
			s.getTransaction().commit();
			
		}
		catch(Exception e) {
			System.out.println("Exception "+e);
			b=true;
		}
		finally {
			sf.close();
		}
		
		return b;
	
		
	}
	public List<Player> getAllPlayers(){
		Session session=sf.openSession();
        session.beginTransaction();
        
        List<Player> li=sf.openSession().createCriteria(Player.class).list();
        //List<ProductsModel> product=sessionFactory.openSession().createCriteria(ProductsModel.class).list();
        
        session.getTransaction().commit();
     
		return li;
		
	}

}
