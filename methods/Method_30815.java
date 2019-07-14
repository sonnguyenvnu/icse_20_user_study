@Override protected void setTimeText(String timeText){
  String text;
  if (!TextUtils.isEmpty(mLocation)) {
    text=getContext().getString(R.string.profile_join_time_location_format,timeText,mLocation);
  }
 else {
    text=getContext().getString(R.string.profile_join_time_format,timeText);
  }
  setText(text);
}
