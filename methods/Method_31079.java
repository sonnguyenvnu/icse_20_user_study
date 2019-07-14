public static void fadeOutThenFadeIn(@NonNull View fromView,@NonNull View toView,int duration,boolean gone){
  fadeOut(fromView,duration,gone,() -> fadeIn(toView,duration));
}
