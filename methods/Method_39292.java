/** 
 * The main email receiving method.
 */
ReceivedEmail[] receiveMessages(final EmailFilter filter,final Flags flagsToSet,final Flags flagsToUnset,final boolean envelope,final Consumer<Message[]> processedMessageConsumer){
  useAndOpenFolderIfNotSet();
  final Message[] messages;
  try {
    if (filter == null) {
      messages=folder.getMessages();
    }
 else {
      messages=folder.search(filter.getSearchTerm());
    }
    if (messages.length == 0) {
      return ReceivedEmail.EMPTY_ARRAY;
    }
    if (envelope) {
      final FetchProfile fetchProfile=new FetchProfile();
      fetchProfile.add(FetchProfile.Item.ENVELOPE);
      fetchProfile.add(FetchProfile.Item.FLAGS);
      folder.fetch(messages,fetchProfile);
    }
    final ReceivedEmail[] emails=new ReceivedEmail[messages.length];
    for (int i=0; i < messages.length; i++) {
      final Message msg=messages[i];
      emails[i]=new ReceivedEmail(msg,envelope,attachmentStorage);
      if (!EmailUtil.isEmptyFlags(flagsToSet)) {
        emails[i].flags(flagsToSet);
        msg.setFlags(flagsToSet,true);
      }
      if (!EmailUtil.isEmptyFlags(flagsToUnset)) {
        emails[i].flags().remove(flagsToUnset);
        msg.setFlags(flagsToUnset,false);
      }
      if (EmailUtil.isEmptyFlags(flagsToSet) && !emails[i].isSeen()) {
        msg.setFlag(Flags.Flag.SEEN,false);
      }
    }
    if (processedMessageConsumer != null) {
      processedMessageConsumer.accept(messages);
    }
    if (!EmailUtil.isEmptyFlags(flagsToSet)) {
      if (flagsToSet.contains(Flags.Flag.DELETED)) {
        folder.expunge();
      }
    }
    return emails;
  }
 catch (  final MessagingException msgexc) {
    throw new MailException("Failed to fetch messages",msgexc);
  }
}
