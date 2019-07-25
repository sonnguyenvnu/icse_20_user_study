/** 
 * Search for existing apple tv services on all network interfaces
 * @param timeout for each interface query
 * @return list of available services
 * @throws IOException
 */
public static java.util.List<Service> search(int timeout) throws IOException {
  java.util.List<InetAddress> networkAddresses=listNetworkAddresses();
  java.util.List<Service> availableServices=new ArrayList<AirPlay.Service>();
  for (  InetAddress address : networkAddresses) {
    final JmDNS jmdns=JmDNS.create(address);
    Service[] tmpResults=formatSearch(jmdns.list(DNSSD_TYPE,timeout));
    for (    Service service : tmpResults) {
      if (!availableServices.contains(service)) {
        availableServices.add(service);
      }
    }
    jmdns.close();
  }
  return availableServices;
}
