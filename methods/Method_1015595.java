public void stop(){
  super.stop();
  is_server=is_coord=false;
  unregister(cluster_name,local_addr);
}
