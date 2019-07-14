/** 
 * ??????
 */
private void showRotaLoading(Boolean isLoading){
  if (isLoading != null && isLoading) {
    bindingView.llLoading.setVisibility(View.VISIBLE);
    bindingView.recyclerView.setVisibility(View.GONE);
    animation.startNow();
  }
 else {
    bindingView.llLoading.setVisibility(View.GONE);
    bindingView.recyclerView.setVisibility(View.VISIBLE);
    animation.cancel();
  }
}
