/** 
 * Move a PDU object from one location to another.
 * @param from Specify the PDU object to be moved.
 * @param to   The destination location, should be one of the following:"content://mms/inbox", "content://mms/sent", "content://mms/drafts", "content://mms/outbox", "content://mms/trash".
 * @return New Uri of the moved PDU.
 * @throws MmsException Error occurred while moving the message.
 */
public Uri move(Uri from,Uri to) throws MmsException {
  long msgId=ContentUris.parseId(from);
  if (msgId == -1L) {
    throw new MmsException("Error! ID of the message: -1.");
  }
  Integer msgBox=MESSAGE_BOX_MAP.get(to);
  if (msgBox == null) {
    throw new MmsException("Bad destination, must be one of " + "content://mms/inbox, content://mms/sent, " + "content://mms/drafts, content://mms/outbox, " + "content://mms/temp.");
  }
  ContentValues values=new ContentValues(1);
  values.put(Mms.MESSAGE_BOX,msgBox);
  SqliteWrapper.update(mContext,mContentResolver,from,values,null,null);
  return ContentUris.withAppendedId(to,msgId);
}
