private List<ParsedGenerated> reload() throws BackingStoreException {
  final List<ParsedGenerated> result=new ArrayList<ParsedGenerated>();
  final int length="histo.".length();
  for (  String key : prefs.keys()) {
    if (key.startsWith("histo.") && key.endsWith(".p.saved")) {
      final String name=key.substring(length,length + 1);
      final ParsedGenerated load=ParsedGenerated.loadDated(prefs,"histo." + name);
      result.add(load);
    }
  }
  return result;
}
