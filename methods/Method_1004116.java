@Override protected void content(HTMLElement body) throws IOException {
  if (getNode().isNoMatch()) {
    body.p().text("A different version of class was executed at runtime.");
  }
  if (getNode().getLineCounter().getTotalCount() == 0) {
    body.p().text("Class files must be compiled with debug information to show line coverage.");
  }
  final String sourceFileName=getNode().getSourceFileName();
  if (sourceFileName == null) {
    body.p().text("Class files must be compiled with debug information to link with source files.");
  }
 else   if (sourcePage == null) {
    final String sourcePath;
    if (getNode().getPackageName().length() != 0) {
      sourcePath=getNode().getPackageName() + "/" + sourceFileName;
    }
 else {
      sourcePath=sourceFileName;
    }
    body.p().text("Source file \"" + sourcePath + "\" was not found during generation of report.");
  }
  super.content(body);
}
