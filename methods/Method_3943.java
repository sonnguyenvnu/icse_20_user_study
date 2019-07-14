/** 
 * @return true if layout and scroll are frozen
 * @deprecated Use {@link #isLayoutSuppressed()}.
 */
@Deprecated public boolean isLayoutFrozen(){
  return isLayoutSuppressed();
}
