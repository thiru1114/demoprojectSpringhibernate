package com.ltts.project.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.ltts.project.Dao.MemberDao;
import com.ltts.project.Dao.PlayerDao;
import com.ltts.project.model.Member;
import com.ltts.project.model.Player;

@RestController
public class HomeController {
	
	@Autowired
	MemberDao md;
	
	@Autowired
	PlayerDao pd;
	
	@RequestMapping("/hi")
	public String firstMethod() {
		return "Hello SpringBoot";
	}
	
	@RequestMapping(" ")
	public ModelAndView secondMethod() {
		return new ModelAndView("index");
	}
	@RequestMapping("/registration")
	public ModelAndView registerUser() {
		return new ModelAndView("registration");
	}
	
	@RequestMapping("/login")
	public ModelAndView loginPage() {
		return new ModelAndView("login");
	}
	
	@RequestMapping("/addplayer")
	public ModelAndView addPlayerPage() {
		return new ModelAndView("addplayer");
	}
	
	
	@RequestMapping(value="adduser", method=RequestMethod.POST)
	public ModelAndView addUser(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		String name=req.getParameter("uname");
		String pass=req.getParameter("pass");
		String email=req.getParameter("email");
		String mobile=req.getParameter("mobile");
		
	//	ApplicationContext ac=new ClassPathXmlApplicationContext();
		Member m=new Member(name,pass,email,mobile);
		System.out.println("***** INSIDE CONTROLLER ****"+m);
		boolean b=md.InsertMember(m);
		if(b==false) {
			mv=new ModelAndView("success");
			model.addAttribute("msg", "Successfully Inserted.. ");
		}
		else {
			mv=new ModelAndView("error");
			model.addAttribute("msg", "Error due to Connection");
			
		}
		/*
		 * try { b=md.InsertMember(m); mv=new ModelAndView("success"); } catch(Exception
		 * e) {
		 * 
		 * mv=new ModelAndView("error"); }
		 */
		
		
		return mv;
	}
	
	@RequestMapping(value="checkuser")
	public ModelAndView checkUser(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		String email=req.getParameter("lemail");
		String pass=req.getParameter("lpass");
		
		Member m=md.getMemberByEmai(email);
		System.out.println(m);
		
		
		if(m !=null) {
		
			if(pass.equals(m.getPassword())) {
				model.addAttribute("value", m.getUserName());
				mv=new ModelAndView("welcome");
			}
			else {
				model.addAttribute("msg", "Password Wrong");
				mv=new ModelAndView("login");
			}
		}
		else {
			model.addAttribute("msg", "User Not Found Please Register");
			mv=new ModelAndView("login");
		}
		return mv;
	}
	
	
	
	
	@RequestMapping(value="insertplayer", method=RequestMethod.POST)
	public ModelAndView insertPlayer(HttpServletRequest req, Model model) {
		ModelAndView mv=null;
		String name=req.getParameter("playername");
		String mobile=req.getParameter("playermobile");
		
	//	ApplicationContext ac=new ClassPathXmlApplicationContext();
	//	Member m=new Member(name,pass,email,mobile);
		Player p=new Player(1,name,mobile);
		System.out.println("***** INSIDE CONTROLLER ****"+p);
		boolean b=pd.insertPlayer(p);
		if(b==false) {
			mv=new ModelAndView("success");
			model.addAttribute("msg", "Player Successfully Inserted.. ");
		}
		else {
			mv=new ModelAndView("error");
			model.addAttribute("msg", "Error due to Connection");
			
		}
		/*
		 * try { b=md.InsertMember(m); mv=new ModelAndView("success"); } catch(Exception
		 * e) {
		 * 
		 * mv=new ModelAndView("error"); }
		 */
		
		
		return mv;
	}
	
	@RequestMapping("/viewplayers")
	public ModelAndView viewAllPlayers(Model model) {
		ModelAndView mv=new ModelAndView("viewplayers");
		List<Player> li=pd.getAllPlayers();
		
		model.addAttribute("list", li);
		
		return mv;
	}

}
