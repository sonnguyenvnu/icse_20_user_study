/** 
 * @param type type
 * @return a DnsResourceRecordType object.
 */
public static DnsResourceRecordType register(DnsResourceRecordType type){
  return registry.put(type.value(),type);
}
