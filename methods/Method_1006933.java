@Override public BufferedReader create(Resource resource,String encoding) throws UnsupportedEncodingException, IOException {
  return new BinaryBufferedReader(new InputStreamReader(resource.getInputStream(),encoding),lineEnding);
}
