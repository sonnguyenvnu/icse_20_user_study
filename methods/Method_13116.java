public void preferencesPanelChanged(PreferencesPanel source){
  SwingUtil.invokeLater(() -> {
    boolean valid=source.arePreferencesValid();
    valids.put(source,Boolean.valueOf(valid));
    if (valid) {
      for (      PreferencesPanel pp : panels) {
        if (valids.get(pp) == Boolean.FALSE) {
          preferencesOkButton.setEnabled(false);
          return;
        }
      }
      preferencesOkButton.setEnabled(true);
    }
 else {
      preferencesOkButton.setEnabled(false);
    }
  }
);
}
