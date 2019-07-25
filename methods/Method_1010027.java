/** 
 * Runs  {@link #enable()} or {@link #disable()} depending on the passed value.
 * @param enable or disable
 */
private void toggle(@NotNull Boolean enable){
  if (enable) {
    enable();
  }
 else {
    disable();
  }
}
