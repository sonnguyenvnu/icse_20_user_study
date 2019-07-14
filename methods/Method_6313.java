private void buildContactsSectionsArrays(boolean sort){
  if (sort) {
    Collections.sort(contacts,(tl_contact,tl_contact2) -> {
      TLRPC.User user1=MessagesController.getInstance(currentAccount).getUser(tl_contact.user_id);
      TLRPC.User user2=MessagesController.getInstance(currentAccount).getUser(tl_contact2.user_id);
      String name1=UserObject.getFirstName(user1);
      String name2=UserObject.getFirstName(user2);
      return name1.compareTo(name2);
    }
);
  }
  final HashMap<String,ArrayList<TLRPC.TL_contact>> sectionsDict=new HashMap<>();
  final ArrayList<String> sortedSectionsArray=new ArrayList<>();
  for (int a=0; a < contacts.size(); a++) {
    TLRPC.TL_contact value=contacts.get(a);
    TLRPC.User user=MessagesController.getInstance(currentAccount).getUser(value.user_id);
    if (user == null) {
      continue;
    }
    String key=UserObject.getFirstName(user);
    if (key.length() > 1) {
      key=key.substring(0,1);
    }
    if (key.length() == 0) {
      key="#";
    }
 else {
      key=key.toUpperCase();
    }
    String replace=sectionsToReplace.get(key);
    if (replace != null) {
      key=replace;
    }
    ArrayList<TLRPC.TL_contact> arr=sectionsDict.get(key);
    if (arr == null) {
      arr=new ArrayList<>();
      sectionsDict.put(key,arr);
      sortedSectionsArray.add(key);
    }
    arr.add(value);
  }
  Collections.sort(sortedSectionsArray,(s,s2) -> {
    char cv1=s.charAt(0);
    char cv2=s2.charAt(0);
    if (cv1 == '#') {
      return 1;
    }
 else     if (cv2 == '#') {
      return -1;
    }
    return s.compareTo(s2);
  }
);
  usersSectionsDict=sectionsDict;
  sortedUsersSectionsArray=sortedSectionsArray;
}
