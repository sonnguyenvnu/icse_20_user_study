public static Intent makeConfirmPasswordIntent(ConfirmPasswordListener listener){
  return makeConfirmPasswordIntent(getActiveAccount(),listener);
}
