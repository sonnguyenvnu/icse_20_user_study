private void createIdentityInterface(final Context context){
  languageMap=new HashMap<>();
  try {
    BufferedReader reader=new BufferedReader(new InputStreamReader(context.getResources().getAssets().open("countries.txt")));
    String line;
    while ((line=reader.readLine()) != null) {
      String[] args=line.split(";");
      languageMap.put(args[1],args[2]);
    }
    reader.close();
  }
 catch (  Exception e) {
    FileLog.e(e);
  }
  topErrorCell=new TextInfoPrivacyCell(context);
  topErrorCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_top,Theme.key_windowBackgroundGrayShadow));
  topErrorCell.setPadding(0,AndroidUtilities.dp(7),0,0);
  linearLayout2.addView(topErrorCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
  checkTopErrorCell(true);
  if (currentDocumentsType != null) {
    headerCell=new HeaderCell(context);
    if (documentOnly) {
      headerCell.setText(LocaleController.getString("PassportDocuments",R.string.PassportDocuments));
    }
 else {
      headerCell.setText(LocaleController.getString("PassportRequiredDocuments",R.string.PassportRequiredDocuments));
    }
    headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
    linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    frontLayout=new LinearLayout(context);
    frontLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout2.addView(frontLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    uploadFrontCell=new TextDetailSettingsCell(context);
    uploadFrontCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
    linearLayout2.addView(uploadFrontCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    uploadFrontCell.setOnClickListener(v -> {
      uploadingFileType=UPLOADING_TYPE_FRONT;
      openAttachMenu();
    }
);
    reverseLayout=new LinearLayout(context);
    reverseLayout.setOrientation(LinearLayout.VERTICAL);
    linearLayout2.addView(reverseLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    boolean divider=currentDocumentsType.selfie_required;
    uploadReverseCell=new TextDetailSettingsCell(context);
    uploadReverseCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
    uploadReverseCell.setTextAndValue(LocaleController.getString("PassportReverseSide",R.string.PassportReverseSide),LocaleController.getString("PassportReverseSideInfo",R.string.PassportReverseSideInfo),divider);
    linearLayout2.addView(uploadReverseCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    uploadReverseCell.setOnClickListener(v -> {
      uploadingFileType=UPLOADING_TYPE_REVERSE;
      openAttachMenu();
    }
);
    if (currentDocumentsType.selfie_required) {
      selfieLayout=new LinearLayout(context);
      selfieLayout.setOrientation(LinearLayout.VERTICAL);
      linearLayout2.addView(selfieLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      uploadSelfieCell=new TextDetailSettingsCell(context);
      uploadSelfieCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
      uploadSelfieCell.setTextAndValue(LocaleController.getString("PassportSelfie",R.string.PassportSelfie),LocaleController.getString("PassportSelfieInfo",R.string.PassportSelfieInfo),currentType.translation_required);
      linearLayout2.addView(uploadSelfieCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      uploadSelfieCell.setOnClickListener(v -> {
        uploadingFileType=UPLOADING_TYPE_SELFIE;
        openAttachMenu();
      }
);
    }
    bottomCell=new TextInfoPrivacyCell(context);
    bottomCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
    bottomCell.setText(LocaleController.getString("PassportPersonalUploadInfo",R.string.PassportPersonalUploadInfo));
    linearLayout2.addView(bottomCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    if (currentDocumentsType.translation_required) {
      headerCell=new HeaderCell(context);
      headerCell.setText(LocaleController.getString("PassportTranslation",R.string.PassportTranslation));
      headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
      linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      translationLayout=new LinearLayout(context);
      translationLayout.setOrientation(LinearLayout.VERTICAL);
      linearLayout2.addView(translationLayout,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      uploadTranslationCell=new TextSettingsCell(context);
      uploadTranslationCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
      linearLayout2.addView(uploadTranslationCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
      uploadTranslationCell.setOnClickListener(v -> {
        uploadingFileType=UPLOADING_TYPE_TRANSLATION;
        openAttachMenu();
      }
);
      bottomCellTranslation=new TextInfoPrivacyCell(context);
      bottomCellTranslation.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
      if (currentBotId != 0) {
        noAllTranslationErrorText=LocaleController.getString("PassportAddTranslationUploadInfo",R.string.PassportAddTranslationUploadInfo);
      }
 else {
        if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypePassport) {
          noAllTranslationErrorText=LocaleController.getString("PassportAddPassportInfo",R.string.PassportAddPassportInfo);
        }
 else         if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeInternalPassport) {
          noAllTranslationErrorText=LocaleController.getString("PassportAddInternalPassportInfo",R.string.PassportAddInternalPassportInfo);
        }
 else         if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeIdentityCard) {
          noAllTranslationErrorText=LocaleController.getString("PassportAddIdentityCardInfo",R.string.PassportAddIdentityCardInfo);
        }
 else         if (currentDocumentsType.type instanceof TLRPC.TL_secureValueTypeDriverLicense) {
          noAllTranslationErrorText=LocaleController.getString("PassportAddDriverLicenceInfo",R.string.PassportAddDriverLicenceInfo);
        }
 else {
          noAllTranslationErrorText="";
        }
      }
      CharSequence text=noAllTranslationErrorText;
      if (documentsErrors != null) {
        String errorText;
        if ((errorText=documentsErrors.get("translation_all")) != null) {
          SpannableStringBuilder stringBuilder=new SpannableStringBuilder(errorText);
          stringBuilder.append("\n\n");
          stringBuilder.append(noAllTranslationErrorText);
          text=stringBuilder;
          stringBuilder.setSpan(new ForegroundColorSpan(Theme.getColor(Theme.key_windowBackgroundWhiteRedText3)),0,errorText.length(),Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
          errorsValues.put("translation_all","");
        }
      }
      bottomCellTranslation.setText(text);
      linearLayout2.addView(bottomCellTranslation,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    }
  }
 else   if (Build.VERSION.SDK_INT >= 18) {
    scanDocumentCell=new TextSettingsCell(context);
    scanDocumentCell.setBackgroundDrawable(Theme.getSelectorDrawable(true));
    scanDocumentCell.setText(LocaleController.getString("PassportScanPassport",R.string.PassportScanPassport),false);
    linearLayout2.addView(scanDocumentCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
    scanDocumentCell.setOnClickListener(v -> {
      if (Build.VERSION.SDK_INT >= 23 && getParentActivity().checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
        getParentActivity().requestPermissions(new String[]{Manifest.permission.CAMERA},22);
        return;
      }
      MrzCameraActivity fragment=new MrzCameraActivity();
      fragment.setDelegate(result -> {
        if (!TextUtils.isEmpty(result.firstName)) {
          inputFields[FIELD_NAME].setText(result.firstName);
        }
        if (!TextUtils.isEmpty(result.middleName)) {
          inputFields[FIELD_MIDNAME].setText(result.middleName);
        }
        if (!TextUtils.isEmpty(result.lastName)) {
          inputFields[FIELD_SURNAME].setText(result.lastName);
        }
        if (result.gender != MrzRecognizer.Result.GENDER_UNKNOWN) {
switch (result.gender) {
case MrzRecognizer.Result.GENDER_MALE:
            currentGender="male";
          inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportMale",R.string.PassportMale));
        break;
case MrzRecognizer.Result.GENDER_FEMALE:
      currentGender="female";
    inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportFemale",R.string.PassportFemale));
  break;
}
}
if (!TextUtils.isEmpty(result.nationality)) {
currentCitizeship=result.nationality;
String country=languageMap.get(currentCitizeship);
if (country != null) {
inputFields[FIELD_CITIZENSHIP].setText(country);
}
}
if (!TextUtils.isEmpty(result.issuingCountry)) {
currentResidence=result.issuingCountry;
String country=languageMap.get(currentResidence);
if (country != null) {
inputFields[FIELD_RESIDENCE].setText(country);
}
}
if (result.birthDay > 0 && result.birthMonth > 0 && result.birthYear > 0) {
inputFields[FIELD_BIRTHDAY].setText(String.format(Locale.US,"%02d.%02d.%d",result.birthDay,result.birthMonth,result.birthYear));
}
}
);
presentFragment(fragment);
}
);
bottomCell=new TextInfoPrivacyCell(context);
bottomCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
bottomCell.setText(LocaleController.getString("PassportScanPassportInfo",R.string.PassportScanPassportInfo));
linearLayout2.addView(bottomCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
}
headerCell=new HeaderCell(context);
if (documentOnly) {
headerCell.setText(LocaleController.getString("PassportDocument",R.string.PassportDocument));
}
 else {
headerCell.setText(LocaleController.getString("PassportPersonal",R.string.PassportPersonal));
}
headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
int count=currentDocumentsType != null ? FIELD_IDENTITY_COUNT : FIELD_IDENTITY_NODOC_COUNT;
inputFields=new EditTextBoldCursor[count];
for (int a=0; a < count; a++) {
final EditTextBoldCursor field=new EditTextBoldCursor(context);
inputFields[a]=field;
ViewGroup container=new FrameLayout(context){
@Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
int width=MeasureSpec.getSize(widthMeasureSpec) - AndroidUtilities.dp(34);
errorLayout=field.getErrorLayout(width);
if (errorLayout != null) {
int lineCount=errorLayout.getLineCount();
if (lineCount > 1) {
int height=AndroidUtilities.dp(64) + (errorLayout.getLineBottom(lineCount - 1) - errorLayout.getLineBottom(0));
heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
}
if (LocaleController.isRTL) {
float maxW=0;
for (int a=0; a < lineCount; a++) {
  float l=errorLayout.getLineLeft(a);
  if (l != 0) {
    offsetX=0;
    break;
  }
  maxW=Math.max(maxW,errorLayout.getLineWidth(a));
  if (a == lineCount - 1) {
    offsetX=width - maxW;
  }
}
}
}
super.onMeasure(widthMeasureSpec,heightMeasureSpec);
}
@Override protected void onDraw(Canvas canvas){
if (errorLayout != null) {
canvas.save();
canvas.translate(AndroidUtilities.dp(21) + offsetX,field.getLineY() + AndroidUtilities.dp(3));
errorLayout.draw(canvas);
canvas.restore();
}
}
}
;
container.setWillNotDraw(false);
linearLayout2.addView(container,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,64));
container.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
if (a == count - 1) {
extraBackgroundView=new View(context);
extraBackgroundView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
linearLayout2.addView(extraBackgroundView,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,6));
}
if (documentOnly && currentDocumentsType != null && a < FIELD_CARDNUMBER) {
container.setVisibility(View.GONE);
if (extraBackgroundView != null) {
extraBackgroundView.setVisibility(View.GONE);
}
}
inputFields[a].setTag(a);
inputFields[a].setSupportRtlHint(true);
inputFields[a].setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
inputFields[a].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
inputFields[a].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
inputFields[a].setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
inputFields[a].setTransformHintToHeader(true);
inputFields[a].setBackgroundDrawable(null);
inputFields[a].setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
inputFields[a].setCursorSize(AndroidUtilities.dp(20));
inputFields[a].setCursorWidth(1.5f);
inputFields[a].setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField),Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated),Theme.getColor(Theme.key_windowBackgroundWhiteRedText3));
if (a == FIELD_CITIZENSHIP || a == FIELD_RESIDENCE) {
inputFields[a].setOnTouchListener((v,event) -> {
if (getParentActivity() == null) {
return false;
}
if (event.getAction() == MotionEvent.ACTION_UP) {
CountrySelectActivity fragment=new CountrySelectActivity(false);
fragment.setCountrySelectActivityDelegate((name,shortName) -> {
int field12=(Integer)v.getTag();
final EditTextBoldCursor editText=inputFields[field12];
if (field12 == FIELD_CITIZENSHIP) {
  currentCitizeship=shortName;
}
 else {
  currentResidence=shortName;
}
editText.setText(name);
}
);
presentFragment(fragment);
}
return true;
}
);
inputFields[a].setInputType(0);
}
 else if (a == FIELD_BIRTHDAY || a == FIELD_EXPIRE) {
inputFields[a].setOnTouchListener((v,event) -> {
if (getParentActivity() == null) {
return false;
}
if (event.getAction() == MotionEvent.ACTION_UP) {
Calendar calendar=Calendar.getInstance();
int year=calendar.get(Calendar.YEAR);
int monthOfYear=calendar.get(Calendar.MONTH);
int dayOfMonth=calendar.get(Calendar.DAY_OF_MONTH);
try {
final EditTextBoldCursor field1=(EditTextBoldCursor)v;
int num=(Integer)field1.getTag();
int minYear;
int maxYear;
int currentYearDiff;
String title;
if (num == FIELD_EXPIRE) {
  title=LocaleController.getString("PassportSelectExpiredDate",R.string.PassportSelectExpiredDate);
  minYear=0;
  maxYear=20;
  currentYearDiff=0;
}
 else {
  title=LocaleController.getString("PassportSelectBithdayDate",R.string.PassportSelectBithdayDate);
  minYear=-120;
  maxYear=0;
  currentYearDiff=-18;
}
int selectedDay=-1;
int selectedMonth=-1;
int selectedYear=-1;
String[] args=field1.getText().toString().split("\\.");
if (args.length == 3) {
  selectedDay=Utilities.parseInt(args[0]);
  selectedMonth=Utilities.parseInt(args[1]);
  selectedYear=Utilities.parseInt(args[2]);
}
AlertDialog.Builder builder=AlertsCreator.createDatePickerDialog(context,minYear,maxYear,currentYearDiff,selectedDay,selectedMonth,selectedYear,title,num == FIELD_EXPIRE,(year1,month,dayOfMonth1) -> {
  if (num == FIELD_EXPIRE) {
    currentExpireDate[0]=year1;
    currentExpireDate[1]=month + 1;
    currentExpireDate[2]=dayOfMonth1;
  }
  field1.setText(String.format(Locale.US,"%02d.%02d.%d",dayOfMonth1,month + 1,year1));
}
);
if (num == FIELD_EXPIRE) {
  builder.setNegativeButton(LocaleController.getString("PassportSelectNotExpire",R.string.PassportSelectNotExpire),(dialog,which) -> {
    currentExpireDate[0]=currentExpireDate[1]=currentExpireDate[2]=0;
    field1.setText(LocaleController.getString("PassportNoExpireDate",R.string.PassportNoExpireDate));
  }
);
}
showDialog(builder.create());
}
 catch (Exception e) {
FileLog.e(e);
}
}
return true;
}
);
inputFields[a].setInputType(0);
inputFields[a].setFocusable(false);
}
 else if (a == FIELD_GENDER) {
inputFields[a].setOnTouchListener((v,event) -> {
if (getParentActivity() == null) {
return false;
}
if (event.getAction() == MotionEvent.ACTION_UP) {
AlertDialog.Builder builder=new AlertDialog.Builder(getParentActivity());
builder.setTitle(LocaleController.getString("PassportSelectGender",R.string.PassportSelectGender));
builder.setItems(new CharSequence[]{LocaleController.getString("PassportMale",R.string.PassportMale),LocaleController.getString("PassportFemale",R.string.PassportFemale)},(dialogInterface,i) -> {
if (i == 0) {
  currentGender="male";
  inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportMale",R.string.PassportMale));
}
 else if (i == 1) {
  currentGender="female";
  inputFields[FIELD_GENDER].setText(LocaleController.getString("PassportFemale",R.string.PassportFemale));
}
}
);
builder.setPositiveButton(LocaleController.getString("Cancel",R.string.Cancel),null);
showDialog(builder.create());
}
return true;
}
);
inputFields[a].setInputType(0);
inputFields[a].setFocusable(false);
}
 else {
inputFields[a].setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
inputFields[a].setImeOptions(EditorInfo.IME_ACTION_NEXT | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
}
String value;
final String key;
HashMap<String,String> values;
switch (a) {
case FIELD_NAME:
if (currentType.native_names) {
inputFields[a].setHintText(LocaleController.getString("PassportNameLatin",R.string.PassportNameLatin));
}
 else {
inputFields[a].setHintText(LocaleController.getString("PassportName",R.string.PassportName));
}
key="first_name";
values=currentValues;
break;
case FIELD_MIDNAME:
if (currentType.native_names) {
inputFields[a].setHintText(LocaleController.getString("PassportMidnameLatin",R.string.PassportMidnameLatin));
}
 else {
inputFields[a].setHintText(LocaleController.getString("PassportMidname",R.string.PassportMidname));
}
key="middle_name";
values=currentValues;
break;
case FIELD_SURNAME:
if (currentType.native_names) {
inputFields[a].setHintText(LocaleController.getString("PassportSurnameLatin",R.string.PassportSurnameLatin));
}
 else {
inputFields[a].setHintText(LocaleController.getString("PassportSurname",R.string.PassportSurname));
}
key="last_name";
values=currentValues;
break;
case FIELD_BIRTHDAY:
inputFields[a].setHintText(LocaleController.getString("PassportBirthdate",R.string.PassportBirthdate));
key="birth_date";
values=currentValues;
break;
case FIELD_GENDER:
inputFields[a].setHintText(LocaleController.getString("PassportGender",R.string.PassportGender));
key="gender";
values=currentValues;
break;
case FIELD_CITIZENSHIP:
inputFields[a].setHintText(LocaleController.getString("PassportCitizenship",R.string.PassportCitizenship));
key="country_code";
values=currentValues;
break;
case FIELD_RESIDENCE:
inputFields[a].setHintText(LocaleController.getString("PassportResidence",R.string.PassportResidence));
key="residence_country_code";
values=currentValues;
break;
case FIELD_CARDNUMBER:
inputFields[a].setHintText(LocaleController.getString("PassportDocumentNumber",R.string.PassportDocumentNumber));
key="document_no";
values=currentDocumentValues;
break;
case FIELD_EXPIRE:
inputFields[a].setHintText(LocaleController.getString("PassportExpired",R.string.PassportExpired));
key="expiry_date";
values=currentDocumentValues;
break;
default :
continue;
}
setFieldValues(values,inputFields[a],key);
inputFields[a].setSelection(inputFields[a].length());
if (a == FIELD_NAME || a == FIELD_SURNAME || a == FIELD_MIDNAME) {
inputFields[a].addTextChangedListener(new TextWatcher(){
@Override public void beforeTextChanged(CharSequence s,int start,int count,int after){
}
@Override public void onTextChanged(CharSequence s,int start,int before,int count){
}
@Override public void afterTextChanged(Editable s){
if (ignore) {
return;
}
int num=(Integer)field.getTag();
boolean error=false;
for (int a=0; a < s.length(); a++) {
char ch=s.charAt(a);
if (!(ch >= '0' && ch <= '9' || ch >= 'a' && ch <= 'z' || ch >= 'A' && ch <= 'Z' || ch == ' ' || ch == '\'' || ch == ',' || ch == '.' || ch == '&' || ch == '-' || ch == '/')) {
error=true;
break;
}
}
if (error && !allowNonLatinName) {
field.setErrorText(LocaleController.getString("PassportUseLatinOnly",R.string.PassportUseLatinOnly));
}
 else {
nonLatinNames[num]=error;
checkFieldForError(field,key,s,false);
}
}
}
);
}
 else {
inputFields[a].addTextChangedListener(new TextWatcher(){
@Override public void beforeTextChanged(CharSequence s,int start,int count,int after){
}
@Override public void onTextChanged(CharSequence s,int start,int before,int count){
}
@Override public void afterTextChanged(Editable s){
checkFieldForError(field,key,s,values == currentDocumentValues);
int field12=(Integer)field.getTag();
final EditTextBoldCursor editText=inputFields[field12];
if (field12 == FIELD_RESIDENCE) {
checkNativeFields(true);
}
}
}
);
}
inputFields[a].setPadding(0,0,0,0);
inputFields[a].setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
container.addView(inputFields[a],LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.LEFT | Gravity.TOP,21,0,21,0));
inputFields[a].setOnEditorActionListener((textView,i,keyEvent) -> {
if (i == EditorInfo.IME_ACTION_NEXT) {
int num=(Integer)textView.getTag();
num++;
if (num < inputFields.length) {
if (inputFields[num].isFocusable()) {
inputFields[num].requestFocus();
}
 else {
inputFields[num].dispatchTouchEvent(MotionEvent.obtain(0,0,MotionEvent.ACTION_UP,0,0,0));
textView.clearFocus();
AndroidUtilities.hideKeyboard(textView);
}
}
return true;
}
return false;
}
);
}
sectionCell2=new ShadowSectionCell(context);
linearLayout2.addView(sectionCell2,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
headerCell=new HeaderCell(context);
headerCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
linearLayout2.addView(headerCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
inputExtraFields=new EditTextBoldCursor[FIELD_NATIVE_COUNT];
for (int a=0; a < FIELD_NATIVE_COUNT; a++) {
final EditTextBoldCursor field=new EditTextBoldCursor(context);
inputExtraFields[a]=field;
ViewGroup container=new FrameLayout(context){
@Override protected void onMeasure(int widthMeasureSpec,int heightMeasureSpec){
int width=MeasureSpec.getSize(widthMeasureSpec) - AndroidUtilities.dp(34);
errorLayout=field.getErrorLayout(width);
if (errorLayout != null) {
int lineCount=errorLayout.getLineCount();
if (lineCount > 1) {
int height=AndroidUtilities.dp(64) + (errorLayout.getLineBottom(lineCount - 1) - errorLayout.getLineBottom(0));
heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
}
if (LocaleController.isRTL) {
float maxW=0;
for (int a=0; a < lineCount; a++) {
float l=errorLayout.getLineLeft(a);
if (l != 0) {
offsetX=0;
break;
}
maxW=Math.max(maxW,errorLayout.getLineWidth(a));
if (a == lineCount - 1) {
offsetX=width - maxW;
}
}
}
}
super.onMeasure(widthMeasureSpec,heightMeasureSpec);
}
@Override protected void onDraw(Canvas canvas){
if (errorLayout != null) {
canvas.save();
canvas.translate(AndroidUtilities.dp(21) + offsetX,field.getLineY() + AndroidUtilities.dp(3));
errorLayout.draw(canvas);
canvas.restore();
}
}
}
;
container.setWillNotDraw(false);
linearLayout2.addView(container,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,64));
container.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
if (a == FIELD_NATIVE_COUNT - 1) {
extraBackgroundView2=new View(context);
extraBackgroundView2.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
linearLayout2.addView(extraBackgroundView2,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,6));
}
inputExtraFields[a].setTag(a);
inputExtraFields[a].setSupportRtlHint(true);
inputExtraFields[a].setTextSize(TypedValue.COMPLEX_UNIT_DIP,16);
inputExtraFields[a].setHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteHintText));
inputExtraFields[a].setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
inputExtraFields[a].setHeaderHintColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueHeader));
inputExtraFields[a].setTransformHintToHeader(true);
inputExtraFields[a].setBackgroundDrawable(null);
inputExtraFields[a].setCursorColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
inputExtraFields[a].setCursorSize(AndroidUtilities.dp(20));
inputExtraFields[a].setCursorWidth(1.5f);
inputExtraFields[a].setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField),Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated),Theme.getColor(Theme.key_windowBackgroundWhiteRedText3));
inputExtraFields[a].setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_CAP_SENTENCES);
inputExtraFields[a].setImeOptions(EditorInfo.IME_ACTION_NEXT | EditorInfo.IME_FLAG_NO_EXTRACT_UI);
String value;
final String key;
HashMap<String,String> values;
switch (a) {
case FIELD_NATIVE_NAME:
key="first_name_native";
values=currentValues;
break;
case FIELD_NATIVE_MIDNAME:
key="middle_name_native";
values=currentValues;
break;
case FIELD_NATIVE_SURNAME:
key="last_name_native";
values=currentValues;
break;
default :
continue;
}
setFieldValues(values,inputExtraFields[a],key);
inputExtraFields[a].setSelection(inputExtraFields[a].length());
if (a == FIELD_NATIVE_NAME || a == FIELD_NATIVE_SURNAME || a == FIELD_NATIVE_MIDNAME) {
inputExtraFields[a].addTextChangedListener(new TextWatcher(){
@Override public void beforeTextChanged(CharSequence s,int start,int count,int after){
}
@Override public void onTextChanged(CharSequence s,int start,int before,int count){
}
@Override public void afterTextChanged(Editable s){
if (ignore) {
return;
}
checkFieldForError(field,key,s,false);
}
}
);
}
inputExtraFields[a].setPadding(0,0,0,0);
inputExtraFields[a].setGravity((LocaleController.isRTL ? Gravity.RIGHT : Gravity.LEFT) | Gravity.CENTER_VERTICAL);
container.addView(inputExtraFields[a],LayoutHelper.createFrame(LayoutHelper.MATCH_PARENT,LayoutHelper.MATCH_PARENT,Gravity.LEFT | Gravity.TOP,21,0,21,0));
inputExtraFields[a].setOnEditorActionListener((textView,i,keyEvent) -> {
if (i == EditorInfo.IME_ACTION_NEXT) {
int num=(Integer)textView.getTag();
num++;
if (num < inputExtraFields.length) {
if (inputExtraFields[num].isFocusable()) {
inputExtraFields[num].requestFocus();
}
 else {
inputExtraFields[num].dispatchTouchEvent(MotionEvent.obtain(0,0,MotionEvent.ACTION_UP,0,0,0));
textView.clearFocus();
AndroidUtilities.hideKeyboard(textView);
}
}
return true;
}
return false;
}
);
}
nativeInfoCell=new TextInfoPrivacyCell(context);
linearLayout2.addView(nativeInfoCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
if ((currentBotId != 0 || currentDocumentsType == null) && currentTypeValue != null && !documentOnly || currentDocumentsTypeValue != null) {
if (currentDocumentsTypeValue != null) {
addDocumentViews(currentDocumentsTypeValue.files);
if (currentDocumentsTypeValue.front_side instanceof TLRPC.TL_secureFile) {
addDocumentViewInternal((TLRPC.TL_secureFile)currentDocumentsTypeValue.front_side,UPLOADING_TYPE_FRONT);
}
if (currentDocumentsTypeValue.reverse_side instanceof TLRPC.TL_secureFile) {
addDocumentViewInternal((TLRPC.TL_secureFile)currentDocumentsTypeValue.reverse_side,UPLOADING_TYPE_REVERSE);
}
if (currentDocumentsTypeValue.selfie instanceof TLRPC.TL_secureFile) {
addDocumentViewInternal((TLRPC.TL_secureFile)currentDocumentsTypeValue.selfie,UPLOADING_TYPE_SELFIE);
}
addTranslationDocumentViews(currentDocumentsTypeValue.translation);
}
TextSettingsCell settingsCell1=new TextSettingsCell(context);
settingsCell1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteRedText3));
settingsCell1.setBackgroundDrawable(Theme.getSelectorDrawable(true));
if (currentDocumentsType == null) {
settingsCell1.setText(LocaleController.getString("PassportDeleteInfo",R.string.PassportDeleteInfo),false);
}
 else {
settingsCell1.setText(LocaleController.getString("PassportDeleteDocument",R.string.PassportDeleteDocument),false);
}
linearLayout2.addView(settingsCell1,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
settingsCell1.setOnClickListener(v -> createDocumentDeleteAlert());
nativeInfoCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
sectionCell=new ShadowSectionCell(context);
sectionCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
linearLayout2.addView(sectionCell,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT));
}
 else {
nativeInfoCell.setBackgroundDrawable(Theme.getThemedDrawable(context,R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
}
updateInterfaceStringsForDocumentType();
checkNativeFields(false);
}
