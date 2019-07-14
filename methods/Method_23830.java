/** 
 * Get any children that match this name or path. Similar to getChild(), but will grab multiple matches rather than only the first.
 * @param name element name or path/to/element
 * @return array of child elements that match
 * @author processing.org
 */
public XML[] getChildren(String name){
  if (name.length() > 0 && name.charAt(0) == '/') {
    throw new IllegalArgumentException("getChildren() should not begin with a slash");
  }
  if (name.indexOf('/') != -1) {
    return getChildrenRecursive(PApplet.split(name,'/'),0);
  }
  if (Character.isDigit(name.charAt(0))) {
    return new XML[]{getChild(Integer.parseInt(name))};
  }
  int childCount=getChildCount();
  XML[] matches=new XML[childCount];
  int matchCount=0;
  for (int i=0; i < childCount; i++) {
    XML kid=getChild(i);
    String kidName=kid.getName();
    if (kidName != null && kidName.equals(name)) {
      matches[matchCount++]=kid;
    }
  }
  return (XML[])PApplet.subset(matches,0,matchCount);
}
