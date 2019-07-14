@SuppressWarnings("unchecked") protected void onPreferences(){
  preferencesController.show(() -> {
    checkPreferencesChange(currentPage);
    mainView.preferencesChanged(getPreferences());
  }
);
}
