private static void propertyUsage(PrintStream errStream,String prefix,String name,String alias,Class<?> type,String delimiter,String description,Object defaultValue){
  StringBuilder sb=new StringBuilder("  ");
  sb.append(prefix);
  sb.append(name);
  if (alias != null) {
    sb.append(" (");
    sb.append(prefix);
    sb.append(alias);
    sb.append(")");
  }
  if (type == Boolean.TYPE || type == Boolean.class) {
    sb.append("\t[flag]\t");
    sb.append(description);
  }
 else {
    sb.append("\t[");
    if (type.isArray()) {
      String typeName=getTypeName(type.getComponentType());
      sb.append(typeName);
      sb.append("[");
      sb.append(delimiter);
      sb.append("]");
    }
 else {
      String typeName=getTypeName(type);
      sb.append(typeName);
    }
    sb.append("]\t");
    sb.append(description);
    if (defaultValue != null) {
      sb.append(" (");
      if (type.isArray()) {
        List<Object> list=new ArrayList<Object>();
        int len=Array.getLength(defaultValue);
        for (int i=0; i < len; i++) {
          list.add(Array.get(defaultValue,i));
        }
        sb.append(list);
      }
 else {
        sb.append(defaultValue);
      }
      sb.append(")");
    }
  }
  errStream.println(sb);
}
