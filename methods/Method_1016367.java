public static final String table(final byte[] b,final int linewidth){
  if (b == null)   return "NULL";
  if (b.length == 0)   return "[]";
  final StringBuilder sb=new StringBuilder(b.length * 4);
  for (int i=0; i < b.length; i++) {
    if (i % linewidth == 0)     sb.append('\n').append("# ").append(Integer.toHexString(i)).append(": ");
 else     sb.append(',');
    sb.append(' ').append(Integer.toString(0xff & b[i]));
    if (i >= 65535)     break;
  }
  sb.append('\n');
  return sb.toString();
}
