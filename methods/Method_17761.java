private LayoutProps getOrCreateLayoutProps(){
  if (mLayoutProps == null) {
    mLayoutProps=new DefaultLayoutProps();
  }
  return mLayoutProps;
}
