@Override public void onViewBound(@NonNull View view){
  super.onViewBound(view);
  tvTitle.setText(getArgs().getCharSequence(KEY_TITLE));
  tvDescription.setText(getArgs().getCharSequence(KEY_DESCRIPTION));
  tvDescription.setMovementMethod(LinkMovementMethod.getInstance());
}
