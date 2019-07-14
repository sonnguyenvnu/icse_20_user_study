public void updateSelectedItemCount(int selectedItemCount){
  if (actionMode != null) {
    actionMode.setTitle(context.getResources().getQuantityString(R.plurals.mp_selected_folders_formatter,selectedItemCount,selectedItemCount));
  }
}
