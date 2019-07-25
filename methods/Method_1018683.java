/** 
 * @param code code
 * @return a DnsOpCode object.
 */
public static DnsOpCode register(DnsOpCode code){
  return registry.put(code.value(),code);
}
