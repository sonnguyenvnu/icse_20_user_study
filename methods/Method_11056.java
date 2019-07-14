/** 
 * ????
 * @param context
 * @param uri     ???Uri
 */
public static void showBigImageView(Context context,Uri uri){
  final RxDialog rxDialog=new RxDialog(context);
  View view=LayoutInflater.from(context).inflate(R.layout.image,null);
  view.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      rxDialog.cancel();
    }
  }
);
  ImageView imageView=view.findViewById(R.id.page_item);
  imageView.setImageURI(uri);
  rxDialog.setContentView(view);
  rxDialog.show();
  rxDialog.setFullScreen();
}
