private void setDefaultTopPadding(final boolean setDefaultPadding){
  if (setDefaultPadding) {
    adjustLandscapeTopPadding(this.landCardViewGroup,this.gridNew2Dimen,this.gridNew2Dimen,this.gridNew2Dimen,this.gridNew2Dimen);
    adjustViewGroupTopMargin(this.projectCardViewGroup,0);
  }
 else {
    adjustLandscapeTopPadding(this.landCardViewGroup,this.gridNew2Dimen,this.gridNew3Dimen,this.gridNew2Dimen,this.gridNew2Dimen);
    adjustViewGroupTopMargin(this.projectCardViewGroup,this.gridNew1Dimen);
  }
}
