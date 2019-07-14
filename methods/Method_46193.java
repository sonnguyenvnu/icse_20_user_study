/** 
 * ??????
 * @return
 */
private List<AuthInfo> buildAuthInfo(){
  List<AuthInfo> info=new ArrayList<AuthInfo>();
  String scheme=registryConfig.getParameter("scheme");
  String addAuth=registryConfig.getParameter("addAuth");
  if (StringUtils.isNotEmpty(addAuth)) {
    String[] addAuths=addAuth.split(",");
    for (    String singleAuthInfo : addAuths) {
      info.add(new AuthInfo(scheme,singleAuthInfo.getBytes()));
    }
  }
  return info;
}
