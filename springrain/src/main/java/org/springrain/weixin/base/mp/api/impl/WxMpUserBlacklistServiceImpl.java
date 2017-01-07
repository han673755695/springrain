package org.springrain.weixin.base.mp.api.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.common.util.http.SimplePostRequestExecutor;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.api.WxMpUserBlacklistService;
import org.springrain.weixin.base.mp.bean.result.WxMpUserBlacklistGetResult;
import org.springrain.weixin.entity.WxMpConfig;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * @author miller
 */
public class WxMpUserBlacklistServiceImpl implements WxMpUserBlacklistService {
  private static final String API_BLACKLIST_PREFIX = "https://api.weixin.qq.com/cgi-bin/tags/members";
  private WxMpService wxMpService;

  public WxMpUserBlacklistServiceImpl() {
  }

  @Override
  public WxMpUserBlacklistGetResult getBlacklist(WxMpConfig wxmpconfig,String nextOpenid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("begin_openid", nextOpenid);
    String url = API_BLACKLIST_PREFIX + "/getblacklist";
    String responseContent = this.wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, jsonObject.toString());
    return WxMpUserBlacklistGetResult.fromJson(responseContent);
  }

  @Override
  public void pushToBlacklist(WxMpConfig wxmpconfig,List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchblacklist";
    this.wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, new Gson().toJson(map));
  }

  @Override
  public void pullFromBlacklist(WxMpConfig wxmpconfig,List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("openid_list", openidList);
    String url = API_BLACKLIST_PREFIX + "/batchunblacklist";
    this.wxMpService.execute(wxmpconfig,new SimplePostRequestExecutor(), url, new Gson().toJson(map));
  }
}
