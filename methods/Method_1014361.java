/** 
 * Transmits the button press for the specified remote
 * @param remote Name of the remote
 * @param button Button to press
 * @param timesToSend Number of times to transmit the button
 */
public void transmit(String remote,String button,int timesToSend){
  sendCommand(String.format("SEND_ONCE %s %s %s",remote,button,timesToSend - 1));
}
