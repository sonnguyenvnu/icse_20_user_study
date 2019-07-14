@Override public void onInitOrgs(@Nullable List<User> orgs){
  if (orgs != null && !orgs.isEmpty()) {
    orgsList.setNestedScrollingEnabled(false);
    ProfileOrgsAdapter adapter=new ProfileOrgsAdapter();
    adapter.addItems(orgs);
    orgsList.setAdapter(adapter);
    orgsCard.setVisibility(VISIBLE);
    organizationsCaption.setVisibility(VISIBLE);
    ((GridManager)orgsList.getLayoutManager()).setIconSize(getResources().getDimensionPixelSize(R.dimen.header_icon_zie) + getResources().getDimensionPixelSize(R.dimen.spacing_xs_large));
  }
 else {
    organizationsCaption.setVisibility(GONE);
    orgsCard.setVisibility(GONE);
  }
}
