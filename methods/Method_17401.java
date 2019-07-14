public TraceFilesSettings traceFiles(){
  checkState(isFiles());
  return new TraceFilesSettings();
}
