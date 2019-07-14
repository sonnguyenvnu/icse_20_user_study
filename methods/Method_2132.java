public static DefaultZoomableController newInstance(){
  return new DefaultZoomableController(TransformGestureDetector.newInstance());
}
