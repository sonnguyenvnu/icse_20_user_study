public Object tryConvertToObject(String json,Class type,OAuth2Response response){
  if (json.startsWith("{")) {
    if (ResponseMessage.class.isAssignableFrom(type)) {
      return JSON.parseObject(json,type);
    }
    JSONObject message=JSON.parseObject(json,Feature.DisableFieldSmartMatch);
    if (message.size() <= responseMessageFieldSize && message.get("status") != null && message.get("timestamp") != null) {
      Integer status=message.getInteger("status");
      if (status != 200) {
        throw new BusinessException(message.getString("message"),status);
      }
      Object data=message.get("result");
      if (data == null) {
        return null;
      }
      if (data instanceof JSONObject) {
        if (type == Authentication.class) {
          return autzParser.apply(data);
        }
        return ((JSONObject)data).toJavaObject(type);
      }
      if (data instanceof JSONArray) {
        if (type == Authentication.class) {
          return ((JSONArray)data).stream().map(autzParser).collect(Collectors.toList());
        }
        return ((JSONArray)data).toJavaList(type);
      }
      return message.getObject("result",type);
    }
    if (springMvcErrorResponseKeys.containsAll(message.keySet())) {
      throw new OAuth2RequestException(ErrorType.SERVICE_ERROR,response);
    }
    return message.toJavaObject(type);
  }
 else   if (json.startsWith("[")) {
    if (type == Authentication.class) {
      return (JSON.parseArray(json)).stream().map(autzParser).collect(Collectors.toList());
    }
    return JSON.parseArray(json,type);
  }
  return null;
}
