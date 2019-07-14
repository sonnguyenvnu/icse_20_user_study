@Override public void onViewBound(@NonNull View view){
  super.onViewBound(view);
  textView.setText(getArgs().getString(KEY_TEXT));
}
