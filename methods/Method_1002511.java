@Override public EntityStream inflate(EntityStream input){
  StreamingInflater inflater=createInflater(input);
  return EntityStreams.newEntityStream(inflater);
}
