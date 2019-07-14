private void addRules(String[] languages,PluralRules rules){
  for (  String language : languages) {
    allRules.put(language,rules);
  }
}
