public static int b3CreateMultiBodyLink(@NativeType("b3SharedMemoryCommandHandle") long commandHandle,double linkMass,double linkCollisionShapeIndex,double linkVisualShapeIndex,@NativeType("double const *") DoubleBuffer linkPosition,@NativeType("double const *") DoubleBuffer linkOrientation,@NativeType("double const *") DoubleBuffer linkInertialFramePosition,@NativeType("double const *") DoubleBuffer linkInertialFrameOrientation,int linkParentIndex,int linkJointType,@NativeType("double const *") DoubleBuffer linkJointAxis){
  if (CHECKS) {
    check(commandHandle);
    check(linkPosition,3);
    check(linkOrientation,4);
    check(linkInertialFramePosition,3);
    check(linkInertialFrameOrientation,4);
    check(linkJointAxis,3);
  }
  return nb3CreateMultiBodyLink(commandHandle,linkMass,linkCollisionShapeIndex,linkVisualShapeIndex,memAddress(linkPosition),memAddress(linkOrientation),memAddress(linkInertialFramePosition),memAddress(linkInertialFrameOrientation),linkParentIndex,linkJointType,memAddress(linkJointAxis));
}
