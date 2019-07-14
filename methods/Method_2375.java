/** 
 * ????
 * @return
 */
public JSONObject policy(){
  JSONObject result=new JSONObject();
  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
  String dir=sdf.format(new Date());
  long expireEndTime=System.currentTimeMillis() + OssConstant.ALIYUN_OSS_EXPIRE * 1000;
  Date expiration=new Date(expireEndTime);
  long maxSize=OssConstant.ALIYUN_OSS_MAX_SIZE * 1024 * 1024;
  JSONObject callback=new JSONObject();
  callback.put("callbackUrl",PropertiesFileUtil.getInstance("config").get("aliyun.oss.callback"));
  callback.put("callbackBody","filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}");
  callback.put("callbackBodyType","application/x-www-form-urlencoded");
  String action="http://" + OssConstant.ALIYUN_OSS_BUCKET_NAME + "." + OssConstant.ALIYUN_OSS_ENDPOINT;
  try {
    PolicyConditions policyConds=new PolicyConditions();
    policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE,0,maxSize);
    policyConds.addConditionItem(MatchMode.StartWith,PolicyConditions.COND_KEY,dir);
    String postPolicy=aliyunOssClient.generatePostPolicy(expiration,policyConds);
    byte[] binaryData=postPolicy.getBytes("utf-8");
    String policy=BinaryUtil.toBase64String(binaryData);
    String signature=aliyunOssClient.calculatePostSignature(postPolicy);
    String callbackData=BinaryUtil.toBase64String(callback.toString().getBytes("utf-8"));
    result.put("OSSAccessKeyId",aliyunOssClient.getCredentialsProvider().getCredentials().getAccessKeyId());
    result.put("policy",policy);
    result.put("signature",signature);
    result.put("dir",dir);
    result.put("callback",callbackData);
    result.put("action",action);
  }
 catch (  Exception e) {
    LOGGER.error("??????",e);
  }
  return result;
}
