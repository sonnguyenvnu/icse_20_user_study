/** 
 * ???Activity??????????????????
 * @param layoutResID
 * @param listener
 * @use ????*1.onCreate?super.onCreate?setContentView(layoutResID, this); *2.??onDragBottom??????????? *3.?????????onClick?????onDragBottom??
 */
public void setContentView(int layoutResID,OnBottomDragListener listener){
  setContentView(layoutResID);
  onBottomDragListener=listener;
  gestureDetector=new GestureDetector(this,this);
  view=inflater.inflate(layoutResID,null);
  view.setOnTouchListener(new OnTouchListener(){
    @SuppressLint("ClickableViewAccessibility") @Override public boolean onTouch(    View v,    MotionEvent event){
      return gestureDetector.onTouchEvent(event);
    }
  }
);
}
