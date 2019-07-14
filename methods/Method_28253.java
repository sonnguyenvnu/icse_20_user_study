@SuppressWarnings("ConstantConditions") @Override public void onClearEditText(){
  if (commentEditorFragment != null && commentEditorFragment.commentText != null)   commentEditorFragment.commentText.setText("");
}
