/** 
 * Fetches device description xml file from server and parses it.
 * @param ddUrl URL of device description xml.
 * @return ServerDevice instance
 */
public static ServerDevice fetch(String ddUrl){
  if (ddUrl == null) {
    throw new NullPointerException("ddUrl is null.");
  }
  String ddXml="";
  try {
    ddXml=SimpleHttpClient.httpGet(ddUrl);
    Log.d(TAG,"fetch () httpGet done.");
  }
 catch (  IOException e) {
    Log.e(TAG,"fetch: IOException.",e);
    return null;
  }
  XmlElement rootElement=XmlElement.parse(ddXml);
  ServerDevice device=null;
  if ("root".equals(rootElement.getTagName())) {
    device=new ServerDevice();
    device.mDDUrl=ddUrl;
    XmlElement deviceElement=rootElement.findChild("device");
    device.mFriendlyName=deviceElement.findChild("friendlyName").getValue();
    device.mModelName=deviceElement.findChild("modelName").getValue();
    device.mUDN=deviceElement.findChild("UDN").getValue();
    XmlElement iconListElement=deviceElement.findChild("iconList");
    List<XmlElement> iconElements=iconListElement.findChildren("icon");
    for (    XmlElement iconElement : iconElements) {
      if ("image/png".equals(iconElement.findChild("mimetype").getValue())) {
        String uri=iconElement.findChild("url").getValue();
        String hostUrl=toSchemeAndHost(ddUrl);
        device.mIconUrl=hostUrl + uri;
      }
    }
    XmlElement wApiElement=deviceElement.findChild("X_ScalarWebAPI_DeviceInfo");
    XmlElement wApiServiceListElement=wApiElement.findChild("X_ScalarWebAPI_ServiceList");
    List<XmlElement> wApiServiceElements=wApiServiceListElement.findChildren("X_ScalarWebAPI_Service");
    for (    XmlElement wApiServiceElement : wApiServiceElements) {
      String serviceName=wApiServiceElement.findChild("X_ScalarWebAPI_ServiceType").getValue();
      String actionUrl=wApiServiceElement.findChild("X_ScalarWebAPI_ActionList_URL").getValue();
      device.addApiService(serviceName,actionUrl);
    }
  }
  Log.d(TAG,"fetch () parsing XML done.");
  return device;
}
