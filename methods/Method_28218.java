private void updateCount(@NonNull TabsCountStateModel model){
  TextView tv=ViewHelper.getTabTextView(tabs,model.getTabIndex());
  tv.setText(SpannableBuilder.builder().append(model.getTabIndex() == 0 ? getString(R.string.opened) : getString(R.string.closed)).append("   ").append("(").bold(String.valueOf(model.getCount())).append(")"));
}
