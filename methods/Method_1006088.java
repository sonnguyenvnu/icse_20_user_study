public void paste(){
  List<BibEntry> entriesToAdd=Globals.clipboardManager.extractData();
  if (!entriesToAdd.isEmpty()) {
    NamedCompound ce=new NamedCompound((entriesToAdd.size() > 1 ? Localization.lang("paste entries") : Localization.lang("paste entry")));
    for (    BibEntry entryToAdd : entriesToAdd) {
      UpdateField.setAutomaticFields(entryToAdd,Globals.prefs.getUpdateFieldPreferences());
      database.getDatabase().insertEntry(entryToAdd);
      ce.addEdit(new UndoableInsertEntry(database.getDatabase(),entryToAdd));
    }
    ce.end();
    undoManager.addEdit(ce);
    panel.output(panel.formatOutputMessage(Localization.lang("Pasted"),entriesToAdd.size()));
    BibEntry firstNewEntry=entriesToAdd.get(0);
    if (Globals.prefs.getBoolean(JabRefPreferences.AUTO_OPEN_FORM)) {
      panel.showAndEdit(firstNewEntry);
    }
    clearAndSelect(firstNewEntry);
    this.requestFocus();
  }
}
