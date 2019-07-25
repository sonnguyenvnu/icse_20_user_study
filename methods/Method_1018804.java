public void _long(final Context context,final String text){
  HANDLER.post(new Runnable(){
    @Override public void run(){
      Toast.makeText(context.getApplicationContext(),text,Toast.LENGTH_LONG).show();
    }
  }
);
}
