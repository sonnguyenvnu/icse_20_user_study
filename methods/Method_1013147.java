@Override protected void style(final TextPresentation textPresentation,boolean merge,final Representation rep){
  if (relation == Relation.PROP) {
    return;
  }
 else   if (isNotInFile) {
    for (    CoverageInformationItem child : getChildren()) {
      child.style(textPresentation,merge,rep);
    }
    return;
  }
  super.style(textPresentation,merge,rep);
}
