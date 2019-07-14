@Override public AttributedCharacterIterator getCommittedText(int beginIndex,int endIndex,AttributedCharacterIterator.Attribute[] attributes){
  int length=endIndex - beginIndex;
  String textAreaString=textArea.getText(beginIndex,length);
  return new AttributedString(textAreaString).getIterator();
}
