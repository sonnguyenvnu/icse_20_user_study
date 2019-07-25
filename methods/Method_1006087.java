public void copy(){
  List<BibEntry> selectedEntries=getSelectedEntries();
  if (!selectedEntries.isEmpty()) {
    try {
      Globals.clipboardManager.setContent(selectedEntries);
      panel.output(panel.formatOutputMessage(Localization.lang("Copied"),selectedEntries.size()));
    }
 catch (    IOException e) {
      LOGGER.error("Error while copying selected entries to clipboard",e);
    }
  }
}
