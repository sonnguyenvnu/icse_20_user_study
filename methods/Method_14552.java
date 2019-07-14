public static Credentials makeCredentials(String str,Provider provider){
  HttpParameters p=OAuth.decodeForm(str);
  return new Credentials(p.getFirst(TOKEN),p.getFirst(SECRET),provider);
}
