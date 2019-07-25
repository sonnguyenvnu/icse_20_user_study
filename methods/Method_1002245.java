private void inflate(){
  inflate(getContext(),R.layout.bootstrap_check_button,this);
  mWrapper=findViewById(R.id.bootstrap_checkbox_wrapper);
  mContent=findViewById(R.id.bootstrap_checkbox_content);
  mChkbox=findViewById(R.id.bootstrap_checkbox);
}
