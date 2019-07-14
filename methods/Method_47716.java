@Override public void showDeleteConfirmationScreen(@NonNull OnConfirmedCallback callback){
  activity.showDialog(confirmDeleteDialogFactory.create(callback));
}
