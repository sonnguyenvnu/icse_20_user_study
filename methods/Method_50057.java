/** 
 * Gets the current thread_id or creates a new one for the given recipient
 * @param context is the context of the activity or service
 * @param recipient is the person message is being sent to
 * @return the thread_id to use in the database
 */
public static long getOrCreateThreadId(Context context,String recipient){
  Set<String> recipients=new HashSet<String>();
  recipients.add(recipient);
  return getOrCreateThreadId(context,recipients);
}
