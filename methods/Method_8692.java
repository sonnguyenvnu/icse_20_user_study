public void onTextChange(){
  textOffset=(length() > 0 ? getPaint().measureText(getText(),0,length()) : 0);
  spaceSize=getPaint().measureText(" ");
  numberSize=getPaint().measureText("1");
  invalidate();
}
