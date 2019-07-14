@Override public void addConnection(OpenMode service){
  try {
    if (cloudHandler.findEntry(service) != null) {
      Toast.makeText(this,getResources().getString(R.string.connection_exists),Toast.LENGTH_LONG).show();
    }
 else {
      Toast.makeText(MainActivity.this,getResources().getString(R.string.please_wait),Toast.LENGTH_LONG).show();
      Bundle args=new Bundle();
      args.putInt(ARGS_KEY_LOADER,service.ordinal());
      Loader loader=getSupportLoaderManager().getLoader(REQUEST_CODE_CLOUD_LIST_KEY);
      if (loader != null && loader.isStarted()) {
        getSupportLoaderManager().destroyLoader(REQUEST_CODE_CLOUD_LIST_KEY);
      }
      getSupportLoaderManager().initLoader(REQUEST_CODE_CLOUD_LIST_KEY,args,this);
    }
  }
 catch (  CloudPluginException e) {
    e.printStackTrace();
    Toast.makeText(this,getResources().getString(R.string.cloud_error_plugin),Toast.LENGTH_LONG).show();
  }
}
