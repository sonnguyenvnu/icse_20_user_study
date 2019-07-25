@Override public List<AuditLogEntry> list(int limit){
  PageRequest pager=new PageRequest(0,limit,new Sort(Direction.DESC,"createdTime"));
  return StreamSupport.stream(this.repository.findAll(pager).spliterator(),false).collect(Collectors.toList());
}
