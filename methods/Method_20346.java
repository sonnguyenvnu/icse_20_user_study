/** 
 * Set a PagedList that should be used to build models in this controller. A listener will be attached to the list so that models are rebuilt as new list items are loaded. <p> By default the Config setting on the PagedList will dictate how many models are built at once, and what prefetch thresholds should be used. This can be overridden with a separate Config via {@link #setConfig(Config)}. <p> See  {@link #setConfig(Config)} for details on how the Config settings are used, and forrecommended values.
 */
public void setList(@Nullable PagedList<T> list){
  if (list == this.pagedList) {
    return;
  }
  PagedList<T> previousList=this.pagedList;
  this.pagedList=list;
  if (previousList != null) {
    previousList.removeWeakCallback(callback);
  }
  if (list != null) {
    list.addWeakCallback(null,callback);
  }
  isFirstBuildForList=true;
  updatePagedListSnapshot();
}
