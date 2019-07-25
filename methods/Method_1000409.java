public boolean drop(Class<?> classOfT){
  Entity<?> en=holder.getEntity(classOfT);
  if (!exists(en.getTableName()))   return false;
  return expert.dropEntity(this,en);
}
