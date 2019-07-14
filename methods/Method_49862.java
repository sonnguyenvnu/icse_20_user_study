/** 
 * Transfer the received response to the caller (for download requests write to content uri)
 * @param fillIn the intent that will be returned to the caller
 * @param response the pdu to transfer
 */
@Override protected boolean transferResponse(Intent fillIn,final byte[] response){
  return mRequestManager.writePduToContentUri(mContentUri,response);
}
