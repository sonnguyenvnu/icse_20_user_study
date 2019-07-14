public void selectCountry(String name){
  int index=countriesArray.indexOf(name);
  if (index != -1) {
    ignoreOnTextChange=true;
    String code=countriesMap.get(name);
    codeField.setText(code);
    countryButton.setText(name);
    String hint=phoneFormatMap.get(code);
    phoneField.setHintText(hint != null ? hint.replace('X','–') : null);
    countryState=0;
    ignoreOnTextChange=false;
  }
}
