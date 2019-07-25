protected void write(SystemLandscapeView view,Writer writer){
  writeSystemLandscapeOrContextView(view,writer,view.isEnterpriseBoundaryVisible());
}
