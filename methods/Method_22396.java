public void refreshContribs(ContributionType ct){
  if (ct == ContributionType.LIBRARY) {
    for (    Mode m : getModeList()) {
      m.rebuildImportMenu();
    }
  }
 else   if (ct == ContributionType.MODE) {
    rebuildContribModes();
    for (    Editor editor : editors) {
      editor.rebuildModePopup();
    }
  }
 else   if (ct == ContributionType.TOOL) {
    rebuildToolList();
    for (    Editor editor : editors) {
      populateToolsMenu(editor.getToolMenu());
    }
  }
 else   if (ct == ContributionType.EXAMPLES) {
    rebuildContribExamples();
    for (    Mode m : getModeList()) {
      m.rebuildExamplesFrame();
    }
  }
}
