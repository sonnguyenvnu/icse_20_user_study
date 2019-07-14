private void updateCount(@NonNull TabsCountStateModel model){
  TextView tv=ViewHelper.getTabTextView(tabs,model.getTabIndex());
  tv.setText(SpannableBuilder.builder().append(getString(R.string.commits)).append("   ").append("(").bold(String.valueOf(model.getCount())).append(")"));
}
