@Override public ReceivedEmail clone(){
  return create().originalMessage(originalMessage()).flags(flags()).messageNumber(messageNumber()).messageId(messageId()).from(from()).replyTo(replyTo()).to(to()).cc(cc()).subject(subject(),subjectEncoding()).receivedDate(receivedDate()).sentDate(sentDate()).headers(headers()).message(messages()).storeAttachments(attachments()).attachedMessages(attachedMessages());
}
