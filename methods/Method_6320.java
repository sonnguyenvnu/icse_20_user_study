protected void markAsContacted(final String contactId){
  if (contactId == null) {
    return;
  }
  Utilities.phoneBookQueue.postRunnable(() -> {
    Uri uri=Uri.parse(contactId);
    ContentValues values=new ContentValues();
    values.put(ContactsContract.Contacts.LAST_TIME_CONTACTED,System.currentTimeMillis());
    ContentResolver cr=ApplicationLoader.applicationContext.getContentResolver();
    cr.update(uri,values,null,null);
  }
);
}
