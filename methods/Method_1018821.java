/** 
 * ????PluginInstallAction??????
 */
public void install(@NonNull PluginLiteInfo info,IInstallCallBack callBack){
  PluginInstallAction action=new PluginInstallAction();
  action.observer=callBack;
  action.info=info;
  action.callbackHost=this;
  if (action.meetCondition() && addAction(action) && actionIsReady(action)) {
    action.doAction();
  }
}
