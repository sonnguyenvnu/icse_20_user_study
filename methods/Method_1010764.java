private void update(){
  myPreferencesPanel.removeAll();
  syncSettings(registrySettings,currentSettings);
  myPreferencesPanel.setLayout(new BoxLayout(myPreferencesPanel,BoxLayout.Y_AXIS));
  ArrayList<String> names=new ArrayList<>(currentSettings.getLanguagesNames());
  Collections.sort(names);
  for (  String langName : names) {
    if (langName != null) {
      JPanel languagePanel=new JPanel();
      languagePanel.setLayout(new BoxLayout(languagePanel,BoxLayout.Y_AXIS));
      languagePanel.add(Box.createHorizontalGlue());
      languagePanel.setBorder(IdeBorderFactory.createTitledBorder(langName,false));
      languagePanel.setAlignmentX(Component.CENTER_ALIGNMENT);
      for (      ConceptEditorHint hint : currentSettings.getHints(langName)) {
        addHintCheckbox(languagePanel,langName,hint,currentSettings.get(langName,hint));
      }
      myPreferencesPanel.add(languagePanel);
    }
  }
}
