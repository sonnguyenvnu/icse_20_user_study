@Override public void stop(){
  try {
    context.unregisterReceiver(directReceiver);
  }
 catch (  IllegalArgumentException e) {
    logger.d("Silenced 'receiver not registered' stuff (calling stop more times than necessary did this)");
  }
  try {
    context.unregisterReceiver(reverseReceiver);
  }
 catch (  IllegalArgumentException e) {
    logger.d("Silenced 'receiver not registered' stuff (calling stop more times than necessary did this)");
  }
}
