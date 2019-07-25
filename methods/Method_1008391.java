/** 
 * Returns a new copy of this object that is backed by its own char array. Closing the new instance has no effect on the instance it was created from. This is useful for APIs which accept a char array and you want to be safe about the API potentially modifying the char array. For example: <pre> try (SecureString copy = secureString.clone()) { // pass thee char[] to a external API PasswordAuthentication auth = new PasswordAuthentication(username, copy.getChars()); ... } </pre>
 */
@Override public synchronized SecureString clone(){
  ensureNotClosed();
  return new SecureString(Arrays.copyOf(chars,chars.length));
}
