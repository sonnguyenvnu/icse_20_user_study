/** 
 * {@inheritDoc}<p> The internal array buffers are not released as a result of this call. </p>
 * @see #release()
 */
@Override public void clear(){
  if (head < tail) {
    Arrays.fill(buffer,head,tail,Intrinsics.empty());
  }
 else {
    Arrays.fill(buffer,0,tail,Intrinsics.empty());
    Arrays.fill(buffer,head,buffer.length,Intrinsics.empty());
  }
  this.head=tail=0;
}
