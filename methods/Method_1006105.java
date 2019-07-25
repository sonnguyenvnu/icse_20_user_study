@Subscribe public void listen(UpdateRefusedEvent updateRefusedEvent){
  jabRefFrame.getDialogService().notify(Localization.lang("Update refused."));
  BibEntry localBibEntry=updateRefusedEvent.getLocalBibEntry();
  BibEntry sharedBibEntry=updateRefusedEvent.getSharedBibEntry();
  StringBuilder message=new StringBuilder();
  message.append(Localization.lang("Update could not be performed due to existing change conflicts."));
  message.append("\r\n");
  message.append(Localization.lang("You are not working on the newest version of BibEntry."));
  message.append("\r\n");
  message.append(Localization.lang("Shared version: %0",String.valueOf(sharedBibEntry.getSharedBibEntryData().getVersion())));
  message.append("\r\n");
  message.append(Localization.lang("Local version: %0",String.valueOf(localBibEntry.getSharedBibEntryData().getVersion())));
  message.append("\r\n");
  message.append(Localization.lang("Press \"Merge entries\" to merge the changes and resolve this problem."));
  message.append("\r\n");
  message.append(Localization.lang("Canceling this operation will leave your changes unsynchronized."));
  ButtonType merge=new ButtonType(Localization.lang("Merge entries"),ButtonBar.ButtonData.YES);
  Optional<ButtonType> response=dialogService.showCustomButtonDialogAndWait(AlertType.CONFIRMATION,Localization.lang("Update refused"),message.toString(),ButtonType.CANCEL,merge);
  if (response.isPresent() && response.get().equals(merge)) {
    MergeEntriesDialog dialog=new MergeEntriesDialog(localBibEntry,sharedBibEntry,updateRefusedEvent.getBibDatabaseContext().getMode());
    Optional<BibEntry> mergedEntry=dialog.showAndWait();
    mergedEntry.ifPresent(mergedBibEntry -> {
      mergedBibEntry.getSharedBibEntryData().setSharedID(sharedBibEntry.getSharedBibEntryData().getSharedID());
      mergedBibEntry.getSharedBibEntryData().setVersion(sharedBibEntry.getSharedBibEntryData().getVersion());
      dbmsSynchronizer.synchronizeSharedEntry(mergedBibEntry);
      dbmsSynchronizer.synchronizeLocalDatabase();
    }
);
  }
}
