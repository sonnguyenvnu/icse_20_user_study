/** 
 * Mark a page as used.
 * @param pageId the page id
 */
void allocate(int pageId){
  int idx=pageId - getPos();
  if (idx >= 0 && !used.get(idx)) {
    used.set(idx);
    store.logUndo(this,data);
    store.update(this);
  }
}
