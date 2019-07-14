/** 
 * @deprecated Use {@link #write(Broadcast,Context)} instead.
 */
public void write(long broadcastId,Context context){
  add(new DeleteBroadcastWriter(broadcastId,this),context);
}
