private void checkIsDuplicateKey(Component component){
  if (mKnownGlobalKeys.contains(component.getGlobalKey())) {
    final String message="Found another " + component.getSimpleName() + " Component with the same key: " + component.getKey();
    final String errorMessage=mLogger == null ? message : getDuplicateKeyMessage();
    if (component.hasState()) {
      throw new RuntimeException(message + "\n" + errorMessage);
    }
    if (mLogger != null) {
      mLogger.emitMessage(ComponentsLogger.LogLevel.ERROR,message + "\n" + errorMessage);
    }
  }
}
