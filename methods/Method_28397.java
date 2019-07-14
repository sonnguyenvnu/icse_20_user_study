@OnTextChanged(value=R.id.searchEditText,callback=OnTextChanged.Callback.AFTER_TEXT_CHANGED) void onTextChange(Editable s){
  String text=s.toString();
  if (text.length() == 0) {
    AnimHelper.animateVisibility(clear,false);
  }
 else {
    AnimHelper.animateVisibility(clear,true);
  }
}
