/** 
 * Performs sparse conditional constant propagation on a method.
 * @param ssaMethod Method to process
 */
public static void process(SsaMethod ssaMethod){
  new SCCP(ssaMethod).run();
}
