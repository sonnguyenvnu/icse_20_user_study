/** 
 * ????
 * @param view
 */
public static void zoomIn(final View view,float scale,float dist){
  view.setPivotY(view.getHeight());
  view.setPivotX(view.getWidth() / 2);
  AnimatorSet mAnimatorSet=new AnimatorSet();
  ObjectAnimator mAnimatorScaleX=ObjectAnimator.ofFloat(view,"scaleX",1.0f,scale);
  ObjectAnimator mAnimatorScaleY=ObjectAnimator.ofFloat(view,"scaleY",1.0f,scale);
  ObjectAnimator mAnimatorTranslateY=ObjectAnimator.ofFloat(view,"translationY",0.0f,-dist);
  mAnimatorSet.play(mAnimatorTranslateY).with(mAnimatorScaleX);
  mAnimatorSet.play(mAnimatorScaleX).with(mAnimatorScaleY);
  mAnimatorSet.setDuration(300);
  mAnimatorSet.start();
}
