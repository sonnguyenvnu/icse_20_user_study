@Override public void onPostExecute(@NonNull Boolean result){
  if (result) {
    final MainActivity mainActivity=this.mainActivity.get();
    if (mainActivity != null) {
      mainActivity.getDrawer().refreshDrawer();
    }
  }
}
