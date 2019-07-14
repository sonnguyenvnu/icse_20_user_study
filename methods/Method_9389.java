private void checkNativeFields(boolean byEdit){
  if (inputExtraFields == null) {
    return;
  }
  String country=languageMap.get(currentResidence);
  HashMap<String,String> map=SharedConfig.getCountryLangs();
  String lang=map.get(currentResidence);
  if (!currentType.native_names || TextUtils.isEmpty(currentResidence) || "EN".equals(lang)) {
    if (nativeInfoCell.getVisibility() != View.GONE) {
      nativeInfoCell.setVisibility(View.GONE);
      headerCell.setVisibility(View.GONE);
      extraBackgroundView2.setVisibility(View.GONE);
      for (int a=0; a < inputExtraFields.length; a++) {
        ((View)inputExtraFields[a].getParent()).setVisibility(View.GONE);
      }
      if ((currentBotId != 0 || currentDocumentsType == null) && currentTypeValue != null && !documentOnly || currentDocumentsTypeValue != null) {
        sectionCell2.setBackgroundDrawable(Theme.getThemedDrawable(getParentActivity(),R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
      }
 else {
        sectionCell2.setBackgroundDrawable(Theme.getThemedDrawable(getParentActivity(),R.drawable.greydivider_bottom,Theme.key_windowBackgroundGrayShadow));
      }
    }
  }
 else {
    if (nativeInfoCell.getVisibility() != View.VISIBLE) {
      nativeInfoCell.setVisibility(View.VISIBLE);
      headerCell.setVisibility(View.VISIBLE);
      extraBackgroundView2.setVisibility(View.VISIBLE);
      for (int a=0; a < inputExtraFields.length; a++) {
        ((View)inputExtraFields[a].getParent()).setVisibility(View.VISIBLE);
      }
      if (inputExtraFields[FIELD_NATIVE_NAME].length() == 0 && inputExtraFields[FIELD_NATIVE_MIDNAME].length() == 0 && inputExtraFields[FIELD_NATIVE_SURNAME].length() == 0) {
        for (int a=0; a < nonLatinNames.length; a++) {
          if (nonLatinNames[a]) {
            inputExtraFields[FIELD_NATIVE_NAME].setText(inputFields[FIELD_NAME].getText());
            inputExtraFields[FIELD_NATIVE_MIDNAME].setText(inputFields[FIELD_MIDNAME].getText());
            inputExtraFields[FIELD_NATIVE_SURNAME].setText(inputFields[FIELD_SURNAME].getText());
            break;
          }
        }
      }
      sectionCell2.setBackgroundDrawable(Theme.getThemedDrawable(getParentActivity(),R.drawable.greydivider,Theme.key_windowBackgroundGrayShadow));
    }
    nativeInfoCell.setText(LocaleController.formatString("PassportNativeInfo",R.string.PassportNativeInfo,country));
    String header=lang != null ? LocaleController.getServerString("PassportLanguage_" + lang) : null;
    if (header != null) {
      headerCell.setText(LocaleController.formatString("PassportNativeHeaderLang",R.string.PassportNativeHeaderLang,header));
    }
 else {
      headerCell.setText(LocaleController.getString("PassportNativeHeader",R.string.PassportNativeHeader));
    }
    for (int a=0; a < FIELD_NATIVE_COUNT; a++) {
switch (a) {
case FIELD_NATIVE_NAME:
        if (header != null) {
          inputExtraFields[a].setHintText(LocaleController.getString("PassportName",R.string.PassportName));
        }
 else {
          inputExtraFields[a].setHintText(LocaleController.formatString("PassportNameCountry",R.string.PassportNameCountry,country));
        }
      break;
case FIELD_NATIVE_MIDNAME:
    if (header != null) {
      inputExtraFields[a].setHintText(LocaleController.getString("PassportMidname",R.string.PassportMidname));
    }
 else {
      inputExtraFields[a].setHintText(LocaleController.formatString("PassportMidnameCountry",R.string.PassportMidnameCountry,country));
    }
  break;
case FIELD_NATIVE_SURNAME:
if (header != null) {
  inputExtraFields[a].setHintText(LocaleController.getString("PassportSurname",R.string.PassportSurname));
}
 else {
  inputExtraFields[a].setHintText(LocaleController.formatString("PassportSurnameCountry",R.string.PassportSurnameCountry,country));
}
break;
}
}
if (byEdit) {
AndroidUtilities.runOnUIThread(() -> {
if (inputExtraFields != null) {
scrollToField(inputExtraFields[FIELD_NATIVE_NAME]);
}
}
);
}
}
}
