private void init(Context context,AttributeSet attrs,int defStyleAttr,int defStyleRes){
  TypedArray typedArray=context.getTheme().obtainStyledAttributes(attrs,R.styleable.CharacterView,defStyleAttr,defStyleRes);
  try {
    mCharacter=typedArray.getString(R.styleable.CharacterView_character);
    mCharacterTextColor=typedArray.getColor(R.styleable.CharacterView_characterTextColor,0);
    mBackgroundRoundAsCircle=typedArray.getBoolean(R.styleable.CharacterView_backgroundRoundAsCircle,false);
    mBackgroundColor=typedArray.getColor(R.styleable.CharacterView_backgroundColor,0);
    mBackgroundRadius=typedArray.getDimension(R.styleable.CharacterView_backgroundRadius,0);
    mCharacterPadding=typedArray.getDimension(R.styleable.CharacterView_characterPadding,0);
    mDrawable=new CharacterDrawable.Builder().setCharacter(mCharacter).setCharacterTextColor(mCharacterTextColor).setBackgroundRoundAsCircle(mBackgroundRoundAsCircle).setBackgroundColor(mBackgroundColor).setBackgroundRadius(mBackgroundRadius).setCharacterPadding(mCharacterPadding).build();
  }
  finally {
    typedArray.recycle();
  }
  setLayerType(View.LAYER_TYPE_SOFTWARE,null);
}
