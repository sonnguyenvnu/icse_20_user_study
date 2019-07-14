@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  if (getArguments() == null) {
    return;
  }
  String login=getArguments().getString(BundleConstant.EXTRA);
  String repo=getArguments().getString(BundleConstant.ID);
  if (login == null || repo == null) {
    return;
  }
  stateLayout.setEmptyText(R.string.no_milestones);
  toolbar.setTitle(R.string.milestone);
  toolbar.setOnMenuItemClickListener(item -> onAddMilestone());
  if (onMilestoneSelected != null)   toolbar.inflateMenu(R.menu.add_menu);
  toolbar.setNavigationIcon(R.drawable.ic_clear);
  toolbar.setNavigationOnClickListener(v -> {
    if (getParentFragment() instanceof BaseDialogFragment) {
      ((BaseDialogFragment)getParentFragment()).dismiss();
    }
  }
);
  recycler.addDivider();
  adapter=new MilestonesAdapter(getPresenter().getMilestones());
  if (onMilestoneSelected != null)   adapter.setListener(getPresenter());
  recycler.setEmptyView(stateLayout,refresh);
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  if (savedInstanceState == null || (getPresenter().getMilestones().isEmpty() && !getPresenter().isApiCalled())) {
    getPresenter().onLoadMilestones(login,repo);
  }
  stateLayout.setOnReloadListener(v -> getPresenter().onLoadMilestones(login,repo));
  refresh.setOnRefreshListener(() -> getPresenter().onLoadMilestones(login,repo));
  fastScroller.attachRecyclerView(recycler);
}
