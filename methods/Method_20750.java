/** 
 * Memory availability callback. TRIM_MEMORY_UI_HIDDEN means the app's UI is no longer visible. This is triggered when the user navigates out of the app and primarily used to free resources used by the UI. http://developer.android.com/training/articles/memory.html
 */
@Override public void onTrimMemory(final int i){
  if (i == ComponentCallbacks2.TRIM_MEMORY_UI_HIDDEN) {
    this.koala.trackAppClose();
    this.isInBackground=true;
  }
}
