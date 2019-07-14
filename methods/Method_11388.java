/** 
 * We currently only support the following set of XML attributes: <ul> <li>app:textColor <li>app:textSize </ul>
 * @param context      context from constructor
 * @param attrs        attrs from constructor
 * @param defStyleAttr defStyleAttr from constructor
 * @param defStyleRes  defStyleRes from constructor
 */
protected void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
  final Resources res=context.getResources();
  int textColor=DEFAULT_TEXT_COLOR;
  float textSize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,DEFAULT_TEXT_SIZE,res.getDisplayMetrics());
  int gravity=DEFAULT_GRAVITY;
  final TypedArray arr=context.obtainStyledAttributes(attrs,R.styleable.ticker_TickerView,defStyleAttr,defStyleRes);
  final int textAppearanceResId=arr.getResourceId(R.styleable.ticker_TickerView_android_textAppearance,-1);
  if (textAppearanceResId != -1) {
    final TypedArray textAppearanceArr=context.obtainStyledAttributes(textAppearanceResId,new int[]{android.R.attr.textSize,android.R.attr.textColor});
    textSize=textAppearanceArr.getDimension(0,textSize);
    textColor=textAppearanceArr.getColor(1,textColor);
    textAppearanceArr.recycle();
  }
  gravity=arr.getInt(R.styleable.ticker_TickerView_android_gravity,gravity);
  textColor=arr.getColor(R.styleable.ticker_TickerView_android_textColor,textColor);
  textSize=arr.getDimension(R.styleable.ticker_TickerView_android_textSize,textSize);
  animationInterpolator=DEFAULT_ANIMATION_INTERPOLATOR;
  this.animationDurationInMillis=arr.getInt(R.styleable.ticker_TickerView_ticker_animationDuration,DEFAULT_ANIMATION_DURATION);
  this.animateMeasurementChange=arr.getBoolean(R.styleable.ticker_TickerView_ticker_animateMeasurementChange,false);
  this.gravity=gravity;
  setTextColor(textColor);
  setTextSize(textSize);
  arr.recycle();
  animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener(){
    @Override public void onAnimationUpdate(    ValueAnimator animation){
      columnManager.setAnimationProgress(animation.getAnimatedFraction());
      checkForRelayout();
      invalidate();
    }
  }
);
  animator.addListener(new AnimatorListenerAdapter(){
    @Override public void onAnimationEnd(    Animator animation){
      columnManager.onAnimationEnd();
      checkForRelayout();
    }
  }
);
}
