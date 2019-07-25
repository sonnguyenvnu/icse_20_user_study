public Object down(Event evt){
switch (evt.getType()) {
case Event.FIND_INITIAL_MBRS:
    long timeout=evt.getArg();
  return findMembers(null,true,false,timeout);
case Event.FIND_MBRS:
return findMembers(evt.getArg(),false,false,0);
case Event.FIND_MBRS_ASYNC:
discovery_rsp_callback=evt.arg();
return findMembers(null,false,false,0);
case Event.VIEW_CHANGE:
View old_view=view;
view=evt.getArg();
current_coord=view.getCoord();
is_coord=Objects.equals(current_coord,local_addr);
Object retval=down_prot.down(evt);
if (send_cache_on_join && !isDynamic() && is_coord) {
List<Address> curr_mbrs, left_mbrs, new_mbrs;
curr_mbrs=new ArrayList<>(view.getMembers());
left_mbrs=View.leftMembers(old_view,view);
new_mbrs=View.newMembers(old_view,view);
startCacheDissemination(curr_mbrs,left_mbrs,new_mbrs);
}
return retval;
case Event.BECOME_SERVER:
down_prot.down(evt);
is_server=true;
return null;
case Event.SET_LOCAL_ADDRESS:
local_addr=evt.getArg();
return down_prot.down(evt);
case Event.CONNECT:
case Event.CONNECT_WITH_STATE_TRANSFER:
case Event.CONNECT_USE_FLUSH:
case Event.CONNECT_WITH_STATE_TRANSFER_USE_FLUSH:
is_leaving=false;
cluster_name=evt.getArg();
Object ret=down_prot.down(evt);
handleConnect();
return ret;
case Event.DISCONNECT:
is_leaving=true;
handleDisconnect();
return down_prot.down(evt);
default :
return down_prot.down(evt);
}
}
