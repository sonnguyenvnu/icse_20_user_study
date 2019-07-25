public void init(ByteBuffer buf){
  type=buf.get();
  x=buf.getInt();
  y=buf.getInt();
  val=buf.getInt();
}
