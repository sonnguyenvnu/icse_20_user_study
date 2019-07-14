private void createAddButton(Context context){
  mAddButton=new AddFloatingActionButton(context){
    @Override void updateBackground(){
      mPlusColor=mAddButtonPlusColor;
      mColorNormal=mAddButtonColorNormal;
      mColorPressed=mAddButtonColorPressed;
      mStrokeVisible=mAddButtonStrokeVisible;
      super.updateBackground();
    }
    @Override Drawable getIconDrawable(){
      final RotatingDrawable rotatingDrawable=new RotatingDrawable(super.getIconDrawable());
      mRotatingDrawable=rotatingDrawable;
      final OvershootInterpolator interpolator=new OvershootInterpolator();
      final ObjectAnimator collapseAnimator=ObjectAnimator.ofFloat(rotatingDrawable,"rotation",EXPANDED_PLUS_ROTATION,COLLAPSED_PLUS_ROTATION);
      final ObjectAnimator expandAnimator=ObjectAnimator.ofFloat(rotatingDrawable,"rotation",COLLAPSED_PLUS_ROTATION,EXPANDED_PLUS_ROTATION);
      collapseAnimator.setInterpolator(interpolator);
      expandAnimator.setInterpolator(interpolator);
      mExpandAnimation.play(expandAnimator);
      mCollapseAnimation.play(collapseAnimator);
      return rotatingDrawable;
    }
  }
;
  mAddButton.setId(R.id.fab_expand_menu_button);
  mAddButton.setSize(mAddButtonSize);
  mAddButton.setOnClickListener(new OnClickListener(){
    @Override public void onClick(    View v){
      toggle();
    }
  }
);
  addView(mAddButton,super.generateDefaultLayoutParams());
  mButtonsCount++;
}
