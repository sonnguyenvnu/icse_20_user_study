/** 
 * Copy buf to mMessage.
 */
protected void arraycopy(byte[] buf,int pos,int length){
  mMessage.write(buf,pos,length);
  mPosition=mPosition + length;
}
