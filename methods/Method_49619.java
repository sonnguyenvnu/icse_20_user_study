private String getToken(final Map<String,String> credentials){
  final String username=credentials.get(PROPERTY_USERNAME);
  final Vertex user=findUser(username);
  final String password=user.value(PROPERTY_PASSWORD);
  final String salt=getBcryptSaltFromStoredPassword(password);
  final String time=Long.toString(new Date().getTime());
  return generateToken(username,salt,time);
}
