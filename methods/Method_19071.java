@UiThread private void dataRendered(Section currentSection,boolean isDataChanged,boolean isMounted,long uptimeMillis,ChangesInfo changesInfo){
  ThreadUtils.assertMainThread();
  if (currentSection != null) {
    dataRenderedRecursive(currentSection,isDataChanged,isMounted,uptimeMillis,changesInfo);
  }
}
