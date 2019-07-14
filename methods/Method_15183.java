@Override public void initEvent(){
  super.initEvent();
  tvEditTextInfoPlace.setOnClickListener(this);
  etEditTextInfo.addTextChangedListener(new TextWatcher(){
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
      inputedString=StringUtil.getTrimedString(s);
      if (StringUtil.isNotEmpty(inputedString,true) == false) {
        ivEditTextInfoClear.setVisibility(View.GONE);
      }
 else {
        ivEditTextInfoClear.setVisibility(View.VISIBLE);
      }
    }
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void afterTextChanged(    Editable s){
    }
  }
);
  ivEditTextInfoClear.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      etEditTextInfo.setText("");
    }
  }
);
  etEditTextInfo.setText(StringUtil.getTrimedString(getIntent().getStringExtra(INTENT_VALUE)));
  etEditTextInfo.setSelection(StringUtil.getLength(etEditTextInfo,true));
}
