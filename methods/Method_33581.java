/** 
 * @param context      activity
 * @param positionData bean
 * @param imageView    imageView
 */
public static void start(Activity context,SubjectsBean positionData,ImageView imageView){
  Intent intent=new Intent(context,OneMovieDetailActivity.class);
  intent.putExtra("bean",positionData);
  ActivityOptionsCompat options=ActivityOptionsCompat.makeSceneTransitionAnimation(context,imageView,CommonUtils.getString(R.string.transition_movie_img));
  ActivityCompat.startActivity(context,intent,options.toBundle());
}
