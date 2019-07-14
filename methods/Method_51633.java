private int selectedLanguageVersionIndex(){
  for (int i=0; i < languageVersionMenuItems.length; i++) {
    if (languageVersionMenuItems[i].isSelected()) {
      return i;
    }
  }
  throw new RuntimeException("Initial default language version not specified");
}
