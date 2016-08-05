/*
 * [y] hybris Platform
 *
 * Copyright (c) 2000-2016 SAP SE
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * Hybris ("Confidential Information"). You shall not disclose such
 * Confidential Information and shall use it only in accordance with the
 * terms of the license agreement you entered into with SAP Hybris.
 */
package de.hybris.platform.lichcodetrail.help;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


/**
 *
 */
public class YaasEmailService
{

	/**
	 *
	 * @param httpurl请求地址
	 * @param param数据
	 * @param token访问权限
	 * @param contentType数据类型
	 * @return http连接返回值
	 * @throws Exception
	 *            lich code start
	 */
	public String callService(final String httpurl, final String param, final String token) throws Exception
	{
		final String urlParameters = param;
		final byte[] postData = urlParameters.getBytes(StandardCharsets.UTF_8);
		final int postDataLength = postData.length;
		final String request = httpurl;
		final URL url = new URL(request);
		final HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setDoOutput(true);
		conn.setInstanceFollowRedirects(false);
		conn.setRequestMethod("POST");
		if (token != null)
		{
			conn.setRequestProperty("Authorization", "Bearer " + token);
			conn.setRequestProperty("Content-Type", "application/json");
		}
		else
		{
			conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		}
		conn.setRequestProperty("charset", "utf-8");
		conn.setRequestProperty("Content-Length", Integer.toString(postDataLength));
		conn.setUseCaches(false);
		StringBuilder sb = null;
		try (DataOutputStream wr = new DataOutputStream(conn.getOutputStream()))
		{
			wr.write(postData);
			final Reader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), "UTF-8"));
			sb = new StringBuilder();
			for (int c; (c = in.read()) >= 0;)
			{
				sb.append((char) c);
			}
			conn.getInputStream().close();
			in.close();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		return sb.toString();
	}
	/*
	 * lich code end
	 */
}
