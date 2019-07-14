private void setStatsMargins(final boolean shouldSetDefaultMargins){
  if (shouldSetDefaultMargins) {
    ViewUtils.setLinearViewGroupMargins(this.projectStatsViewGroup,0,this.grid3Dimen,0,this.grid2Dimen);
  }
 else {
    ViewUtils.setLinearViewGroupMargins(this.projectStatsViewGroup,0,this.grid3Dimen,0,this.grid4Dimen);
  }
}
