private Builder wrap(Object instance,Object source){
  PersistentEntity<?,?> entity=entities.getRequiredPersistentEntity(source.getClass());
  return PersistentEntityResource.build(instance,entity).withEmbedded(getEmbeddedResources(source)).withLink(getExpandedSelfLink(source)).withLink(linkProvider.createSelfLinkFor(source));
}
