/** 
 * Filters a non-method descriptor based on display configuration <i>(simplification)</i>
 * @param type Input type.
 * @return Filtered version of given type.
 */
public static String filter(Type type){
  if (type.getSort() == Type.METHOD) {
    Thread.dumpStack();
  }
  if (ConfDisplay.instance().simplify) {
    if (type.getSort() == Type.ARRAY) {
      String base=filter(type.getElementType());
      StringBuilder sb=new StringBuilder();
      for (int i=0; i < type.getDimensions(); i++) {
        sb.append("[]");
      }
      return base + sb.toString();
    }
    String name=type.getClassName();
    if (name.contains(".")) {
      name=name.substring(name.lastIndexOf(".") + 1);
    }
    return name;
  }
  return toString(type);
}
