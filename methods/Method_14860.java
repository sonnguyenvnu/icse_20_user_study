@Override public void initEvent(){
  findViewById(R.id.btnPasswordGetVerify).setOnClickListener(this);
  findViewById(R.id.btnPasswordNext).setOnClickListener(this);
  new TextClearSuit().addClearListener(etPasswordPhone,findViewById(R.id.ivPasswordPhoneClear));
  new TextClearSuit().addClearListener(etPasswordVerify,findViewById(R.id.ivPasswordVerifyClear));
  new TextClearSuit().addClearListener(etPasswordPassword0,findViewById(R.id.ivPasswordPassword0Clear));
  new TextClearSuit().addClearListener(etPasswordPassword1,findViewById(R.id.ivPasswordPassword1Clear));
}
