@Override public void handleLocationNotAvailable(){
  Toast.makeText(mContext,mContext.getResources().getString(R.string.current_location_error),Toast.LENGTH_LONG).show();
}
