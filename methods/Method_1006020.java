private void init(){
  this.setTitle(Localization.lang("BibTeX key patterns"));
  this.getDialogPane().setContent(bibtexKeyPatternPanel.getPanel());
  this.getDialogPane().getButtonTypes().addAll(ButtonType.APPLY,ButtonType.CANCEL);
  this.setResultConverter(button -> {
    if (button == ButtonType.APPLY) {
      metaData.setCiteKeyPattern(bibtexKeyPatternPanel.getKeyPatternAsDatabaseBibtexKeyPattern());
      panel.markNonUndoableBaseChanged();
    }
    return null;
  }
);
}
