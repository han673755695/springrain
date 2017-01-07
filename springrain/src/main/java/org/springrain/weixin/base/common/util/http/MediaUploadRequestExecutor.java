package org.springrain.weixin.base.common.util.http;

import org.springrain.frame.util.HttpClientUtils;
import org.springrain.weixin.base.common.api.IWxConfig;
import org.springrain.weixin.base.common.bean.result.WxError;
import org.springrain.weixin.base.common.bean.result.WxMediaUploadResult;
import org.springrain.weixin.base.common.exception.WxErrorException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.IOException;

/**
 * 上传媒体文件请求执行器，请求的参数是File, 返回的结果是String
 *
 * @author Daniel Qian
 */
public class MediaUploadRequestExecutor implements RequestExecutor<WxMediaUploadResult, File> {

  @Override
  public WxMediaUploadResult execute(IWxConfig wxconfig, String uri, File file) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (wxconfig.getHttpProxyHost()!=null) {
        RequestConfig config = RequestConfig.custom().setProxy(new HttpHost(wxconfig.getHttpProxyHost(), wxconfig.getHttpProxyPort())).build();
        httpPost.setConfig(config);
      }
    if (file != null) {
      HttpEntity entity = MultipartEntityBuilder
              .create()
              .addBinaryBody("media", file)
              .setMode(HttpMultipartMode.RFC6532)
              .build();
      httpPost.setEntity(entity);
      httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());
    }
    try (CloseableHttpResponse response = HttpClientUtils.getHttpClient(wxconfig.getSslContext()).execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      return WxMediaUploadResult.fromJson(responseContent);
    } finally {
      httpPost.releaseConnection();
    }
  }

}
