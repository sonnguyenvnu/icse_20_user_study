public Object down(Event evt){
  Object retval=super.down(evt);
switch (evt.type()) {
case Event.ADD_PHYSICAL_ADDRESS:
    Tuple<Address,PhysicalAddress> tuple=evt.arg();
  IpAddress val=(IpAddress)tuple.getVal2();
addr_table.put(tuple.getVal1(),new InetSocketAddress(val.getIpAddress(),val.getPort()));
break;
case Event.VIEW_CHANGE:
for (Iterator<Map.Entry<Address,SocketAddress>> it=addr_table.entrySet().iterator(); it.hasNext(); ) {
Map.Entry<Address,SocketAddress> entry=it.next();
if (!view.containsMember(entry.getKey())) {
SocketAddress sock_addr=entry.getValue();
it.remove();
Connection conn=connections.remove(sock_addr);
Util.close(conn);
}
}
break;
}
return retval;
}
