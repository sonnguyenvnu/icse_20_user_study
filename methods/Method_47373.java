/** 
 * Retrieve a path with  {@link OTGUtil#PREFIX_OTG} as prefix
 */
public String parseOTGPath(String path){
  if (path.contains(OTGUtil.PREFIX_OTG))   return path;
 else   return OTGUtil.PREFIX_OTG + path.substring(path.indexOf(":") + 1,path.length());
}
