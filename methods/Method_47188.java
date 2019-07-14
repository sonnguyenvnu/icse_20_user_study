public static MaterialDialog showBasicDialog(ThemedActivity themedActivity,@StringRes int content,@StringRes int title,@StringRes int postiveText,@StringRes int negativeText){
  int accentColor=themedActivity.getAccent();
  MaterialDialog.Builder a=new MaterialDialog.Builder(themedActivity).content(content).widgetColor(accentColor).theme(themedActivity.getAppTheme().getMaterialDialogTheme()).title(title).positiveText(postiveText).positiveColor(accentColor).negativeText(negativeText).negativeColor(accentColor);
  return a.build();
}
