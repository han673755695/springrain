package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.cms.util.DirectiveUtils;
import org.springrain.system.weixin.entity.WxMpConfig;
import org.springrain.weixin.sdk.common.service.IWxMpConfig;
import org.springrain.weixin.sdk.common.service.IWxMpConfigService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxconfigDirective")
public class CmsSiteWxconfigDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_site_wxconfig";
	@Resource
	private IWxMpConfigService wxMpConfigService;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		IWxMpConfig config;
		String id = DirectiveUtils.getString("id", params);

		try {
			config = wxMpConfigService.findWxMpConfigById(id);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			config = new WxMpConfig();
		}

		env.setVariable("config", DirectiveUtils.wrap(config));
		if (body != null) {
			body.render(env.getOut());
		}
	}

	@Resource
	private SpringUtils springUtils;
	@PostConstruct
	public void registerFreeMarkerVariable() {
		// setFreeMarkerSharedVariable(TPL_NAME, this);
		FreeMarkerConfigurer freeMarkerConfigurer = (FreeMarkerConfigurer) springUtils.getBean("freeMarkerConfigurer");
		freeMarkerConfigurer.getConfiguration().setSharedVariable(TPL_NAME, this);
	}

}
