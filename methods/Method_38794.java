/** 
 * Creates new  {@link jodd.jerry.Jerry.JerryParser Jerry runner} withprovided implementation of  {@link jodd.lagarto.dom.DOMBuilder}.
 */
public static JerryParser jerry(final DOMBuilder domBuilder){
  return new JerryParser(domBuilder);
}
