public static OutputModule system(){
  return new OutputModule(new PrintWriter(System.out,true),new PrintWriter(System.err,true));
}
