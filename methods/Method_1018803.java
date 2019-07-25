private void init(){
  Log.i(TAG,"Start " + TAG);
  shutdownThread=new Thread(){
    @Override public void run(){
      stopService();
    }
  }
;
  notifyManager=(NotificationManager)getSystemService(NOTIFICATION_SERVICE);
  Context context=getApplicationContext();
  repo=new TorrentStorage(context);
  pref=SettingsManager.getPreferences(context);
  pref.registerOnSharedPreferenceChangeListener(this);
  makeNotifyChans(notifyManager);
  Utils.enableBootReceiverIfNeeded(getApplicationContext());
  checkPauseControl();
  if (pref.getBoolean(getString(R.string.pref_key_cpu_do_not_sleep),SettingsManager.Default.cpuDoNotSleep))   setKeepCpuAwake(true);
  TorrentEngine.getInstance().setContext(context);
  TorrentEngine.getInstance().setCallback(this);
  TorrentEngine.getInstance().setSettings(SettingsManager.readEngineSettings(context));
  TorrentEngine.getInstance().start();
  makeForegroundNotify();
  startUpdateForegroundNotify();
  if (!TorrentTaskServiceReceiver.getInstance().isRegistered(msgReceiver))   TorrentTaskServiceReceiver.getInstance().register(msgReceiver);
}
