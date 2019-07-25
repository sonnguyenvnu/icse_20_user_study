protected void route(String group,Address dest,byte[] msg,int offset,int length){
  ConcurrentMap<Address,Entry> map=address_mappings.get(group);
  if (map == null)   return;
  if (dest != null) {
    Entry entry=map.get(dest);
    if (entry != null)     sendToMember(entry.client_addr,msg,offset,length);
 else     log.warn("dest %s in cluster %s not found",dest,group);
  }
 else {
    Set<Map.Entry<Address,Entry>> dests=map.entrySet();
    sendToAllMembersInGroup(dests,msg,offset,length);
  }
}
