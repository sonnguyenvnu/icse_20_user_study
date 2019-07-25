@Activate protected void activate(Map<String,Object> config){
  this.proxy=new MarketplaceProxy();
  modified(config);
}
