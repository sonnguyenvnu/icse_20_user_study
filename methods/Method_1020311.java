/** 
 */
public void clear(Background background){
  integrityCheck();
  final Background finalBackground=background;
  Platform.executeInUIThread(new M3gRunnable(){
    @Override public void doRun(){
      _clear(handle,finalBackground != null ? finalBackground.handle : 0);
    }
  }
);
}
