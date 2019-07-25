/** 
 * Convience function that this Sketch is valid. This is a troubleshooting tool for sketches that have been heapified from serialized images. <p>If you are starting with a serialized image as a byte array, first heapify the byte array to a sketch, which performs a number of checks. Then use this function as one additional check on the sketch.</p>
 * @return true if this sketch is validated.
 */
public boolean validate(){
  final long[] bitMatrix=bitMatrixOfSketch(this);
  final long matrixCoupons=countBitsSetInMatrix(bitMatrix);
  return matrixCoupons == numCoupons;
}
