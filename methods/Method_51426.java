private String delimitedPackageNames(){
  if (legalPackageNames == null || legalPackageNames.length == 0) {
    return "";
  }
  if (legalPackageNames.length == 1) {
    return legalPackageNames[0];
  }
  StringBuilder sb=new StringBuilder();
  sb.append(legalPackageNames[0]);
  for (int i=1; i < legalPackageNames.length; i++) {
    sb.append(PACKAGE_NAME_DELIMITER).append(legalPackageNames[i]);
  }
  return sb.toString();
}
