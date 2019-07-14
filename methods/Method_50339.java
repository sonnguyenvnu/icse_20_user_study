@SuppressWarnings("deprecation") @TargetApi(VERSION_CODES.ECLAIR) private static int getPointerIndexEclair(int action){
  return (action & MotionEvent.ACTION_POINTER_ID_MASK) >> MotionEvent.ACTION_POINTER_ID_SHIFT;
}
