/** 
 * Get spring object
 * @param xam XAnnotated member
 * @param applicationContext application context
 * @param base element base
 * @return
 */
public static Object getSpringOjbect(XAnnotatedMember xam,ApplicationContext applicationContext,Element base){
  String val=DOMHelper.getNodeValue(base,xam.path);
  if (val != null && val.length() > 0) {
    if (xam.trim) {
      val=val.trim();
    }
    return getSpringObject(xam.type,val,applicationContext);
  }
  return null;
}
