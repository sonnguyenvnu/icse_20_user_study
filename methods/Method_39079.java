/** 
 * Adds root package and its path mapping. Duplicate root packages are ignored, if mapping path is equals, otherwise exception is thrown.
 */
public void addRootPackage(final String rootPackage,String mapping){
  if (packages == null) {
    packages=new String[0];
  }
  if (mappings == null) {
    mappings=new String[0];
  }
  if (mapping.length() > 0) {
    if (!mapping.startsWith(StringPool.SLASH)) {
      mapping=StringPool.SLASH + mapping;
    }
    if (mapping.endsWith(StringPool.SLASH)) {
      mapping=StringUtil.substring(mapping,0,-1);
    }
  }
  for (int i=0; i < packages.length; i++) {
    if (packages[i].equals(rootPackage)) {
      if (mappings[i].equals(mapping)) {
        return;
      }
      throw new MadvocException("Different mappings for the same root package: " + rootPackage);
    }
  }
  packages=ArraysUtil.append(packages,rootPackage);
  mappings=ArraysUtil.append(mappings,mapping);
}
