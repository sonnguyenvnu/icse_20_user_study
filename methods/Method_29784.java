public static void setUser(Account account,User user){
  String userInfoJson=GsonHelper.GSON.toJson(user,User.class);
  AccountPreferences.forAccount(account).putString(AccountContract.KEY_USER_INFO,userInfoJson);
}
