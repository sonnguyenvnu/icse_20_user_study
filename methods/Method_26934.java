@Nullable public static <T>Animator ofPointF(@Nullable T target,@NonNull PointFProperty<T> property,@Nullable PathMotion pathMotion,float startLeft,float startTop,float endLeft,float endTop){
  if (startLeft != endLeft || startTop != endTop) {
    if (pathMotion == null || pathMotion.equals(PathMotion.STRAIGHT_PATH_MOTION)) {
      return ofPointF(target,property,startLeft,startTop,endLeft,endTop);
    }
 else {
      return ofPointF(target,property,pathMotion.getPath(startLeft,startTop,endLeft,endTop));
    }
  }
 else {
    return null;
  }
}
