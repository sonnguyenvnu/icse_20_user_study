default AuthenticationPredicate and(String permissionString){
  return and(has(permissionString));
}
