public void createOrUpdateConnectionServiceContact(int id,String firstName,String lastName){
  if (!hasContactsPermission())   return;
  try {
    ContentResolver resolver=ApplicationLoader.applicationContext.getContentResolver();
    ArrayList<ContentProviderOperation> ops=new ArrayList<>();
    final Uri groupsURI=ContactsContract.Groups.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER,"true").build();
    final Uri rawContactsURI=ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER,"true").build();
    Cursor cursor=resolver.query(groupsURI,new String[]{ContactsContract.Groups._ID},ContactsContract.Groups.TITLE + "=? AND " + ContactsContract.Groups.ACCOUNT_TYPE + "=? AND " + ContactsContract.Groups.ACCOUNT_NAME + "=?",new String[]{"TelegramConnectionService",systemAccount.type,systemAccount.name},null);
    int groupID;
    if (cursor != null && cursor.moveToFirst()) {
      groupID=cursor.getInt(0);
    }
 else {
      ContentValues values=new ContentValues();
      values.put(ContactsContract.Groups.ACCOUNT_TYPE,systemAccount.type);
      values.put(ContactsContract.Groups.ACCOUNT_NAME,systemAccount.name);
      values.put(ContactsContract.Groups.GROUP_VISIBLE,0);
      values.put(ContactsContract.Groups.GROUP_IS_READ_ONLY,1);
      values.put(ContactsContract.Groups.TITLE,"TelegramConnectionService");
      Uri res=resolver.insert(groupsURI,values);
      groupID=Integer.parseInt(res.getLastPathSegment());
    }
    if (cursor != null)     cursor.close();
    cursor=resolver.query(ContactsContract.Data.CONTENT_URI,new String[]{ContactsContract.Data.RAW_CONTACT_ID},ContactsContract.Data.MIMETYPE + "=? AND " + ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID + "=?",new String[]{ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE,groupID + ""},null);
    int backRef=ops.size();
    if (cursor != null && cursor.moveToFirst()) {
      int contactID=cursor.getInt(0);
      ops.add(ContentProviderOperation.newUpdate(rawContactsURI).withSelection(ContactsContract.RawContacts._ID + "=?",new String[]{contactID + ""}).withValue(ContactsContract.RawContacts.DELETED,0).build());
      ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(ContactsContract.Data.RAW_CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + "=?",new String[]{contactID + "",ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE}).withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,"+99084" + id).build());
      ops.add(ContentProviderOperation.newUpdate(ContactsContract.Data.CONTENT_URI).withSelection(ContactsContract.Data.RAW_CONTACT_ID + "=? AND " + ContactsContract.Data.MIMETYPE + "=?",new String[]{contactID + "",ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE}).withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,firstName).withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,lastName).build());
    }
 else {
      ops.add(ContentProviderOperation.newInsert(rawContactsURI).withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,systemAccount.type).withValue(ContactsContract.RawContacts.ACCOUNT_NAME,systemAccount.name).withValue(ContactsContract.RawContacts.RAW_CONTACT_IS_READ_ONLY,1).withValue(ContactsContract.RawContacts.AGGREGATION_MODE,ContactsContract.RawContacts.AGGREGATION_MODE_DISABLED).build());
      ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,backRef).withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,firstName).withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,lastName).build());
      ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,backRef).withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.Phone.NUMBER,"+99084" + id).build());
      ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI).withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,backRef).withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.GroupMembership.CONTENT_ITEM_TYPE).withValue(ContactsContract.CommonDataKinds.GroupMembership.GROUP_ROW_ID,groupID).build());
    }
    if (cursor != null)     cursor.close();
    resolver.applyBatch(ContactsContract.AUTHORITY,ops);
  }
 catch (  Exception x) {
    FileLog.e(x);
  }
}
