/** 
 * Change internal settings based on what was chosen in the prefs, then send a message to the editor saying that it's time to do the same.
 */
protected void applyFrame(){
  Preferences.setBoolean("editor.smooth",editorAntialiasBox.isSelected());
  Preferences.setBoolean("export.delete_target_folder",deletePreviousBox.isSelected());
  String oldPath=Preferences.getSketchbookPath();
  String newPath=sketchbookLocationField.getText();
  if (!newPath.equals(oldPath)) {
    base.setSketchbookFolder(new File(newPath));
  }
  Preferences.setBoolean("update.check",checkUpdatesBox.isSelected());
  Map<String,String> languages=Language.getLanguages();
  String language="";
  for (  Map.Entry<String,String> lang : languages.entrySet()) {
    if (lang.getValue().equals(String.valueOf(languageSelectionBox.getSelectedItem()))) {
      language=lang.getKey().trim().toLowerCase();
      break;
    }
  }
  if (!language.equals(Language.getLanguage()) && !language.equals("")) {
    Language.saveLanguage(language);
  }
  if (displaySelectionBox.isEnabled()) {
    int oldDisplayNum=Preferences.getInteger("run.display");
    int displayNum=-1;
    for (int d=0; d < displaySelectionBox.getItemCount(); d++) {
      if (displaySelectionBox.getSelectedIndex() == d) {
        displayNum=d + 1;
      }
    }
    if ((displayNum != -1) && (displayNum != oldDisplayNum)) {
      Preferences.setInteger("run.display",displayNum);
      for (      Editor editor : base.getEditors()) {
        editor.setSketchLocation(null);
      }
    }
  }
  Preferences.setBoolean("run.options.memory",memoryOverrideBox.isSelected());
  int memoryMin=Preferences.getInteger("run.options.memory.initial");
  int memoryMax=Preferences.getInteger("run.options.memory.maximum");
  try {
    memoryMax=Integer.parseInt(memoryField.getText().trim());
    if (memoryMax < memoryMin)     memoryMax=memoryMin;
    Preferences.setInteger("run.options.memory.maximum",memoryMax);
  }
 catch (  NumberFormatException e) {
    System.err.println("Ignoring bad memory setting");
  }
  if (fontSelectionBox.isEnabled()) {
    String fontFamily=(String)fontSelectionBox.getSelectedItem();
    Preferences.set("editor.font.family",fontFamily);
  }
  try {
    Object selection=fontSizeField.getSelectedItem();
    if (selection instanceof String) {
      selection=Integer.parseInt((String)selection);
    }
    Preferences.set("editor.font.size",String.valueOf(selection));
  }
 catch (  NumberFormatException e) {
    Messages.log("Ignoring invalid font size " + fontSizeField);
    fontSizeField.setSelectedItem(Preferences.getInteger("editor.font.size"));
  }
  Preferences.setBoolean("editor.zoom.auto",zoomAutoBox.isSelected());
  Preferences.set("editor.zoom",String.valueOf(zoomSelectionBox.getSelectedItem()));
  try {
    Object selection=consoleFontSizeField.getSelectedItem();
    if (selection instanceof String) {
      selection=Integer.parseInt((String)selection);
    }
    Preferences.set("console.font.size",String.valueOf(selection));
  }
 catch (  NumberFormatException e) {
    Messages.log("Ignoring invalid font size " + consoleFontSizeField);
    consoleFontSizeField.setSelectedItem(Preferences.getInteger("console.font.size"));
  }
  Preferences.setColor("run.present.bgcolor",presentColor.getBackground());
  Preferences.setBoolean("editor.input_method_support",inputMethodBox.isSelected());
  if (autoAssociateBox != null) {
    Preferences.setBoolean("platform.auto_file_type_associations",autoAssociateBox.isSelected());
  }
  Preferences.setBoolean("pdex.errorCheckEnabled",errorCheckerBox.isSelected());
  Preferences.setBoolean("pdex.warningsEnabled",warningsCheckerBox.isSelected());
  Preferences.setBoolean("pdex.completion",codeCompletionBox.isSelected());
  Preferences.setBoolean("pdex.suggest.imports",importSuggestionsBox.isSelected());
  for (  Editor editor : base.getEditors()) {
    editor.applyPreferences();
  }
}
