@Override protected void write(OutputStream outputStream,@NonNull Uri content) throws IOException {
  UriUtils.copyFromUri(context,outputStream,content);
}
