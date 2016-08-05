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

import de.hybris.platform.core.model.user.UserModel;
import de.hybris.platform.servicelayer.model.ModelService;
import de.hybris.platform.servicelayer.user.UserService;

import java.util.Date;


/**
 *
 */
public class UserLoginController
{
	private UserService userService;
	private ModelService modelService;

	/*
	 * if user's last loginTime has more than 5 minutes, make user's loginDisable = flase.
	 */
	public void checkLoginTime(final String uid)
	{
		System.out.println("userLoginController checkLoginTime..");
		try
		{
			final Date now = new Date();
			final UserModel user = getUserService().getUserForUID(uid);
			if (user.getLastLogin() == null)
			{
				user.setLoginDisabled(false);
			}
			else
			{//check the time-difference between now and last, if > 3 minutes, make loginDisable==false.
				final Date last = user.getLastLogin();
				if ((now.getTime() - last.getTime()) / (1000 * 60) > 1)
				{
					user.setLoginDisabled(false);
				}
			}
			user.setLastLogin(now);
			getModelService().save(user);
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
}
