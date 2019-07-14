@Override protected void write(OutputStream outputStream,@NonNull Pair<String,List<Uri>> content) throws IOException {
  final PrintWriter writer=new PrintWriter(new OutputStreamWriter(outputStream,ACRAConstants.UTF8));
  writer.append(SECTION_START).format(CONTENT_DISPOSITION,"ACRA_REPORT","").format(CONTENT_TYPE,contentType).append(NEW_LINE).append(content.first);
  for (  Uri uri : content.second) {
    try {
      String name=UriUtils.getFileNameFromUri(context,uri);
      writer.append(SECTION_START).format(CONTENT_DISPOSITION,"ACRA_ATTACHMENT",name).format(CONTENT_TYPE,UriUtils.getMimeType(context,uri)).append(NEW_LINE).flush();
      UriUtils.copyFromUri(context,outputStream,uri);
    }
 catch (    FileNotFoundException e) {
      ACRA.log.w("Not sending attachment",e);
    }
  }
  writer.append(MESSAGE_END).flush();
}
