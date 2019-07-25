@Benchmark public ParserResult parse() throws IOException {
  BibtexParser parser=new BibtexParser(Globals.prefs.getImportFormatPreferences(),new DummyFileUpdateMonitor());
  return parser.parse(new StringReader(bibtexString));
}
