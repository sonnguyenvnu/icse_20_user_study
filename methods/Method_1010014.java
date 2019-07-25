public void replace(Set<User> newUsers){
synchronized (mLock) {
    String ourLogin=Gh4Application.get().getAuthLogin();
    mOriginalUsers.clear();
    for (    User user : newUsers) {
      if (!TextUtils.equals(ourLogin,user.login())) {
        mOriginalUsers.add(user);
      }
    }
    Collections.sort(mOriginalUsers,(first,second) -> {
      final String firstUsername=ApiHelpers.getUserLogin(mContext,first);
      final String secondUsername=ApiHelpers.getUserLogin(mContext,second);
      return firstUsername.compareToIgnoreCase(secondUsername);
    }
);
  }
  notifyDataSetChanged();
}
