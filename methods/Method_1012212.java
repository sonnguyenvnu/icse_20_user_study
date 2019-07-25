/** 
 * ????
 * @param listener
 */
@Permission(LOCATION) public static void start(BDAbstractLocationListener listener){
  get().registerListener(listener).start();
}
