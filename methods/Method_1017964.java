@Nonnull @Override public ControllerServiceDTO update(@Nonnull final ControllerServiceDTO controllerService){
  return findEntityById(controllerService.getId()).flatMap(current -> {
    final ControllerServiceEntity entity=new ControllerServiceEntity();
    entity.setComponent(controllerService);
    final RevisionDTO revision=new RevisionDTO();
    revision.setVersion(current.getRevision().getVersion());
    entity.setRevision(revision);
    try {
      return Optional.of(client.put(BASE_PATH + controllerService.getId(),entity,ControllerServiceEntity.class).getComponent());
    }
 catch (    final NotFoundException e) {
      return Optional.empty();
    }
catch (    final ClientErrorException e) {
      throw new NifiClientRuntimeException("Error updating controller service: " + e.getResponse().readEntity(String.class),e);
    }
  }
).orElseThrow(() -> new NifiComponentNotFoundException(controllerService.getId(),NifiConstants.NIFI_COMPONENT_TYPE.CONTROLLER_SERVICE,null));
}
