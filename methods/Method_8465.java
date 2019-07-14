public boolean isRtlText(){
  try {
    return messageEditText.getLayout().getParagraphDirection(0) == Layout.DIR_RIGHT_TO_LEFT;
  }
 catch (  Throwable ignore) {
  }
  return false;
}
