public Object down(Event evt){
switch (evt.getType()) {
case Event.GET_PHYSICAL_ADDRESS:
    Object addr=down_prot.down(evt);
  Address arg=evt.getArg();
return addr != null ? addr : cache.get(arg);
case Event.GET_PHYSICAL_ADDRESSES:
Collection<PhysicalAddress> addrs=(Collection<PhysicalAddress>)down_prot.down(evt);
Collection<PhysicalAddress> tmp=new HashSet<>(addrs);
tmp.addAll(cache.values());
return tmp;
case Event.GET_LOGICAL_PHYSICAL_MAPPINGS:
Map<Address,PhysicalAddress> map=(Map<Address,PhysicalAddress>)down_prot.down(evt);
Map<Address,PhysicalAddress> new_map=new HashMap<>(map);
new_map.putAll(cache);
return new_map;
case Event.ADD_PHYSICAL_ADDRESS:
Tuple<Address,PhysicalAddress> new_val=evt.getArg();
if (new_val != null) {
cache.put(new_val.getVal1(),new_val.getVal2());
writeNodeToDisk(new_val.getVal1(),new_val.getVal2());
}
break;
case Event.REMOVE_ADDRESS:
Address tmp_addr=evt.getArg();
if (cache.remove(tmp_addr) != null) removeNodeFromDisk(tmp_addr);
break;
case Event.SET_LOCAL_ADDRESS:
local_addr=evt.getArg();
break;
case Event.VIEW_CHANGE:
List<Address> members=((View)evt.getArg()).getMembers();
cache.keySet().stream().filter(mbr -> !members.contains(mbr)).forEach(mbr -> {
cache.remove(mbr);
removeNodeFromDisk(mbr);
}
);
break;
}
return down_prot.down(evt);
}
