/** 
 * Returns true if the message should be printed in the given timestamp, otherwise returns false. If this method returns false, the message will not be printed. The timestamp is in seconds granularity. 
 */
public boolean shouldPrintMessage(int timestamp,String message){
  if (messages.containsKey(message)) {
    if (timestamp - messages.get(message) >= 10) {
      messages.put(message,timestamp);
      return true;
    }
 else {
      return false;
    }
  }
 else {
    messages.put(message,timestamp);
    return true;
  }
}
