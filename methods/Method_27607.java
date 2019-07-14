private GistFilesListFragment getFilesFragment(){
  if (filesListFragment == null) {
    filesListFragment=(GistFilesListFragment)getSupportFragmentManager().findFragmentById(R.id.files);
  }
  return filesListFragment;
}
