public void init(final Context context){
  mContext=context;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
    mSubscriptionManager=SubscriptionManager.from(context);
    IntentFilter intentFilterLoaded=new IntentFilter("LOADED");
    try {
      context.registerReceiver(mReceiver,intentFilterLoaded);
    }
 catch (    Exception e) {
    }
    load(context);
  }
 else {
    load(context);
  }
}
