public String getUserField(Class type){
  if (ReflectionUtils.findField(type,"userId") != null) {
    return "userId";
  }
  return "creatorId";
}
