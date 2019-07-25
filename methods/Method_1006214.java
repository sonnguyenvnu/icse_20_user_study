@Override public String format(String fieldText){
  return repostioryLoader.getRepository(journalAbbreviationPreferences).getIsoAbbreviation(fieldText).orElse(fieldText);
}
