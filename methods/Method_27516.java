public void closeDrawer(){
  if (drawer != null) {
    if (drawer.isDrawerOpen(GravityCompat.START)) {
      drawer.closeDrawer(GravityCompat.START);
    }
  }
}
