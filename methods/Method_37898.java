public static String getShortClassName(final Class clazz,final int shortUpTo){
  final String[] chunks=StringUtil.splitc(clazz.getName(),'.');
  final StringBand stringBand=new StringBand(chunks.length);
  int ndx=chunks.length - shortUpTo;
  if (ndx < 0) {
    ndx=0;
  }
  for (int i=0; i < ndx; i++) {
    if (i > 0) {
      stringBand.append('.');
    }
    stringBand.append(chunks[i].charAt(0));
  }
  for (int i=ndx; i < chunks.length; i++) {
    if (i > 0) {
      stringBand.append('.');
    }
    stringBand.append(chunks[i]);
  }
  return stringBand.toString();
}
