@Override public boolean isTracing(){
  return ComponentsConfiguration.IS_INTERNAL_BUILD && Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR2;
}
