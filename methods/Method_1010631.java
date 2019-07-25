public void dispose(){
  clear();
  ProjectManager.getInstance().removeProjectListener(this);
}
