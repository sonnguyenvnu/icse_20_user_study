public void setColors(String key,String switchKey,String switchKeyChecked,String switchThumb,String switchThumbChecked){
  textView.setTextColor(Theme.getColor(key));
  checkBox.setColors(switchKey,switchKeyChecked,switchThumb,switchThumbChecked);
  textView.setTag(key);
}
