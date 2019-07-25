@Override public void write(String secretId,Credentials secret){
  secret.getUserCredentials().forEach((principalName,options) -> write(secretId,USERS,principalName,new Options(options)));
  Object[] groups=secret.getGroupCredentials().keySet().toArray();
  IntStream.range(0,groups.length).mapToObj(index -> {
    String name=groups[index].toString();
    return new PrincipalCredentials(name,new IndexedOptions(index,secret.getGroupCredentials().get(name)));
  }
).forEach(p -> write(secretId,GROUPS,p.principalName,p.options));
  write(secretId,DEFAULTS,null,new Options(secret.getDefaultCredentials()));
}
