private static String getDebugText(Cursor cursor){
  final StringBuilder sb=new StringBuilder();
  sb.append("APN [");
  for (int i=0; i < cursor.getColumnCount(); i++) {
    final String name=cursor.getColumnName(i);
    final String value=cursor.getString(i);
    if (TextUtils.isEmpty(value)) {
      continue;
    }
    if (i > 0) {
      sb.append(' ');
    }
    sb.append(name).append('=').append(value);
  }
  sb.append("]");
  return sb.toString();
}
