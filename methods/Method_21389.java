protected void initialize(final @NonNull Context context,final @Nullable AttributeSet attrs,final int defStyleAttr,final int defStyleRes){
  final TypedArray attributes=context.obtainStyledAttributes(attrs,R.styleable.IconTextView,defStyleAttr,defStyleRes);
  this.iconType=attributes.getInt(R.styleable.IconTextView_iconType,DEFAULT_ICON_TYPE);
  attributes.recycle();
}
