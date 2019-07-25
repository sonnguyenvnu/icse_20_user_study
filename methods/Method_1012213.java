/** 
 * ????
 */
public static void stop(BDAbstractLocationListener listener){
  get().unregisterListener(listener).stop();
}
