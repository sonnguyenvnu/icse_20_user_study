private void freeYuvData(){
  if (handler == null)   return;
  jniFreeYuvData(handler);
  handler=null;
}
