public void hasJavaTabsChanged(boolean hasJavaTabs){
  enabled=!hasJavaTabs;
  if (!enabled) {
    usage.hide();
  }
}
