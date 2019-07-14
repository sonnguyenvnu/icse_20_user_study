@Override ViewDataBinding getDataBinder(DataBindingComponent bindingComponent,View[] view,int layoutId){
  ViewDataBinding viewDataBinding;
  for (  DataBinderMapper mapper : getCache()) {
    viewDataBinding=mapper.getDataBinder(bindingComponent,view,layoutId);
    if (viewDataBinding != null) {
      return viewDataBinding;
    }
  }
  return null;
}
