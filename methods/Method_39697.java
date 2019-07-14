/** 
 * Generates new class.
 */
public Class defineProxy(final Class target){
  ProxyProxettaFactory builder=proxetta.proxy();
  builder.setTarget(target);
  return builder.define();
}
