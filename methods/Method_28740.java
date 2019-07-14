/** 
 * Set the the respective keys to the respective values.  {@link #mset(String) MSET} willreplace old values with new values, while MSETNX will not perform any operation at all even if just a single key already exists. <p> Because of this semantic MSETNX can be used in order to set different keys representing different fields of an unique logic object in a way that ensures that either all the fields or none at all are set. <p> Both MSET and MSETNX are atomic operations. This means that for instance if the keys A and B are modified, another client talking to Redis can either see the changes to both A and B at once, or no modification at all.
 * @see #mset(String)
 * @param keysvalues
 * @return Integer reply, specifically: 1 if the all the keys were set 0 if no key was set (atleast one key already existed)
 */
@Override public Long msetnx(final String... keysvalues){
  checkIsInMultiOrPipeline();
  client.msetnx(keysvalues);
  return client.getIntegerReply();
}
