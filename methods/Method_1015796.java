@Override public void reset(){
  headers=new Headers();
  responseBody=null;
  writer=null;
  bodyOutputStream=new ByteArrayOutputStream();
}
