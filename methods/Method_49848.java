/** 
 * This function returns the value of "enabledTransID" present in mms_config file. In case of single segment wap push message, this "enabledTransID" indicates whether TransactionID should be appended to URI or not.
 */
public static boolean getTransIdEnabled(){
  return mTransIdEnabled;
}
