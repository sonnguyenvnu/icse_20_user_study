private void addBackup(){
  addPreferencesFromResource(R.xml.backup_settings);
  findPreference("backup").setOnPreferenceClickListener((  Preference preference) -> {
    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
      Map<String,?> preferences=PrefHelper.getAll();
      preferences.remove("token");
      String json=new Gson().toJson(preferences);
      String path=FileHelper.PATH;
      File folder=new File(path);
      folder.mkdirs();
      File backup=new File(folder,"backup.json");
      try {
        backup.createNewFile();
        try (FileOutputStream outputStream=new FileOutputStream(backup)){
          try (OutputStreamWriter myOutWriter=new OutputStreamWriter(outputStream)){
            myOutWriter.append(json);
          }
         }
       }
 catch (      IOException e) {
        Log.e(getTag(),"Couldn't backup: " + e.toString());
      }
      PrefHelper.set("backed_up",new SimpleDateFormat("MM/dd",Locale.ENGLISH).format(new Date()));
      Toasty.success(App.getInstance(),getString(R.string.backed_up)).show();
    }
 else {
      requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
    }
    return true;
  }
);
  if (PrefHelper.getString("backed_up") != null) {
    findPreference("backup").setSummary(SpannableBuilder.builder().append(getString(R.string.backup_summary,PrefHelper.getString("backed_up"))).append("\n").append(FileHelper.PATH));
  }
 else {
    findPreference("backup").setSummary("");
  }
  findPreference("restore").setOnPreferenceClickListener(preference -> {
    if (ContextCompat.checkSelfPermission(getActivity(),Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
      showFileChooser();
    }
 else {
      requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},PERMISSION_REQUEST_CODE);
    }
    return true;
  }
);
}
