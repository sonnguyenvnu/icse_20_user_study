public void trackViewedMailbox(final @NonNull Mailbox mailbox,final @Nullable Project project,final @Nullable RefTag intentRefTag,final @NonNull KoalaContext.Mailbox context){
  final Map<String,Object> props=project == null ? new HashMap<>() : KoalaUtils.projectProperties(project,this.client.loggedInUser());
  props.put("context",context.getTrackingString());
  if (intentRefTag != null) {
    props.put("ref_tag",intentRefTag.tag());
  }
switch (mailbox) {
case INBOX:
    this.client.track(KoalaEvent.VIEWED_MESSAGE_INBOX,props);
  break;
case SENT:
this.client.track(KoalaEvent.VIEWED_SENT_MESSAGES,props);
break;
}
}
