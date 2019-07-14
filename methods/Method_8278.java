private void updateSearchButtons(int mask,int num,int count){
  if (searchUpButton != null) {
    searchUpButton.setEnabled((mask & 1) != 0);
    searchDownButton.setEnabled((mask & 2) != 0);
    searchUpButton.setAlpha(searchUpButton.isEnabled() ? 1.0f : 0.5f);
    searchDownButton.setAlpha(searchDownButton.isEnabled() ? 1.0f : 0.5f);
    if (count < 0) {
      searchCountText.setText("");
    }
 else     if (count == 0) {
      searchCountText.setText(LocaleController.getString("NoResult",R.string.NoResult));
    }
 else {
      searchCountText.setText(LocaleController.formatString("Of",R.string.Of,num + 1,count));
    }
  }
}
