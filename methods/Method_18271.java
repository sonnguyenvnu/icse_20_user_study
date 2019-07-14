private void calculateAndSetVisibilityOutputId(VisibilityOutput visibilityOutput,int level,long previousId){
  if (mLayoutStateOutputIdCalculator == null) {
    mLayoutStateOutputIdCalculator=new LayoutStateOutputIdCalculator();
  }
  mLayoutStateOutputIdCalculator.calculateAndSetVisibilityOutputId(visibilityOutput,level,previousId);
}
