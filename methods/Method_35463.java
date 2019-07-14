public static CharacterDrawable create(Context context,char character,boolean roundAsCircle,@DimenRes int padding){
  return new CharacterDrawable.Builder().setCharacter(character).setBackgroundRoundAsCircle(roundAsCircle).setCharacterPadding(context.getResources().getDimensionPixelSize(padding)).build();
}
