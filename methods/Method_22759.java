public AttributedCharacterIterator getCommittedText(int beginIndex,int endIndex){
  int length=endIndex - beginIndex;
  String textAreaString=textArea.getText(beginIndex,length);
  return new AttributedString(textAreaString).getIterator();
}
