private void encode(BSONObject dbo){
  DBEncoder dbEncoder=DefaultDBEncoder.FACTORY.create();
  dbEncoder.writeObject(buffer,dbo);
}
