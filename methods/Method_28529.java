protected void hideShow(){
  if (recyclerView != null && recyclerView.getAdapter() != null) {
    setVisibility(recyclerView.getAdapter().getItemCount() > 10 ? VISIBLE : GONE);
  }
 else {
    setVisibility(GONE);
  }
}
