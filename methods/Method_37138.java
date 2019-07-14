private void handleException(Exception e){
  if (TangramBuilder.isPrintLog())   Log.e(TAG,"Exception when create instance: " + mClz.getCanonicalName() + "  message: " + e.getMessage(),e);
  throw new RuntimeException(e);
}
