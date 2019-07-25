@Override public String serialize(){
  return REF_PREFIX + ID_DELIM + myRoleId.serialize() + ID_DELIM + getRoleName();
}
