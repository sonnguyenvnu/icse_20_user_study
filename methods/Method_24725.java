protected void initEditorCode(List<List<Handle>> handles,boolean withSpaces){
  SketchCode[] sketchCode=sketch.getCode();
  for (int tab=0; tab < baseCode.length; tab++) {
    int charInc=0;
    String code=baseCode[tab];
    for (    Handle n : handles.get(tab)) {
      int s=n.startChar + charInc;
      int e=n.endChar + charInc;
      String newStr=n.strNewValue;
      if (withSpaces) {
        newStr="  " + newStr + "  ";
      }
      code=replaceString(code,s,e,newStr);
      n.newStartChar=n.startChar + charInc;
      charInc+=n.strNewValue.length() - n.strValue.length();
      if (withSpaces) {
        charInc+=4;
      }
      n.newEndChar=n.endChar + charInc;
    }
    sketchCode[tab].setProgram(code);
    sketchCode[tab].setDocument(null);
  }
  setCode(sketch.getCurrentCode());
}
