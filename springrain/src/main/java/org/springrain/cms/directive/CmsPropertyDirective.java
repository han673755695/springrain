package org.springrain.cms.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springrain.cms.entity.CmsProperty;
import org.springrain.cms.service.ICmsPropertyService;
import org.springrain.cms.utils.DirectiveUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

@Component("cmsPropertyDirective")
public class CmsPropertyDirective extends AbstractCMSDirective {

	@Resource
	private ICmsPropertyService cmsPropertyService;
	
	/**
	 * 模板名称
	 */
	private static final String TPL_NAME = "cms_property_list";

	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars,
			TemplateDirectiveBody body) throws TemplateException, IOException {
		List<CmsProperty> list = null;
		try {
			list = cmsPropertyService.findByBusinessId(getBusinessId(params),
					null);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		setDirectiveData(list);
		env.setVariable("propertyList", DirectiveUtils.wrap(list));
		if (body != null) {
			body.render(env.getOut());
		}
	}
	
	
	@PostConstruct
	public void  registerFreeMarkerVariable(){
		setFreeMarkerSharedVariable(TPL_NAME, this);
	}
	
	

}
