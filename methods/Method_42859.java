/** 
 * Helper method that performs a null check. SignatureCreator is null if no API key is provided.
 * @return The timestamp as maintained by the signature creator.
 */
protected String timestamp(){
  return String.valueOf((System.currentTimeMillis() / 1000) + timeDiffFromServer);
}
