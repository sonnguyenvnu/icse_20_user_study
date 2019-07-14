private void setupDate(@NonNull Date createdDate,@NonNull Date updated){
  date.setText(ParseDateFormat.getTimeAgo(createdDate));
}
