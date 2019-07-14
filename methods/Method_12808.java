public static String checkZipFileForCertificate(String zipPath) throws IOException {
  String certPath="";
  ZipFile zip=new ZipFile(new File(zipPath));
  InputStream is=zip.getInputStream(zip.getEntry("META-INF/MANIFEST.MF"));
  Manifest man=new Manifest(is);
  is.close();
  Set<String> signed=new HashSet();
  for (  Map.Entry<String,Attributes> entry : man.getEntries().entrySet()) {
    for (    Object attrkey : entry.getValue().keySet()) {
      if (attrkey instanceof Attributes.Name && ((Attributes.Name)attrkey).toString().indexOf("-Digest") != -1)       signed.add(entry.getKey());
    }
  }
  Set<String> entries=new HashSet<String>();
  for (Enumeration<ZipEntry> entry=(Enumeration<ZipEntry>)zip.entries(); entry.hasMoreElements(); ) {
    ZipEntry ze=entry.nextElement();
    if (!ze.isDirectory()) {
      String name=ze.getName();
      if (!name.startsWith("META-INF/")) {
        entries.add(name);
      }
 else       if (name.endsWith(".RSA") || name.endsWith(".DSA")) {
        certPath=name;
      }
    }
  }
  Set<String> unsigned=new HashSet<String>(entries);
  unsigned.removeAll(signed);
  Set<String> missing=new HashSet<String>(signed);
  missing.removeAll(entries);
  zip.close();
  if (unsigned.isEmpty() && missing.isEmpty()) {
    return certPath;
  }
  return null;
}
