public boolean verify(String owner,String tag,String user,String pwd){
  MultiKey key=createKey(owner,tag);
  ArrayList<Credential> list=credentials.get(key);
  if (list == null) {
    return false;
  }
  for (  Credential c : list) {
    if (user.equals(c.username)) {
      return pwd.equals(c.password);
    }
  }
  return false;
}
