public static void main(String... args) throws Exception {
  System.out.println("Service subject: " + doInitialLogin());
  GSSCredential impersonatedUserCreds=impersonate();
  System.out.println("Credentials for " + TARGET_USER_NAME + ": " + impersonatedUserCreds);
  try (Connection con=createConnection(impersonatedUserCreds)){
    System.out.println("Connection successfully: " + con);
  }
 }
