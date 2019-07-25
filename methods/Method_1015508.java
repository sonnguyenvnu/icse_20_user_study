protected void suspect(Set<Address> suspects){
  if (suspects == null)   return;
  suspects.remove(local_addr);
  suspects.forEach(suspect -> suspect_history.add(String.format("%s: %s",new Date(),suspect)));
  suspected_mbrs.addAll(suspects);
  List<Address> eligible_mbrs=new ArrayList<>(this.members);
  eligible_mbrs.removeAll(suspected_mbrs);
  if (local_addr != null && !eligible_mbrs.isEmpty() && local_addr.equals(eligible_mbrs.get(0))) {
    log.debug("%s: suspecting %s",local_addr,suspected_mbrs);
    up_prot.up(new Event(Event.SUSPECT,suspected_mbrs));
    down_prot.down(new Event(Event.SUSPECT,suspected_mbrs));
  }
}
