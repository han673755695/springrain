package org.springrain.system.cms.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springrain.frame.entity.IBaseEntity;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.cms.entity.CmsFriendSite;
import org.springrain.system.cms.service.ICmsFriendSiteService;
import org.springrain.system.manager.service.impl.BaseSpringrainServiceImpl;

/**
 * TODO 在此加入类描述
 * 
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version 2016-11-10 11:55:19
 * @see org.springrain.cms.entity.demo.service.impl.CmsFriendSite
 */
@Service("cmsFriendSiteService")
public class CmsFriendSiteServiceImpl extends BaseSpringrainServiceImpl implements ICmsFriendSiteService {

	@Override
	public String save(Object entity) throws Exception {
		CmsFriendSite cmsFriendSite = (CmsFriendSite) entity;
		return super.save(cmsFriendSite).toString();
	}

	@Override
	public String saveorupdate(Object entity) throws Exception {
		CmsFriendSite cmsFriendSite = (CmsFriendSite) entity;
		return super.saveorupdate(cmsFriendSite).toString();
	}

	@Override
	public Integer update(IBaseEntity entity) throws Exception {
		CmsFriendSite cmsFriendSite = (CmsFriendSite) entity;
		return super.update(cmsFriendSite);
	}

	@Override
	public CmsFriendSite findCmsFriendSiteById(String id) throws Exception {
		return super.findById(id, CmsFriendSite.class);
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
