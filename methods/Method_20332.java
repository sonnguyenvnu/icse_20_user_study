int getViewTypeAndRememberModel(EpoxyModel<?> model){
  lastModelForViewTypeLookup=model;
  return getViewType(model);
}
