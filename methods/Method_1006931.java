@Override public BufferedReader create(Resource resource,String encoding) throws UnsupportedEncodingException, IOException {
  return new BufferedReader(new InputStreamReader(resource.getInputStream(),encoding));
}
