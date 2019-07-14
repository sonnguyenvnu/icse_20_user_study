/** 
 * Toggle text selection mode in the view. 
 */
@TargetApi(23) public void toggleSelectingText(MotionEvent ev){
  mIsSelectingText=!mIsSelectingText;
  mClient.copyModeChanged(mIsSelectingText);
  if (mIsSelectingText) {
    if (mLeftSelectionHandle == null) {
      mLeftSelectionHandle=(BitmapDrawable)getContext().getDrawable(R.drawable.text_select_handle_left_material);
      mRightSelectionHandle=(BitmapDrawable)getContext().getDrawable(R.drawable.text_select_handle_right_material);
    }
    int cx=(int)(ev.getX() / mRenderer.mFontWidth);
    final boolean eventFromMouse=ev.isFromSource(InputDevice.SOURCE_MOUSE);
    final int SELECT_TEXT_OFFSET_Y=eventFromMouse ? 0 : -40;
    int cy=(int)((ev.getY() + SELECT_TEXT_OFFSET_Y) / mRenderer.mFontLineSpacing) + mTopRow;
    mSelX1=mSelX2=cx;
    mSelY1=mSelY2=cy;
    TerminalBuffer screen=mEmulator.getScreen();
    if (!" ".equals(screen.getSelectedText(mSelX1,mSelY1,mSelX1,mSelY1))) {
      while (mSelX1 > 0 && !"".equals(screen.getSelectedText(mSelX1 - 1,mSelY1,mSelX1 - 1,mSelY1))) {
        mSelX1--;
      }
      while (mSelX2 < mEmulator.mColumns - 1 && !"".equals(screen.getSelectedText(mSelX2 + 1,mSelY1,mSelX2 + 1,mSelY1))) {
        mSelX2++;
      }
    }
    mInitialTextSelection=true;
    mIsDraggingLeftSelection=true;
    mSelectionDownX=ev.getX();
    mSelectionDownY=ev.getY();
    final ActionMode.Callback callback=new ActionMode.Callback(){
      @Override public boolean onCreateActionMode(      ActionMode mode,      Menu menu){
        int show=MenuItem.SHOW_AS_ACTION_ALWAYS | MenuItem.SHOW_AS_ACTION_WITH_TEXT;
        ClipboardManager clipboard=(ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
        menu.add(Menu.NONE,1,Menu.NONE,R.string.copy_text).setShowAsAction(show);
        menu.add(Menu.NONE,2,Menu.NONE,R.string.paste_text).setEnabled(clipboard.hasPrimaryClip()).setShowAsAction(show);
        menu.add(Menu.NONE,3,Menu.NONE,R.string.text_selection_more);
        return true;
      }
      @Override public boolean onPrepareActionMode(      ActionMode mode,      Menu menu){
        return false;
      }
      @Override public boolean onActionItemClicked(      ActionMode mode,      MenuItem item){
        if (!mIsSelectingText) {
          return true;
        }
switch (item.getItemId()) {
case 1:
          String selectedText=mEmulator.getSelectedText(mSelX1,mSelY1,mSelX2,mSelY2).trim();
        mTermSession.clipboardText(selectedText);
      break;
case 2:
    ClipboardManager clipboard=(ClipboardManager)getContext().getSystemService(Context.CLIPBOARD_SERVICE);
  ClipData clipData=clipboard.getPrimaryClip();
if (clipData != null) {
  CharSequence paste=clipData.getItemAt(0).coerceToText(getContext());
  if (!TextUtils.isEmpty(paste))   mEmulator.paste(paste.toString());
}
break;
case 3:
showContextMenu();
break;
}
toggleSelectingText(null);
return true;
}
@Override public void onDestroyActionMode(ActionMode mode){
}
}
;
if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
mActionMode=startActionMode(new ActionMode.Callback2(){
@Override public boolean onCreateActionMode(ActionMode mode,Menu menu){
return callback.onCreateActionMode(mode,menu);
}
@Override public boolean onPrepareActionMode(ActionMode mode,Menu menu){
return false;
}
@Override public boolean onActionItemClicked(ActionMode mode,MenuItem item){
return callback.onActionItemClicked(mode,item);
}
@Override public void onDestroyActionMode(ActionMode mode){
}
@Override public void onGetContentRect(ActionMode mode,View view,Rect outRect){
int x1=Math.round(mSelX1 * mRenderer.mFontWidth);
int x2=Math.round(mSelX2 * mRenderer.mFontWidth);
int y1=Math.round((mSelY1 - mTopRow) * mRenderer.mFontLineSpacing);
int y2=Math.round((mSelY2 + 1 - mTopRow) * mRenderer.mFontLineSpacing);
outRect.set(Math.min(x1,x2),y1,Math.max(x1,x2),y2);
}
}
,ActionMode.TYPE_FLOATING);
}
 else {
mActionMode=startActionMode(callback);
}
invalidate();
}
 else {
mActionMode.finish();
mSelX1=mSelY1=mSelX2=mSelY2=-1;
invalidate();
}
}
