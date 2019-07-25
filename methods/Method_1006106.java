@Subscribe public void listen(SharedEntryNotPresentEvent event){
  BasePanel panel=jabRefFrame.getCurrentBasePanel();
  EntryEditor entryEditor=panel.getEntryEditor();
  panel.getUndoManager().addEdit(new UndoableRemoveEntry(panel.getDatabase(),event.getBibEntry()));
  if (Objects.nonNull(entryEditor) && (entryEditor.getEntry() == event.getBibEntry())) {
    dialogService.showInformationDialogAndWait(Localization.lang("Shared entry is no longer present"),Localization.lang("The entry you currently work on has been deleted on the shared side.") + "\n" + Localization.lang("You can restore the entry using the \"Undo\" operation."));
    panel.closeBottomPane();
  }
}
