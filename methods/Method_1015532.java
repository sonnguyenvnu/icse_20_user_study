public Iterator<Address> iterator(){
  Address[] combined=new Address[left_members.length + new_members.length];
  int left_len=left_members.length;
  System.arraycopy(left_members,0,combined,0,left_len);
  System.arraycopy(new_members,0,combined,left_len,new_members.length);
  return new ArrayIterator<>(combined);
}
