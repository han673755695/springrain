package org.springrain.weixin.base.mp.api.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.springrain.weixin.base.mp.api.WxMpService;
import org.springrain.weixin.base.mp.api.WxMpUserTagService;
import org.springrain.weixin.base.mp.bean.tag.WxTagListUser;
import org.springrain.weixin.base.mp.bean.tag.WxUserTag;
import org.springrain.weixin.base.mp.util.json.WxMpGsonBuilder;
import org.springrain.weixin.entity.WxMpConfig;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

/**
 *
 * @author <a href="https://github.com/binarywang">binarywang(Binary Wang)</a>
 *         Created by Binary Wang on 2016/9/2.
 */
public class WxMpUserTagServiceImpl implements WxMpUserTagService {
  private static final String API_URL_PREFIX = "https://api.weixin.qq.com/cgi-bin/tags";

  private WxMpService wxMpService;

  public WxMpUserTagServiceImpl() {
  }

  @Override
  public WxUserTag tagCreate(WxMpConfig wxmpconfig,String name) throws WxErrorException {
    String url = API_URL_PREFIX + "/create";
    JsonObject json = new JsonObject();
    JsonObject tagJson = new JsonObject();
    tagJson.addProperty("name", name);
    json.add("tag", tagJson);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    return WxUserTag.fromJson(responseContent);
  }

  @Override
  public List<WxUserTag> tagGet(WxMpConfig wxmpconfig) throws WxErrorException {
    String url = API_URL_PREFIX + "/get";

    String responseContent = wxMpService.get(wxmpconfig,url, null);
    return WxUserTag.listFromJson(responseContent);
  }

  @Override
  public Boolean tagUpdate(WxMpConfig wxmpconfig,Long id, String name) throws WxErrorException {
    String url = API_URL_PREFIX + "/update";

    JsonObject json = new JsonObject();
    JsonObject tagJson = new JsonObject();
    tagJson.addProperty("id", id);
    tagJson.addProperty("name", name);
    json.add("tag", tagJson);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    WxError wxError = WxError.fromJson(responseContent);
    if (wxError.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(wxError);
  }

  @Override
  public Boolean tagDelete(WxMpConfig wxmpconfig,Long id) throws WxErrorException {
    String url = API_URL_PREFIX + "/delete";

    JsonObject json = new JsonObject();
    JsonObject tagJson = new JsonObject();
    tagJson.addProperty("id", id);
    json.add("tag", tagJson);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    WxError wxError = WxError.fromJson(responseContent);
    if (wxError.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(wxError);
  }

  @Override
  public WxTagListUser tagListUser(WxMpConfig wxmpconfig,Long tagId, String nextOpenid)
      throws WxErrorException {
    String url = "https://api.weixin.qq.com/cgi-bin/user/tag/get";

    JsonObject json = new JsonObject();
    json.addProperty("tagid", tagId);
    json.addProperty("next_openid", StringUtils.trimToEmpty(nextOpenid));

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    return WxTagListUser.fromJson(responseContent);
  }

  @Override
  public boolean batchTagging(WxMpConfig wxmpconfig,Long tagId, String[] openids)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/members/batchtagging";

    JsonObject json = new JsonObject();
    json.addProperty("tagid", tagId);
    JsonArray openidArrayJson = new JsonArray();
    for (String openid : openids) {
      openidArrayJson.add(openid);
    }
    json.add("openid_list", openidArrayJson);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    WxError wxError = WxError.fromJson(responseContent);
    if (wxError.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(wxError);
  }

  @Override
  public boolean batchUntagging(WxMpConfig wxmpconfig,Long tagId, String[] openids)
      throws WxErrorException {
    String url = API_URL_PREFIX + "/members/batchuntagging";

    JsonObject json = new JsonObject();
    json.addProperty("tagid", tagId);
    JsonArray openidArrayJson = new JsonArray();
    for (String openid : openids) {
      openidArrayJson.add(openid);
    }
    json.add("openid_list", openidArrayJson);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());
    WxError wxError = WxError.fromJson(responseContent);
    if (wxError.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(wxError);
  }

  @Override
  public List<Long> userTagList(WxMpConfig wxmpconfig,String openid) throws WxErrorException {
    String url = API_URL_PREFIX + "/getidlist";

    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);

    String responseContent = wxMpService.post(wxmpconfig,url, json.toString());

    return WxMpGsonBuilder.create().fromJson(
        new JsonParser().parse(responseContent).getAsJsonObject().get("tagid_list"),
        new TypeToken<List<Long>>() {
    }.getType());
  }
}
