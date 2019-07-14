private static Alignment createSwitchingAlignment(final Alignment ltr){
  return new Alignment(){
    @Override int getGravityOffset(    Child view,    int cellDelta){
      return ltr.getGravityOffset(view,cellDelta);
    }
    @Override public int getAlignmentValue(    Child view,    int viewSize){
      return ltr.getAlignmentValue(view,viewSize);
    }
  }
;
}
