@SuppressLint("PrivateApi") public void fixHandleView(boolean reset){
  if (reset) {
    fixed=false;
  }
 else   if (!fixed) {
    try {
      if (editorClass == null) {
        editorClass=Class.forName("android.widget.Editor");
        mEditor=TextView.class.getDeclaredField("mEditor");
        mEditor.setAccessible(true);
        editor=mEditor.get(this);
      }
      if (listenerFixer == null) {
        Method initDrawablesMethod=editorClass.getDeclaredMethod("getPositionListener");
        initDrawablesMethod.setAccessible(true);
        listenerFixer=(ViewTreeObserver.OnPreDrawListener)initDrawablesMethod.invoke(editor);
      }
      AndroidUtilities.runOnUIThread(listenerFixer::onPreDraw,500);
    }
 catch (    Throwable ignore) {
    }
    fixed=true;
  }
}
