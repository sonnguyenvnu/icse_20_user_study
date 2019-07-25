@Override public String serialize(){
  return LINK_PREFIX + ID_DELIM + myRoleId.serialize() + ID_DELIM + getRoleName();
}
