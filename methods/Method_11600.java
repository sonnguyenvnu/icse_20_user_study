public Task toTask(){
  return new Task(){
    @Override public String getUUID(){
      String uuid=Site.this.getDomain();
      if (uuid == null) {
        uuid=UUID.randomUUID().toString();
      }
      return uuid;
    }
    @Override public Site getSite(){
      return Site.this;
    }
  }
;
}
