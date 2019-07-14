default Delete<DeleteParamEntity> createDelete(){
  Delete<DeleteParamEntity> delete=new Delete<>(new DeleteParamEntity());
  delete.setExecutor(getDao()::delete);
  return delete;
}
