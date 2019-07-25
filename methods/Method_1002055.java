@Override public void run(){
  Handler handler=new Handler();
  final int total=10;
  for (int a=1; a <= total; a++) {
    final int requestNumber=a;
    handler.postDelayed(new Runnable(){
      @Override public void run(){
        multipleRequests(ctx,requestNumber,total);
      }
    }
,1000 * a);
  }
}
