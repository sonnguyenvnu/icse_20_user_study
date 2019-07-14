@Override public void unInBinding(Object contract,JvmBinding binding,SofaRuntimeContext sofaRuntimeContext){
  binding.setDestroyed(true);
  if (binding.hasBackupProxy()) {
    binding.setBackupProxy(null);
  }
}
