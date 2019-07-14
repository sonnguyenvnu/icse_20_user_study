public void setValue(String name,String nameEnglish){
  textView.setText(name);
  textView2.setText(nameEnglish);
  checkImage.setVisibility(INVISIBLE);
  currentLocale=null;
  needDivider=false;
}
