@ReactMethod public void _init(ReadableMap options){
  Activity activity=getCurrentActivity();
  if (activity != null && options.hasKey(PICKER_DATA)) {
    View view=activity.getLayoutInflater().inflate(R.layout.picker_view,null);
    RelativeLayout barLayout=(RelativeLayout)view.findViewById(R.id.barLayout);
    TextView cancelTV=(TextView)view.findViewById(R.id.cancel);
    TextView titleTV=(TextView)view.findViewById(R.id.title);
    TextView confirmTV=(TextView)view.findViewById(R.id.confirm);
    RelativeLayout pickerLayout=(RelativeLayout)view.findViewById(R.id.pickerLayout);
    pickerViewLinkage=(PickerViewLinkage)view.findViewById(R.id.pickerViewLinkage);
    pickerViewAlone=(PickerViewAlone)view.findViewById(R.id.pickerViewAlone);
    int barViewHeight;
    if (options.hasKey(PICKER_TOOL_BAR_HEIGHT)) {
      try {
        barViewHeight=options.getInt(PICKER_TOOL_BAR_HEIGHT);
      }
 catch (      Exception e) {
        barViewHeight=(int)options.getDouble(PICKER_TOOL_BAR_HEIGHT);
      }
    }
 else {
      barViewHeight=(int)(activity.getResources().getDisplayMetrics().density * 40);
    }
    RelativeLayout.LayoutParams params=new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT,barViewHeight);
    barLayout.setLayoutParams(params);
    if (options.hasKey(PICKER_TOOL_BAR_BG)) {
      ReadableArray array=options.getArray(PICKER_TOOL_BAR_BG);
      int[] colors=getColor(array);
      barLayout.setBackgroundColor(argb(colors[3],colors[0],colors[1],colors[2]));
    }
    if (options.hasKey(PICKER_TOOL_BAR_TEXT_SIZE)) {
      int toolBarTextSize=options.getInt(PICKER_TOOL_BAR_TEXT_SIZE);
      cancelTV.setTextSize(toolBarTextSize);
      titleTV.setTextSize(toolBarTextSize);
      confirmTV.setTextSize(toolBarTextSize);
    }
    if (options.hasKey(PICKER_CONFIRM_BTN_TEXT)) {
      confirmText=options.getString(PICKER_CONFIRM_BTN_TEXT);
    }
    confirmTV.setText(!TextUtils.isEmpty(confirmText) ? confirmText : "");
    if (options.hasKey(PICKER_CONFIRM_BTN_COLOR)) {
      ReadableArray array=options.getArray(PICKER_CONFIRM_BTN_COLOR);
      int[] colors=getColor(array);
      confirmTV.setTextColor(argb(colors[3],colors[0],colors[1],colors[2]));
    }
    confirmTV.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
switch (curStatus) {
case 0:
          returnData=pickerViewAlone.getSelectedData();
        break;
case 1:
      returnData=pickerViewLinkage.getSelectedData();
    break;
}
commonEvent(EVENT_KEY_CONFIRM);
hide();
}
}
);
if (options.hasKey(PICKER_TITLE_TEXT)) {
titleText=options.getString(PICKER_TITLE_TEXT);
}
titleTV.setText(!TextUtils.isEmpty(titleText) ? titleText : "");
if (options.hasKey(PICKER_TITLE_TEXT_COLOR)) {
ReadableArray array=options.getArray(PICKER_TITLE_TEXT_COLOR);
int[] colors=getColor(array);
titleTV.setTextColor(argb(colors[3],colors[0],colors[1],colors[2]));
}
if (options.hasKey(PICKER_CANCEL_BTN_TEXT)) {
cancelText=options.getString(PICKER_CANCEL_BTN_TEXT);
}
cancelTV.setText(!TextUtils.isEmpty(cancelText) ? cancelText : "");
if (options.hasKey(PICKER_CANCEL_BTN_COLOR)) {
ReadableArray array=options.getArray(PICKER_CANCEL_BTN_COLOR);
int[] colors=getColor(array);
cancelTV.setTextColor(argb(colors[3],colors[0],colors[1],colors[2]));
}
cancelTV.setOnClickListener(new View.OnClickListener(){
@Override public void onClick(View v){
switch (curStatus) {
case 0:
  returnData=pickerViewAlone.getSelectedData();
break;
case 1:
returnData=pickerViewLinkage.getSelectedData();
break;
}
commonEvent(EVENT_KEY_CANCEL);
hide();
}
}
);
if (options.hasKey(PICKER_TEXT_ELLIPSIS_LEN)) {
pickerTextEllipsisLen=options.getInt(PICKER_TEXT_ELLIPSIS_LEN);
}
if (options.hasKey(IS_LOOP)) {
isLoop=options.getBoolean(IS_LOOP);
}
if (options.hasKey(WEIGHTS)) {
ReadableArray array=options.getArray(WEIGHTS);
weights=new double[array.size()];
for (int i=0; i < array.size(); i++) {
switch (array.getType(i).name()) {
case "Number":
try {
weights[i]=array.getInt(i);
}
 catch (Exception e) {
weights[i]=array.getDouble(i);
}
break;
case "String":
try {
weights[i]=Double.parseDouble(array.getString(i));
}
 catch (Exception e) {
weights[i]=1.0;
}
break;
default :
weights[i]=1.0;
break;
}
}
}
int pickerTextColor=0xff000000;
if (options.hasKey(PICKER_TEXT_COLOR)) {
ReadableArray array=options.getArray(PICKER_TEXT_COLOR);
int[] colors=getColor(array);
pickerTextColor=Color.argb(colors[3],colors[0],colors[1],colors[2]);
}
int pickerTextSize=16;
if (options.hasKey(PICKER_TEXT_SIZE)) {
try {
pickerTextSize=options.getInt(PICKER_TEXT_SIZE);
}
 catch (Exception e) {
pickerTextSize=(int)options.getDouble(PICKER_TEXT_SIZE);
}
}
ReadableArray pickerData=options.getArray(PICKER_DATA);
int pickerViewHeight;
String name=pickerData.getType(0).name();
switch (name) {
case "Map":
curStatus=1;
pickerViewLinkage.setVisibility(View.VISIBLE);
pickerViewAlone.setVisibility(View.GONE);
pickerViewLinkage.setPickerData(pickerData,weights);
pickerViewLinkage.setTextColor(pickerTextColor);
pickerViewLinkage.setTextSize(pickerTextSize);
pickerViewLinkage.setTextEllipsisLen(pickerTextEllipsisLen);
pickerViewLinkage.setIsLoop(isLoop);
pickerViewLinkage.setOnSelectListener(new OnSelectedListener(){
@Override public void onSelected(ArrayList<ReturnData> selectedList){
returnData=selectedList;
commonEvent(EVENT_KEY_SELECTED);
}
}
);
pickerViewHeight=pickerViewLinkage.getViewHeight();
break;
default :
curStatus=0;
pickerViewAlone.setVisibility(View.VISIBLE);
pickerViewLinkage.setVisibility(View.GONE);
pickerViewAlone.setPickerData(pickerData,weights);
pickerViewAlone.setTextColor(pickerTextColor);
pickerViewAlone.setTextSize(pickerTextSize);
pickerViewAlone.setTextEllipsisLen(pickerTextEllipsisLen);
pickerViewAlone.setIsLoop(isLoop);
pickerViewAlone.setOnSelectedListener(new OnSelectedListener(){
@Override public void onSelected(ArrayList<ReturnData> selectedList){
returnData=selectedList;
commonEvent(EVENT_KEY_SELECTED);
}
}
);
pickerViewHeight=pickerViewAlone.getViewHeight();
break;
}
if (options.hasKey(PICKER_FONT_FAMILY)) {
Typeface typeface=null;
AssetManager assetManager=activity.getApplicationContext().getAssets();
final String fontFamily=options.getString(PICKER_FONT_FAMILY);
try {
String path=FONTS + fontFamily + TTF;
typeface=Typeface.createFromAsset(assetManager,path);
}
 catch (Exception ignored) {
try {
String path=FONTS + fontFamily + OTF;
typeface=Typeface.createFromAsset(assetManager,path);
}
 catch (Exception ignored2) {
try {
typeface=Typeface.create(fontFamily,Typeface.NORMAL);
}
 catch (Exception ignored3) {
}
}
}
cancelTV.setTypeface(typeface);
titleTV.setTypeface(typeface);
confirmTV.setTypeface(typeface);
pickerViewAlone.setTypeface(typeface);
pickerViewLinkage.setTypeface(typeface);
}
if (options.hasKey(SELECTED_VALUE)) {
ReadableArray array=options.getArray(SELECTED_VALUE);
String[] selectedValue=getSelectedValue(array);
select(selectedValue);
}
if (options.hasKey(PICKER_BG_COLOR)) {
ReadableArray array=options.getArray(PICKER_BG_COLOR);
int[] colors=getColor(array);
pickerLayout.setBackgroundColor(argb(colors[3],colors[0],colors[1],colors[2]));
}
int height=barViewHeight + pickerViewHeight;
if (dialog == null) {
dialog=new Dialog(activity,R.style.Dialog_Full_Screen);
dialog.setContentView(view);
WindowManager.LayoutParams layoutParams=new WindowManager.LayoutParams();
Window window=dialog.getWindow();
if (window != null) {
if (android.os.Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
window.setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
}
 else {
if (MIUIUtils.isMIUI()) {
layoutParams.type=WindowManager.LayoutParams.TYPE_APPLICATION;
}
 else {
}
}
layoutParams.flags=WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;
layoutParams.format=PixelFormat.TRANSPARENT;
layoutParams.windowAnimations=R.style.PickerAnim;
layoutParams.width=WindowManager.LayoutParams.MATCH_PARENT;
layoutParams.height=height;
layoutParams.gravity=Gravity.BOTTOM;
window.setAttributes(layoutParams);
}
}
 else {
dialog.dismiss();
dialog.setContentView(view);
}
}
}
