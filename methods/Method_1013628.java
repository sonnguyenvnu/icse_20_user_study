/** 
 * only support rollback once
 */
@Override public void rollback(Exception e){
  logger.info("[rollback]{},{} -> {}, reason:{}",this,phaseName,previoisPhaseName,e.getMessage());
  phaseName.set(previoisPhaseName.get());
}
