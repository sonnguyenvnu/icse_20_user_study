/** 
 * called to refresh the connection if needed
 */
private void refresh(){
  if (isRefreshing.compareAndSet(false,true)) {
    log.info("REFRESHING DATASOURCE for {} ",this.url);
    target.set(create());
    isRefreshing.set(false);
  }
 else {
  }
}
