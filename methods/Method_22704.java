protected void insertCode(SketchCode newCode){
  ensureExistence();
  code=(SketchCode[])PApplet.append(code,newCode);
  codeCount++;
}
