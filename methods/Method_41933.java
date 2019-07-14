@Override public void onContactClicked(Contact contact){
  chromeTabs.launchUrl(contact.getValue());
}
