public void style(final TextPresentation textPresentation,final Representation rep){
  if (isRoot()) {
    style(textPresentation,true,rep);
  }
 else {
    style(textPresentation,false,rep);
  }
}
