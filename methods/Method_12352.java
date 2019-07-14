public String[] getAdminUrl(){
  String[] adminUrls=this.url.clone();
  for (int i=0; i < adminUrls.length; i++) {
    adminUrls[i]+="/" + this.apiPath;
  }
  return adminUrls;
}
