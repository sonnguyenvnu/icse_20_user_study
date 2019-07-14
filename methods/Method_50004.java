public String generateLocation(){
  byte[] location=(byte[])mPartHeader.get(P_NAME);
  if (null == location) {
    location=(byte[])mPartHeader.get(P_FILENAME);
    if (null == location) {
      location=(byte[])mPartHeader.get(P_CONTENT_LOCATION);
    }
  }
  if (null == location) {
    byte[] contentId=(byte[])mPartHeader.get(P_CONTENT_ID);
    return "cid:" + new String(contentId);
  }
 else {
    return new String(location);
  }
}
