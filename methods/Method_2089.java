private static Matrix getMatrix(float skewX,float skewY,float degrees){
  Matrix matrix=new Matrix();
  matrix.postSkew(skewX,skewY);
  matrix.postRotate(degrees);
  return matrix;
}
