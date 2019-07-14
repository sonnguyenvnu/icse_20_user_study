public static void setCurrentPersonId(String id){
  ThreadLocalUtils.put(PersonnelAuthenticationHolder.CURRENT_USER_ID_KEY,id);
}
