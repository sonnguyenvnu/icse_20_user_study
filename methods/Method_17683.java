public void setMarginAuto(YogaEdge edge){
  YogaNative.jni_YGNodeStyleSetMarginAuto(mNativePointer,edge.intValue());
}
