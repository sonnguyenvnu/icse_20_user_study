protected <T>GroupRequest<T> cast(final Collection<Address> dests,Buffer data,RequestOptions options,boolean block_for_results) throws Exception {
  if (options == null) {
    log.warn("request options were null, using default of sync");
    options=RequestOptions.SYNC();
  }
  List<Address> real_dests;
  if (dests != null)   real_dests=dests.stream().filter(dest -> dest instanceof SiteAddress || this.members.contains(dest)).collect(ArrayList::new,(list,dest) -> {
    if (!list.contains(dest))     list.add(dest);
  }
,(l,r) -> {
  }
);
 else   real_dests=new ArrayList<>(members);
  JChannel tmp=channel;
  if ((tmp != null && tmp.getDiscardOwnMessages()) || options.transientFlagSet(Message.TransientFlag.DONT_LOOPBACK)) {
    if (local_addr == null)     local_addr=tmp != null ? tmp.getAddress() : null;
    real_dests.remove(local_addr);
  }
  if (options.hasExclusionList())   Stream.of(options.exclusionList()).forEach(real_dests::remove);
  if (real_dests.isEmpty()) {
    log.trace("destination list is empty, won't send message");
    return empty_group_request;
  }
  boolean sync=options.mode() != ResponseMode.GET_NONE;
  boolean non_blocking=!sync || !block_for_results, anycast=options.anycasting();
  if (non_blocking)   updateStats(real_dests,anycast,sync,0);
  if (!sync) {
    corr.sendRequest(real_dests,data,null,options);
    return null;
  }
  GroupRequest<T> req=new GroupRequest<>(corr,real_dests,options);
  long start=non_blocking || !rpc_stats.extendedStats() ? 0 : System.nanoTime();
  req.execute(data,block_for_results);
  long time=non_blocking || !rpc_stats.extendedStats() ? 0 : System.nanoTime() - start;
  if (!non_blocking)   updateStats(real_dests,anycast,true,time);
  return req;
}
