private void updateUnregisteredContacts(){
  final HashMap<String,TLRPC.TL_contact> contactsPhonesShort=new HashMap<>();
  for (int a=0, size=contacts.size(); a < size; a++) {
    TLRPC.TL_contact value=contacts.get(a);
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(value.user_id);
    if (user == null || TextUtils.isEmpty(user.phone)) {
      continue;
    }
    contactsPhonesShort.put(user.phone,value);
  }
  final ArrayList<Contact> sortedPhoneBookContacts=new ArrayList<>();
  for (  HashMap.Entry<String,Contact> pair : contactsBook.entrySet()) {
    Contact value=pair.getValue();
    boolean skip=false;
    for (int a=0; a < value.phones.size(); a++) {
      String sphone=value.shortPhones.get(a);
      if (contactsPhonesShort.containsKey(sphone) || value.phoneDeleted.get(a) == 1) {
        skip=true;
        break;
      }
    }
    if (skip) {
      continue;
    }
    sortedPhoneBookContacts.add(value);
  }
  Collections.sort(sortedPhoneBookContacts,(contact,contact2) -> {
    String toComapre1=contact.first_name;
    if (toComapre1.length() == 0) {
      toComapre1=contact.last_name;
    }
    String toComapre2=contact2.first_name;
    if (toComapre2.length() == 0) {
      toComapre2=contact2.last_name;
    }
    return toComapre1.compareTo(toComapre2);
  }
);
  phoneBookContacts=sortedPhoneBookContacts;
}
