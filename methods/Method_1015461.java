public void start(String name){
  myname=name;
  int xloc=(int)(10 + 250 * Math.random());
  int yloc=(int)(10 + 250 * Math.random());
  try {
    MethodCall call=new MethodCall("addNode",new Object[]{name,my_addr,Integer.valueOf(xloc),Integer.valueOf(yloc)},new Class[]{String.class,Address.class,int.class,int.class});
    wb.disp.callRemoteMethods(null,call,new RequestOptions(ResponseMode.GET_ALL,0));
  }
 catch (  Exception e) {
    log.error(e.toString());
  }
  repaint();
}
