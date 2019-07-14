protected boolean isContainingPackage(String pkg1,String pkg2){
  return pkg1.equals(pkg2) || pkg1.length() < pkg2.length() && pkg2.startsWith(pkg1) && pkg2.charAt(pkg1.length()) == '.';
}
