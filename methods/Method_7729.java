public void setColor(int color,boolean useDefault,boolean save){
  if (save) {
    Theme.setColor(currentKey,color,useDefault);
  }
  if (paintToUpdate != null) {
    for (int a=0; a < paintToUpdate.length; a++) {
      if ((changeFlags & FLAG_LINKCOLOR) != 0 && paintToUpdate[a] instanceof TextPaint) {
        ((TextPaint)paintToUpdate[a]).linkColor=color;
      }
 else {
        paintToUpdate[a].setColor(color);
      }
    }
  }
  if (drawablesToUpdate != null) {
    for (int a=0; a < drawablesToUpdate.length; a++) {
      if (drawablesToUpdate[a] == null) {
        continue;
      }
      if (drawablesToUpdate[a] instanceof ScamDrawable) {
        ((ScamDrawable)drawablesToUpdate[a]).setColor(color);
      }
 else       if (drawablesToUpdate[a] instanceof LottieDrawable) {
        if (lottieLayerName != null) {
          ((LottieDrawable)drawablesToUpdate[a]).addValueCallback(new KeyPath(lottieLayerName,"**"),LottieProperty.COLOR_FILTER,new LottieValueCallback<>(new SimpleColorFilter(color)));
        }
      }
 else       if (drawablesToUpdate[a] instanceof CombinedDrawable) {
        if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
          ((CombinedDrawable)drawablesToUpdate[a]).getBackground().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
        }
 else {
          ((CombinedDrawable)drawablesToUpdate[a]).getIcon().setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
        }
      }
 else       if (drawablesToUpdate[a] instanceof AvatarDrawable) {
        ((AvatarDrawable)drawablesToUpdate[a]).setColor(color);
      }
 else {
        drawablesToUpdate[a].setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
      }
    }
  }
  if (viewToInvalidate != null && listClasses == null && listClassesFieldName == null) {
    if ((changeFlags & FLAG_CHECKTAG) == 0 || checkTag(currentKey,viewToInvalidate)) {
      if ((changeFlags & FLAG_BACKGROUND) != 0) {
        Drawable background=viewToInvalidate.getBackground();
        if (background instanceof MessageBackgroundDrawable) {
          ((MessageBackgroundDrawable)background).setColor(color);
        }
 else {
          viewToInvalidate.setBackgroundColor(color);
        }
      }
      if ((changeFlags & FLAG_BACKGROUNDFILTER) != 0) {
        if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
          if (viewToInvalidate instanceof EditTextBoldCursor) {
            ((EditTextBoldCursor)viewToInvalidate).setErrorLineColor(color);
          }
        }
 else {
          Drawable drawable=viewToInvalidate.getBackground();
          if (drawable instanceof CombinedDrawable) {
            if ((changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0) {
              drawable=((CombinedDrawable)drawable).getBackground();
            }
 else {
              drawable=((CombinedDrawable)drawable).getIcon();
            }
          }
          if (drawable != null) {
            if (drawable instanceof StateListDrawable || Build.VERSION.SDK_INT >= 21 && drawable instanceof RippleDrawable) {
              Theme.setSelectorDrawableColor(drawable,color,(changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
            }
 else             if (drawable instanceof ShapeDrawable) {
              ((ShapeDrawable)drawable).getPaint().setColor(color);
            }
 else {
              drawable.setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
            }
          }
        }
      }
    }
  }
  if (viewToInvalidate instanceof ActionBar) {
    if ((changeFlags & FLAG_AB_ITEMSCOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setItemsColor(color,false);
    }
    if ((changeFlags & FLAG_AB_TITLECOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setTitleColor(color);
    }
    if ((changeFlags & FLAG_AB_SELECTORCOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setItemsBackgroundColor(color,false);
    }
    if ((changeFlags & FLAG_AB_AM_SELECTORCOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setItemsBackgroundColor(color,true);
    }
    if ((changeFlags & FLAG_AB_AM_ITEMSCOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setItemsColor(color,true);
    }
    if ((changeFlags & FLAG_AB_SUBTITLECOLOR) != 0) {
      ((ActionBar)viewToInvalidate).setSubtitleColor(color);
    }
    if ((changeFlags & FLAG_AB_AM_BACKGROUND) != 0) {
      ((ActionBar)viewToInvalidate).setActionModeColor(color);
    }
    if ((changeFlags & FLAG_AB_AM_TOPBACKGROUND) != 0) {
      ((ActionBar)viewToInvalidate).setActionModeTopColor(color);
    }
    if ((changeFlags & FLAG_AB_SEARCHPLACEHOLDER) != 0) {
      ((ActionBar)viewToInvalidate).setSearchTextColor(color,true);
    }
    if ((changeFlags & FLAG_AB_SEARCH) != 0) {
      ((ActionBar)viewToInvalidate).setSearchTextColor(color,false);
    }
    if ((changeFlags & FLAG_AB_SUBMENUITEM) != 0) {
      ((ActionBar)viewToInvalidate).setPopupItemsColor(color,(changeFlags & FLAG_IMAGECOLOR) != 0);
    }
    if ((changeFlags & FLAG_AB_SUBMENUBACKGROUND) != 0) {
      ((ActionBar)viewToInvalidate).setPopupBackgroundColor(color);
    }
  }
  if (viewToInvalidate instanceof EmptyTextProgressView) {
    if ((changeFlags & FLAG_TEXTCOLOR) != 0) {
      ((EmptyTextProgressView)viewToInvalidate).setTextColor(color);
    }
 else     if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
      ((EmptyTextProgressView)viewToInvalidate).setProgressBarColor(color);
    }
  }
  if (viewToInvalidate instanceof RadialProgressView) {
    ((RadialProgressView)viewToInvalidate).setProgressColor(color);
  }
 else   if (viewToInvalidate instanceof LineProgressView) {
    if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
      ((LineProgressView)viewToInvalidate).setProgressColor(color);
    }
 else {
      ((LineProgressView)viewToInvalidate).setBackColor(color);
    }
  }
 else   if (viewToInvalidate instanceof ContextProgressView) {
    ((ContextProgressView)viewToInvalidate).updateColors();
  }
  if ((changeFlags & FLAG_TEXTCOLOR) != 0) {
    if ((changeFlags & FLAG_CHECKTAG) == 0 || checkTag(currentKey,viewToInvalidate)) {
      if (viewToInvalidate instanceof TextView) {
        ((TextView)viewToInvalidate).setTextColor(color);
      }
 else       if (viewToInvalidate instanceof NumberTextView) {
        ((NumberTextView)viewToInvalidate).setTextColor(color);
      }
 else       if (viewToInvalidate instanceof SimpleTextView) {
        ((SimpleTextView)viewToInvalidate).setTextColor(color);
      }
 else       if (viewToInvalidate instanceof ChatBigEmptyView) {
        ((ChatBigEmptyView)viewToInvalidate).setTextColor(color);
      }
    }
  }
  if ((changeFlags & FLAG_CURSORCOLOR) != 0) {
    if (viewToInvalidate instanceof EditTextBoldCursor) {
      ((EditTextBoldCursor)viewToInvalidate).setCursorColor(color);
    }
  }
  if ((changeFlags & FLAG_HINTTEXTCOLOR) != 0) {
    if (viewToInvalidate instanceof EditTextBoldCursor) {
      if ((changeFlags & FLAG_PROGRESSBAR) != 0) {
        ((EditTextBoldCursor)viewToInvalidate).setHeaderHintColor(color);
      }
 else {
        ((EditTextBoldCursor)viewToInvalidate).setHintColor(color);
      }
    }
 else     if (viewToInvalidate instanceof EditText) {
      ((EditText)viewToInvalidate).setHintTextColor(color);
    }
  }
  if (viewToInvalidate != null && (changeFlags & FLAG_SERVICEBACKGROUND) != 0) {
    Drawable background=viewToInvalidate.getBackground();
    if (background != null) {
      background.setColorFilter(Theme.colorFilter);
    }
  }
  if ((changeFlags & FLAG_IMAGECOLOR) != 0) {
    if ((changeFlags & FLAG_CHECKTAG) == 0 || checkTag(currentKey,viewToInvalidate)) {
      if (viewToInvalidate instanceof ImageView) {
        if ((changeFlags & FLAG_USEBACKGROUNDDRAWABLE) != 0) {
          Drawable drawable=((ImageView)viewToInvalidate).getDrawable();
          if (drawable instanceof StateListDrawable || Build.VERSION.SDK_INT >= 21 && drawable instanceof RippleDrawable) {
            Theme.setSelectorDrawableColor(drawable,color,(changeFlags & FLAG_DRAWABLESELECTEDSTATE) != 0);
          }
        }
 else {
          ((ImageView)viewToInvalidate).setColorFilter(new PorterDuffColorFilter(color,PorterDuff.Mode.MULTIPLY));
        }
      }
 else       if (viewToInvalidate instanceof BackupImageView) {
      }
 else       if (viewToInvalidate instanceof SimpleTextView) {
        SimpleTextView textView=(SimpleTextView)viewToInvalidate;
        textView.setSideDrawablesColor(color);
      }
    }
  }
  if (viewToInvalidate instanceof ScrollView) {
    if ((changeFlags & FLAG_LISTGLOWCOLOR) != 0) {
      AndroidUtilities.setScrollViewEdgeEffectColor((ScrollView)viewToInvalidate,color);
    }
  }
  if (viewToInvalidate instanceof ViewPager) {
    if ((changeFlags & FLAG_LISTGLOWCOLOR) != 0) {
      AndroidUtilities.setViewPagerEdgeEffectColor((ViewPager)viewToInvalidate,color);
    }
  }
  if (viewToInvalidate instanceof RecyclerListView) {
    RecyclerListView recyclerListView=(RecyclerListView)viewToInvalidate;
    if ((changeFlags & FLAG_SELECTOR) != 0) {
      if (currentKey.equals(Theme.key_listSelector)) {
        recyclerListView.setListSelectorColor(color);
      }
    }
    if ((changeFlags & FLAG_FASTSCROLL) != 0) {
      recyclerListView.updateFastScrollColors();
    }
    if ((changeFlags & FLAG_LISTGLOWCOLOR) != 0) {
      recyclerListView.setGlowColor(color);
    }
    if ((changeFlags & FLAG_SECTIONS) != 0) {
      ArrayList<View> headers=recyclerListView.getHeaders();
      if (headers != null) {
        for (int a=0; a < headers.size(); a++) {
          processViewColor(headers.get(a),color);
        }
      }
      headers=recyclerListView.getHeadersCache();
      if (headers != null) {
        for (int a=0; a < headers.size(); a++) {
          processViewColor(headers.get(a),color);
        }
      }
      View header=recyclerListView.getPinnedHeader();
      if (header != null) {
        processViewColor(header,color);
      }
    }
  }
 else   if (viewToInvalidate != null && (listClasses == null || listClasses.length == 0)) {
    if ((changeFlags & FLAG_SELECTOR) != 0) {
      viewToInvalidate.setBackgroundDrawable(Theme.getSelectorDrawable(false));
    }
 else     if ((changeFlags & FLAG_SELECTORWHITE) != 0) {
      viewToInvalidate.setBackgroundDrawable(Theme.getSelectorDrawable(true));
    }
  }
  if (listClasses != null) {
    if (viewToInvalidate instanceof RecyclerListView) {
      RecyclerListView recyclerListView=(RecyclerListView)viewToInvalidate;
      recyclerListView.getRecycledViewPool().clear();
      int count=recyclerListView.getHiddenChildCount();
      for (int a=0; a < count; a++) {
        processViewColor(recyclerListView.getHiddenChildAt(a),color);
      }
      count=recyclerListView.getCachedChildCount();
      for (int a=0; a < count; a++) {
        processViewColor(recyclerListView.getCachedChildAt(a),color);
      }
      count=recyclerListView.getAttachedScrapChildCount();
      for (int a=0; a < count; a++) {
        processViewColor(recyclerListView.getAttachedScrapChildAt(a),color);
      }
    }
    if (viewToInvalidate instanceof ViewGroup) {
      ViewGroup viewGroup=(ViewGroup)viewToInvalidate;
      int count=viewGroup.getChildCount();
      for (int a=0; a < count; a++) {
        processViewColor(viewGroup.getChildAt(a),color);
      }
    }
    processViewColor(viewToInvalidate,color);
  }
  currentColor=color;
  if (delegate != null) {
    delegate.didSetColor();
  }
  if (viewToInvalidate != null) {
    viewToInvalidate.invalidate();
  }
}
