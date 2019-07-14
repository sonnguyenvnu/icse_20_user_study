/** 
 * Check if any items have had their values changed, batching if possible.
 */
private void collectChanges(UpdateOpHelper helper){
  for (  ModelState newItem : currentStateList) {
    ModelState previousItem=newItem.pair;
    if (previousItem == null) {
      continue;
    }
    boolean modelChanged;
    if (immutableModels) {
      if (previousItem.model.isDebugValidationEnabled()) {
        previousItem.model.validateStateHasNotChangedSinceAdded("Model was changed before it could be diffed.",previousItem.position);
      }
      modelChanged=!previousItem.model.equals(newItem.model);
    }
 else {
      modelChanged=previousItem.hashCode != newItem.hashCode;
    }
    if (modelChanged) {
      helper.update(newItem.position,previousItem.model);
    }
  }
}
