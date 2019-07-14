private void processDiagnostic(Diagnostic d){
  StyleRun style=new StyleRun(StyleRun.Type.WARNING,d.start,d.end - d.start);
  style.message=d.msg;
  style.url=d.file;
  addFileStyle(d.file,style);
}
