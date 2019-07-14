protected long toLong(Object object){
  final long since;
  if (object instanceof Integer) {
    since=(Integer)object;
  }
 else   if (object instanceof Long) {
    since=(Long)object;
  }
 else {
    since=Long.parseLong(object.toString());
  }
  return since;
}
