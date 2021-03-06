package org.springrain.system.cms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.cms.entity.CmsTemplate;
import org.springrain.system.cms.service.ICmsTemplateService;
import org.springrain.system.manager.service.impl.BaseSpringrainServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2016-11-10 11:55:22
 * @see org.springrain.cms.entity.demo.service.impl.CmsTemplate
 */
@Service("cmsTemplateService")
public class CmsTemplateServiceImpl extends BaseSpringrainServiceImpl implements ICmsTemplateService {

	@Override
	public String save(Object entity) throws Exception {
		CmsTemplate cmsTemplate = (CmsTemplate) entity;
		return super.save(cmsTemplate).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		CmsTemplate cmsTemplate = (CmsTemplate) entity;
		return super.saveorupdate(cmsTemplate).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		CmsTemplate cmsTemplate = (CmsTemplate) entity;
		return super.update(cmsTemplate);
	}

	@Override
	public CmsTemplate findCmsTemplateById(String id) throws Exception {
		return super.findById(id, CmsTemplate.class);
	}

	/**
	 * 列表查询,每个service都会重载,要把sql语句封装到service中,Finder只是最后的方案
	 * 
	 * @param finder
	 * @param page
	 * @param clazz
	 * @param o
	 * @return
	 * @throws Exception
	 */
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page, Class<T> clazz, Object o) throws Exception {
		return super.findListDataByFinder(finder, page, clazz, o);
	}

}
