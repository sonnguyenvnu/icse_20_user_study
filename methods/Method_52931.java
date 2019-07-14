private void setBeginLocation(Node n,int index){
  if (n != null) {
    int line=sourceCodePositioner.lineNumberFromOffset(index);
    int column=sourceCodePositioner.columnFromOffset(line,index);
    n.setUserData(XmlNode.BEGIN_LINE,line,null);
    n.setUserData(XmlNode.BEGIN_COLUMN,column,null);
  }
}
