protected boolean reconnect(Target target){
  RouterStub stub=new RouterStub(target.bind_addr,target.router_addr,this.use_nio,this).receiver(target.receiver);
  if (!add(stub))   return false;
  try {
    stub.connect(this.cluster_name,this.local_addr,this.logical_name,this.phys_addr);
    log.debug("%s: re-established connection to %s successfully for group %s",local_addr,stub.remote(),this.cluster_name);
    return true;
  }
 catch (  Throwable t) {
    remove(stub);
    return false;
  }
}
