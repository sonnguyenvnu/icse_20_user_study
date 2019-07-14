/** 
 * Run the launch4j build.xml file through ant to create the exe. Most of this code was lifted from Android mode.
 */
protected boolean buildWindowsLauncher(File buildFile,String target){
  Project p=new Project();
  String path=buildFile.getAbsolutePath().replace('\\','/');
  p.setUserProperty("ant.file",path);
  p.setUserProperty("build.compiler","extJavac");
  DefaultLogger errorLogger=new DefaultLogger();
  ByteArrayOutputStream errb=new ByteArrayOutputStream();
  PrintStream errp=new PrintStream(errb);
  errorLogger.setErrorPrintStream(errp);
  ByteArrayOutputStream outb=new ByteArrayOutputStream();
  PrintStream outp=new PrintStream(outb);
  errorLogger.setOutputPrintStream(outp);
  errorLogger.setMessageOutputLevel(Project.MSG_INFO);
  p.addBuildListener(errorLogger);
  try {
    p.fireBuildStarted();
    p.init();
    final ProjectHelper helper=ProjectHelper.getProjectHelper();
    p.addReference("ant.projectHelper",helper);
    helper.parse(p,buildFile);
    p.executeTarget(target);
    return true;
  }
 catch (  final BuildException e) {
    p.fireBuildFinished(e);
    String out=new String(outb.toByteArray());
    String err=new String(errb.toByteArray());
    System.out.println(out);
    System.err.println(err);
  }
  return false;
}
