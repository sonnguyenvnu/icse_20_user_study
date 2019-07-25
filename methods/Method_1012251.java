@SuppressWarnings("ConstantConditions") @UiThread public static void init(final MaterialDialog dialog){
  final MaterialDialog.Builder builder=dialog.builder;
  dialog.setCancelable(builder.cancelable);
  dialog.setCanceledOnTouchOutside(builder.canceledOnTouchOutside);
  if (builder.backgroundColor == 0) {
    builder.backgroundColor=ThemeUtils.resolveColor(builder.context,R.attr.md_background_color,ThemeUtils.resolveColor(dialog.getContext(),R.attr.colorBackgroundFloating));
  }
  if (builder.backgroundColor != 0) {
    GradientDrawable drawable=new GradientDrawable();
    drawable.setCornerRadius(builder.context.getResources().getDimension(R.dimen.md_bg_corner_radius));
    drawable.setColor(builder.backgroundColor);
    dialog.getWindow().setBackgroundDrawable(drawable);
  }
  if (!builder.positiveColorSet) {
    builder.positiveColor=ThemeUtils.resolveActionTextColorStateList(builder.context,R.attr.md_positive_color,builder.positiveColor);
  }
  if (!builder.neutralColorSet) {
    builder.neutralColor=ThemeUtils.resolveActionTextColorStateList(builder.context,R.attr.md_neutral_color,builder.neutralColor);
  }
  if (!builder.negativeColorSet) {
    builder.negativeColor=ThemeUtils.resolveActionTextColorStateList(builder.context,R.attr.md_negative_color,builder.negativeColor);
  }
  if (!builder.widgetColorSet) {
    builder.widgetColor=ThemeUtils.resolveColor(builder.context,R.attr.md_widget_color,builder.widgetColor);
  }
  if (!builder.titleColorSet) {
    final int titleColorFallback=ThemeUtils.resolveColor(dialog.getContext(),android.R.attr.textColorPrimary);
    builder.titleColor=ThemeUtils.resolveColor(builder.context,R.attr.md_title_color,titleColorFallback);
  }
  if (!builder.contentColorSet) {
    final int contentColorFallback=ThemeUtils.resolveColor(dialog.getContext(),android.R.attr.textColorSecondary);
    builder.contentColor=ThemeUtils.resolveColor(builder.context,R.attr.md_content_color,contentColorFallback);
  }
  if (!builder.itemColorSet) {
    builder.itemColor=ThemeUtils.resolveColor(builder.context,R.attr.md_item_color,builder.contentColor);
  }
  dialog.title=(TextView)dialog.view.findViewById(R.id.md_title);
  dialog.icon=(ImageView)dialog.view.findViewById(R.id.md_icon);
  dialog.titleFrame=dialog.view.findViewById(R.id.md_titleFrame);
  dialog.content=(TextView)dialog.view.findViewById(R.id.md_content);
  dialog.recyclerView=(RecyclerView)dialog.view.findViewById(R.id.md_contentRecyclerView);
  dialog.checkBoxPrompt=(CheckBox)dialog.view.findViewById(R.id.md_promptCheckbox);
  dialog.positiveButton=(MDButton)dialog.view.findViewById(R.id.md_buttonDefaultPositive);
  dialog.neutralButton=(MDButton)dialog.view.findViewById(R.id.md_buttonDefaultNeutral);
  dialog.negativeButton=(MDButton)dialog.view.findViewById(R.id.md_buttonDefaultNegative);
  if (builder.inputCallback != null && builder.positiveText == null) {
    builder.positiveText=builder.context.getText(android.R.string.ok);
  }
  dialog.positiveButton.setVisibility(builder.positiveText != null ? View.VISIBLE : View.GONE);
  dialog.neutralButton.setVisibility(builder.neutralText != null ? View.VISIBLE : View.GONE);
  dialog.negativeButton.setVisibility(builder.negativeText != null ? View.VISIBLE : View.GONE);
  dialog.positiveButton.setFocusable(true);
  dialog.neutralButton.setFocusable(true);
  dialog.negativeButton.setFocusable(true);
  if (builder.positiveFocus) {
    dialog.positiveButton.requestFocus();
  }
  if (builder.neutralFocus) {
    dialog.neutralButton.requestFocus();
  }
  if (builder.negativeFocus) {
    dialog.negativeButton.requestFocus();
  }
  if (builder.icon != null) {
    dialog.icon.setVisibility(View.VISIBLE);
    dialog.icon.setImageDrawable(builder.icon);
  }
 else {
    Drawable d=ThemeUtils.resolveDrawable(builder.context,R.attr.md_icon);
    if (d != null) {
      dialog.icon.setVisibility(View.VISIBLE);
      dialog.icon.setImageDrawable(d);
    }
 else {
      dialog.icon.setVisibility(View.GONE);
    }
  }
  int maxIconSize=builder.maxIconSize;
  if (maxIconSize == -1) {
    maxIconSize=ThemeUtils.resolveDimension(builder.context,R.attr.md_icon_max_size);
  }
  if (builder.limitIconToDefaultSize || ThemeUtils.resolveBoolean(builder.context,R.attr.md_icon_limit_icon_to_default_size)) {
    maxIconSize=builder.context.getResources().getDimensionPixelSize(R.dimen.default_md_icon_max_size);
  }
  if (maxIconSize > -1) {
    dialog.icon.setAdjustViewBounds(true);
    dialog.icon.setMaxHeight(maxIconSize);
    dialog.icon.setMaxWidth(maxIconSize);
    dialog.icon.requestLayout();
  }
  if (!builder.dividerColorSet) {
    final int dividerFallback=ThemeUtils.resolveColor(dialog.getContext(),R.attr.md_divider);
    builder.dividerColor=ThemeUtils.resolveColor(builder.context,R.attr.md_divider_color,dividerFallback);
  }
  dialog.view.setDividerColor(builder.dividerColor);
  if (dialog.title != null) {
    dialog.setTypeface(dialog.title,builder.mediumFont);
    dialog.title.setTextColor(builder.titleColor);
    dialog.title.setGravity(builder.titleGravity.getGravityInt());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      dialog.title.setTextAlignment(builder.titleGravity.getTextAlignment());
    }
    if (builder.title == null) {
      dialog.titleFrame.setVisibility(View.GONE);
    }
 else {
      dialog.title.setText(builder.title);
      dialog.titleFrame.setVisibility(View.VISIBLE);
    }
  }
  if (dialog.content != null) {
    dialog.content.setMovementMethod(new LinkMovementMethod());
    dialog.setTypeface(dialog.content,builder.regularFont);
    dialog.content.setLineSpacing(0f,builder.contentLineSpacingMultiplier);
    if (builder.linkColor == null) {
      dialog.content.setLinkTextColor(ThemeUtils.resolveColor(dialog.getContext(),android.R.attr.textColorPrimary));
    }
 else {
      dialog.content.setLinkTextColor(builder.linkColor);
    }
    dialog.content.setTextColor(builder.contentColor);
    dialog.content.setGravity(builder.contentGravity.getGravityInt());
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
      dialog.content.setTextAlignment(builder.contentGravity.getTextAlignment());
    }
    if (builder.content != null) {
      dialog.content.setText(builder.content);
      dialog.content.setVisibility(View.VISIBLE);
    }
 else {
      dialog.content.setVisibility(View.GONE);
    }
  }
  if (dialog.checkBoxPrompt != null) {
    dialog.checkBoxPrompt.setText(builder.checkBoxPrompt);
    dialog.checkBoxPrompt.setChecked(builder.checkBoxPromptInitiallyChecked);
    dialog.checkBoxPrompt.setOnCheckedChangeListener(builder.checkBoxPromptListener);
    dialog.setTypeface(dialog.checkBoxPrompt,builder.regularFont);
    dialog.checkBoxPrompt.setTextColor(builder.contentColor);
    MDTintHelper.setTint(dialog.checkBoxPrompt,builder.widgetColor);
  }
  dialog.view.setButtonGravity(builder.buttonsGravity);
  dialog.view.setButtonStackedGravity(builder.btnStackedGravity);
  dialog.view.setStackingBehavior(builder.stackingBehavior);
  boolean textAllCaps;
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
    textAllCaps=ThemeUtils.resolveBoolean(builder.context,android.R.attr.textAllCaps,true);
    if (textAllCaps) {
      textAllCaps=ThemeUtils.resolveBoolean(builder.context,R.attr.textAllCaps,true);
    }
  }
 else {
    textAllCaps=ThemeUtils.resolveBoolean(builder.context,R.attr.textAllCaps,true);
  }
  MDButton positiveTextView=dialog.positiveButton;
  dialog.setTypeface(positiveTextView,builder.mediumFont);
  positiveTextView.setAllCapsCompat(textAllCaps);
  positiveTextView.setText(builder.positiveText);
  positiveTextView.setTextColor(builder.positiveColor);
  dialog.positiveButton.setStackedSelector(dialog.getButtonSelector(DialogAction.POSITIVE,true));
  dialog.positiveButton.setDefaultSelector(dialog.getButtonSelector(DialogAction.POSITIVE,false));
  dialog.positiveButton.setTag(DialogAction.POSITIVE);
  dialog.positiveButton.setOnClickListener(dialog);
  dialog.positiveButton.setVisibility(View.VISIBLE);
  MDButton negativeTextView=dialog.negativeButton;
  dialog.setTypeface(negativeTextView,builder.mediumFont);
  negativeTextView.setAllCapsCompat(textAllCaps);
  negativeTextView.setText(builder.negativeText);
  negativeTextView.setTextColor(builder.negativeColor);
  dialog.negativeButton.setStackedSelector(dialog.getButtonSelector(DialogAction.NEGATIVE,true));
  dialog.negativeButton.setDefaultSelector(dialog.getButtonSelector(DialogAction.NEGATIVE,false));
  dialog.negativeButton.setTag(DialogAction.NEGATIVE);
  dialog.negativeButton.setOnClickListener(dialog);
  dialog.negativeButton.setVisibility(View.VISIBLE);
  MDButton neutralTextView=dialog.neutralButton;
  dialog.setTypeface(neutralTextView,builder.mediumFont);
  neutralTextView.setAllCapsCompat(textAllCaps);
  neutralTextView.setText(builder.neutralText);
  neutralTextView.setTextColor(builder.neutralColor);
  dialog.neutralButton.setStackedSelector(dialog.getButtonSelector(DialogAction.NEUTRAL,true));
  dialog.neutralButton.setDefaultSelector(dialog.getButtonSelector(DialogAction.NEUTRAL,false));
  dialog.neutralButton.setTag(DialogAction.NEUTRAL);
  dialog.neutralButton.setOnClickListener(dialog);
  dialog.neutralButton.setVisibility(View.VISIBLE);
  if (builder.listCallbackMultiChoice != null) {
    dialog.selectedIndicesList=new ArrayList<>();
  }
  if (dialog.recyclerView != null) {
    if (builder.adapter == null) {
      if (builder.listCallbackSingleChoice != null) {
        dialog.listType=MaterialDialog.ListType.SINGLE;
      }
 else       if (builder.listCallbackMultiChoice != null) {
        dialog.listType=MaterialDialog.ListType.MULTI;
        if (builder.selectedIndices != null) {
          dialog.selectedIndicesList=new ArrayList<>(Arrays.asList(builder.selectedIndices));
          builder.selectedIndices=null;
        }
      }
 else {
        dialog.listType=MaterialDialog.ListType.REGULAR;
      }
      builder.adapter=new DefaultRvAdapter(dialog,MaterialDialog.ListType.getLayoutForType(dialog.listType));
    }
 else     if (builder.adapter instanceof MDAdapter) {
      ((MDAdapter)builder.adapter).setDialog(dialog);
    }
  }
  setupProgressDialog(dialog);
  setupInputDialog(dialog);
  if (builder.customView != null) {
    ((MDRootLayout)dialog.view.findViewById(R.id.md_root)).noTitleNoPadding();
    FrameLayout frame=dialog.view.findViewById(R.id.md_customViewFrame);
    dialog.customViewFrame=frame;
    View innerView=builder.customView;
    if (innerView.getParent() != null) {
      ((ViewGroup)innerView.getParent()).removeView(innerView);
    }
    if (builder.wrapCustomViewInScroll) {
      final Resources r=dialog.getContext().getResources();
      final int framePadding=ThemeUtils.resolveDimension(dialog.getContext(),R.attr.md_dialog_frame_margin,R.dimen.default_md_dialog_frame_margin);
      final ScrollView sv=new XUIKeyboardScrollView(dialog.getContext());
      int paddingTop=r.getDimensionPixelSize(R.dimen.md_content_padding_top);
      int paddingBottom=r.getDimensionPixelSize(R.dimen.md_content_padding_bottom);
      sv.setClipToPadding(false);
      if (innerView instanceof EditText) {
        sv.setPadding(framePadding,paddingTop,framePadding,paddingBottom);
      }
 else {
        sv.setPadding(0,paddingTop,0,paddingBottom);
        innerView.setPadding(framePadding,0,framePadding,0);
      }
      sv.addView(innerView,new ScrollView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
      innerView=sv;
    }
    frame.addView(innerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.WRAP_CONTENT));
  }
  if (builder.showListener != null) {
    dialog.setOnShowListener(builder.showListener);
  }
  if (builder.cancelListener != null) {
    dialog.setOnCancelListener(builder.cancelListener);
  }
  if (builder.dismissListener != null) {
    dialog.setOnDismissListener(builder.dismissListener);
  }
  if (builder.keyListener != null) {
    dialog.setOnKeyListener(builder.keyListener);
  }
  dialog.setOnShowListenerInternal();
  dialog.invalidateList();
  dialog.setViewInternal(dialog.view);
  dialog.checkIfListInitScroll();
  WindowManager wm=dialog.getWindow().getWindowManager();
  Display display=wm.getDefaultDisplay();
  Point size=new Point();
  display.getSize(size);
  final int windowWidth=size.x;
  final int windowHeight=size.y;
  int windowVerticalPadding=ThemeUtils.resolveDimension(builder.context,R.attr.md_dialog_vertical_margin,ResUtils.getDimensionPixelSize(R.dimen.default_md_dialog_vertical_margin_phone));
  int windowHorizontalPadding=ThemeUtils.resolveDimension(builder.context,R.attr.md_dialog_horizontal_margin,ResUtils.getDimensionPixelSize(R.dimen.default_md_dialog_horizontal_margin_phone));
  int maxWidth=ThemeUtils.resolveDimension(builder.context,R.attr.md_dialog_max_width);
  final int calculatedWidth=windowWidth - (windowHorizontalPadding * 2);
  dialog.view.setMaxHeight(windowHeight - windowVerticalPadding * 2);
  WindowManager.LayoutParams lp=new WindowManager.LayoutParams();
  lp.copyFrom(dialog.getWindow().getAttributes());
  lp.width=Math.min(maxWidth,calculatedWidth);
  dialog.getWindow().setAttributes(lp);
}
