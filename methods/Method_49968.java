private void putPartToMaps(PduPart part){
  byte[] contentId=part.getContentId();
  if (null != contentId) {
    mPartMapByContentId.put(new String(contentId),part);
  }
  byte[] contentLocation=part.getContentLocation();
  if (null != contentLocation) {
    String clc=new String(contentLocation);
    mPartMapByContentLocation.put(clc,part);
  }
  byte[] name=part.getName();
  if (null != name) {
    String clc=new String(name);
    mPartMapByName.put(clc,part);
  }
  byte[] fileName=part.getFilename();
  if (null != fileName) {
    String clc=new String(fileName);
    mPartMapByFileName.put(clc,part);
  }
}
