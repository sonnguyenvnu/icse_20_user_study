public void checkText(){
  String newText=LocaleController.getString("ScamMessage",R.string.ScamMessage);
  if (!newText.equals(text)) {
    text=newText;
    textWidth=(int)Math.ceil(textPaint.measureText(text));
  }
}
