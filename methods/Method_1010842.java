public void reset(MPSConfigurationBean data){
  if (data.isUseTransientOutputFolder()) {
    myUseTransientOutputFolder.doClick();
  }
 else   if (data.isUseModuleSourceFolder()) {
    myUseModuleSourceFolderRadioButton.doClick();
  }
 else {
    myUseCustomFolderRadioButton.doClick();
  }
  myFieldPanel.setText(data.getGeneratorOutputPath());
}
