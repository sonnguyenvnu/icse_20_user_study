protected File downloadSourceJarFile(Container.Entry entry){
  if (cache.containsKey(entry)) {
    return cache.get(entry);
  }
  if (!entry.isDirectory() && !failed.contains(entry)) {
    try {
      MessageDigest messageDigest=MessageDigest.getInstance("SHA-1");
      byte[] buffer=new byte[1024 * 2];
      try (DigestInputStream is=new DigestInputStream(entry.getInputStream(),messageDigest)){
        while (is.read(buffer) > -1)         ;
      }
       byte[] array=messageDigest.digest();
      StringBuilder sb=new StringBuilder();
      for (      byte b : array) {
        sb.append(hexa((b & 255) >> 4));
        sb.append(hexa(b & 15));
      }
      String sha1=sb.toString();
      URL searchUrl=new URL(MAVENORG_SEARCH_URL_PREFIX + sha1 + MAVENORG_SEARCH_URL_SUFFIX);
      boolean sourceAvailable=false;
      String id=null;
      String numFound=null;
      try (InputStream is=searchUrl.openStream()){
        XMLStreamReader reader=XMLInputFactory.newInstance().createXMLStreamReader(is);
        String name="";
        while (reader.hasNext()) {
switch (reader.next()) {
case XMLStreamConstants.START_ELEMENT:
            if ("str".equals(reader.getLocalName())) {
              if ("id".equals(reader.getAttributeValue(null,"name"))) {
                name="id";
              }
 else {
                name="str";
              }
            }
 else             if ("result".equals(reader.getLocalName())) {
              numFound=reader.getAttributeValue(null,"numFound");
            }
 else {
              name="";
            }
          break;
case XMLStreamConstants.CHARACTERS:
switch (name) {
case "id":
          id=reader.getText().trim();
        break;
case "str":
      sourceAvailable|="-sources.jar".equals(reader.getText().trim());
    break;
}
break;
}
}
reader.close();
}
 String groupId=null, artifactId=null, version=null;
if ("0".equals(numFound)) {
Properties pomProperties=getPomProperties(entry);
if (pomProperties != null) {
groupId=pomProperties.getProperty("groupId");
artifactId=pomProperties.getProperty("artifactId");
version=pomProperties.getProperty("version");
}
}
 else if ("1".equals(numFound) && sourceAvailable) {
int index1=id.indexOf(':');
int index2=id.lastIndexOf(':');
groupId=id.substring(0,index1);
artifactId=id.substring(index1 + 1,index2);
version=id.substring(index2 + 1);
}
if (artifactId != null) {
String filePath=groupId.replace('.','/') + '/' + artifactId + '/' + version + '/' + artifactId + '-' + version;
URL loadUrl=new URL(MAVENORG_LOAD_URL_PREFIX + filePath + MAVENORG_LOAD_URL_SUFFIX);
File tmpFile=File.createTempFile("jd-gui.tmp.",'.' + groupId + '_' + artifactId + '_' + version + "-sources.jar");
tmpFile.delete();
tmpFile.deleteOnExit();
try (InputStream is=new BufferedInputStream(loadUrl.openStream());OutputStream os=new BufferedOutputStream(new FileOutputStream(tmpFile))){
int read=is.read(buffer);
while (read > 0) {
os.write(buffer,0,read);
read=is.read(buffer);
}
}
 cache.put(entry,tmpFile);
return tmpFile;
}
}
 catch (Exception e) {
assert ExceptionUtil.printStackTrace(e);
}
}
failed.add(entry);
return null;
}
