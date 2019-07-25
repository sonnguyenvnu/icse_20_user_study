public void update(JournalAbbreviationPreferences journalAbbreviationPreferences){
  journalAbbrev=new JournalAbbreviationRepository();
  journalAbbrev.addEntries(readJournalListFromResource(JOURNALS_FILE_BUILTIN));
  if (journalAbbreviationPreferences.useIEEEAbbreviations()) {
    journalAbbrev.addEntries(getOfficialIEEEAbbreviations());
  }
 else {
    journalAbbrev.addEntries(getStandardIEEEAbbreviations());
  }
  List<String> lists=journalAbbreviationPreferences.getExternalJournalLists();
  if (!(lists.isEmpty())) {
    Collections.reverse(lists);
    for (    String filename : lists) {
      try {
        journalAbbrev.addEntries(readJournalListFromFile(new File(filename)));
      }
 catch (      FileNotFoundException e) {
        LOGGER.info("Cannot find external journal list file " + filename,e);
      }
    }
  }
  String personalJournalList=journalAbbreviationPreferences.getPersonalJournalLists();
  if ((personalJournalList != null) && !personalJournalList.trim().isEmpty()) {
    try {
      journalAbbrev.addEntries(readJournalListFromFile(new File(personalJournalList),journalAbbreviationPreferences.getDefaultEncoding()));
    }
 catch (    FileNotFoundException e) {
      LOGGER.info("Personal journal list file '" + personalJournalList + "' not found.",e);
    }
  }
}
