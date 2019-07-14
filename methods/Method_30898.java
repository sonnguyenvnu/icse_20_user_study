private static UserResource newInstance(String userIdOrUid,SimpleUser simpleUser,User user){
  return new UserResource().setArguments(userIdOrUid,simpleUser,user);
}
