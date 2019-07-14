@Override protected String androidUUID(){
  return FirebaseInstanceId.getInstance().getId();
}
