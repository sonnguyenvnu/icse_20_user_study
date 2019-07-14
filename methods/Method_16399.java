@Override public void writeFile(String fileId,OutputStream out,long skip) throws IOException {
  try (InputStream stream=createRequest("/download/" + fileId).header("Range","bytes=" + skip).get().asStream()){
    StreamUtils.copy(stream,out);
  }
 }
