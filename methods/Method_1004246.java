long entries(){
  if (entries < 0) {
    throw new IllegalStateException("If in-memory Chronicle Map is created or persisted\n" + "to a file for the first time (i. e. not accessing existing file),\n" + "ChronicleMapBuilder.entries() must be configured.\n" + "See Chronicle Map 3 tutorial and javadocs for more information");
  }
  return entries;
}
