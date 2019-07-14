private void loadContributions(ExtensionPoint extensionPoint,Extension extension){
  if (extensionPoint != null && extensionPoint.hasContribution()) {
    try {
      Object[] contribs=((ExtensionPointInternal)extensionPoint).loadContributions((ExtensionInternal)extension);
      ((ExtensionInternal)extension).setContributions(contribs);
    }
 catch (    Exception e) {
      SofaLogger.error(e,"Failed to create contribution objects");
    }
  }
}
