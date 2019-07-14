@Provides @Singleton public Display provideDisplayManager(){
  return ((WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
}
