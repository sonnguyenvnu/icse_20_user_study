/** 
 * @deprecated Use {@link #write(Broadcast,boolean,Context)} instead.
 */
public void write(long broadcastId,boolean like,Context context){
  add(new LikeBroadcastWriter(broadcastId,like,this),context);
}
