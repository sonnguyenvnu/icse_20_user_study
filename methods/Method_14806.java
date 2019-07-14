@Override public void initEvent(){
  tvBaseTitle.setOnTouchListener(this);
  findViewById(R.id.tvLoginForget).setOnClickListener(this);
  findViewById(R.id.tvLoginLogin).setOnClickListener(this);
  new TextClearSuit().addClearListener(etLoginPhone,findViewById(R.id.ivLoginPhoneClear));
  new TextClearSuit().addClearListener(etLoginPassword,findViewById(R.id.ivLoginPasswordClear));
}
