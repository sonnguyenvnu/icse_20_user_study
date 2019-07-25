/** 
 * parses compilation result for errors and prints them out
 */
public ClassesErrorsTracker handle(org.eclipse.jdt.internal.compiler.CompilationResult result){
  if (result.getErrors().length > 0) {
    mySender.error(COMPILATION_PROBLEMS);
    mySender.info(String.format(MODULES_CLASSPATH_STR,myModulesContainer.getModules(),myClassPath));
  }
  for (  final CategorizedProblem problem : result.getErrors()) {
    String fileName=new String(problem.getOriginatingFileName());
    final String fqName=NameUtil.namespaceFromPath(fileName.substring(0,fileName.length() - MPSExtentions.DOT_JAVAFILE.length()));
    myErrorTracker.add(fqName);
    SModule containingModule=myModulesContainer.getModuleContainingClass(fqName);
    assert containingModule != null;
    JavaFile javaFile=myModulesContainer.getSources(containingModule).getJavaFile(fqName);
    String messageString=String.valueOf(problem.getOriginatingFileName()) + " : " + problem.getMessage();
    Object hintObject=new FileWithPosition(javaFile.getFile(),problem.getSourceStart());
    String errMsg=String.format(ERROR_FORMAT_STRING,messageString,problem.getSourceLineNumber());
    if (problem.isWarning()) {
      mySender.warn(errMsg,hintObject);
    }
 else     if (myErrorTracker.errorsBelowLimit()) {
      myErrorTracker.incErrCnt();
      mySender.error(errMsg,hintObject);
    }
  }
  return myErrorTracker;
}
