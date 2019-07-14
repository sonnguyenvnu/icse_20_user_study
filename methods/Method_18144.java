@Override public void useHeightAsBaseline(boolean useHeightAsBaselineFunction){
  if (useHeightAsBaselineFunction) {
    mYogaNode.setBaselineFunction(new YogaBaselineFunction(){
      @Override public float baseline(      YogaNode yogaNode,      float width,      float height){
        return height;
      }
    }
);
  }
}
