@Override public void finished(TaskEvent taskEvent){
  if (taskEvent.getKind() != Kind.ANALYZE) {
    return;
  }
  if (JavaCompiler.instance(context).errorCount() > errorProneErrors) {
    return;
  }
  TreePath path=JavacTrees.instance(context).getPath(taskEvent.getTypeElement());
  if (path == null) {
    path=new TreePath(taskEvent.getCompilationUnit());
  }
  verify(seen.add(path.getLeaf()),"Duplicate FLOW event for: %s",taskEvent.getTypeElement());
  Context subContext=new SubContext(context);
  subContext.put(ErrorProneOptions.class,errorProneOptions);
  Log log=Log.instance(context);
  JCCompilationUnit compilation=(JCCompilationUnit)path.getCompilationUnit();
  DescriptionListener descriptionListener=descriptionListenerFactory.getDescriptionListener(log,compilation);
  DescriptionListener countingDescriptionListener=d -> {
    if (d.severity == SeverityLevel.ERROR) {
      errorProneErrors++;
    }
    descriptionListener.onDescribed(d);
  }
;
  JavaFileObject originalSource=log.useSource(compilation.getSourceFile());
  try {
    if (shouldExcludeSourceFile(compilation)) {
      return;
    }
    if (path.getLeaf().getKind() == Tree.Kind.COMPILATION_UNIT) {
      transformer.get().apply(path,subContext,countingDescriptionListener);
    }
 else     if (finishedCompilation(path.getCompilationUnit())) {
      transformer.get().apply(new TreePath(compilation),subContext,countingDescriptionListener);
    }
  }
 catch (  ErrorProneError e) {
    e.logFatalError(log,context);
    throw e;
  }
catch (  LinkageError e) {
    String version=ErrorProneVersion.loadVersionFromPom().or("unknown version");
    log.error("error.prone.crash",getStackTraceAsString(e),version,"(see stack trace)");
    throw e;
  }
catch (  CompletionFailure e) {
    log.error("proc.cant.access",e.sym,e.getDetailValue(),getStackTraceAsString(e));
  }
 finally {
    log.useSource(originalSource);
  }
}
