package com.olchat.servlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.Vector;

public class ChatServlet extends HttpServlet {

    public ChatServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=GBK");
		String action = request.getParameter("action");
        if ("send".equals(action)) {
            this.send(request, response);
        }else if("get".equals(action)){
        	this.get(request,response);
        }

	}
	public void send(HttpServletRequest request,HttpServletResponse response)  throws ServletException, IOException {
		ServletContext application=getServletContext();
		response.setContentType("text/html;charset=GBK");
		String user=request.getParameter("user");
		String speak=request.getParameter("speak");
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Vector<String> v=null;
		String message = df.format(new Date()) + " 萌萌哒的用户 " + "["+user+"]˵:" +speak;
		if(null==application.getAttribute("message")){
			v=new Vector<String>();
		}else{
			v=(Vector<String>)application.getAttribute("message");
		}
		v.add(message);
		application.setAttribute("message", v);
        Random random = new Random();
		request.getRequestDispatcher("ChatServlet?action=get&nocache=" + random.nextInt(10000)).forward(request, response);
	}
	public void get(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		response.setContentType("text/html;charset=GBK");
		response.setHeader("Cache-Control", "no-cache");
		PrintWriter out = response.getWriter();

		ServletContext application=getServletContext();
		String msg="";
		if(null!=application.getAttribute("message")){
			Vector<String> v_temp=(Vector<String>)application.getAttribute("message");
			for(int i=v_temp.size()-1;i>=0;i--){
				msg=msg+"<br>"+v_temp.get(i);
			}
		}else{
			msg="Welcome to here";
		}
		out.println(msg);
		out.close();
	}
}
