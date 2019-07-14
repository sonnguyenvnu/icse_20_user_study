@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  toolbar.setTitle(R.string.organizations);
  toolbar.inflateMenu(R.menu.add_menu);
  toolbar.getMenu().findItem(R.id.add).setIcon(R.drawable.ic_info_outline).setTitle(R.string.no_orgs_dialog_title);
  toolbar.setOnMenuItemClickListener(item -> {
    MessageDialogView.newInstance(getString(R.string.no_orgs_dialog_title),getString(R.string.no_orgs_description),false,true).show(getChildFragmentManager(),MessageDialogView.TAG);
    return true;
  }
);
  toolbar.setNavigationIcon(R.drawable.ic_clear);
  toolbar.setNavigationOnClickListener(v -> dismiss());
  stateLayout.setEmptyText(R.string.no_orgs);
  stateLayout.setOnReloadListener(v -> getPresenter().onLoadOrgs());
  refresh.setOnRefreshListener(() -> getPresenter().onLoadOrgs());
  recycler.setEmptyView(stateLayout,refresh);
  adapter=new UsersAdapter(getPresenter().getOrgs());
  recycler.setAdapter(adapter);
  recycler.addKeyLineDivider();
  if (savedInstanceState == null) {
    getPresenter().onLoadOrgs();
  }
  fastScroller.attachRecyclerView(recycler);
}
