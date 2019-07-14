public static boolean isSQLite3File(@NonNull File file) throws IOException {
  FileInputStream fis=new FileInputStream(file);
  byte[] sqliteHeader="SQLite format 3".getBytes();
  byte[] buffer=new byte[sqliteHeader.length];
  int count=fis.read(buffer);
  if (count < sqliteHeader.length)   return false;
  return Arrays.equals(buffer,sqliteHeader);
}
