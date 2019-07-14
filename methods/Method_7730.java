private void processViewColor(View child,int color){
  for (int b=0; b < listClasses.length; b++) {
    if (listClasses[b].isInstance(child)) {
      child.invalidate();
      boolean passedCheck;
      if ((changeFlags & FLAG_CHECKTAG) == 0 || checkTag(currentKey,child)) {
        passedCheck=true;
        child.invalidate();
        if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
          Drawable drawable=child.getBackground();
          if (drawable != null) {
            if ((changeFlags & FLAG_CELLBACKGROUNDCOLOR) != 0) {
              if (drawable instanceof CombinedDrawable) {
                Drawable back=((CombinedDrawable)drawable).getBackground();
                if (back instanceof ColorDrawable) {
                  ((ColorDrawable)back).setColor(color);
                }
              }
            }
 else {
              if (drawable instanceof CombinedDrawable) {
                drawable=((CombinedDrawable)drawable).getIcon();
              }
 else               if (drawable instanceof StateListDrawable || Build.VERSION.SDK_INT >= 21 && drawable instanceof RippleDrawable) {
                Theme.setSelectorDrawableColor(drawable,color,(changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
              }
              drawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
            }
          }
        }
 else         if ((changeFlags & FLAG_CELLBACKGROUNDCOLOR) != 0) {
          child.setBackgroundColor(color);
        }
 else         if ((changeFlags & FLAG_TEXTCOLOR) != 0) {
          if (child instanceof TextView) {
            ((TextView)child).setTextColor(color);
          }
        }
 else         if ((changeFlags & FLAG_SERVICEBACKGROUND) != 0) {
          Drawable background=child.getBackground();
          if (background != null) {
            background.setColorFilter(Theme.colorFilter);
          }
        }
 else         if ((changeFlags & FLAG_SELECTOR) != 0) {
          child.setBackgroundDrawable(Theme.getSelectorDrawable(false));
        }
 else         if ((changeFlags & FLAG_SELECTORWHITE) != 0) {
          child.setBackgroundDrawable(Theme.getSelectorDrawable(true));
        }
      }
 else {
        passedCheck=false;
      }
      if (listClassesFieldName != null) {
        String key=listClasses[b] + "_" + listClassesFieldName[b];
        if (notFoundCachedFields != null && notFoundCachedFields.containsKey(key)) {
          continue;
        }
        try {
          Field field=cachedFields.get(key);
          if (field == null) {
            field=listClasses[b].getDeclaredField(listClassesFieldName[b]);
            if (field != null) {
              field.setAccessible(true);
              cachedFields.put(key,field);
            }
          }
          if (field != null) {
            Object object=field.get(child);
            if (object != null) {
              if (!passedCheck && object instanceof View && !checkTag(currentKey,(View)object)) {
                continue;
              }
              if (object instanceof View) {
                ((View)object).invalidate();
              }
              if (lottieLayerName != null && object instanceof LottieAnimationView) {
                ((LottieAnimationView)object).addValueCallback(new KeyPath(lottieLayerName,"**"),LottieProperty.COLOR_FILTER,new LottieValueCallback<>(new SimpleColorFilter(color)));
              }
              if ((changeFlags & FLAG_USEBACKGROUNDDRAWABLE) != 0 && object instanceof View) {
                object=((View)object).getBackground();
              }
              if ((changeFlags & FLAG_BACKGROUND) != 0 && object instanceof View) {
                View view=(View)object;
                Drawable background=view.getBackground();
                if (background instanceof MessageBackgroundDrawable) {
                  ((MessageBackgroundDrawable)background).setColor(color);
                }
 else {
                  view.setBackgroundColor(color);
                }
              }
 else               if (object instanceof EditTextCaption) {
                if ((changeFlags & FLAG_HINTTEXTCOLOR) != 0) {
                  ((EditTextCaption)object).setHintColor(color);
                  ((EditTextCaption)object).setHintTextColor(color);
                }
 else {
                  ((EditTextCaption)object).setTextColor(color);
                }
              }
 else               if (object instanceof SimpleTextView) {
                if ((changeFlags & FLAG_LINKCOLOR) != 0) {
                  ((SimpleTextView)object).setLinkTextColor(color);
                }
 else {
                  ((SimpleTextView)object).setTextColor(color);
                }
              }
 else               if (object instanceof TextView) {
                TextView textView=(TextView)object;
                if ((changeFlags & FLAG_IMAGECOLOR) != 0) {
                  Drawable[] drawables=textView.getCompoundDrawables();
                  if (drawables != null) {
                    for (int a=0; a < drawables.length; a++) {
                      if (drawables[a] != null) {
                        drawables[a].setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                      }
                    }
                  }
                }
 else                 if ((changeFlags & FLAG_LINKCOLOR) != 0) {
                  textView.getPaint().linkColor=color;
                  textView.invalidate();
                }
 else                 if ((changeFlags & FLAG_FASTSCROLL) != 0) {
                  CharSequence text=textView.getText();
                  if (text instanceof SpannedString) {
                    TypefaceSpan[] spans=((SpannedString)text).getSpans(0,text.length(),TypefaceSpan.class);
                    if (spans != null && spans.length > 0) {
                      for (int i=0; i < spans.length; i++) {
                        spans[i].setColor(color);
                      }
                    }
                  }
                }
 else {
                  textView.setTextColor(color);
                }
              }
 else               if (object instanceof ImageView) {
                ((ImageView)object).setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
              }
 else               if (object instanceof BackupImageView) {
                Drawable drawable=((BackupImageView)object).getImageReceiver().getStaticThumb();
                if (drawable instanceof CombinedDrawable) {
                  if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                    ((CombinedDrawable)drawable).getBackground().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                  }
 else {
                    ((CombinedDrawable)drawable).getIcon().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                  }
                }
 else                 if (drawable != null) {
                  drawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                }
              }
 else               if (object instanceof Drawable) {
                if (object instanceof LetterDrawable) {
                  if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                    ((LetterDrawable)object).setBackgroundColor(color);
                  }
 else {
                    ((LetterDrawable)object).setColor(color);
                  }
                }
 else                 if (object instanceof CombinedDrawable) {
                  if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
                    ((CombinedDrawable)object).getBackground().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                  }
 else {
                    ((CombinedDrawable)object).getIcon().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                  }
                }
 else                 if (object instanceof StateListDrawable || Build.VERSION.SDK_INT >= 21 && object instanceof RippleDrawable) {
                  Theme.setSelectorDrawableColor((Drawable)object,color,(changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
                }
 else                 if (object instanceof GradientDrawable) {
                  ((GradientDrawable)object).setColor(color);
                }
 else {
                  ((Drawable)object).setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
                }
              }
 else               if (object instanceof CheckBox) {
                if ((changeFlags & FLAG_CHECKBOX) != 0) {
                  ((CheckBox)object).setBackgroundColor(color);
                }
 else                 if ((changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                  ((CheckBox)object).setCheckColor(color);
                }
              }
 else               if (object instanceof GroupCreateCheckBox) {
                ((GroupCreateCheckBox)object).updateColors();
              }
 else               if (object instanceof Integer) {
                field.set(child,color);
              }
 else               if (object instanceof RadioButton) {
                if ((changeFlags & FLAG_CHECKBOX) != 0) {
                  ((RadioButton)object).setBackgroundColor(color);
                  ((RadioButton)object).invalidate();
                }
 else                 if ((changeFlags & FLAG_CHECKBOXCHECK) != 0) {
                  ((RadioButton)object).setCheckedColor(color);
                  ((RadioButton)object).invalidate();
                }
              }
 else               if (object instanceof TextPaint) {
                if ((changeFlags & FLAG_LINKCOLOR) != 0) {
                  ((TextPaint)object).linkColor=color;
                }
 else {
                  ((TextPaint)object).setColor(color);
                }
              }
 else               if (object instanceof LineProgressView) {
                if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
                  ((LineProgressView)object).setProgressColor(color);
                }
 else {
                  ((LineProgressView)object).setBackColor(color);
                }
              }
 else               if (object instanceof Paint) {
                ((Paint)object).setColor(color);
              }
 else               if (object instanceof SeekBarView) {
                if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
                  ((SeekBarView)object).setOuterColor(color);
                }
 else {
                  ((SeekBarView)object).setInnerColor(color);
                }
              }
            }
          }
        }
 catch (        Throwable e) {
          FileLog.e(e);
          notFoundCachedFields.put(key,true);
        }
      }
 else       if (child instanceof GroupCreateSpan) {
        ((GroupCreateSpan)child).updateColors();
      }
    }
  }
}
