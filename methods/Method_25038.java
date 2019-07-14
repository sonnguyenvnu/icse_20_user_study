private Response newFixedFileResponse(File file,String mime) throws FileNotFoundException {
  Response res;
  res=Response.newFixedLengthResponse(Status.OK,mime,new FileInputStream(file),(int)file.length());
  res.addHeader("Accept-Ranges","bytes");
  return res;
}
