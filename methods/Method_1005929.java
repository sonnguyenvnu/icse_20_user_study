static FragmentManagerCompat create(Activity activity){
  if (activity instanceof AppCompatActivity) {
    return new Support((AppCompatActivity)activity);
  }
 else {
    return new Android(activity);
  }
}
