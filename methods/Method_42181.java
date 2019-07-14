private void setupViews(@NonNull View view){
  ThemeHelper th=ThemeHelper.getInstanceLoaded(getContext());
  view.setBackgroundColor(th.getBackgroundColor());
  headerLayout.setBackgroundColor(th.getPrimaryColor());
  progressBar.setFinishedStrokeColor(th.getAccentColor());
  progressBar.setTextColor(th.getTextColor());
  txtTitle.setText(title);
  if (!showCancel)   btnDoneCancel.setVisibility(View.GONE);
}
