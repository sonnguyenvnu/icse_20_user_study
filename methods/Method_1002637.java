@Setup(Level.Invocation) @SuppressWarnings("RedundantStringConstructorCall") public void setup(){
  path1=new String("/armeria/services/hello-world");
  path2=new String("/armeria/services/goodbye-world");
}
