private void performWriteContactsToPhoneBookInternal(ArrayList<TLRPC.TL_contact> contactsArray){
  Cursor cursor=null;
  try {
    if (!hasContactsPermission()) {
      return;
    }
    Uri rawContactUri=ContactsContract.RawContacts.CONTENT_URI.buildUpon().appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_NAME,systemAccount.name).appendQueryParameter(ContactsContract.RawContacts.ACCOUNT_TYPE,systemAccount.type).build();
    cursor=ApplicationLoader.applicationContext.getContentResolver().query(rawContactUri,new String[]{BaseColumns._ID,ContactsContract.RawContacts.SYNC2},null,null,null);
    SparseLongArray bookContacts=new SparseLongArray();
    if (cursor != null) {
      while (cursor.moveToNext()) {
        bookContacts.put(cursor.getInt(1),cursor.getLong(0));
      }
      cursor.close();
      cursor=null;
      for (int a=0; a < contactsArray.size(); a++) {
        TLRPC.TL_contact u=contactsArray.get(a);
        if (bookContacts.indexOfKey(u.user_id) < 0) {
          TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(u.user_id);
          addContactToPhoneBook(user,false);
        }
      }
    }
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
 finally {
    if (cursor != null) {
      cursor.close();
    }
  }
}
