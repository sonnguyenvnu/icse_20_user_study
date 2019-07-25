@Override public void init(Action action,ActionArgumentValue[] presetInputValues){
  this.action=action;
  view.setPresenter(this);
  view.init(action,presetInputValues);
}
