@NonNull @Override public <T extends ViewModel>T create(@NonNull Class<T> modelClass){
  if (modelClass.isAssignableFrom(SplashViewModel.class)) {
    return (T)new SplashViewModel(mApplication,new SplashModel(mApplication));
  }
  throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
}
