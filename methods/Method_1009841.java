/** 
 * Defines a matrix identifier for a dedicated medim
 * @param medium the medium
 * @param mxid   the matrixId
 */
public void put(String medium,MXID mxid){
  if ((null != medium) && (null != mxid) && !TextUtils.isEmpty(mxid.mMatrixId)) {
    mMXIDsByElement.put(medium,mxid);
  }
}
