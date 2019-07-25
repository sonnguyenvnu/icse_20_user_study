public void setup(){
  Button btnSend=(Button)findViewById(R.id.btnSend);
  btnSend.setOnClickListener(new OnClickListener(){
    public void onClick(    View v){
      bt.send("Text",true);
    }
  }
);
  bt.autoConnect("IOIO");
}
