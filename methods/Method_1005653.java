/** 
 * Process a method.
 * @param ssaMethod {@code non-null;} method to process
 */
public static void process(SsaMethod ssaMethod){
  LiteralOpUpgrader dc;
  dc=new LiteralOpUpgrader(ssaMethod);
  dc.run();
}
