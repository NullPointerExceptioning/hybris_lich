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
package de.hybris.platform.lichcodetrail;

import de.hybris.platform.lichcodetrail.help.YaasEmailService;
import de.hybris.platform.lichcodetrail.help.YaasToken;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import com.google.gson.Gson;


public class UserRegistController
{
	private UserService userService;
	private ModelService modelService;
	private YaasEmailService yaasEmailService;

	/**
	 * if user wants to regist, add one more step. send a email to user.
	 *
	 * @param:yaas服务请求需要的固定参数
	 * @httpurl:调用的httpurl服务链接
	 * @token:调用yaas服务所需要的证明
	 *
	 */
	public void sendYaasEmail(final String uid, final String name)
	{
		final YaasEmailService service = getYaasEmailService();
		String param = "grant_type=client_credentials&scope=hybris.email_send&client_id=l1mAkZnWGmGSFVsZYjFhnaDNa6L6X2PH&client_secret=8Dhzp4GZFWB8m6ao";
		String httpurl = "https://api.yaas.io/hybris/oauth2/v1/token";
		String token = null;
		try
		{
			//获取yaas  service token
			token = new Gson().fromJson(service.callService(httpurl, param, token), YaasToken.class).getAccess_token();
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		try
		{
			//调用send service 服务，发送邮件
			param = "{\"toAddress\":\"" + uid
					+ "\",\"fromAddress\":\"chenghui.li@ber.mail.yaas.io\",\"toName\":\"Register\",\"fromName\": \"admin\",\"templateCode\":\"regsit\",\"locale\":\"en_us\",\"attributes\":[{\"key\":\"user\",\"value\":\""
					+ name + "\"},{\"key\":\"link\",\"value\":" + "\"https://localhost:9002/yacceleratorstorefront"
					+ "/?site=electronics/registconfirm" + uid + "\"" + "}]}";
			httpurl = "https://api.yaas.io/hybris/email/v1/ber/send-sync";
			service.callService(httpurl, param, token);
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
	}


	public UserService getUserService()
	{
		return userService;
	}

	public void setUserService(final UserService userService)
	{
		this.userService = userService;
	}

	public ModelService getModelService()
	{
		return modelService;
	}

	public void setModelService(final ModelService modelService)
	{
		this.modelService = modelService;
	}


	/**
	 * @return the yaasEmailService
	 */
	public YaasEmailService getYaasEmailService()
	{
		return yaasEmailService;
	}


	/**
	 * @param yaasEmailService
	 *           the yaasEmailService to set
	 */
	public void setYaasEmailService(final YaasEmailService yaasEmailService)
	{
		this.yaasEmailService = yaasEmailService;
	}
}
