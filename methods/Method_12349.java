/** 
 * Returns current transform bounds - latest received by  {@link #onBoundsChange(Rect)}.
 * @return current transform bounds
 */
@NonNull public RectF getBounds(){
  return mDstRectF;
}
