@Override public void configure(ConfigProperties configProperties){
  String rolesString=configProperties.getStringValue("roles","");
  if (rolesString != null) {
    roles=new HashSet<>(Arrays.asList(rolesString.split(",")));
  }
}
