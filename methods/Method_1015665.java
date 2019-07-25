public boolean contains(Address mbr){
  if (mbr == null || members == null)   return false;
  for (  Address member : members)   if (Objects.equals(member,mbr))   return true;
  return false;
}
