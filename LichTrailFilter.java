/**
 *
 */
package de.hybris.platform.yacceleratorstorefront.filters;



import de.hybris.platform.core.Registry;
import de.hybris.platform.lichcodetrail.UserLoginController;
import de.hybris.platform.lichcodetrail.UserRegistController;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


/**
 * @author i330993
 *
 */
public class LichTrailFilter implements Filter
{


	/*
	 * if user's last loginTime has more than 5 minutes, make user's loginDisable = flase.
	 *
	 * @author:lich
	 */
	@Override
	public void doFilter(final ServletRequest request, final ServletResponse response, final FilterChain chain)
			throws IOException, ServletException
	{
		final HttpServletRequest httpRequest = (HttpServletRequest) request;
		final String uri = httpRequest.getRequestURI();
		System.out.println("httpRequest uri: " + uri);
		//当用户点击登陆时触发
		if (uri.contains("electronics") && uri.contains("j_spring_security_check"))
		{
			System.out.println("user login");
			final UserLoginController ulc = (UserLoginController) Registry.getApplicationContext().getBean("userLoginController");
			ulc.checkLoginTime(httpRequest.getParameter("j_username"));
		}
		//当用户点击注册时触发
		if (uri.contains("electronics") && uri.contains("login/register"))
		{
			System.out.println("user regist");
			final UserRegistController urc = (UserRegistController) Registry.getApplicationContext().getBean("userRegistController");
			urc.sendYaasEmail("297378104@qq.com", "Chenghui, Li");
		}
		//当用户点击邮件中链接时触发
		if (uri.contains("registconfirm"))
		{
			System.out.println("邮箱已验证! uri: " + uri);
		}
		{
			chain.doFilter(request, response);
		}
	}



	@Override
	public void destroy()
	{
		//
	}

	@Override
	public void init(final FilterConfig arg0) throws ServletException
	{
		//
	}
}
