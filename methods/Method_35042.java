@Override public Parsed apply(@NonNull String s) throws ParserException {
  return gson.fromJson(s,type);
}
