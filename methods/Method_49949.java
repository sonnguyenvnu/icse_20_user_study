private String getMessage(Uri uri) throws MmsException {
  NotificationInd ind=(NotificationInd)PduPersister.getPduPersister(mContext).load(uri);
  EncodedStringValue v=ind.getSubject();
  String subject=(v != null) ? v.getString() : mContext.getString(R.string.no_subject);
  String from=mContext.getString(R.string.unknown_sender);
  return mContext.getString(R.string.dl_failure_notification,subject,from);
}
