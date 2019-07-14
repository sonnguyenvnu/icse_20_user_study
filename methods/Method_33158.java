private void resetPseudoClass(){
  if (activePseudoClass != null) {
    content.pseudoClassStateChanged(activePseudoClass,false);
    activePseudoClass=null;
  }
}
