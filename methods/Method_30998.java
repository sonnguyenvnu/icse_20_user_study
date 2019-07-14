@NonNull public static String readerToString(@NonNull Reader reader) throws IOException {
  StringBuilder builder=new StringBuilder();
  char[] buffer=new char[BUFFER_SIZE];
  int length;
  while ((length=reader.read(buffer)) != -1) {
    builder.append(buffer,0,length);
  }
  return builder.toString();
}
