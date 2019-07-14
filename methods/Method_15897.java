private Map<String,Object> parseObject(String json){
  if (!StringUtils.hasText(json)) {
    return null;
  }
  return JSON.parseObject(json);
}
