private HashMap<String,String> getLocaleFileStrings(File file,boolean preserveEscapes){
  FileInputStream stream=null;
  reloadLastFile=false;
  try {
    if (!file.exists()) {
      return new HashMap<>();
    }
    HashMap<String,String> stringMap=new HashMap<>();
    XmlPullParser parser=Xml.newPullParser();
    stream=new FileInputStream(file);
    parser.setInput(stream,"UTF-8");
    int eventType=parser.getEventType();
    String name=null;
    String value=null;
    String attrName=null;
    while (eventType != XmlPullParser.END_DOCUMENT) {
      if (eventType == XmlPullParser.START_TAG) {
        name=parser.getName();
        int c=parser.getAttributeCount();
        if (c > 0) {
          attrName=parser.getAttributeValue(0);
        }
      }
 else       if (eventType == XmlPullParser.TEXT) {
        if (attrName != null) {
          value=parser.getText();
          if (value != null) {
            value=value.trim();
            if (preserveEscapes) {
              value=value.replace("<","&lt;").replace(">","&gt;").replace("'","\\'").replace("& ","&amp; ");
            }
 else {
              value=value.replace("\\n","\n");
              value=value.replace("\\","");
              String old=value;
              value=value.replace("&lt;","<");
              if (!reloadLastFile && !value.equals(old)) {
                reloadLastFile=true;
              }
            }
          }
        }
      }
 else       if (eventType == XmlPullParser.END_TAG) {
        value=null;
        attrName=null;
        name=null;
      }
      if (name != null && name.equals("string") && value != null && attrName != null && value.length() != 0 && attrName.length() != 0) {
        stringMap.put(attrName,value);
        name=null;
        value=null;
        attrName=null;
      }
      eventType=parser.next();
    }
    return stringMap;
  }
 catch (  Exception e) {
    FileLog.e(e);
    reloadLastFile=true;
  }
 finally {
    try {
      if (stream != null) {
        stream.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  return new HashMap<>();
}
