private static int getUniqueId(AnimationBackend backend,int frameNumber){
  int result=backend.hashCode();
  result=31 * result + frameNumber;
  return result;
}
