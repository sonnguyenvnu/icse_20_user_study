@Override public void onMilestoneSelected(@NonNull MilestoneModel milestoneModel){
  if (onMilestoneSelected != null)   onMilestoneSelected.onMilestoneSelected(milestoneModel);
  if (getParentFragment() instanceof BaseDialogFragment) {
    ((BaseDialogFragment)getParentFragment()).dismiss();
  }
}
