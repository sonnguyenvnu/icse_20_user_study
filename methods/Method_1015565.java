/** 
 * Routes the message to the target destination, used by a site master (coordinator)
 * @param dest
 * @param sender the address of the sender
 * @param msg The message
 */
protected void route(SiteAddress dest,SiteAddress sender,Message msg){
  String target_site=dest.getSite();
  if (target_site.equals(site)) {
    if (local_addr.equals(dest) || ((dest instanceof SiteMaster) && is_site_master)) {
      deliver(dest,sender,msg);
    }
 else     deliverLocally(dest,sender,msg);
    return;
  }
  Relayer tmp=relayer;
  if (tmp == null) {
    log.warn(local_addr + ": not site master; dropping message");
    return;
  }
  Route route=tmp.getRoute(target_site,sender);
  if (route == null) {
    log.error(local_addr + ": no route to " + target_site + ": dropping message");
    sendSiteUnreachableTo(sender,target_site);
  }
 else   route.send(dest,sender,msg);
}
