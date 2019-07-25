public boolean authenticate(String username,char[] password){
  if (username.equalsIgnoreCase(this.username) && Arrays.equals(password,this.password)) {
    return true;
  }
  return false;
}
