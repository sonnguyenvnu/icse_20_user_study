public void setNumber(int number,boolean animated){
  if (currentNumber == number && animated) {
    return;
  }
  if (animator != null) {
    animator.cancel();
    animator=null;
  }
  oldLetters.clear();
  oldLetters.addAll(letters);
  letters.clear();
  String oldText=String.format(Locale.US,"%d",currentNumber);
  String text=String.format(Locale.US,"%d",number);
  boolean forwardAnimation=number > currentNumber;
  currentNumber=number;
  progress=0;
  for (int a=0; a < text.length(); a++) {
    String ch=text.substring(a,a + 1);
    String oldCh=!oldLetters.isEmpty() && a < oldText.length() ? oldText.substring(a,a + 1) : null;
    if (oldCh != null && oldCh.equals(ch)) {
      letters.add(oldLetters.get(a));
      oldLetters.set(a,null);
    }
 else {
      StaticLayout layout=new StaticLayout(ch,textPaint,(int)Math.ceil(textPaint.measureText(ch)),Layout.Alignment.ALIGN_NORMAL,1.0f,0.0f,false);
      letters.add(layout);
    }
  }
  if (animated && !oldLetters.isEmpty()) {
    animator=ObjectAnimator.ofFloat(this,"progress",forwardAnimation ? -1 : 1,0);
    animator.setDuration(150);
    animator.addListener(new AnimatorListenerAdapter(){
      @Override public void onAnimationEnd(      Animator animation){
        animator=null;
        oldLetters.clear();
      }
    }
);
    animator.start();
  }
  invalidate();
}
