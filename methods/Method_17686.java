/** 
 * This method replaces the child at childIndex position with the newNode received by parameter. This is different than calling removeChildAt and addChildAt because this method ONLY replaces the child in the mChildren datastructure. @DoNotStrip: called from JNI
 * @return the nativePointer of the newNode {@linl YogaNode}
 */
@DoNotStrip private final long replaceChild(YogaNodeJNIBase newNode,int childIndex){
  if (mChildren == null) {
    throw new IllegalStateException("Cannot replace child. YogaNode does not have children");
  }
  mChildren.remove(childIndex);
  mChildren.add(childIndex,newNode);
  newNode.mOwner=this;
  return newNode.mNativePointer;
}
