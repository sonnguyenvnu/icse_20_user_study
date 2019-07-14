static protected float matrixScale(PMatrix matrix){
  float factor=1;
  if (matrix != null) {
    if (matrix instanceof PMatrix2D) {
      PMatrix2D tr=(PMatrix2D)matrix;
      float areaScaleFactor=Math.abs(tr.m00 * tr.m11 - tr.m01 * tr.m10);
      factor=(float)Math.sqrt(areaScaleFactor);
    }
 else     if (matrix instanceof PMatrix3D) {
      PMatrix3D tr=(PMatrix3D)matrix;
      float volumeScaleFactor=Math.abs(tr.m00 * (tr.m11 * tr.m22 - tr.m12 * tr.m21) + tr.m01 * (tr.m12 * tr.m20 - tr.m10 * tr.m22) + tr.m02 * (tr.m10 * tr.m21 - tr.m11 * tr.m20));
      factor=(float)Math.pow(volumeScaleFactor,1.0f / 3.0f);
    }
  }
  return factor;
}
