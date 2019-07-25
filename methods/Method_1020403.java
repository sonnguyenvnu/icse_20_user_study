private static Tracestate create(List<Entry> entries){
  Utils.checkState(entries.size() <= MAX_KEY_VALUE_PAIRS,"Invalid size");
  return new AutoValue_Tracestate(Collections.unmodifiableList(entries));
}
