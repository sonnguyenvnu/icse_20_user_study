/** 
 * line format: NET,Network I/O bx-50-13,lo-read-KB/s,eth0-read-KB/s,eth1-read-KB/s,eth2-read-KB/s,eth3-read-KB/s,lo-write-KB/s,eth0-write-KB/s,eth1-write-KB/s,eth2-write-KB/s,eth3-write-KB/s, NET,T0001,190.3,3317.8,0.0,0.0,0.0,190.3,3377.7,0.0,0.0,0.0,
 */
public void parse(String line,String timeKey) throws Exception {
  if (line.startsWith(FLAG)) {
    String[] items=line.split(",");
    if (items[1].startsWith("Network")) {
      for (int i=0; i < items.length; ++i) {
        if (items[i].startsWith("eth")) {
          NetworkInterfaceCard nic=new NetworkInterfaceCard();
          nic.setName(items[i]);
          nic.setIdx(i);
          ncList.add(nic);
        }
      }
    }
 else {
      for (      NetworkInterfaceCard nic : ncList) {
        nic.setValue(NumberUtils.toFloat(items[nic.getIdx()]));
      }
      caculate();
    }
  }
}
