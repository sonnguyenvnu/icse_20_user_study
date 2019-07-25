@PostConstruct private void init() throws IOException, InterruptedException, NoSuchAlgorithmException {
  Map<String,String> paramMap=new HashMap<>(Map.of("client_id",client_id,"client_secret",client_secret,"grant_type","password","username",username,"password",password,"device_token",device_token,"get_secure_url","true"));
  setAccess_token(refreshToken(httpUtil.getPostEntity(paramMap)));
  paramMap.replace("grant_type","refresh_token");
  paramMap.put("refresh_token",refresh_token);
  param=httpUtil.getPostEntity(paramMap);
}
