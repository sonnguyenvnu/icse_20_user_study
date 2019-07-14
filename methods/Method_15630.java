static AuthenticationPredicate has(String permissionString){
  return AuthenticationUtils.createPredicate(permissionString);
}
