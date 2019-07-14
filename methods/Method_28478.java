@SuppressWarnings("unchecked") @Override public void onItemClick(int position,View v,O item){
  if (onSimpleItemSelection != null) {
    onSimpleItemSelection.onItemSelected(item);
  }
  dismiss();
}
