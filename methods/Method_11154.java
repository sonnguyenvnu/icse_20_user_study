private static Path createQuad(float controlX,float controlY){
  final Path path=new Path();
  path.moveTo(0.0f,0.0f);
  path.quadTo(controlX,controlY,1.0f,1.0f);
  return path;
}
