public void setup(){
  Button btnSend=(Button)findViewById(R.id.btnSend);
  btnSend.setOnClickListener(new OnClickListener(){
    public void onClick(    View v){
      if (etMessage.getText().length() != 0) {
        bt.send(etMessage.getText().toString(),true);
        etMessage.setText("");
      }
    }
  }
);
}
