public void suspect(Address mbr){
  if (mbr.equals(gms.local_addr)) {
    if (log.isWarnEnabled())     log.warn("I am the coord and I'm suspected -- will probably leave shortly");
    return;
  }
  Collection<Request> suspected=new LinkedHashSet<>(1);
  suspected.add(new Request(Request.SUSPECT,mbr));
  handleMembershipChange(suspected);
}
