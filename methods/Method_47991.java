public void onToggleShowArchived(){
  showArchived=!showArchived;
  preferences.setShowArchived(showArchived);
  updateAdapterFilter();
}
