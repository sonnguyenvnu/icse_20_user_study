private void setContentFragment(){
  getSupportFragmentManager().beginTransaction().replace(R.id.content,albumsFragment,AlbumsFragment.TAG).addToBackStack(null).commit();
}
