/** 
 * Returns <code>true</code> if parent node tag can be closed implicitly.
 */
public boolean implicitlyCloseParentTagOnNewTag(String parentNodeName,String nodeName){
  if (parentNodeName == null) {
    return false;
  }
  parentNodeName=parentNodeName.toLowerCase();
  nodeName=nodeName.toLowerCase();
  for (int i=0; i < IMPLIED_ON_START.length; i+=2) {
    if (StringUtil.equalsOne(parentNodeName,IMPLIED_ON_START[i]) != -1) {
      if (StringUtil.equalsOne(nodeName,IMPLIED_ON_START[i + 1]) != -1) {
        return true;
      }
    }
  }
  return false;
}
