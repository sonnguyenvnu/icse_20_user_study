@Override public final void visitSource(final String file,final String debug){
  if (file != null) {
    sourceFileIndex=symbolTable.addConstantUtf8(file);
  }
  if (debug != null) {
    debugExtension=new ByteVector().encodeUtf8(debug,0,Integer.MAX_VALUE);
  }
}
