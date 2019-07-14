@Override public void setTag(int key,Object tag){
  super.setTag(key,tag);
  if (key == R.id.component_node_info && tag != null) {
    refreshAccessibilityDelegatesIfNeeded(isAccessibilityEnabled(getContext()));
    if (mComponentAccessibilityDelegate != null) {
      mComponentAccessibilityDelegate.setNodeInfo((NodeInfo)tag);
    }
  }
}
