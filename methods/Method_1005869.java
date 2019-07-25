static boolean extract(String libPath,boolean withLinuxVendor,StringBuffer message){
  String libFullName=computeLibraryFullName(withLinuxVendor);
  return extract(libPath + SEPARATOR + libFullName,libFullName,message);
}
