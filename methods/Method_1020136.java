public static void put(User user){
  cache.put(user.getName(),user);
  UserContext.setCurrent(user);
  setCookie(user);
}
