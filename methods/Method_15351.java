@Override public DemoObjectParser createObjectParser(JSONObject request,String parentPath,String name,SQLConfig arrayConfig,boolean isSubquery) throws Exception {
  return new DemoObjectParser(session,request,parentPath,name,arrayConfig,isSubquery){
    @Override public JSONObject parseResponse(    JSONRequest request) throws Exception {
      DemoParser demoParser=new DemoParser(RequestMethod.GET);
      demoParser.setSession(session);
      demoParser.setNoVerifyLogin(noVerifyLogin);
      demoParser.setNoVerifyRole(noVerifyRole);
      return demoParser.parseResponse(request);
    }
  }
.setMethod(requestMethod).setParser(this);
}
