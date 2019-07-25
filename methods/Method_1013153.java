public void style(final TextPresentation textPresentation,final Color c,final Representation rep){
  if (!isRoot()) {
    final StyleRange rs=new StyleRange();
    rs.start=region.getOffset();
    rs.length=region.getLength();
    rs.background=c;
    if ((rep == Representation.STATES_DISTINCT || rep == Representation.STATES) && !(this instanceof ActionInformationItem)) {
      rs.background=JFaceResources.getColorRegistry().get(ModuleCoverageInformation.GRAY);
      rs.borderStyle=SWT.NONE;
      rs.borderColor=null;
    }
 else     if (rep != Representation.COST && rep.getValue(this,Grouping.INDIVIDUAL) == 0L) {
      rs.background=null;
      rs.borderStyle=SWT.BORDER_SOLID;
      rs.borderColor=JFaceResources.getColorRegistry().get(ModuleCoverageInformation.RED);
    }
    active=false;
    textPresentation.replaceStyleRange(addStlye(rs));
  }
  for (  CoverageInformationItem child : childs) {
    child.style(textPresentation,c,rep);
  }
}
