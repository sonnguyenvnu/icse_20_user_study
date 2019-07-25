@Override public void execute(){
  if (jabRefFrame.getBasePanelCount() <= 0) {
    LOGGER.error("Action 'New entry' must be disabled when no database is open.");
    return;
  }
  if (type.isPresent()) {
    jabRefFrame.getCurrentBasePanel().insertEntry(new BibEntry(type.get()));
  }
 else {
    EntryTypeView typeChoiceDialog=new EntryTypeView(jabRefFrame.getCurrentBasePanel(),dialogService,preferences);
    EntryType selectedType=typeChoiceDialog.showAndWait().orElse(null);
    if (selectedType == null) {
      return;
    }
    trackNewEntry(selectedType);
    jabRefFrame.getCurrentBasePanel().insertEntry(new BibEntry(selectedType));
  }
}
