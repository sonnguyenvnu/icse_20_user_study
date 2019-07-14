private void processSearch(EditText editText){
  if (editText.getText().toString().length() == 0) {
    return;
  }
  searchResult.clear();
  searchResultKeys.clear();
  imageSearchEndReached=true;
  searchImages(type == 1,editText.getText().toString(),"",true);
  lastSearchString=editText.getText().toString();
  if (lastSearchString.length() == 0) {
    lastSearchString=null;
    emptyView.setText("");
  }
 else {
    emptyView.setText(LocaleController.getString("NoResult",R.string.NoResult));
  }
  updateSearchInterface();
}
