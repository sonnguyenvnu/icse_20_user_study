/** 
 * @param uri The Uri to be normalized.
 * @return Uri The normalized key of cached entry.
 */
private Uri normalizeKey(Uri uri){
  int match=URI_MATCHER.match(uri);
  Uri normalizedKey=null;
switch (match) {
case MMS_ALL_ID:
    normalizedKey=uri;
  break;
case MMS_INBOX_ID:
case MMS_SENT_ID:
case MMS_DRAFTS_ID:
case MMS_OUTBOX_ID:
String msgId=uri.getLastPathSegment();
normalizedKey=Uri.withAppendedPath(Mms.CONTENT_URI,msgId);
break;
default :
return null;
}
if (LOCAL_LOGV) {
Timber.v(uri + " -> " + normalizedKey);
}
return normalizedKey;
}
