/** 
 * @deprecated Use {@link #write(Broadcast,String,Context)} instead.
 */
public void write(long broadcastId,String text,Context context){
  add(new RebroadcastBroadcastWriter(broadcastId,text,this),context);
}
