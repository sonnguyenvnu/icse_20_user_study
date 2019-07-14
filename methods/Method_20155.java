/** 
 * Enables support for automatically notifying model changes via  {@link #notifyModelsChanged()}. If used, this should be called in the constructor, before any models are changed.
 * @see #notifyModelsChanged()
 */
protected void enableDiffing(){
  if (diffHelper != null) {
    throw new IllegalStateException("Diffing was already enabled");
  }
  if (!models.isEmpty()) {
    throw new IllegalStateException("You must enable diffing before modifying models");
  }
  if (!hasStableIds()) {
    throw new IllegalStateException("You must have stable ids to use diffing");
  }
  diffHelper=new DiffHelper(this,false);
}
