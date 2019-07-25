public ButtonType close(){
  if (Objects.nonNull(getPath()))   tabService.getClosedPaths().add(Optional.ofNullable(getPath()));
  this.select();
  if (isNew() && !isChanged()) {
    closeIt();
    return ButtonType.YES;
  }
 else   if (isNew()) {
    ButtonType type=AlertHelper.saveAlert().orElse(ButtonType.CANCEL);
    if (type == ButtonType.YES) {
      closeIt();
    }
    return type;
  }
 else {
    saveDoc();
    if (isSaved()) {
      closeIt();
      return ButtonType.YES;
    }
 else {
      ButtonType type=AlertHelper.saveAlert().orElse(ButtonType.CANCEL);
      if (type == ButtonType.YES) {
        closeIt();
      }
      return type;
    }
  }
}
