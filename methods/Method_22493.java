private void installContribution(AvailableContribution info){
  if (info.link == null) {
    setErrorMessage(Language.interpolate("contrib.unsupported_operating_system",info.getType()));
  }
 else {
    installContribution(info,info.link);
  }
}
