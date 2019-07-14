public static NavigationViewAdapter override(NavigationView navigationView,NavigationAccountListLayout.Adapter accountListAdapter,NavigationAccountListLayout.Listener accountListListener){
  RecyclerView recyclerView=(RecyclerView)navigationView.getChildAt(navigationView.getChildCount() - 1);
  NavigationViewAdapter adapter=new NavigationViewAdapter(navigationView,recyclerView.getAdapter(),accountListAdapter,accountListListener);
  recyclerView.setAdapter(adapter);
  return adapter;
}
