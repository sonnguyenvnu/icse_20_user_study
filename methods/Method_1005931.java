void detach(Activity activity){
  if (this.activity != null) {
    final Activity current=this.activity.get();
    if (current == activity) {
      stopForeground(true);
      this.activity=null;
    }
  }
}
