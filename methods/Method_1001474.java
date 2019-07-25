public void endif(LinkRendering nextLinkRenderer){
  endifCalled=true;
  if (elseBranch == null) {
    this.elseBranch=new Branch(swimlane,Display.NULL,Display.NULL,null,Display.NULL);
  }
  this.elseBranch.setSpecial(nextLinkRenderer);
  this.current.setInlinkRendering(nextLinkRenderer);
}
