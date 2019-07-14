@Override public void onMessageArrived(final Request request){
  writer.write(formatter.format(request));
}
