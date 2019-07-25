public static <T extends CharSequence>String join(@NotNull T[] strings,@NotNull String delimiter){
  int capacity=0;
  int delimLength=delimiter.length();
  for (  T value : strings)   capacity+=value.length() + delimLength;
  StringBuilder buffer=new StringBuilder(capacity);
  boolean first=true;
  for (  T value : strings) {
    if (!first) {
      buffer.append(delimiter);
    }
 else {
      first=false;
    }
    buffer.append(value);
  }
  return buffer.toString();
}
