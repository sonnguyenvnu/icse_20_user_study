@Nonnull @Override public Optional<ProcessorDTO> update(@Nonnull final ProcessorEntity processorEntity){
  if (processorEntity.getRevision() == null) {
    if (processorEntity.getId() == null && processorEntity.getComponent() != null) {
      processorEntity.setId(processorEntity.getComponent().getId());
    }
    findEntityById(processorEntity.getId()).ifPresent(current -> {
      final RevisionDTO revision=new RevisionDTO();
      revision.setVersion(current.getRevision().getVersion());
      processorEntity.setRevision(revision);
    }
);
  }
  try {
    return Optional.of(client.put(BASE_PATH + processorEntity.getId(),processorEntity,ProcessorEntity.class).getComponent());
  }
 catch (  final NotFoundException e) {
    return Optional.empty();
  }
}
