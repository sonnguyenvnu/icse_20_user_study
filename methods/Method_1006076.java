private String unabbreviate(){
  List<BibEntry> entries=panel.getSelectedEntries();
  UndoableUnabbreviator undoableAbbreviator=new UndoableUnabbreviator(Globals.journalAbbreviationLoader.getRepository(Globals.prefs.getJournalAbbreviationPreferences()));
  NamedCompound ce=new NamedCompound(Localization.lang("Unabbreviate journal names"));
  int count=0;
  for (  BibEntry entry : entries) {
    for (    String journalField : InternalBibtexFields.getJournalNameFields()) {
      if (undoableAbbreviator.unabbreviate(panel.getDatabase(),entry,journalField,ce)) {
        count++;
      }
    }
  }
  if (count > 0) {
    ce.end();
    panel.getUndoManager().addEdit(ce);
    panel.markBaseChanged();
    return Localization.lang("Unabbreviated %0 journal names.",String.valueOf(count));
  }
 else {
    return Localization.lang("No journal names could be unabbreviated.");
  }
}
