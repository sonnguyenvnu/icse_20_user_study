public static void writeStringToFile(@NonNull File file,@NonNull String content) throws IOException {
  final OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(file),ACRAConstants.UTF8);
  try {
    writer.write(content);
    writer.flush();
  }
  finally {
    safeClose(writer);
  }
}
