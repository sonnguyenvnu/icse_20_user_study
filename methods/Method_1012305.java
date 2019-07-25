/** 
 * Creates a new instance of  {@code AutofitHelper} that wraps a {@link TextView} and enablesautomatically sizing the text to fit.
 */
public static AutoFitHelper create(TextView view,AttributeSet attrs,int defStyle){
  AutoFitHelper helper=new AutoFitHelper(view);
  boolean enableFit=true;
  if (attrs != null) {
    Context context=view.getContext();
    int minTextSize=(int)helper.getMinTextSize();
    float precision=helper.getPrecision();
    TypedArray ta=context.obtainStyledAttributes(attrs,R.styleable.AutoFitTextView,defStyle,0);
    enableFit=ta.getBoolean(R.styleable.AutoFitTextView_aftv_enable,enableFit);
    minTextSize=ta.getDimensionPixelSize(R.styleable.AutoFitTextView_aftv_minTextSize,minTextSize);
    precision=ta.getFloat(R.styleable.AutoFitTextView_aftv_precision,precision);
    ta.recycle();
    helper.setMinTextSize(TypedValue.COMPLEX_UNIT_PX,minTextSize).setPrecision(precision);
  }
  helper.setEnabled(enableFit);
  return helper;
}
