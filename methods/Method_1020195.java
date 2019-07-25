@Override public <T extends BaseEntity>void remove(Class<T> type,String id) throws PersistenceException {
  try {
    this.readEntity(getEntityPath(type,id));
  }
 catch (  IOException|ClassNotFoundException e) {
    throw new PersistenceException(e);
  }
}
