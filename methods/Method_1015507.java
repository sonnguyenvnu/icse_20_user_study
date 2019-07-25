protected void suspect(List<Address> suspects){
  if (suspects == null || suspects.isEmpty())   return;
  num_suspect_events+=suspects.size();
  final List<Address> eligible_mbrs=new ArrayList<>();
synchronized (this) {
    suspected_mbrs.addAll(suspects);
    eligible_mbrs.addAll(members);
    eligible_mbrs.removeAll(suspected_mbrs);
    has_suspected_mbrs=!suspected_mbrs.isEmpty();
  }
  if (local_addr != null && !eligible_mbrs.isEmpty() && local_addr.equals(eligible_mbrs.get(0))) {
    log.debug("%s: suspecting %s",local_addr,suspects);
    up_prot.up(new Event(Event.SUSPECT,suspects));
    down_prot.down(new Event(Event.SUSPECT,suspects));
  }
}
