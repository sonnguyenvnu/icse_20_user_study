@Override public View createView(Context context,ViewGroup parent,ComponentInfo info){
  return vafContext.getContainerService().getContainer(info.getName(),true);
}
