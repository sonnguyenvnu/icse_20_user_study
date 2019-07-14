/** 
 * Set the listener to be invoked when Profile links are tapped.
 */
public void setupListeners(ContactListener listener,String mail,ArrayList<Contact> contacts){
  devContacts.removeAllViews();
  if (mail != null) {
    ContactButton email=new ContactButton(getContext());
    email.setText(getContext().getString(R.string.send_email));
    email.bold();
    email.setOnClickListener(v -> listener.onMailClicked(mail));
    devContacts.addView(email);
  }
  for (  Contact contact : contacts) {
    ContactButton c=new ContactButton(getContext());
    c.setText(contact.getLabel());
    c.setOnClickListener(v -> listener.onContactClicked(contact));
    devContacts.addView(c);
  }
}
