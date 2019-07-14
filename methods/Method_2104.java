private void handleNavigationItemClick(int itemId){
  ShowcaseFragment fragment;
switch (itemId) {
case R.id.nav_drawee_simple:
    fragment=new DraweeSimpleFragment();
  break;
case R.id.nav_drawee_media_picker:
fragment=new DraweeMediaPickerFragment();
break;
case R.id.nav_drawee_scaletype:
fragment=new DraweeScaleTypeFragment();
break;
case R.id.nav_drawee_span_simple:
fragment=new DraweeSpanSimpleTextFragment();
break;
case R.id.nav_drawee_rounded_corners:
fragment=new DraweeRoundedCornersFragment();
break;
case R.id.nav_drawee_hierarchy:
fragment=new DraweeHierarchyFragment();
break;
case R.id.nav_drawee_rotation:
fragment=new DraweeRotationFragment();
break;
case R.id.nav_drawee_recycler:
fragment=new DraweeRecyclerViewFragment();
break;
case R.id.nav_drawee_transition:
fragment=new DraweeTransitionFragment();
break;
case R.id.nav_drawee_retaining_supplier:
fragment=new RetainingDataSourceSupplierFragment();
break;
case R.id.nav_imagepipeline_notification:
fragment=new ImagePipelineNotificationFragment();
break;
case R.id.nav_imagepipeline_postprocessor:
fragment=new ImagePipelinePostProcessorFragment();
break;
case R.id.nav_imagepipeline_prefetch:
fragment=new ImagePipelinePrefetchFragment();
break;
case R.id.nav_imagepipeline_resizing:
fragment=new ImagePipelineResizingFragment();
break;
case R.id.nav_imagepipeline_qualified_resource:
fragment=new ImagePipelineQualifiedResourceFragment();
break;
case R.id.nav_imagepipeline_partial_request:
fragment=new PartialRequestFragment();
break;
case R.id.nav_imagepipeline_bitmap_factory:
fragment=new ImagePipelineBitmapFactoryFragment();
break;
case R.id.nav_imagepipeline_region_decoding:
fragment=new ImagePipelineRegionDecodingFragment();
break;
case R.id.nav_format_pjpeg:
fragment=new ImageFormatProgressiveJpegFragment();
break;
case R.id.nav_format_color:
fragment=new ImageFormatColorFragment();
break;
case R.id.nav_format_gif:
fragment=new ImageFormatGifFragment();
break;
case R.id.nav_format_webp:
fragment=new ImageFormatWebpFragment();
break;
case R.id.nav_format_svg:
fragment=new ImageFormatSvgFragment();
break;
case R.id.nav_format_keyframes:
fragment=new ImageFormatKeyframesFragment();
break;
case R.id.nav_format_override:
fragment=new ImageFormatOverrideExample();
break;
case R.id.nav_format_datauri:
fragment=new ImageFormatDataUriFragment();
break;
case R.id.nav_vito_litho_simple:
fragment=new FrescoVitoLithoSimpleFragment();
break;
case R.id.nav_vito_image_options_config:
fragment=new FrescoVitoLithoImageOptionsConfigFragment();
break;
case R.id.nav_vito_litho_sections:
fragment=new FrescoVitoLithoSectionsFragment();
break;
case R.id.nav_vito_litho_gallery:
fragment=new FrescoVitoLithoGalleryFragment();
break;
case R.id.nav_welcome:
fragment=new WelcomeFragment();
break;
case R.id.nav_action_settings:
fragment=new SettingsFragment();
break;
default :
fragment=new WelcomeFragment();
}
showFragment(fragment);
if (itemId != R.id.nav_action_settings) {
PreferenceManager.getDefaultSharedPreferences(this).edit().putInt(KEY_SELECTED_NAVDRAWER_ITEM_ID,itemId).apply();
}
}
