@Override public void style(final TextPresentation textPresentation,final Color c,final Representation rep){
  for (  CoverageInformationItem child : getChildren()) {
    child.style(textPresentation,c,rep);
  }
}
