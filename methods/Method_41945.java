public void load(Contributor contributor,ContactListener listener){
  setName(contributor.getName());
  setDescription(contributor.getDescription());
  setProfileImage(contributor.getProfileImage());
  contribContacts.removeAllViews();
  if (contributor.getEmail() != null) {
    ContactButton email=new ContactButton(itemView.getContext());
    email.setText(itemView.getContext().getString(R.string.send_email));
    email.bold();
    email.setOnClickListener(v -> listener.onMailClicked(contributor.getEmail()));
    contribContacts.addView(email);
  }
  ArrayList<Contact> contacts=contributor.getContacts();
  for (  Contact contact : contacts) {
    ContactButton c=new ContactButton(itemView.getContext());
    c.setText(contact.getLabel());
    c.setOnClickListener(v -> listener.onContactClicked(contact));
    contribContacts.addView(c);
  }
}
