public void updatePath(@NonNull final String news,boolean results,String query,OpenMode openmode,int folderCount,int fileCount,BottomBarButtonPath buttonPathInterface){
  if (news.length() == 0)   return;
  MainActivityHelper mainActivityHelper=mainActivity.mainActivityHelper;
switch (openmode) {
case SFTP:
    newPath=mainActivityHelper.parseSftpPath(news);
  break;
case SMB:
newPath=mainActivityHelper.parseSmbPath(news);
break;
case OTG:
newPath=mainActivityHelper.parseOTGPath(news);
break;
case CUSTOM:
newPath=mainActivityHelper.getIntegralNames(news);
break;
case DROPBOX:
case BOX:
case ONEDRIVE:
case GDRIVE:
newPath=mainActivityHelper.parseCloudPath(openmode,news);
break;
default :
newPath=news;
}
if (!results) {
pathText.setText(mainActivity.getString(R.string.folderfilecount,folderCount,fileCount));
}
 else {
fullPathText.setText(mainActivity.getString(R.string.searchresults,query));
pathText.setText("");
return;
}
final String oldPath=fullPathText.getText().toString();
if (oldPath.equals(newPath)) return;
if (!areButtonsShowing()) {
final Animation slideIn=AnimationUtils.loadAnimation(mainActivity,R.anim.slide_in);
Animation slideOut=AnimationUtils.loadAnimation(mainActivity,R.anim.slide_out);
if (newPath.length() > oldPath.length() && newPath.contains(oldPath) && oldPath.length() != 0) {
fullPathAnim.setAnimation(slideIn);
fullPathAnim.animate().setListener(new AnimatorListenerAdapter(){
@Override public void onAnimationEnd(Animator animation){
super.onAnimationEnd(animation);
new Handler().postDelayed(() -> {
fullPathAnim.setVisibility(View.GONE);
fullPathText.setText(newPath);
}
,PATH_ANIM_END_DELAY);
}
@Override public void onAnimationStart(Animator animation){
super.onAnimationStart(animation);
fullPathAnim.setVisibility(View.VISIBLE);
fullPathAnim.setText(Utils.differenceStrings(oldPath,newPath));
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_RIGHT));
}
@Override public void onAnimationCancel(Animator animation){
super.onAnimationCancel(animation);
}
}
).setStartDelay(PATH_ANIM_START_DELAY).start();
}
 else if (newPath.length() < oldPath.length() && oldPath.contains(newPath)) {
fullPathAnim.setAnimation(slideOut);
fullPathAnim.animate().setListener(new AnimatorListenerAdapter(){
@Override public void onAnimationEnd(Animator animation){
super.onAnimationEnd(animation);
fullPathAnim.setVisibility(View.GONE);
fullPathText.setText(newPath);
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_RIGHT));
}
@Override public void onAnimationStart(Animator animation){
super.onAnimationStart(animation);
fullPathAnim.setVisibility(View.VISIBLE);
fullPathAnim.setText(Utils.differenceStrings(newPath,oldPath));
fullPathText.setText(newPath);
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_LEFT));
}
}
).setStartDelay(PATH_ANIM_START_DELAY).start();
}
 else if (oldPath.isEmpty()) {
fullPathAnim.setAnimation(slideIn);
fullPathAnim.setText(newPath);
fullPathAnim.animate().setListener(new AnimatorListenerAdapter(){
@Override public void onAnimationStart(Animator animation){
super.onAnimationStart(animation);
fullPathAnim.setVisibility(View.VISIBLE);
fullPathText.setText("");
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_RIGHT));
}
@Override public void onAnimationEnd(Animator animation){
super.onAnimationEnd(animation);
new Handler().postDelayed(() -> {
fullPathAnim.setVisibility(View.GONE);
fullPathText.setText(newPath);
}
,PATH_ANIM_END_DELAY);
}
@Override public void onAnimationCancel(Animator animation){
super.onAnimationCancel(animation);
}
}
).setStartDelay(PATH_ANIM_START_DELAY).start();
}
 else {
fullPathAnim.setAnimation(slideOut);
fullPathAnim.animate().setListener(new AnimatorListenerAdapter(){
@Override public void onAnimationStart(Animator animator){
super.onAnimationStart(animator);
fullPathAnim.setVisibility(View.VISIBLE);
fullPathAnim.setText(oldPath);
fullPathText.setText("");
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_LEFT));
}
@Override public void onAnimationEnd(Animator animator){
super.onAnimationEnd(animator);
fullPathAnim.setText(newPath);
fullPathText.setText("");
fullPathAnim.setAnimation(slideIn);
fullPathAnim.animate().setListener(new AnimatorListenerAdapter(){
@Override public void onAnimationEnd(Animator animation){
super.onAnimationEnd(animation);
new Handler().postDelayed(() -> {
fullPathAnim.setVisibility(View.GONE);
fullPathText.setText(newPath);
}
,PATH_ANIM_END_DELAY);
}
@Override public void onAnimationStart(Animator animation){
super.onAnimationStart(animation);
fullPathAnim.setVisibility(View.VISIBLE);
fullPathText.setText("");
scroll.post(() -> pathScroll.fullScroll(View.FOCUS_RIGHT));
}
}
).start();
}
@Override public void onAnimationCancel(Animator animation){
super.onAnimationCancel(animation);
}
}
).setStartDelay(PATH_ANIM_START_DELAY).start();
}
}
 else {
showButtons(buttonPathInterface);
fullPathText.setText(newPath);
}
}
