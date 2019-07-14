private String usernameFromEmail(String email){
  if (email.contains("@")) {
    return email.split("@")[0];
  }
 else {
    return email;
  }
}
