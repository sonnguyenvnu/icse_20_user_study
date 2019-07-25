public Document[] parse(final MultiProtocolURL location,final String mimeType,final String charset,final JarFile jf){
  StringBuilder sb=new StringBuilder();
  String title=location.getFileName();
  AndroidManifestParser manifest=null;
  try {
    InputStream is=jf.getInputStream(jf.getEntry("AndroidManifest.xml"));
    byte[] xml=new byte[is.available()];
    is.read(xml);
    manifest=new AndroidManifestParser(xml,true);
    title=location.getFileName() + " " + manifest.packageName + " " + manifest.versionName;
    sb.append(title).append(". ");
    for (    String p : manifest.permissions)     sb.append(p).append(". ");
  }
 catch (  IOException e) {
    Data.logger.error("Catched Exception",e);
  }
  Enumeration<JarEntry> je=jf.entries();
  while (je.hasMoreElements()) {
    String path=je.nextElement().toString();
    sb.append(path).append(". ");
  }
  final Collection<AnchorURL> links=new ArrayList<>();
  try {
    InputStream is=jf.getInputStream(jf.getEntry("resources.arsc"));
    List<String> resources=resourcesArscParser(is);
    for (    String s : resources) {
      sb.append(s).append(". ");
      int p=s.indexOf("http://");
      if (p < 0)       p=s.indexOf("https://");
      if (p < 0)       p=s.indexOf("ftp://");
      if (p >= 0) {
        int q=s.indexOf(' ',p + 1);
        String link=q < 0 ? s.substring(p) : s.substring(p,q);
        try {
          links.add(new AnchorURL(link));
        }
 catch (        MalformedURLException e) {
        }
      }
    }
  }
 catch (  IOException e) {
    Data.logger.error("Catched Exception",e);
  }
  return new Document[]{new Document(location,mimeType,charset,this,null,null,singleList(title),null,manifest == null ? "" : manifest.packageName,null,null,0.0d,0.0d,sb.toString(),links,null,null,false,new Date())};
}
