/** 
 * can be extended
 * @param appName
 * @return
 */
protected ApplicationInfoRequest buildApplicationRequest(String appName){
  ApplicationInfoRequest applicationInfoRequest=new ApplicationInfoRequest();
  applicationInfoRequest.setAppName(appName);
  return applicationInfoRequest;
}
