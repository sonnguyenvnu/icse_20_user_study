public void setType(int value){
  currentType=value;
  String help;
  if (currentType == 0) {
    help=LocaleController.getString("NoChatsHelp",R.string.NoChatsHelp);
    if (AndroidUtilities.isTablet() && !AndroidUtilities.isSmallTablet()) {
      help=help.replace('\n',' ');
    }
  }
 else {
    help=LocaleController.getString("NoChatsContactsHelp",R.string.NoChatsContactsHelp);
    if (AndroidUtilities.isTablet() && !AndroidUtilities.isSmallTablet()) {
      help=help.replace('\n',' ');
    }
  }
  emptyTextView2.setText(help);
}
