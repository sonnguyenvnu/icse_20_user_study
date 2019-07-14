@PostConstruct public void init(){
  if (this.properties == null || this.server == null) {
    return;
  }
  String prefix=this.server.getPrefix();
  if (StringUtils.hasText(prefix) && !StringUtils.hasText(this.properties.getMetadata().get("configPath"))) {
    this.properties.getMetadata().put("configPath",prefix);
  }
}
