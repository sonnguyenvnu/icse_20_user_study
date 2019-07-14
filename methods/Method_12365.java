@Override public Map<String,String> getMetadata(){
  if (StringUtils.hasText(this.cfApplicationProperties.getApplicationId()) && StringUtils.hasText(this.cfApplicationProperties.getInstanceIndex())) {
    Map<String,String> map=new HashMap<>();
    map.put("applicationId",this.cfApplicationProperties.getApplicationId());
    map.put("instanceId",this.cfApplicationProperties.getInstanceIndex());
    return map;
  }
  return Collections.emptyMap();
}
