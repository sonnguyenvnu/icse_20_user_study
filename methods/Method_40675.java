private void processDiagnostic(@NotNull Diagnostic d){
  Style style=new Style(Style.Type.WARNING,d.start,d.end);
  style.message=d.msg;
  style.url=d.file;
  addFileStyle(d.file,style);
}
