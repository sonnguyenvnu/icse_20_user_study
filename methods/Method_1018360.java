@Override public RepositoryLinkBuilder slash(Object object){
  return PersistentProperty.class.isInstance(object) ? slash((PersistentProperty<?>)object) : super.slash(object);
}
