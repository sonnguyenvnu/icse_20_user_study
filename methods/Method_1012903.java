@Override protected LoadType<Course> load(){
  return ofy().load().type(Course.class);
}
