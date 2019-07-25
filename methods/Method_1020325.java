@Override public void apply(){
  FileOutputStream fos=null;
  try {
    fos=new FileOutputStream(configFile);
    XmlUtils.writeMapXml(configMap,fos);
  }
 catch (  IOException e) {
    e.printStackTrace();
  }
catch (  XmlPullParserException e) {
    e.printStackTrace();
  }
}
