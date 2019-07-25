public Asm parse() throws IOException {
  reader=new LineNumberReader(new FileReader(fileName));
  cv=new ClassWriter(computeFrames ? ClassWriter.COMPUTE_FRAMES : 0);
  try {
    parseClass();
  }
 catch (  EOF eof) {
    if (!eofOK) {
      System.err.println("Premature end of file: " + fileName);
      System.exit(1);
    }
  }
catch (  AsmException e) {
    System.err.println(e.getMessage());
    System.exit(1);
  }
catch (  RuntimeException e) {
    System.out.println("File: " + fileName);
    if (methodName != null) {
      System.out.println("Method: " + methodName);
    }
    System.out.println("");
    System.out.println("Line " + line);
    System.out.println("Last pattern match: " + lastPattern);
    throw e;
  }
  return this;
}
