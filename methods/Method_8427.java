@Override protected void onDraw(Canvas canvas){
  for (int i=0; i < numStars; i++) {
    paint.setColor(Theme.getColor(i < selectedRating ? Theme.key_dialogTextBlue : Theme.key_dialogTextHint));
    canvas.drawBitmap(i < selectedRating ? filledStar : hollowStar,i * AndroidUtilities.dp(32 + 16),0,paint);
  }
}
