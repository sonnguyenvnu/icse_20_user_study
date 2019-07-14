void applyStyle(InternalNode node,@AttrRes int defStyleAttr,@StyleRes int defStyleRes){
  if (defStyleAttr != 0 || defStyleRes != 0) {
    setDefStyle(defStyleAttr,defStyleRes);
    final TypedArray typedArray=mContext.obtainStyledAttributes(null,R.styleable.ComponentLayout,defStyleAttr,defStyleRes);
    node.applyAttributes(typedArray);
    typedArray.recycle();
    setDefStyle(0,0);
  }
}
