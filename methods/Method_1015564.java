public Object down(Message msg){
  Address dest=msg.getDest();
  if (dest == null || !(dest instanceof SiteAddress))   return down_prot.down(msg);
  SiteAddress target=(SiteAddress)dest;
  Address src=msg.getSrc();
  SiteAddress sender=src instanceof SiteMaster ? new SiteMaster(((SiteMaster)src).getSite()) : new SiteUUID((UUID)local_addr,NameCache.get(local_addr),site);
  if (local_addr instanceof ExtendedUUID)   ((ExtendedUUID)sender).addContents((ExtendedUUID)local_addr);
  if (target.getSite().equals(site)) {
    if (local_addr.equals(target) || (target instanceof SiteMaster && is_site_master)) {
      long start=stats ? System.nanoTime() : 0;
      forwardTo(local_addr,target,sender,msg,false);
      if (stats) {
        local_delivery_time.add(System.nanoTime() - start);
        local_deliveries.increment();
      }
    }
 else     deliverLocally(target,sender,msg);
    return null;
  }
  if (!is_site_master) {
    long start=stats ? System.nanoTime() : 0;
    Address site_master=pickSiteMaster(sender);
    if (site_master == null)     throw new IllegalStateException("site master is null");
    forwardTo(site_master,target,sender,msg,max_site_masters == 1);
    if (stats) {
      forward_sm_time.add(System.nanoTime() - start);
      forward_to_site_master.increment();
    }
  }
 else   route(target,sender,msg);
  return null;
}
