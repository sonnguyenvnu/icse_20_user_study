@Override public void initEvent(){
  findViewById(R.id.llPasswordSettingPayNumberContainer).setOnClickListener(this);
  for (int i=0; i < tvPasswordSettingNumbers.length; i++) {
    tvPasswordSettingNumbers[i].setOnClickListener(new OnClickListener(){
      @Override public void onClick(      View v){
        EditTextManager.showKeyboard(context,etPasswordSetting,true);
      }
    }
);
  }
  etPasswordSetting.addTextChangedListener(new TextWatcher(){
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
      inputedString=StringUtil.getNoBlankString(s);
      inputedLength=inputedString.length();
      for (int i=0; i < inputedLength; i++) {
        tvPasswordSettingNumbers[i].setText(inputedString.substring(i,i + 1));
      }
      for (int j=inputedString.length(); j < PASSWORD_LENGTH; j++) {
        tvPasswordSettingNumbers[j].setText("");
      }
      if (inputedLength >= PASSWORD_LENGTH) {
        next();
      }
    }
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void afterTextChanged(    Editable s){
    }
  }
);
}
