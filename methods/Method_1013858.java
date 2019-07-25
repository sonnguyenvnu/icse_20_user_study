public static String join(@NotNull Iterable<? extends CharSequence> strings,@NotNull String delimiter){
  int capacity=0;
  int delimLength=delimiter.length();
  Iterator<? extends CharSequence> iter=strings.iterator();
  if (iter.hasNext())   capacity+=iter.next().length() + delimLength;
  StringBuilder buffer=new StringBuilder(capacity);
  iter=strings.iterator();
  if (iter.hasNext()) {
    buffer.append(iter.next());
    while (iter.hasNext()) {
      buffer.append(delimiter);
      buffer.append(iter.next());
    }
  }
  return buffer.toString();
}
