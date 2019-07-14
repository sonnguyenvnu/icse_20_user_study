private void bindCommonDynamicProp(int key,DynamicValue<?> value,View target){
switch (key) {
case KEY_ALPHA:
    target.setAlpha(DynamicPropsManager.<Float>resolve(value));
  break;
case KEY_TRANSLATION_X:
target.setTranslationX(DynamicPropsManager.<Float>resolve(value));
break;
case KEY_TRANSLATION_Y:
target.setTranslationY(DynamicPropsManager.<Float>resolve(value));
break;
case KEY_SCALE_X:
target.setScaleX(DynamicPropsManager.<Float>resolve(value));
break;
case KEY_SCALE_Y:
target.setScaleY(DynamicPropsManager.<Float>resolve(value));
break;
case KEY_ELEVATION:
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
target.setElevation(DynamicPropsManager.<Float>resolve(value));
}
break;
case KEY_BACKGROUND_COLOR:
target.setBackgroundColor(DynamicPropsManager.<Integer>resolve(value));
break;
}
}
