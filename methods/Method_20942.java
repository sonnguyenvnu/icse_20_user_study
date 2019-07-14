public static User collaborator(){
  return user().toBuilder().createdProjectsCount(0).memberProjectsCount(10).build();
}
