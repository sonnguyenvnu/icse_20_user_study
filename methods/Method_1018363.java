@PostConstruct public void init() throws IOException, InterruptedException {
  formData=new HashMap<>(){
{
      put("entry","sso");
      put("gateway","1");
      put("from","null");
      put("savestate","30");
      put("useticket","0");
      put("pagerefer","");
      put("vsnf","1");
      put("su",Base64.getEncoder().encodeToString(username.getBytes()));
      put("service","sso");
      put("sp",password);
      put("sr","1920*1080");
      put("encoding","UTF-8");
      put("cdult","3");
      put("domain","sina.com.cn");
      put("prelt","0");
      put("returntype","TEXT");
    }
  }
;
  setSinaCookies();
}
