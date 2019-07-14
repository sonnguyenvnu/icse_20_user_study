private void onToggle(boolean expanded,boolean animate,int position){
  if (!expanded) {
    patch.swapAdapter(null,true);
    patch.setVisibility(View.GONE);
    name.setMaxLines(2);
    toggle.setRotation(0.0f);
  }
 else {
    if (adapter != null) {
      CommitFileChanges model=(CommitFileChanges)adapter.getItem(position);
      if (model.getLinesModel() != null && !model.getLinesModel().isEmpty()) {
        if (model.getLinesModel().size() <= 100) {
          patch.setAdapter(new CommitLinesAdapter(model.getLinesModel(),this));
          patch.setVisibility(View.VISIBLE);
        }
 else         if (CommitFileChanges.canAttachToBundle(model)) {
          if (adapter.getListener() != null) {
            adapter.getListener().onItemClick(position,patch,model);
          }
        }
 else {
          Toasty.warning(itemView.getContext(),itemView.getResources().getString(R.string.too_large_changes)).show();
          return;
        }
      }
 else {
        patch.swapAdapter(null,true);
        patch.setVisibility(View.GONE);
      }
    }
    name.setMaxLines(5);
    toggle.setRotation(180f);
  }
}
