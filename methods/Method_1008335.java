@Override public void notify(Errors errors){
  InjectorImpl privateInjector=(InjectorImpl)privateElements.getInjector();
  BindingImpl<T> explicitBinding=privateInjector.state.getExplicitBinding(key);
  if (explicitBinding.getInternalFactory() == this) {
    errors.withSource(explicitBinding.getSource()).exposedButNotBound(key);
    return;
  }
  this.delegate=explicitBinding;
}
