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

/**
 *
 */
public class YaasToken
{
	private String token_type;
	private String access_token;
	private String expires_in;
	private String scope;

	public String getAccess_token()
	{
		return this.access_token;
	}
}
