public void copyToClipboard(){
  AndroidUtilities.addToClipboard(currentMessage.subSequence(currentStart,currentEnd).toString());
}
