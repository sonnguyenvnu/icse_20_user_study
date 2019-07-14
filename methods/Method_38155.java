void forEachNamedParameter(final Consumer<DbQueryNamedParameter> consumer){
  DbQueryNamedParameter p=rootNP;
  while (p != null) {
    consumer.accept(p);
    p=p.next;
  }
}
