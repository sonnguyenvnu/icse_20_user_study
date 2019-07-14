@OnClick(R.id.filter_sheet_apply_btn) public void onApply(){
  if (listener != null) {
    listener.onTypeSelected((String)typeSelectionSpinner.getSelectedItem());
    listener.onSortOptionSelected((String)sortSelectionSpinner.getSelectedItem());
    listener.onSortDirectionSelected((String)sortDirectionSpinner.getSelectedItem());
    listener.onFilterApply();
    dismiss();
  }
}
