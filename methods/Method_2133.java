/** 
 * Returns true if the zoomable transform is identity matrix.
 */
@Override public boolean isIdentity(){
  return isMatrixIdentity(mActiveTransform,1e-3f);
}
