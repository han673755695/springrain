package org.springrain.system.cms.directive;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;
import org.springrain.frame.util.SpringUtils;
import org.springrain.system.cms.util.DirectiveUtils;
import org.springrain.system.weixin.entity.WxMenu;
import org.springrain.system.weixin.service.IWxMenuService;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("siteWxmenuListDirective")
public class CmsSiteWxmenuListDirective extends AbstractCMSDirective {

	private static final String TPL_NAME = "cms_site_wxmenu_list";
	@Resource
	private IWxMenuService wxMenuService;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		String siteId = DirectiveUtils.getString("id", params);
		List<WxMenu> menuList;
		try {
			menuList = wxMenuService.findParentMenuList(siteId);
			if (menuList != null) {
				for (WxMenu cmsWxMenu : menuList) {
					cmsWxMenu.setChildMenuList(wxMenuService.findChildMenuListByPid(cmsWxMenu.getId()));
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			menuList = new ArrayList<>();
		}
		env.setVariable("menuList", DirectiveUtils.wrap(menuList));
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
