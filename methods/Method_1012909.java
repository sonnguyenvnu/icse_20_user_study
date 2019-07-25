@Override protected LoadType<Instructor> load(){
  return ofy().load().type(Instructor.class);
}
