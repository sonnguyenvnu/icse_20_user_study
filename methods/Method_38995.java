/** 
 * Returns shorten class name.
 */
protected String shortenClassName(final String className){
  int lastDotIndex=className.lastIndexOf('.');
  if (lastDotIndex == -1) {
    return className;
  }
  StringBuilder shortClassName=new StringBuilder(className.length());
  int start=0;
  while (true) {
    shortClassName.append(className.charAt(start));
    int next=className.indexOf('.',start);
    if (next == lastDotIndex) {
      break;
    }
    start=next + 1;
    shortClassName.append('.');
  }
  shortClassName.append(className.substring(lastDotIndex));
  return shortClassName.toString();
}
