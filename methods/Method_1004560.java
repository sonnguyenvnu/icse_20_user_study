private String capitialize(String group){
  if (group.charAt(0) >= 'a' && group.charAt(0) <= 'z') {
    String first=group.substring(0,1).toUpperCase();
    group=first + group.substring(1);
  }
  return group;
}
