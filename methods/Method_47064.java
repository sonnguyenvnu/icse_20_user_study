public static boolean mkfile(final Context context,final File file){
  final OutputStream outputStream=getOutputStream(context,file.getPath());
  if (outputStream == null) {
    return false;
  }
  try {
    outputStream.close();
    return true;
  }
 catch (  final IOException e) {
  }
  return false;
}
