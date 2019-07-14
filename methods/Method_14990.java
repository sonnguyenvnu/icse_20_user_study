public MomentItem setIsPraised(boolean isPraised){
  this.isPraised=isPraised;
  User currentUser=APIJSONApplication.getInstance().getCurrentUser();
  long userId=currentUser == null ? 0 : currentUser.getId();
  List<Long> list=getPraiseUserIdList();
  if (list == null) {
    list=new ArrayList<>();
  }
  if (userList == null) {
    userList=new ArrayList<User>();
  }
  if (isPraised == false) {
    list.remove(userId);
    if (userList.isEmpty() == false) {
      User[] users=userList.toArray(new User[]{});
      for (      User user : users) {
        if (user != null && user.getId() == userId) {
          userList.remove(user);
          break;
        }
      }
    }
  }
 else {
    if (list.contains(userId) == false) {
      list.add(userId);
      userList.add(currentUser);
    }
  }
  getMoment().setPraiseUserIdList(list);
  setPraiseCount(praiseCount + (isPraised ? 1 : -1));
  return this;
}
