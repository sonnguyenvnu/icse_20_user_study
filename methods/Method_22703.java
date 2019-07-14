protected void replaceCode(SketchCode newCode){
  for (int i=0; i < codeCount; i++) {
    if (code[i].getFileName().equals(newCode.getFileName())) {
      code[i]=newCode;
      break;
    }
  }
}
