protected T fail(String text,String details,String token){
  String clazz=this.getClass().getSimpleName() + "InsnNode";
  StringBuilder sb=new StringBuilder(clazz + " parse failure: " + text);
  if (details != null)   sb.append('\n').append(details).append('\n');
  sb.append("Failed on token: ").append(token);
  throw new AssemblyParseException(sb.toString());
}
