@Override public void setBorderWidth(YogaEdge edge,@Px int borderWidth){
  if (mNestedTreeProps != null && mNestedTreeProps.mIsNestedTreeHolder) {
    NestedTreeProps props=getOrCreateNestedTreeProps();
    if (props.mNestedTreeBorderWidth == null) {
      props.mNestedTreeBorderWidth=new Edges();
    }
    props.mNestedTreeBorderWidth.set(edge,borderWidth);
  }
 else {
    mYogaNode.setBorder(edge,borderWidth);
  }
}
