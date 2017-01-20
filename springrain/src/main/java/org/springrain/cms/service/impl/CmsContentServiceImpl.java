package org.springrain.cms.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springrain.cms.entity.CmsChannelContent;
import org.springrain.cms.entity.CmsContent;
import org.springrain.cms.entity.CmsLink;
import org.springrain.cms.service.ICmsChannelService;
import org.springrain.cms.service.ICmsContentService;
import org.springrain.cms.service.ICmsLinkService;
import org.springrain.cms.service.ICmsSiteService;
import org.springrain.frame.util.Finder;
import org.springrain.frame.util.Page;
import org.springrain.system.service.BaseSpringrainServiceImpl;
import org.springrain.system.service.ITableindexService;




/**
 * TODO 在此加入类描述
 * @copyright {@link weicms.net}
 * @author springrain<Auto generate>
 * @version  2016-11-10 11:55:19
 * @see org.springrain.cms.entity.demo.service.impl.CmsContent
 */
@Service("cmsContentService")
public class CmsContentServiceImpl extends BaseSpringrainServiceImpl implements ICmsContentService {

	@Resource
	private ITableindexService tableindexService;
	
	@Resource
	private ICmsSiteService cmsSiteService;
	
	@Resource
	private ICmsLinkService cmsLinkService;
	@Resource
	private ICmsChannelService cmsChannelService;

	@SuppressWarnings("unchecked")
	@Override
	public <T> List<T> findListDataByFinder(Finder finder, Page page,
			Class<T> clazz, Object queryBean) throws Exception {
		List<CmsContent> contentList;
		if(page.getPageIndex()==1){
			contentList = getByCache("contentList", "'findListDataByFinder'", List.class);
			if(CollectionUtils.isEmpty(contentList)){
				contentList = super.findListDataByFinder(finder, page, CmsContent.class, queryBean);
				putByCache("contentList", "'findListDataByFinder'", contentList);
			}
		}else{
			contentList = super.findListDataByFinder(finder, page, CmsContent.class, queryBean);
		}
		return (List<T>) contentList;
	}
	
    @Override
	public String  saveContent(CmsContent cmsContent ) throws Exception{
    	if(cmsContent==null){
    		return null;
    	}
    	
    	evictByKey("contentList", "'findListDataByFinder'");//清空后台列表缓存
    	
    	String siteId=cmsContent.getSiteId();
    	if(StringUtils.isBlank(siteId)){
    		return null;
    	}
    	
    	Integer siteType=cmsSiteService.findSiteTypeById(cmsContent.getSiteId());
    	if(siteType==null){
         	return null;
    	}
	   String id= tableindexService.updateNewId(CmsContent.class);
	   if(StringUtils.isBlank(id)){
		    	return null;
	   }
	   cmsContent.setId(id);
	   cmsContent.setCreateDate(new Date());
	   super.save(cmsContent);
	   
	   //保存中间对应
	   CmsChannelContent ccc=new CmsChannelContent();
	   ccc.setChannelId(cmsContent.getChannelId());
	   ccc.setSortno(cmsContent.getSortno());
	   ccc.setActive(cmsContent.getActive());
	   ccc.setContentId(id);
	   ccc.setSiteId(cmsContent.getSiteId());
	   super.save(ccc);
	   
	   
	 //保存 相应的 link 链接
	    CmsLink cmsLink=new CmsLink();
	    
	    cmsLink.setBusinessId(id);
	    cmsLink.setSiteId(cmsContent.getSiteId());
	    cmsLink.setName(cmsContent.getTitle());
	    cmsLink.setModelType(2);//内容
	    cmsLink.setLookcount(1);
	    cmsLink.setStatichtml(0);//默认不静态化
	    cmsLink.setActive(cmsContent.getActive());//默认可以使用
	    cmsLink.setSortno(cmsContent.getSortno());
	    //首页默认
	    String _index="/f/"+siteType+"/"+siteId+"/"+id;
	    cmsLink.setDefaultLink(_index);
	    cmsLink.setLink(_index);
	    //设置模板路径
	    cmsLink.setFtlfile("/u/"+siteId+"/content");
	    cmsLinkService.save(cmsLink);
	    
	    //清除缓存
	    evictByKey(cmsContent.getChannelId(), "'findContentByChannelId_'#"+cmsContent.getChannelId());
	    evictByKey(siteId, "'findListBySiteId_'#"+siteId);
	    //添加新缓存
	    putByCache(siteId, "'findCmsContentById_'+#"+id, cmsContent);
	    return id;
	}

  
	@Override
    public Integer updateCmsContent(CmsContent cmsContent) throws Exception{
		if(cmsContent==null){
    		return null;
    	}
		//清除缓存
		evictByKey("contentList", "'findListDataByFinder'");//清空后台列表缓存
		evictByKey(cmsContent.getChannelId(), "'findContentByChannelId_'#"+cmsContent.getChannelId());
	    evictByKey(cmsContent.getSiteId(), "'findListBySiteId_'#"+cmsContent.getSiteId());
	    evictByKey(cmsContent.getSiteId(), "'findCmsContentById_'+#"+cmsContent.getId());
	    
	    //添加新缓存
	    putByCache(cmsContent.getSiteId(), "'findCmsContentById_'+#"+cmsContent.getId(), cmsContent);
	    return super.update(cmsContent,true);
    }
    @Override
	public CmsContent findCmsContentById(String id) throws Exception{
	 return super.findById(id,CmsContent.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsContent> findListBySiteId(String siteId, Page page) throws Exception {
		if(page.getPageIndex() == 1){
			List<CmsContent> contentList = getByCache(siteId, "'findListBySiteId_'#"+siteId, List.class);
			if(CollectionUtils.isEmpty(contentList)){
				Finder finder = new Finder("SELECT c.*,d.link FROM cms_site a INNER JOIN cms_channel_content b ON a.id=b.siteId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON d.businessId = c.id WHERE a.id=:siteId");
				finder.setParam("siteId", siteId);
				contentList = super.queryForList(finder, CmsContent.class, page);
				putByCache(siteId, "'findListBySiteId_'#"+siteId, contentList);
				
			}
			return contentList;
		}else{
			Finder finder = new Finder("SELECT c.*,d.link FROM cms_site a INNER JOIN cms_channel_content b ON a.id=b.siteId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON d.businessId = c.id WHERE a.id=:siteId");
			finder.setParam("siteId", siteId);
			return super.queryForList(finder, CmsContent.class, page);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CmsContent> findContentByChannelId(String channelId, Page page) throws Exception {
		List<CmsContent> contentList;
		if(page.getPageIndex()==1){
			contentList = getByCache(channelId, "'findContentByChannelId_'#"+channelId, List.class);
			if(CollectionUtils.isEmpty(contentList)){
				Finder finder = new Finder("SELECT c.*,d.link FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON c.id=d.businessId WHERE a.id=:channelId");
				finder.setParam("channelId", channelId);
				contentList = super.queryForList(finder, CmsContent.class, page);
				putByCache(channelId, "'findContentByChannelId_'#"+channelId, contentList);
			}
		}else{
			Finder finder = new Finder("SELECT c.*,d.link FROM cms_channel a INNER JOIN cms_channel_content b ON a.id=b.channelId INNER JOIN cms_content c ON c.id=b.contentId INNER JOIN cms_link d ON c.id=d.businessId WHERE a.id=:channelId");
			finder.setParam("channelId", channelId);
			contentList = super.queryForList(finder, CmsContent.class, page);
		}
		
		return contentList;
	}

}