protected void initBaseCode(){
  SketchCode[] code=sketch.getCode();
  baseCode=new String[code.length];
  for (int i=0; i < code.length; i++) {
    baseCode[i]=code[i].getSavedProgram();
  }
}
