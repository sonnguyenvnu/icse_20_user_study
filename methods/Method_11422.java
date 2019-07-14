/** 
 * Initiates data and parameters from styles
 * @param attrs    a collection of attributes.
 * @param defStyle The default style to apply to this view.
 */
protected void initAttributes(AttributeSet attrs,int defStyle){
  TypedArray a=getContext().obtainStyledAttributes(attrs,R.styleable.AbstractWheelView,defStyle,0);
  mVisibleItems=a.getInt(R.styleable.AbstractWheelView_visibleItems,DEF_VISIBLE_ITEMS);
  mIsAllVisible=a.getBoolean(R.styleable.AbstractWheelView_isAllVisible,false);
  mIsCyclic=a.getBoolean(R.styleable.AbstractWheelView_isCyclic,DEF_IS_CYCLIC);
  a.recycle();
}
