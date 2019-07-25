@Override public List<Student> list() throws ServiceException {
  try {
    return persistenceService.list(Student.class);
  }
 catch (  PersistenceException e) {
    throw new ServiceException(e);
  }
}
