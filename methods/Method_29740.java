private void notifyChanged(final String key){
  if (Looper.myLooper() == Looper.getMainLooper()) {
    for (    OnSharedPreferenceChangeListener listener : listeners) {
      if (listener != null) {
        listener.onSharedPreferenceChanged(AccountPreferences.this,key);
      }
    }
  }
 else {
    mainHandler.post(new Runnable(){
      @Override public void run(){
        notifyChanged(key);
      }
    }
);
  }
}
