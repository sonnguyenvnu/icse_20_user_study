public void dirtyAllDescendants(){
  YogaNative.jni_YGNodeMarkDirtyAndPropogateToDescendants(mNativePointer);
}
