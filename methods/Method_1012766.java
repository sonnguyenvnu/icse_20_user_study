/** 
 * Starts up two threads: one to broadcast UPnP ALIVE messages and another to listen for responses.
 * @throws IOException Signals that an I/O exception has occurred.
 */
public static void listen() throws IOException {
  Runnable rAlive=new Runnable(){
    @Override public void run(){
      while (true) {
        sleep(ALIVE_delay);
        sendAlive();
      }
    }
  }
;
  aliveThread=new Thread(rAlive,"UPNP-AliveMessageSender");
  aliveThread.start();
  Runnable r=new Runnable(){
    @Override public void run(){
      boolean bindErrorReported=false;
      while (true) {
        MulticastSocket multicastSocket=null;
        try {
          multicastSocket=new MulticastSocket(configuration.getUpnpPort());
          if (bindErrorReported) {
            LOGGER.warn("Finally, acquiring port {} was successful!",configuration.getUpnpPort());
          }
          NetworkInterface ni=NetworkConfiguration.getInstance().getNetworkInterfaceByServerName();
          try {
            if (ni != null) {
              multicastSocket.setNetworkInterface(ni);
              LOGGER.trace("Setting multicast network interface: {}",ni);
            }
 else             if (PMS.get().getServer().getNetworkInterface() != null) {
              multicastSocket.setNetworkInterface(PMS.get().getServer().getNetworkInterface());
              LOGGER.trace("Setting multicast network interface: {}",PMS.get().getServer().getNetworkInterface());
            }
          }
 catch (          SocketException e) {
          }
          multicastSocket.setTimeToLive(4);
          multicastSocket.setReuseAddress(true);
          InetAddress upnpAddress=getUPNPAddress();
          multicastSocket.joinGroup(upnpAddress);
          final int M_SEARCH=1;
          final int NOTIFY=2;
          InetAddress lastAddress=null;
          int lastPacketType=0;
          while (true) {
            byte[] buf=new byte[1024];
            DatagramPacket receivePacket=new DatagramPacket(buf,buf.length);
            multicastSocket.receive(receivePacket);
            String s=new String(receivePacket.getData(),0,receivePacket.getLength(),StandardCharsets.UTF_8);
            InetAddress address=receivePacket.getAddress();
            int packetType=s.startsWith("M-SEARCH") ? M_SEARCH : s.startsWith("NOTIFY") ? NOTIFY : 0;
            boolean redundant=address.equals(lastAddress) && packetType == lastPacketType;
            if (packetType == M_SEARCH) {
              if (configuration.getIpFiltering().allowed(address)) {
                String remoteAddr=address.getHostAddress();
                int remotePort=receivePacket.getPort();
                if (!redundant && LOGGER.isTraceEnabled()) {
                  LOGGER.trace("Received a M-SEARCH from [{}:{}]: {}",remoteAddr,remotePort,s);
                }
                if (StringUtils.indexOf(s,"urn:schemas-upnp-org:service:ContentDirectory:1") > 0) {
                  sendDiscover(remoteAddr,remotePort,"urn:schemas-upnp-org:service:ContentDirectory:1");
                }
                if (StringUtils.indexOf(s,"upnp:rootdevice") > 0) {
                  sendDiscover(remoteAddr,remotePort,"upnp:rootdevice");
                }
                if (StringUtils.indexOf(s,"urn:schemas-upnp-org:device:MediaServer:1") > 0 || StringUtils.indexOf(s,"ssdp:all") > 0) {
                  sendDiscover(remoteAddr,remotePort,"urn:schemas-upnp-org:device:MediaServer:1");
                }
                if (StringUtils.indexOf(s,PMS.get().usn()) > 0) {
                  sendDiscover(remoteAddr,remotePort,PMS.get().usn());
                }
              }
            }
 else             if (packetType == NOTIFY && !redundant && LOGGER.isTraceEnabled()) {
              LOGGER.trace("Received a NOTIFY from [{}:{}]",address.getHostAddress(),receivePacket.getPort());
            }
            lastAddress=address;
            lastPacketType=packetType;
          }
        }
 catch (        BindException e) {
          if (!bindErrorReported) {
            LOGGER.error("Unable to bind to " + configuration.getUpnpPort() + ", which means that UMS will not automatically appear on your renderer! " + "This usually means that another program occupies the port. Please " + "stop the other program and free up the port. " + "UMS will keep trying to bind to it...[{}]",e.getMessage());
          }
          bindErrorReported=true;
          sleep(5000);
        }
catch (        IOException e) {
          LOGGER.error("UPnP network exception: {}",e.getMessage());
          LOGGER.trace("",e);
          sleep(1000);
        }
 finally {
          if (multicastSocket != null) {
            try {
              InetAddress upnpAddress=getUPNPAddress();
              multicastSocket.leaveGroup(upnpAddress);
            }
 catch (            IOException e) {
              LOGGER.trace("Final UPnP network exception: {}",e.getMessage());
              LOGGER.trace("",e);
            }
            multicastSocket.disconnect();
            multicastSocket.close();
          }
        }
      }
    }
  }
;
  listenerThread=new Thread(r,"UPNPHelper");
  listenerThread.start();
}
