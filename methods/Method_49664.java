private void saveCodeAsContact(String code){
  Intent intent=new Intent(Intent.ACTION_INSERT_OR_EDIT);
  intent.setType(Contacts.CONTENT_ITEM_TYPE);
  intent.putExtra(Insert.POSTAL,code);
  mView.getContext().startActivity(intent);
}
