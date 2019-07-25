private void build(String moduleFileName,IResource rootFile,IProgressMonitor monitor){
  if (rootFile != null && (rootFile.getName().equals(moduleFileName))) {
    monitor.beginTask("Invoking the SANY to re-parse the spec",IProgressMonitor.UNKNOWN);
    ParserHelper.rebuildSpec(new SubProgressMonitor(monitor,IProgressMonitor.UNKNOWN));
    monitor.done();
  }
 else {
    IProject project=getProject();
    IResource moduleFile=project.getFile(moduleFileName);
    if (moduleFile == null || !moduleFile.exists()) {
      throw new IllegalStateException("Resource not found during build: " + moduleFileName);
    }
    if (!moduleFile.isDerived()) {
      monitor.beginTask("Invoking SANY to re-parse a module " + moduleFileName,IProgressMonitor.UNKNOWN);
      ParserHelper.rebuildModule(moduleFile,new SubProgressMonitor(monitor,IProgressMonitor.UNKNOWN));
      monitor.done();
    }
 else {
      Activator.getDefault().logDebug("Skipping resource: " + moduleFileName);
    }
  }
}
