private static void demoUsers(CoinbaseAccountService accountService) throws IOException {
  CoinbaseUsers users=accountService.getCoinbaseUsers();
  System.out.println("Current User: " + users);
  CoinbaseUser user=users.getUsers().get(0);
  user.updateTimeZone("Tijuana").updateNativeCurrency("MXN");
  user=accountService.updateCoinbaseUser(user);
  System.out.println("Updated User: " + user);
  CoinbaseUser newUser=CoinbaseUser.createCoinbaseNewUserWithReferrerId("demo@demo.com","pass1234","527d2a1ffedcb8b73b000028");
  String oauthClientId="";
  CoinbaseUser createdUser=accountService.createCoinbaseUser(newUser,oauthClientId);
  System.out.println("Newly created user: " + createdUser);
}
