protected boolean match(PackageParser.Component component,ComponentName target){
  ComponentName source=component.getComponentName();
  if (source == target)   return true;
  if (source != null && target != null && source.getClassName().equals(target.getClassName()) && (source.getPackageName().equals(target.getPackageName()) || mHostContext.getPackageName().equals(target.getPackageName()))) {
    return true;
  }
  return false;
}
