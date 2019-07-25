protected int find(Address mbr){
  if (mbr == null || members == null)   return -1;
  for (int i=0; i < members.length; i++) {
    Address member=members[i];
    if (Objects.equals(member,mbr))     return i;
  }
  return -1;
}
