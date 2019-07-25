/** 
 * Transmits the button press for the specified remote.
 * @param remote Name of the remote
 * @param button Button to press
 */
public void transmit(String remote,String button){
  int timesToSend=1;
  String buttonName;
  String[] parts=button.split(" ");
  if (parts.length > 1) {
    buttonName=parts[0];
    timesToSend=Integer.parseInt(parts[1]);
  }
 else {
    buttonName=button;
  }
  transmit(remote,buttonName,timesToSend);
}
