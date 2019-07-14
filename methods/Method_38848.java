/** 
 * Returns <code>true</code> if tag should be closed on EOF.
 */
public boolean implicitlyCloseTagOnEOF(String nodeName){
  if (nodeName == null) {
    return false;
  }
  nodeName=nodeName.toLowerCase();
  return StringUtil.equalsOne(nodeName,CLOSED_ON_EOF) != -1;
}
