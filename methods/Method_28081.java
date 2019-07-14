@Override protected void onFragmentCreated(@NonNull View view,@Nullable Bundle savedInstanceState){
  String login=getArguments().getString(BundleConstant.EXTRA);
  String repo=getArguments().getString(BundleConstant.ID);
  if (login == null || repo == null) {
    return;
  }
  toolbar.setTitle(R.string.create_milestone);
  toolbar.setNavigationIcon(R.drawable.ic_clear);
  toolbar.setNavigationOnClickListener(item -> dismiss());
  toolbar.inflateMenu(R.menu.add_menu);
  toolbar.getMenu().findItem(R.id.add).setIcon(R.drawable.ic_send);
  toolbar.setOnMenuItemClickListener(item -> {
    getPresenter().onSubmit(InputHelper.toString(title),InputHelper.toString(dueOnEditText),InputHelper.toString(description),login,repo);
    return true;
  }
);
}
