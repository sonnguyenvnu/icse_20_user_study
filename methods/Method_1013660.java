private AlertEntityHolderManager refresh(){
  AlertEntityHolderManager prevHolderManager=holderManager;
  holderManager=new DefaultAlertEntityHolderManager();
  return prevHolderManager;
}
