@SuppressWarnings("unchecked") protected PK createParentIdOnExists(){
  if (getPrimaryKeyType() == String.class) {
    return (PK)"-1";
  }
  return null;
}
