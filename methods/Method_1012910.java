@Override protected LoadType<StudentProfile> load(){
  return ofy().load().type(StudentProfile.class);
}
