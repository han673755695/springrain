package org.springrain.weixin.base.mp.api.impl;

import java.util.List;

import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.api.WxMpUserService;
import org.springrain.weixin.base.mp.bean.WxMpUserQuery;
import org.springrain.weixin.base.mp.bean.result.WxMpUser;
import org.springrain.weixin.base.mp.bean.result.WxMpUserList;
import org.springrain.weixin.entity.WxMpConfig;

import com.google.gson.JsonObject;

/**
 * Created by Binary Wang on 2016/7/21.
 */
public class WxMpUserServiceImpl implements WxMpUserService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/user";
  private WxMpService wxMpService;

  public WxMpUserServiceImpl() {
  }

  @Override
  public void userUpdateRemark(WxMpConfig wxmpconfig,String openid, String remark) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/updateremark";
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.wxMpService.post(wxmpconfig,url, json.toString());
  }

  @Override
  public WxMpUser userInfo(WxMpConfig wxmpconfig,String openid) throws WxErrorException {
    return this.userInfo(wxmpconfig,openid, null);
  }

  @Override
  public WxMpUser userInfo(WxMpConfig wxmpconfig,String openid, String lang) throws WxErrorException {
    String url = API_URL_PREFIX + "/info";
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.wxMpService.get(wxmpconfig,url,
        "openid=" + openid + "&lang=" + lang);
    return WxMpUser.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList(WxMpConfig wxmpconfig,String next_openid) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";
    String responseContent = this.wxMpService.get(wxmpconfig,url,
        next_openid == null ? null : "next_openid=" + next_openid);
    return WxMpUserList.fromJson(responseContent);
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpConfig wxmpconfig,List<String> openids)
      throws WxErrorException {
    return this.userInfoList(wxmpconfig,new WxMpUserQuery(openids));
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpConfig wxmpconfig,WxMpUserQuery userQuery) throws WxErrorException {
    String url = API_URL_PREFIX + "/info/batchget";
    String responseContent = this.wxMpService.post(wxmpconfig,url,
        userQuery.toJsonString());
    return WxMpUser.fromJsonList(responseContent);
  }

}
