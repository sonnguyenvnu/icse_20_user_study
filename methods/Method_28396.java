@OnItemSelected(R.id.searchOptions) void onOptionSelected(){
  if (onSpinnerTouched) {
    onSearch();
  }
}
