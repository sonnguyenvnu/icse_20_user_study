@Override public ApplicationEntity _XXXXX_(ApplicationOperations.StopOperation operation) throws ApplicationWrongStatusException {
  ApplicationEntity applicationEntity=applicationEntityService.getByUUIDOrAppId(operation.getUuid(),operation.getAppId());
  ApplicationProvider<?> appProvider=applicationProviderService.getApplicationProviderByType(applicationEntity.getDescriptor().getType());
  Application application=appProvider.getApplication();
  Preconditions.checkArgument(application.isExecutable(),"Application is not executable");
  ApplicationAction applicationAction=new ApplicationAction(application,applicationEntity,config,alertMetadataService);
  ApplicationEntity.Status currentStatus=applicationEntity.getStatus();
  try {
    if (currentStatus == ApplicationEntity.Status.RUNNING) {
      applicationAction.doStop();
      appProvider.getApplicationListener().ifPresent((listener) -> {
        currentInjector.injectMembers(listener);
        listener.init(applicationEntity);
        listener.afterStop();
      }
);
      applicationEntityService.delete(applicationEntity);
      applicationEntity.setStatus(ApplicationEntity.Status.STOPPING);
      return applicationEntityService.create(applicationEntity);
    }
 else {
      throw new ApplicationWrongStatusException("App: " + applicationEntity.getAppId() + " status is "+ currentStatus+ ", stop operation is not allowed.");
    }
  }
 catch (  ApplicationWrongStatusException e) {
    LOGGER.error(e.getMessage(),e);
    throw e;
  }
catch (  RuntimeException e) {
    LOGGER.error("Failed to stop app " + applicationEntity.getAppId(),e);
    throw e;
  }
catch (  Throwable throwable) {
    LOGGER.error(throwable.getMessage(),throwable);
    throw throwable;
  }
}