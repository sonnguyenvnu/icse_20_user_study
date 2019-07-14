public void setRoleDesc(Boolean isMaster){
  if (isMaster == null) {
    roleDesc="??";
  }
 else   if (isMaster) {
    roleDesc="master";
  }
 else {
    roleDesc="slave";
  }
}
