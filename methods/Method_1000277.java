protected static final String str(Object... args){
  if (args == null || args.length == 0)   return "[]";
  StringBuilder sb=new StringBuilder();
  sb.append('[');
  for (  Object object : args)   sb.append(toString(object)).append(",");
  sb.replace(sb.length() - 1,sb.length(),"]");
  return sb.toString();
}
