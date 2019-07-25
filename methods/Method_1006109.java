@Subscribe public void listen(FieldChangedEvent fieldChangedEvent){
  if (!Globals.prefs.isKeywordSyncEnabled()) {
    return;
  }
  final BibEntry entry=fieldChangedEvent.getBibEntry();
  final String fieldName=fieldChangedEvent.getFieldName();
  SwingUtilities.invokeLater(() -> {
    if (FieldName.KEYWORDS.equals(fieldName)) {
      SpecialFieldsUtils.syncSpecialFieldsFromKeywords(entry,Globals.prefs.getKeywordDelimiter());
    }
 else     if (SpecialField.isSpecialField(fieldName)) {
      SpecialFieldsUtils.syncKeywordsFromSpecialFields(entry,Globals.prefs.getKeywordDelimiter());
    }
    SwingUtilities.invokeLater(() -> JabRefGUI.getMainFrame().getCurrentBasePanel().updateEntryEditorIfShowing());
  }
);
}
