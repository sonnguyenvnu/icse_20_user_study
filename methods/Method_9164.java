public static void showCallDebugSettings(final Context context){
  final SharedPreferences preferences=MessagesController.getGlobalMainSettings();
  LinearLayout ll=new LinearLayout(context);
  ll.setOrientation(LinearLayout.VERTICAL);
  TextView warning=new TextView(context);
  warning.setTextSize(TypedValue.COMPLEX_UNIT_DIP,15);
  warning.setText("Please only change these settings if you know exactly what they do.");
  warning.setTextColor(Theme.getColor(Theme.key_dialogTextBlack));
  ll.addView(warning,LayoutHelper.createLinear(LayoutHelper.MATCH_PARENT,LayoutHelper.WRAP_CONTENT,16,8,16,8));
  final TextCheckCell tcpCell=new TextCheckCell(context);
  tcpCell.setTextAndCheck("Force TCP",preferences.getBoolean("dbg_force_tcp_in_calls",false),false);
  tcpCell.setOnClickListener(new View.OnClickListener(){
    @Override public void onClick(    View v){
      boolean force=preferences.getBoolean("dbg_force_tcp_in_calls",false);
      SharedPreferences.Editor editor=preferences.edit();
      editor.putBoolean("dbg_force_tcp_in_calls",!force);
      editor.commit();
      tcpCell.setChecked(!force);
    }
  }
);
  ll.addView(tcpCell);
  if (BuildVars.DEBUG_VERSION && BuildVars.LOGS_ENABLED) {
    final TextCheckCell dumpCell=new TextCheckCell(context);
    dumpCell.setTextAndCheck("Dump detailed stats",preferences.getBoolean("dbg_dump_call_stats",false),false);
    dumpCell.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        boolean force=preferences.getBoolean("dbg_dump_call_stats",false);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("dbg_dump_call_stats",!force);
        editor.commit();
        dumpCell.setChecked(!force);
      }
    }
);
    ll.addView(dumpCell);
  }
  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
    final TextCheckCell connectionServiceCell=new TextCheckCell(context);
    connectionServiceCell.setTextAndCheck("Enable ConnectionService",preferences.getBoolean("dbg_force_connection_service",false),false);
    connectionServiceCell.setOnClickListener(new View.OnClickListener(){
      @Override public void onClick(      View v){
        boolean force=preferences.getBoolean("dbg_force_connection_service",false);
        SharedPreferences.Editor editor=preferences.edit();
        editor.putBoolean("dbg_force_connection_service",!force);
        editor.commit();
        connectionServiceCell.setChecked(!force);
      }
    }
);
    ll.addView(connectionServiceCell);
  }
  new AlertDialog.Builder(context).setTitle(LocaleController.getString("DebugMenuCallSettings",R.string.DebugMenuCallSettings)).setView(ll).show();
}
