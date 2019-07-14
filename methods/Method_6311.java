private void mergePhonebookAndTelegramContacts(final HashMap<String,ArrayList<Object>> phoneBookSectionsDictFinal,final ArrayList<String> phoneBookSectionsArrayFinal,final HashMap<String,Contact> phoneBookByShortPhonesFinal){
  final ArrayList<TLRPC.TL_contact> contactsCopy=new ArrayList<>(contacts);
  Utilities.globalQueue.postRunnable(() -> {
    for (int a=0, size=contactsCopy.size(); a < size; a++) {
      TLRPC.TL_contact value=contactsCopy.get(a);
      TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(value.user_id);
      if (user == null || TextUtils.isEmpty(user.phone)) {
        continue;
      }
      String phone=user.phone.substring(Math.max(0,user.phone.length() - 7));
      Contact contact=phoneBookByShortPhonesFinal.get(phone);
      if (contact != null) {
        if (contact.user == null) {
          contact.user=user;
        }
      }
 else {
        String key=Contact.getLetter(user.first_name,user.last_name);
        ArrayList<Object> arrayList=phoneBookSectionsDictFinal.get(key);
        if (arrayList == null) {
          arrayList=new ArrayList<>();
          phoneBookSectionsDictFinal.put(key,arrayList);
          phoneBookSectionsArrayFinal.add(key);
        }
        arrayList.add(user);
      }
    }
    for (    ArrayList<Object> arrayList : phoneBookSectionsDictFinal.values()) {
      Collections.sort(arrayList,(o1,o2) -> {
        String name1;
        String name2;
        if (o1 instanceof TLRPC.User) {
          TLRPC.User user=(TLRPC.User)o1;
          name1=ContactsController.formatName(user.first_name,user.last_name);
        }
 else         if (o1 instanceof Contact) {
          Contact contact=(Contact)o1;
          if (contact.user != null) {
            name1=ContactsController.formatName(contact.user.first_name,contact.user.last_name);
          }
 else {
            name1=ContactsController.formatName(contact.first_name,contact.last_name);
          }
        }
 else {
          name1="";
        }
        if (o2 instanceof TLRPC.User) {
          TLRPC.User user=(TLRPC.User)o2;
          name2=ContactsController.formatName(user.first_name,user.last_name);
        }
 else         if (o2 instanceof Contact) {
          Contact contact=(Contact)o2;
          if (contact.user != null) {
            name2=ContactsController.formatName(contact.user.first_name,contact.user.last_name);
          }
 else {
            name2=ContactsController.formatName(contact.first_name,contact.last_name);
          }
        }
 else {
          name2="";
        }
        return name1.compareTo(name2);
      }
);
    }
    Collections.sort(phoneBookSectionsArrayFinal,(s,s2) -> {
      char cv1=s.charAt(0);
      char cv2=s2.charAt(0);
      if (cv1 == '#') {
        return 1;
      }
 else       if (cv2 == '#') {
        return -1;
      }
      return s.compareTo(s2);
    }
);
    AndroidUtilities.runOnUIThread(() -> {
      phoneBookSectionsArray=phoneBookSectionsArrayFinal;
      phoneBookSectionsDict=phoneBookSectionsDictFinal;
    }
);
  }
);
}
