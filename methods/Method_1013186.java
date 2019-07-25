public String location(){
  if (macroLine < 0) {
    return "line " + line + ", column " + col;
  }
 else {
    return "line " + line + ", column " + col + " of macro called at line " + macroLine + ", column " + macroCol;
  }
}
