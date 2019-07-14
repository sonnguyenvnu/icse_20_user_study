private HashMap<String,Contact> readContactsFromPhoneBook(){
  if (!UserConfig.getInstance(currentAccount).syncContacts) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("contacts sync disabled");
    }
    return new HashMap<>();
  }
  if (!hasContactsPermission()) {
    if (BuildVars.LOGS_ENABLED) {
      FileLog.d("app has no contacts permissions");
    }
    return new HashMap<>();
  }
  Cursor pCur=null;
  HashMap<String,Contact> contactsMap=null;
  try {
    StringBuilder escaper=new StringBuilder();
    ContentResolver cr=ApplicationLoader.applicationContext.getContentResolver();
    HashMap<String,Contact> shortContacts=new HashMap<>();
    ArrayList<String> idsArr=new ArrayList<>();
    pCur=cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,projectionPhones,null,null,null);
    int lastContactId=1;
    if (pCur != null) {
      int count=pCur.getCount();
      if (count > 0) {
        if (contactsMap == null) {
          contactsMap=new HashMap<>(count);
        }
        while (pCur.moveToNext()) {
          String number=pCur.getString(1);
          String accountType=pCur.getString(5);
          if (accountType == null) {
            accountType="";
          }
          boolean isGoodAccountType=accountType.indexOf(".sim") != 0;
          if (TextUtils.isEmpty(number)) {
            continue;
          }
          number=PhoneFormat.stripExceptNumbers(number,true);
          if (TextUtils.isEmpty(number)) {
            continue;
          }
          String shortNumber=number;
          if (number.startsWith("+")) {
            shortNumber=number.substring(1);
          }
          String lookup_key=pCur.getString(0);
          escaper.setLength(0);
          DatabaseUtils.appendEscapedSQLString(escaper,lookup_key);
          String key=escaper.toString();
          Contact existingContact=shortContacts.get(shortNumber);
          if (existingContact != null) {
            if (!existingContact.isGoodProvider && !accountType.equals(existingContact.provider)) {
              escaper.setLength(0);
              DatabaseUtils.appendEscapedSQLString(escaper,existingContact.key);
              idsArr.remove(escaper.toString());
              idsArr.add(key);
              existingContact.key=lookup_key;
              existingContact.isGoodProvider=isGoodAccountType;
              existingContact.provider=accountType;
            }
            continue;
          }
          if (!idsArr.contains(key)) {
            idsArr.add(key);
          }
          int type=pCur.getInt(2);
          Contact contact=contactsMap.get(lookup_key);
          if (contact == null) {
            contact=new Contact();
            String displayName=pCur.getString(4);
            if (displayName == null) {
              displayName="";
            }
 else {
              displayName=displayName.trim();
            }
            if (isNotValidNameString(displayName)) {
              contact.first_name=displayName;
              contact.last_name="";
            }
 else {
              int spaceIndex=displayName.lastIndexOf(' ');
              if (spaceIndex != -1) {
                contact.first_name=displayName.substring(0,spaceIndex).trim();
                contact.last_name=displayName.substring(spaceIndex + 1).trim();
              }
 else {
                contact.first_name=displayName;
                contact.last_name="";
              }
            }
            contact.provider=accountType;
            contact.isGoodProvider=isGoodAccountType;
            contact.key=lookup_key;
            contact.contact_id=lastContactId++;
            contactsMap.put(lookup_key,contact);
          }
          contact.shortPhones.add(shortNumber);
          contact.phones.add(number);
          contact.phoneDeleted.add(0);
          if (type == ContactsContract.CommonDataKinds.Phone.TYPE_CUSTOM) {
            String custom=pCur.getString(3);
            contact.phoneTypes.add(custom != null ? custom : LocaleController.getString("PhoneMobile",R.string.PhoneMobile));
          }
 else           if (type == ContactsContract.CommonDataKinds.Phone.TYPE_HOME) {
            contact.phoneTypes.add(LocaleController.getString("PhoneHome",R.string.PhoneHome));
          }
 else           if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE) {
            contact.phoneTypes.add(LocaleController.getString("PhoneMobile",R.string.PhoneMobile));
          }
 else           if (type == ContactsContract.CommonDataKinds.Phone.TYPE_WORK) {
            contact.phoneTypes.add(LocaleController.getString("PhoneWork",R.string.PhoneWork));
          }
 else           if (type == ContactsContract.CommonDataKinds.Phone.TYPE_MAIN) {
            contact.phoneTypes.add(LocaleController.getString("PhoneMain",R.string.PhoneMain));
          }
 else {
            contact.phoneTypes.add(LocaleController.getString("PhoneOther",R.string.PhoneOther));
          }
          shortContacts.put(shortNumber,contact);
        }
      }
      try {
        pCur.close();
      }
 catch (      Exception ignore) {
      }
      pCur=null;
    }
    String ids=TextUtils.join(",",idsArr);
    pCur=cr.query(ContactsContract.Data.CONTENT_URI,projectionNames,ContactsContract.CommonDataKinds.StructuredName.LOOKUP_KEY + " IN (" + ids + ") AND " + ContactsContract.Data.MIMETYPE + " = '" + ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE + "'",null,null);
    if (pCur != null) {
      while (pCur.moveToNext()) {
        String lookup_key=pCur.getString(0);
        String fname=pCur.getString(1);
        String sname=pCur.getString(2);
        String mname=pCur.getString(3);
        Contact contact=contactsMap.get(lookup_key);
        if (contact != null && !contact.namesFilled) {
          if (contact.isGoodProvider) {
            if (fname != null) {
              contact.first_name=fname;
            }
 else {
              contact.first_name="";
            }
            if (sname != null) {
              contact.last_name=sname;
            }
 else {
              contact.last_name="";
            }
            if (!TextUtils.isEmpty(mname)) {
              if (!TextUtils.isEmpty(contact.first_name)) {
                contact.first_name+=" " + mname;
              }
 else {
                contact.first_name=mname;
              }
            }
          }
 else {
            if (!isNotValidNameString(fname) && (contact.first_name.contains(fname) || fname.contains(contact.first_name)) || !isNotValidNameString(sname) && (contact.last_name.contains(sname) || fname.contains(contact.last_name))) {
              if (fname != null) {
                contact.first_name=fname;
              }
 else {
                contact.first_name="";
              }
              if (!TextUtils.isEmpty(mname)) {
                if (!TextUtils.isEmpty(contact.first_name)) {
                  contact.first_name+=" " + mname;
                }
 else {
                  contact.first_name=mname;
                }
              }
              if (sname != null) {
                contact.last_name=sname;
              }
 else {
                contact.last_name="";
              }
            }
          }
          contact.namesFilled=true;
        }
      }
      try {
        pCur.close();
      }
 catch (      Exception ignore) {
      }
      pCur=null;
    }
  }
 catch (  Throwable e) {
    FileLog.e(e);
    if (contactsMap != null) {
      contactsMap.clear();
    }
  }
 finally {
    try {
      if (pCur != null) {
        pCur.close();
      }
    }
 catch (    Exception e) {
      FileLog.e(e);
    }
  }
  return contactsMap != null ? contactsMap : new HashMap<>();
}
