private TextDetailSecureCell getViewByType(TLRPC.TL_secureRequiredType requiredType){
  TextDetailSecureCell view=typesViews.get(requiredType);
  if (view == null) {
    requiredType=documentsToTypesLink.get(requiredType);
    if (requiredType != null) {
      view=typesViews.get(requiredType);
    }
  }
  return view;
}
