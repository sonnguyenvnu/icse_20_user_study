/** 
 * ??????????
 * @param context
 * @param name
 * @return
 */
@SuppressLint({"NewApi","InflateParams"}) public TextView newTopRightTextView(Context context,String name){
  TextView topRightButton=(TextView)LayoutInflater.from(context).inflate(R.layout.top_right_tv,null);
  topRightButton.setText(name);
  return topRightButton;
}
