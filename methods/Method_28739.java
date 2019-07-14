/** 
 * Return a randomly selected key from the currently selected DB. <p> Time complexity: O(1)
 * @return Singe line reply, specifically the randomly selected key or an empty string is thedatabase is empty
 */
@Override public String randomKey(){
  checkIsInMultiOrPipeline();
  client.randomKey();
  return client.getBulkReply();
}
