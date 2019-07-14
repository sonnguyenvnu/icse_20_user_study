public void showButtons(final BottomBarButtonPath buttonPathInterface){
  final String path=buttonPathInterface.getPath();
  if (buttons.getVisibility() == View.VISIBLE) {
    lastUsedArrowButton=0;
    lastUsedFolderButton=0;
    buttons.removeAllViews();
    buttons.setMinimumHeight(pathLayout.getHeight());
    buttonRoot.setImageDrawable(mainActivity.getResources().getDrawable(buttonPathInterface.getRootDrawable()));
    buttonRoot.setOnClickListener(p1 -> {
      buttonPathInterface.changePath("/");
      timer.cancel();
      timer.start();
    }
);
    String[] names=FileUtils.getFolderNamesInPath(path);
    final String[] paths=FileUtils.getPathsInPath(path);
    View view=new View(mainActivity);
    LinearLayout.LayoutParams params1=new LinearLayout.LayoutParams(appbar.getToolbar().getContentInsetLeft(),LinearLayout.LayoutParams.WRAP_CONTENT);
    view.setLayoutParams(params1);
    buttons.addView(view);
    for (int i=0; i < names.length; i++) {
      final int k=i;
      if (paths[i].equals("/")) {
        buttons.addView(buttonRoot);
      }
 else       if (FileUtils.isStorage(paths[i])) {
        buttonStorage.setOnClickListener(p1 -> {
          buttonPathInterface.changePath(paths[k]);
          timer.cancel();
          timer.start();
        }
);
        buttons.addView(buttonStorage);
      }
 else {
        Button button=createFolderButton(names[i]);
        button.setOnClickListener(p1 -> {
          buttonPathInterface.changePath(paths[k]);
          timer.cancel();
          timer.start();
        }
);
        buttons.addView(button);
      }
      if (names.length - i != 1) {
        buttons.addView(createArrow());
      }
    }
    scroll.post(() -> {
      sendScroll(scroll);
      sendScroll(pathScroll);
    }
);
    if (buttons.getVisibility() == View.VISIBLE) {
      timer.cancel();
      timer.start();
    }
  }
}
