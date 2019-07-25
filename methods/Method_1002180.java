private void init(){
  mMessage=(TextView)findViewById(R.id.message);
  mAllowButton=(Button)findViewById(R.id.allow_button);
  mDenyButton=(Button)findViewById(R.id.deny_button);
  mRemember=(CheckBox)findViewById(R.id.remember);
  mAllowButton.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      handleButtonClick(true);
    }
  }
);
  mDenyButton.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      handleButtonClick(false);
    }
  }
);
}
