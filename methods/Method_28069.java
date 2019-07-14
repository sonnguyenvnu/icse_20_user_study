@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  String login=getArguments().getString(BundleConstant.EXTRA);
  String repo=getArguments().getString(BundleConstant.ID);
  if (login == null || repo == null) {
    return;
  }
  recycler.setAdapter(new LabelColorsAdapter(Arrays.asList(getResources().getStringArray(R.array.label_colors)),getPresenter()));
  recycler.addKeyLineDivider();
  toolbar.setTitle(R.string.create_label);
  toolbar.setNavigationIcon(R.drawable.ic_clear);
  toolbar.setNavigationOnClickListener(item -> dismiss());
  toolbar.inflateMenu(R.menu.add_menu);
  toolbar.getMenu().findItem(R.id.add).setIcon(R.drawable.ic_send);
  toolbar.setOnMenuItemClickListener(item -> {
    boolean emptyColor=InputHelper.isEmpty(description);
    boolean emptyName=InputHelper.isEmpty(name);
    description.setError(emptyColor ? getString(R.string.required_field) : null);
    name.setError(emptyName ? getString(R.string.required_field) : null);
    if (!emptyColor && !emptyName) {
      getPresenter().onSubmitLabel(InputHelper.toString(name),InputHelper.toString(description),repo,login);
    }
    return true;
  }
);
  fastScroller.attachRecyclerView(recycler);
}
