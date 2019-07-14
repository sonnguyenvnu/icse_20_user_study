@OnClick(value={R.id.clear}) void onClear(View view){
  if (view.getId() == R.id.clear) {
    AppHelper.hideKeyboard(searchEditText);
    searchEditText.setText("");
  }
}
