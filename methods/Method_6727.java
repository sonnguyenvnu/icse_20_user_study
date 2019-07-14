public CharSequence replaceWithLink(CharSequence source,String param,ArrayList<Integer> uids,AbstractMap<Integer,TLRPC.User> usersDict,SparseArray<TLRPC.User> sUsersDict){
  int start=TextUtils.indexOf(source,param);
  if (start >= 0) {
    SpannableStringBuilder names=new SpannableStringBuilder("");
    for (int a=0; a < uids.size(); a++) {
      TLRPC.User user=null;
      if (usersDict != null) {
        user=usersDict.get(uids.get(a));
      }
 else       if (sUsersDict != null) {
        user=sUsersDict.get(uids.get(a));
      }
      if (user == null) {
        user=MessagesController.getInstance(currentAccount).getUser(uids.get(a));
      }
      if (user != null) {
        String name=UserObject.getUserName(user);
        start=names.length();
        if (names.length() != 0) {
          names.append(", ");
        }
        names.append(name);
        names.setSpan(new URLSpanNoUnderlineBold("" + user.id),start,start + name.length(),Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }
    return TextUtils.replace(source,new String[]{param},new CharSequence[]{names});
  }
  return source;
}
