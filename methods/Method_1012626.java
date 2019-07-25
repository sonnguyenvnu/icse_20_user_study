public void execute(Request request){
switch (request.type) {
case CONNECT:
    Log.d(getClass().getSimpleName(),"connection");
  break;
case DISCONNECT:
Log.d(getClass().getSimpleName(),"disconnect");
break;
}
}
