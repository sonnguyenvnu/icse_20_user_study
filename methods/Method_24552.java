/** 
 * @generate Serial_list.xml
 * @webref serial
 * @usage web_application
 */
public static String[] list(){
  return SerialPortList.getPortNames();
}
