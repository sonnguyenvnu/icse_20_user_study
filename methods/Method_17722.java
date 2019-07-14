@Override public void collectTransitioningProperties(ArrayList<PropertyAnimation> outList){
  for (int i=0, size=mBindings.size(); i < size; i++) {
    mBindings.get(i).collectTransitioningProperties(outList);
  }
}
