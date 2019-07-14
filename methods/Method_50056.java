/** 
 * Gets the number of pages in the SMS based on settings and the length of string
 * @param settings is the settings object to check against
 * @param text     is the text from the message object to be sent
 * @return the number of pages required to hold message
 */
public static int getNumPages(Settings settings,String text){
  if (settings.getStripUnicode()) {
    text=StripAccents.stripAccents(text);
  }
  int[] data=SmsMessage.calculateLength(text,false);
  return data[0];
}
