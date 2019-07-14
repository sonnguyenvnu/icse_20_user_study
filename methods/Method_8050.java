public void setLanguage(LocaleController.LocaleInfo language,String desc,boolean divider){
  textView.setText(desc != null ? desc : language.name);
  textView2.setText(language.nameEnglish);
  currentLocale=language;
  needDivider=divider;
}
