/** 
 * Returns email store.
 * @return {@link com.sun.mail.pop3.POP3Store}
 * @throws NoSuchProviderException If a provider for the given protocol is not found.
 */
protected Store getStore(final Session session) throws NoSuchProviderException {
  return session.getStore(PROTOCOL_POP3);
}
