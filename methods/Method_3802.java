@SuppressWarnings("WeakerAccess") boolean hasRunningRecoverAnim(){
  final int size=mRecoverAnimations.size();
  for (int i=0; i < size; i++) {
    if (!mRecoverAnimations.get(i).mEnded) {
      return true;
    }
  }
  return false;
}
