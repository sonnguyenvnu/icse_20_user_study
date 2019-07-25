@OnTextChanged(value=R.id.searchEditText,callback=OnTextChanged.Callback.AFTER_TEXT_CHANGED) void search(Editable editable){
  if (searchAndPickContactFragment == null) {
    return;
  }
  String key=editable.toString();
  if (!TextUtils.isEmpty(key)) {
    searchAndPickContactFragment.search(key);
  }
 else {
    searchAndPickContactFragment.rest();
  }
}
