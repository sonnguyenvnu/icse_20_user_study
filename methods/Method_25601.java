/** 
 * Given a canonical class name, infers the binary class name using case conventions. For example, give  {@code com.example.Outer.Inner} returns {@code com.example.Outer$Inner}.
 */
@VisibleForTesting static String inferBinaryName(String classname){
  int len=classname.length();
  checkArgument(!classname.isEmpty(),"class name must be non-empty");
  checkArgument(classname.charAt(len - 1) != '.',"invalid class name: %s",classname);
  int lastPeriod=classname.lastIndexOf('.');
  if (lastPeriod == -1) {
    return classname;
  }
  int secondToLastPeriod=classname.lastIndexOf('.',lastPeriod - 1);
  if (secondToLastPeriod != -1 && !Character.isUpperCase(classname.charAt(secondToLastPeriod + 1))) {
    return classname;
  }
  StringBuilder sb=new StringBuilder(len);
  boolean foundUppercase=false;
  for (int i=0; i < len; i++) {
    char c=classname.charAt(i);
    foundUppercase=foundUppercase || Character.isUpperCase(c);
    if (c == '.') {
      sb.append(foundUppercase ? '$' : '.');
    }
 else {
      sb.append(c);
    }
  }
  return sb.toString();
}
