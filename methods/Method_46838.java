@Override public void beforeTextChanged(CharSequence charSequence,int i,int i2,int i3){
  if (searchEditText != null && charSequence.hashCode() == searchEditText.getText().hashCode()) {
    if (searchTextTask != null)     searchTextTask.cancel(true);
    cleanSpans();
  }
}
