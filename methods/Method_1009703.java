private void Bind(){
  SettingValues.expandedSettings=true;
  setSettingItems();
  final ScrollView mScrollView=((ScrollView)findViewById(R.id.base));
  prefsListener=new SharedPreferences.OnSharedPreferenceChangeListener(){
    @Override public void onSharedPreferenceChanged(    SharedPreferences sharedPreferences,    String key){
      Settings.changed=true;
    }
  }
;
  SettingValues.prefs.registerOnSharedPreferenceChangeListener(prefsListener);
  mScrollView.post(new Runnable(){
    @Override public void run(){
      ViewTreeObserver observer=mScrollView.getViewTreeObserver();
      if (getIntent().hasExtra("position")) {
        mScrollView.scrollTo(0,getIntent().getIntExtra("position",0));
      }
      if (getIntent().hasExtra("prev_text")) {
        prev_text=getIntent().getStringExtra("prev_text");
      }
      observer.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener(){
        @Override public void onScrollChanged(){
          scrollY=mScrollView.getScrollY();
        }
      }
);
    }
  }
);
}
