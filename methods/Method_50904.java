private JPanel makeSettingsPanel(JButton browseButton,JButton goButton,JButton cxButton){
  JPanel settingsPanel=new JPanel();
  GridBagHelper helper=new GridBagHelper(settingsPanel,new double[]{0.2,0.7,0.1,0.1});
  helper.addLabel("Root source directory:");
  helper.add(rootDirectoryField);
  helper.add(browseButton,2);
  helper.nextRow();
  helper.addLabel("Report duplicate chunks larger than:");
  minimumLengthField.setColumns(4);
  helper.add(minimumLengthField);
  helper.addLabel("Language:");
  for (int i=0; i < LANGUAGE_SETS.length; i++) {
    languageBox.addItem(String.valueOf(LANGUAGE_SETS[i][0]));
  }
  languageBox.addActionListener(new ActionListener(){
    @Override public void actionPerformed(    ActionEvent e){
      adjustLanguageControlsFor(languageConfigFor((String)languageBox.getSelectedItem()));
    }
  }
);
  helper.add(languageBox);
  helper.nextRow();
  helper.addLabel("Also scan subdirectories?");
  helper.add(recurseCheckbox);
  helper.add(extensionLabel);
  helper.add(extensionField);
  helper.nextRow();
  helper.addLabel("Ignore literals?");
  helper.add(ignoreLiteralsCheckbox);
  helper.addLabel("");
  helper.addLabel("");
  helper.nextRow();
  helper.nextRow();
  helper.addLabel("Ignore identifiers?");
  helper.add(ignoreIdentifiersCheckbox);
  helper.addLabel("");
  helper.addLabel("");
  helper.nextRow();
  helper.nextRow();
  helper.addLabel("Ignore annotations?");
  helper.add(ignoreAnnotationsCheckbox);
  helper.addLabel("");
  helper.addLabel("");
  helper.nextRow();
  helper.nextRow();
  helper.addLabel("Ignore usings?");
  helper.add(ignoreUsingsCheckbox);
  helper.add(goButton);
  helper.add(cxButton);
  helper.nextRow();
  helper.addLabel("File encoding (defaults based upon locale):");
  encodingField.setColumns(1);
  helper.add(encodingField);
  helper.addLabel("");
  helper.addLabel("");
  helper.nextRow();
  return settingsPanel;
}
