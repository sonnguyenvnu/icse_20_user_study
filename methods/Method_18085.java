@Override public void appendUnresolvedComponent(Component component){
  if (mUnresolvedComponents == null) {
    mUnresolvedComponents=new ArrayList<>();
  }
  mUnresolvedComponents.add(component);
}
