void hydrate(Model model){
  this.model=model;
  for (  Section section : sections) {
    if (!StringUtils.isNullOrEmpty(section.getElementId())) {
      section.setElement(model.getElement(section.getElementId()));
    }
  }
  for (  Decision decision : decisions) {
    if (!StringUtils.isNullOrEmpty(decision.getElementId())) {
      decision.setElement(model.getElement(decision.getElementId()));
    }
  }
}
