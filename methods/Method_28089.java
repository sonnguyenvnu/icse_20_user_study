@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (savedInstanceState == null) {
    Bundle bundle=getArguments();
    com.fastaccess.ui.modules.repos.extras.milestone.MilestoneDialogFragment milestoneView=new com.fastaccess.ui.modules.repos.extras.milestone.MilestoneDialogFragment();
    milestoneView.setArguments(bundle);
    getChildFragmentManager().beginTransaction().replace(R.id.singleContainer,milestoneView,com.fastaccess.ui.modules.repos.extras.milestone.MilestoneDialogFragment.TAG).commit();
  }
}
