public static Iterator<String> strings(final byte[] a){
  if (a == null) {
    return new ArrayList<String>().iterator();
  }
  return new StringsIterator(new BufferedReader(new InputStreamReader(new ByteArrayInputStream(a),StandardCharsets.UTF_8)));
}
