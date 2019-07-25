public boolean contains(String file,int line){
  return Objects.equals(myFileName,file) && myStartLine <= line && line <= myEndLine;
}
