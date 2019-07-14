public YogaWrap getWrap(){
  return YogaWrap.fromInt(YogaNative.jni_YGNodeStyleGetFlexWrap(mNativePointer));
}
