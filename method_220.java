/** 
 * Initialize the transport sender by reading pre-defined connection factories for outgoing messages. These will create sessions (one per each destination dealt with) to be used when messages are being sent.
 * @param confContext the configuration context
 * @param transportOut the transport sender definition from axis2.xml
 * @throws AxisFault on error
 */
public void _XXXXX_(ConfigurationContext confContext,TransportOutDescription transportOut) throws AxisFault {
  serverCredentials=new XMPPServerCredentials();
  getConnectionDetailsFromAxisConfiguration(transportOut);
  defaultConnectionFactory=new XMPPConnectionFactory();
}