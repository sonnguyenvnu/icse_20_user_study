@SuppressLint("RtlHardcoded") private int getMaxDistance(@NonNull ViewGroup sceneRoot){
switch (mSide) {
case Gravity.LEFT:
case Gravity.RIGHT:
case Gravity.START:
case Gravity.END:
    return sceneRoot.getWidth();
default :
  return sceneRoot.getHeight();
}
}
