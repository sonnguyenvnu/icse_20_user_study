/** 
 * @param code code
 * @return a DnsRCode object.
 */
public static DnsRCode register(DnsRCode code){
  return registry.put(code.value(),code);
}
