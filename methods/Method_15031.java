@Override public void onClick(View v){
  if (data == null) {
    return;
  }
  toActivity(UserActivity.createIntent(context,data.getId()));
}
