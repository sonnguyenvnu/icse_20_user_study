private void buildSettingsMenu(JMenu settingsMenu,ConfigSaver configSaver){
  settingsMenu.removeAll();
  ActionListener settingsChanged=new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      new Thread(){
        @Override public void run(){
          populateSettingsFromSettingsMenu();
          mainWindow.onSettingsChanged();
        }
      }
.start();
    }
  }
;
  flattenSwitchBlocks=new JCheckBoxMenuItem("Flatten Switch Blocks");
  flattenSwitchBlocks.setSelected(settings.getFlattenSwitchBlocks());
  flattenSwitchBlocks.addActionListener(settingsChanged);
  settingsMenu.add(flattenSwitchBlocks);
  forceExplicitImports=new JCheckBoxMenuItem("Force Explicit Imports");
  forceExplicitImports.setSelected(settings.getForceExplicitImports());
  forceExplicitImports.addActionListener(settingsChanged);
  settingsMenu.add(forceExplicitImports);
  forceExplicitTypes=new JCheckBoxMenuItem("Force Explicit Types");
  forceExplicitTypes.setSelected(settings.getForceExplicitTypeArguments());
  forceExplicitTypes.addActionListener(settingsChanged);
  settingsMenu.add(forceExplicitTypes);
  showSyntheticMembers=new JCheckBoxMenuItem("Show Synthetic Members");
  showSyntheticMembers.setSelected(settings.getShowSyntheticMembers());
  showSyntheticMembers.addActionListener(settingsChanged);
  settingsMenu.add(showSyntheticMembers);
  excludeNestedTypes=new JCheckBoxMenuItem("Exclude Nested Types");
  excludeNestedTypes.setSelected(settings.getExcludeNestedTypes());
  excludeNestedTypes.addActionListener(settingsChanged);
  settingsMenu.add(excludeNestedTypes);
  retainRedundantCasts=new JCheckBoxMenuItem("Retain Redundant Casts");
  retainRedundantCasts.setSelected(settings.getRetainRedundantCasts());
  retainRedundantCasts.addActionListener(settingsChanged);
  settingsMenu.add(retainRedundantCasts);
  unicodeReplacement=new JCheckBoxMenuItem("Enable Unicode Replacement");
  unicodeReplacement.setSelected(settings.isUnicodeOutputEnabled());
  unicodeReplacement.addActionListener(settingsChanged);
  settingsMenu.add(unicodeReplacement);
  debugLineNumbers=new JCheckBoxMenuItem("Show Debug Line Numbers");
  debugLineNumbers.setSelected(settings.getShowDebugLineNumbers());
  debugLineNumbers.addActionListener(settingsChanged);
  settingsMenu.add(debugLineNumbers);
  JMenu debugSettingsMenu=new JMenu("Debug Settings");
  showDebugInfo=new JCheckBoxMenuItem("Include Error Diagnostics");
  showDebugInfo.setSelected(settings.getIncludeErrorDiagnostics());
  showDebugInfo.addActionListener(settingsChanged);
  debugSettingsMenu.add(showDebugInfo);
  settingsMenu.add(debugSettingsMenu);
  settingsMenu.addSeparator();
  languageLookup.put(Languages.java().getName(),Languages.java());
  languageLookup.put(Languages.bytecode().getName(),Languages.bytecode());
  languageLookup.put(Languages.bytecodeAst().getName(),Languages.bytecodeAst());
  languagesGroup=new ButtonGroup();
  java=new JRadioButtonMenuItem(Languages.java().getName());
  java.getModel().setActionCommand(Languages.java().getName());
  java.setSelected(Languages.java().getName().equals(settings.getLanguage().getName()));
  languagesGroup.add(java);
  settingsMenu.add(java);
  bytecode=new JRadioButtonMenuItem(Languages.bytecode().getName());
  bytecode.getModel().setActionCommand(Languages.bytecode().getName());
  bytecode.setSelected(Languages.bytecode().getName().equals(settings.getLanguage().getName()));
  languagesGroup.add(bytecode);
  settingsMenu.add(bytecode);
  bytecodeAST=new JRadioButtonMenuItem(Languages.bytecodeAst().getName());
  bytecodeAST.getModel().setActionCommand(Languages.bytecodeAst().getName());
  bytecodeAST.setSelected(Languages.bytecodeAst().getName().equals(settings.getLanguage().getName()));
  languagesGroup.add(bytecodeAST);
  settingsMenu.add(bytecodeAST);
  JMenu debugLanguagesMenu=new JMenu("Debug Languages");
  for (  final Language language : Languages.debug()) {
    final JRadioButtonMenuItem m=new JRadioButtonMenuItem(language.getName());
    m.getModel().setActionCommand(language.getName());
    m.setSelected(language.getName().equals(settings.getLanguage().getName()));
    languagesGroup.add(m);
    debugLanguagesMenu.add(m);
    languageLookup.put(language.getName(),language);
  }
  for (  AbstractButton button : Collections.list(languagesGroup.getElements())) {
    button.addActionListener(settingsChanged);
  }
  settingsMenu.add(debugLanguagesMenu);
  bytecodeLineNumbers=new JCheckBoxMenuItem("Show Line Numbers In Bytecode");
  bytecodeLineNumbers.setSelected(settings.getIncludeLineNumbersInBytecode());
  bytecodeLineNumbers.addActionListener(settingsChanged);
  settingsMenu.add(bytecodeLineNumbers);
}
