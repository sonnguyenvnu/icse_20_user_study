@Override public void addAlias(String alias,String index) throws IOException {
  final Map actionAlias=ImmutableMap.of("actions",ImmutableList.of(ImmutableMap.of("add",ImmutableMap.of("index",index,"alias",alias))));
  performRequest(REQUEST_TYPE_POST,REQUEST_SEPARATOR + "_aliases",mapWriter.writeValueAsBytes(actionAlias));
}
