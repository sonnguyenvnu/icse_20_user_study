private void setupTab(){
  for (  TabsCountStateModel model : tabsCountSet) {
    int index=model.getTabIndex();
    int count=model.getCount();
    TextView textView=ViewHelper.getTabTextView(tabs,index);
    if (index == 0) {
      textView.setText(String.format("%s(%s)",getString(R.string.repos),numberFormat.format(count)));
    }
 else     if (index == 1) {
      textView.setText(String.format("%s(%s)",getString(R.string.users),numberFormat.format(count)));
    }
 else     if (index == 2) {
      textView.setText(String.format("%s(%s)",getString(R.string.issues),numberFormat.format(count)));
    }
 else     if (index == 3) {
      textView.setText(String.format("%s(%s)",getString(R.string.code),numberFormat.format(count)));
    }
  }
}
