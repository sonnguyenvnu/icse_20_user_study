public static UserToken currentToken(){
  return ThreadLocalUtils.get(UserToken.class.getName());
}
