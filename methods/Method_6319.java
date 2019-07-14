public long addContactToPhoneBook(TLRPC.User user,boolean check){
  if (systemAccount == null || user == null || TextUtils.isEmpty(user.phone)) {
    return -1;
  }
  if (!hasContactsPermission()) {
    return -1;
  }
  long res=-1;
synchronized (observerLock) {
    ignoreChanges=true;
  }
  ContentResolver contentResolver=ApplicationLoader.applicationContext.getContentResolver();
  if (check) {
    try {
      Uri rawContactUri=ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.CALLER_IS_SYNCADAPTER,"true").appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME,systemAccount.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE,systemAccount.type).build();
      int value=contentResolver.delete(rawContactUri,ContactsContract.RawContacts.SYNC2 + " = " + user.id,null);
    }
 catch (    Exception ignore) {
    }
  }
  ArrayList<ContentProviderOperation> query=new ArrayList<>();
  ContentProviderOperation.Builder builder=ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI);
  builder.withValue(ContactsContract.RawContacts.ACCOUNT_NAME,systemAccount.name);
  builder.withValue(ContactsContract.RawContacts.ACCOUNT_TYPE,systemAccount.type);
  builder.withValue(ContactsContract.RawContacts.SYNC1,user.phone);
  builder.withValue(ContactsContract.RawContacts.SYNC2,user.id);
  query.add(builder.build());
  builder=ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
  builder.withValueBackReference(ContactsContract.CommonDataKinds.StructuredName.RAW_CONTACT_ID,0);
  builder.withValue(ContactsContract.Data.MIMETYPE,ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
  builder.withValue(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME,user.first_name);
  builder.withValue(ContactsContract.CommonDataKinds.StructuredName.FAMILY_NAME,user.last_name);
  query.add(builder.build());
  builder=ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI);
  builder.withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,0);
  builder.withValue(ContactsContract.Data.MIMETYPE,"vnd.android.cursor.item/vnd.org.telegram.messenger.android.profile");
  builder.withValue(ContactsContract.Data.DATA1,user.id);
  builder.withValue(ContactsContract.Data.DATA2,"Telegram Profile");
  builder.withValue(ContactsContract.Data.DATA3,"+" + user.phone);
  builder.withValue(ContactsContract.Data.DATA4,user.id);
  query.add(builder.build());
  try {
    ContentProviderResult[] result=contentResolver.applyBatch(ContactsContract.AUTHORITY,query);
    if (result != null && result.length > 0 && result[0].uri != null) {
      res=Long.parseLong(result[0].uri.getLastPathSegment());
    }
  }
 catch (  Exception ignore) {
  }
synchronized (observerLock) {
    ignoreChanges=false;
  }
  return res;
}
