/** 
 * A wrapper method for <code>int pcap_loop(pcap_t *, int, pcap_handler, u_char *)</code>. When a packet is captured, the  {@link java.util.concurrent.Executor#execute(Runnable) executor.execute()} is called with a Runnable object in the thread which called the <code>loop()</code>. Then, the Runnable object calls <code>listener.gotPacket(byte[])</code>. If <code>listener.gotPacket(byte[])</code> is expected to take a long time to process a packet, this method should be used with a proper executor instead of  {@link #loop(int,RawPacketListener)} in order to prevent the pcap buffer from overflowing.
 * @param packetCount the number of packets to capture. -1 is equivalent to infinity. 0 may resultin different behaviors between platforms and pcap library versions.
 * @param listener listener
 * @param executor executor
 * @throws PcapNativeException if an error occurs in the pcap native library.
 * @throws InterruptedException if the loop terminated due to a call to {@link #breakLoop()}.
 * @throws NotOpenException if this PcapHandle is not open.
 */
public void loop(int packetCount,RawPacketListener listener,Executor executor) throws PcapNativeException, InterruptedException, NotOpenException {
  if (listener == null || executor == null) {
    StringBuilder sb=new StringBuilder();
    sb.append("listener: ").append(listener).append(" executor: ").append(executor);
    throw new NullPointerException(sb.toString());
  }
  doLoop(packetCount,new GotRawPacketFuncExecutor(listener,executor));
}
