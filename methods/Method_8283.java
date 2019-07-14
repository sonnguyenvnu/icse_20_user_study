@Override protected void onDialogDismiss(Dialog dialog){
  if (closeChatDialog != null && dialog == closeChatDialog) {
    MessagesController.getInstance(currentAccount).deleteDialog(dialog_id,0);
    if (parentLayout != null && !parentLayout.fragmentsStack.isEmpty() && parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 1) != this) {
      BaseFragment fragment=parentLayout.fragmentsStack.get(parentLayout.fragmentsStack.size() - 1);
      removeSelfFromStack();
      fragment.finishFragment();
    }
 else {
      finishFragment();
    }
  }
}
