void initFontList(){
  if (monoFontFamilies == null) {
    monoFontFamilies=Toolkit.getMonoFontFamilies();
    EventQueue.invokeLater(new Runnable(){
      @Override public void run(){
        fontSelectionBox.setModel(new DefaultComboBoxModel<>(monoFontFamilies));
        String family=Preferences.get("editor.font.family");
        fontSelectionBox.setSelectedItem("Monospaced");
        fontSelectionBox.setSelectedItem(family);
        fontSelectionBox.setEnabled(true);
      }
    }
);
  }
}
