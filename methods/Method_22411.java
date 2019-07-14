private Mode selectMode(final File sketch){
  final ModeInfo modeInfo=modeInfoFor(sketch);
  final Mode specifiedMode=modeInfo == null ? null : findMode(modeInfo.id);
  if (specifiedMode != null) {
    return specifiedMode;
  }
  return promptForMode(sketch,modeInfo);
}
