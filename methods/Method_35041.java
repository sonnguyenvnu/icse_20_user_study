@Override public Parsed apply(@NonNull BufferedSource bufferedSource) throws ParserException {
  try (InputStreamReader reader=new InputStreamReader(bufferedSource.inputStream(),Charset.forName("UTF-8"))){
    return gson.fromJson(reader,type);
  }
 catch (  IOException e) {
    throw new ParserException(e.getMessage(),e);
  }
}
