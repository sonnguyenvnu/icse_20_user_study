/** 
 * Checks whether a subscription with a given id (still) exists.
 * @param subscriptionId the id of the subscription to check
 * @return true, if it exists, false otherwise
 */
public boolean exists(String subscriptionId){
  return callbacks.containsKey(subscriptionId);
}
