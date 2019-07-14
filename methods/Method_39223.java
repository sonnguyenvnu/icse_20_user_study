@Override public Email clone(){
  return create().from(from()).replyTo(replyTo()).to(to()).cc(cc()).bcc(bcc()).subject(subject(),subjectEncoding()).sentDate(sentDate()).headers(headers()).storeAttachments(attachments()).message(messages());
}
