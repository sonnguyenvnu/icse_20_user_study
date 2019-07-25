@NonNull @Override public <T extends ViewModel>T create(@NonNull Class<T> modelClass){
  if (modelClass.isAssignableFrom(NewsDetailViewModel.class)) {
    return (T)new NewsDetailViewModel(mApplication,new NewsDetailModel(mApplication));
  }
 else   if (modelClass.isAssignableFrom(NewsListViewModel.class)) {
    return (T)new NewsListViewModel(mApplication,new NewsListModel(mApplication));
  }
 else   if (modelClass.isAssignableFrom(NewsTypeViewModel.class)) {
    return (T)new NewsTypeViewModel(mApplication,new NewsTypeModel(mApplication));
  }
  throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
}
