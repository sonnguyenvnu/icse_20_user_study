private void processSelectOpenTag(final Tag tag){
  CharSequence name=tag.getAttributeValue(NAME);
  if (name == null) {
    return;
  }
  currentSelectName=name.toString();
  inSelect=true;
}
