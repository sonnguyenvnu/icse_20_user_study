@Override public List<AnsServer> getInitialListOfServers(){
  try {
    List<Host> hosts=NamingService.getHosts(getDom());
    return hostsToServerList(hosts);
  }
 catch (  Exception e) {
    throw new IllegalStateException("Can not get ans hosts, dom=" + getDom(),e);
  }
}
