@Override public void init(){
  NewRelic.setTransactionName(null,name);
  NewRelic.addCustomParameter("timestamp",timestamp);
  NewRelic.setRequestAndResponse(new RatpackRequest(context.getRequest()),new RatpackResponse(context.getResponse()));
}
