/** 
 * If there is a new adapter on the attached RecyclerView it will resister the data observer and clear the current visibility states
 */
private void processNewAdapterIfNecessary(){
  if (attachedRecyclerView != null && attachedRecyclerView.getAdapter() != null) {
    if (lastAdapterSeen != attachedRecyclerView.getAdapter()) {
      if (lastAdapterSeen != null) {
        lastAdapterSeen.unregisterAdapterDataObserver(this.observer);
      }
      attachedRecyclerView.getAdapter().registerAdapterDataObserver(this.observer);
      lastAdapterSeen=attachedRecyclerView.getAdapter();
    }
  }
}
