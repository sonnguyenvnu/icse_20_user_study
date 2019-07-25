@Override public boolean contains(BibEntry entry){
  if (keysUsedInAux == null) {
    AuxParserResult auxResult=auxParser.parse(filePath);
    keysUsedInAux=auxResult.getUniqueKeys();
  }
  return entry.getCiteKeyOptional().map(keysUsedInAux::contains).orElse(false);
}
