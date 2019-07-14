private void initEvent(){
  mEtMobile.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
    }
    @Override public void afterTextChanged(    Editable s){
      if (!TextUtils.isEmpty(s) && mIvCleanPhone.getVisibility() == View.GONE) {
        mIvCleanPhone.setVisibility(View.VISIBLE);
      }
 else       if (TextUtils.isEmpty(s)) {
        mIvCleanPhone.setVisibility(View.GONE);
      }
    }
  }
);
  mEtPassword.addTextChangedListener(new TextWatcher(){
    @Override public void beforeTextChanged(    CharSequence s,    int start,    int count,    int after){
    }
    @Override public void onTextChanged(    CharSequence s,    int start,    int before,    int count){
    }
    @Override public void afterTextChanged(    Editable s){
      if (!TextUtils.isEmpty(s) && mCleanPassword.getVisibility() == View.GONE) {
        mCleanPassword.setVisibility(View.VISIBLE);
      }
 else       if (TextUtils.isEmpty(s)) {
        mCleanPassword.setVisibility(View.GONE);
      }
      if (s.toString().isEmpty()) {
        return;
      }
      if (!s.toString().matches("[A-Za-z0-9]+")) {
        String temp=s.toString();
        Toast.makeText(mContext,"????????",Toast.LENGTH_SHORT).show();
        s.delete(temp.length() - 1,temp.length());
        mEtPassword.setSelection(s.length());
      }
    }
  }
);
  mScrollView.setOnTouchListener(new View.OnTouchListener(){
    @Override public boolean onTouch(    View v,    MotionEvent event){
      return true;
    }
  }
);
  mScrollView.addOnLayoutChangeListener(new ViewGroup.OnLayoutChangeListener(){
    @Override public void onLayoutChange(    View v,    int left,    int top,    int right,    int bottom,    int oldLeft,    int oldTop,    int oldRight,    int oldBottom){
      if (oldBottom != 0 && bottom != 0 && (oldBottom - bottom > keyHeight)) {
        Log.e("wenzhihao","up------>" + (oldBottom - bottom));
        int dist=mContent.getBottom() - bottom;
        if (dist > 0) {
          ObjectAnimator mAnimatorTranslateY=ObjectAnimator.ofFloat(mContent,"translationY",0.0f,-dist);
          mAnimatorTranslateY.setDuration(300);
          mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
          mAnimatorTranslateY.start();
          RxAnimationTool.zoomIn(mLogo,0.6f,dist);
        }
        mService.setVisibility(View.INVISIBLE);
      }
 else       if (oldBottom != 0 && bottom != 0 && (bottom - oldBottom > keyHeight)) {
        Log.e("wenzhihao","down------>" + (bottom - oldBottom));
        if ((mContent.getBottom() - oldBottom) > 0) {
          ObjectAnimator mAnimatorTranslateY=ObjectAnimator.ofFloat(mContent,"translationY",mContent.getTranslationY(),0);
          mAnimatorTranslateY.setDuration(300);
          mAnimatorTranslateY.setInterpolator(new LinearInterpolator());
          mAnimatorTranslateY.start();
          RxAnimationTool.zoomOut(mLogo,0.6f);
        }
        mService.setVisibility(View.VISIBLE);
      }
    }
  }
);
  mBtnLogin.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      RxKeyboardTool.hideSoftInput(mContext);
    }
  }
);
}
