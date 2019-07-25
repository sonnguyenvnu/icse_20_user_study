@Override public void style(TextPresentation textPresentation,final Representation rep){
  if (relation == Relation.PROP) {
    return;
  }
 else   if (isNotInFile) {
    for (    CoverageInformationItem child : getChildren()) {
      child.style(textPresentation,rep);
    }
    return;
  }
  super.style(textPresentation,rep);
}
