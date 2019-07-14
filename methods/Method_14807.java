@Override public void onDragBottom(boolean rightToLeft){
  if (rightToLeft) {
    toPassword(PasswordActivity.TYPE_REGISTER,REQUEST_TO_REGISTER);
    return;
  }
  finish();
}
