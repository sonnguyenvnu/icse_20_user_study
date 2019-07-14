public HashMap<String,Contact> getContactsCopy(HashMap<String,Contact> original){
  HashMap<String,Contact> ret=new HashMap<>();
  for (  HashMap.Entry<String,Contact> entry : original.entrySet()) {
    Contact copyContact=new Contact();
    Contact originalContact=entry.getValue();
    copyContact.phoneDeleted.addAll(originalContact.phoneDeleted);
    copyContact.phones.addAll(originalContact.phones);
    copyContact.phoneTypes.addAll(originalContact.phoneTypes);
    copyContact.shortPhones.addAll(originalContact.shortPhones);
    copyContact.first_name=originalContact.first_name;
    copyContact.last_name=originalContact.last_name;
    copyContact.contact_id=originalContact.contact_id;
    copyContact.key=originalContact.key;
    ret.put(copyContact.key,copyContact);
  }
  return ret;
}
