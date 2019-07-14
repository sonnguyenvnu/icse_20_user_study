private void usage(MemberNode use,MemberNode user){
  use.addUser(user);
  user.addUse(use);
}
