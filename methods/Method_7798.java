@Override public void onBindViewHolder(int section,int position,RecyclerView.ViewHolder holder){
switch (holder.getItemViewType()) {
case 0:
    UserCell userCell=(UserCell)holder.itemView;
  Object object=getItem(section,position);
TLRPC.User user=null;
if (object instanceof ContactsController.Contact) {
ContactsController.Contact contact=(ContactsController.Contact)object;
if (contact.user != null) {
  user=contact.user;
}
 else {
  userCell.setCurrentId(contact.contact_id);
  userCell.setData(null,ContactsController.formatName(contact.first_name,contact.last_name),contact.phones.isEmpty() ? "" : PhoneFormat.getInstance().format(contact.phones.get(0)),0);
}
}
 else {
user=(TLRPC.User)object;
}
if (user != null) {
userCell.setData(user,null,PhoneFormat.getInstance().format("+" + user.phone),0);
}
break;
}
}
