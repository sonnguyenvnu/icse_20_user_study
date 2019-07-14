private void ensureBlock(BaseCell cell){
  if (cell.isValid()) {
    if (cell.style.extras == null) {
      cell.style.extras=new JSONObject();
    }
    try {
      cell.style.extras.put(Style.KEY_DISPLAY,Style.DISPLAY_BLOCK);
    }
 catch (    JSONException e) {
      Log.w(TAG,Log.getStackTraceString(e),e);
    }
  }
}
