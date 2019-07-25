/** 
 * @param text Text to parse as Type.
 * @return Type from text.
 */
public static Type parse(String text){
  Type t=null;
  if (text.isEmpty()) {
    return t;
  }
  if (isPrimitive(text)) {
    t=Type.getType(text);
  }
 else   if (isMethod(text)) {
    t=Type.getMethodType(text);
  }
 else   if (isObject(text)) {
    t=Type.getObjectType(text);
  }
 else {
    t=Type.getType(text);
  }
  return t;
}
