package cn.wildfire.chat.kit.conversation.ext.core;

import android.content.Intent;
import android.widget.FrameLayout;

import androidx.fragment.app.FragmentActivity;

import com.afollestad.materialdialogs.MaterialDialog;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import cn.wildfire.chat.kit.annotation.ExtContextMenuItem;
import cn.wildfire.chat.kit.conversation.ConversationViewModel;
import cn.wildfire.chat.kit.widget.ViewPagerFixed;
import cn.wildfirechat.model.Conversation;

public class ConversationExtension {
    private FragmentActivity activity;
    private Conversation conversation;
    private FrameLayout containerLayout;
    private ViewPagerFixed extViewPager;
    private List<ConversationExt> exts;

    private boolean hideOnScroll = true;

    /**
     * @param activity
     * @param inputContainerLayout 包�?�整个输入区域的framelayout
     * @param extViewPager         用于展示{@link ConversationExtPageView}, �?个ConversationExtPageView包�?�8个{@link ConversationExt}
     */
    public ConversationExtension(FragmentActivity activity, FrameLayout inputContainerLayout, ViewPagerFixed extViewPager) {
        this.activity = activity;
        this.containerLayout = inputContainerLayout;
        this.extViewPager = extViewPager;
    }

    private void onConversationExtClick(ConversationExt ext) {
        List<ExtMenuItemWrapper> extMenuItems = new ArrayList<>();
        Method[] allMethods = ext.getClass().getDeclaredMethods();
        for (final Method method : allMethods) {
            if (method.isAnnotationPresent(ExtContextMenuItem.class)) {
                ExtContextMenuItem item = method.getAnnotation(ExtContextMenuItem.class);
                extMenuItems.add(new ExtMenuItemWrapper(item, method));
            }
        }
        if (extMenuItems.size() > 0) {
            if (extMenuItems.size() == 1) {
                try {
                    extMenuItems.get(0).method.invoke(ext, containerLayout, conversation);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            } else {
                List<String> titles = new ArrayList<>(extMenuItems.size());
                for (ExtMenuItemWrapper itemWrapper : extMenuItems) {
                    titles.add(itemWrapper.extContextMenuItem.title());
                }
                // TODO sort
                new MaterialDialog.Builder(activity).items(titles).itemsCallback((dialog, v, position, text) -> {
                    try {
                        extMenuItems.get(position).method.invoke(ext, containerLayout, conversation);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e) {
                        e.printStackTrace();
                    }

                }).show();
            }
        }
    }


    public void bind(ConversationViewModel conversationViewModel, Conversation conversation) {
        this.conversation = conversation;
        setupExtViewPager(extViewPager);

        for (int i = 0; i < exts.size(); i++) {
            exts.get(i).onBind(activity, conversationViewModel, conversation, this, i);
        }
    }

    public void onDestroy() {
        for (int i = 0; i < exts.size(); i++) {
            exts.get(i).onDestroy();
        }
    }

    private void setupExtViewPager(ViewPagerFixed viewPager) {
        exts = ConversationExtManager.getInstance().getConversationExts(conversation);
        if (exts.isEmpty()) {
            return;
        }
        viewPager.setAdapter(new ConversationExtPagerAdapter(exts, index -> {
            onConversationExtClick(exts.get(index));
        }));
    }


    public void reset() {
        int childCount = containerLayout.getChildCount();
        // �?删除最下层的layout，最下层是咱们的input panel
        while (--childCount > 0) {
            containerLayout.removeViewAt(childCount);
        }
        hideOnScroll = true;
    }


    public boolean canHideOnScroll() {
        return hideOnScroll;
    }

    public void disableHideOnScroll() {
        this.hideOnScroll = false;
    }

    public static final int REQUEST_CODE_MIN = 0x8000;


    /**
     * 低16�?是�?�法的request code
     * <p>
     * 第15�?强制置1，表示从ConversationExtension�?�起的
     * <p>
     * 第14-7�?，共8个�?，是{@link ConversationExt}�?�用所有request code, �?�request code�?�能在0-256
     * <p>
     * 第6-0�?，共7个�?，是index
     *
     * @param intent
     * @param requestCode
     * @param index
     */
    public void startActivityForResult(Intent intent, int requestCode, int index) {
        int extRequestCode = (requestCode << 7) | 0x8000;
        extRequestCode += index;
        activity.startActivityForResult(intent, extRequestCode);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        int index = requestCode & 0x7F;
        exts.get(index).onActivityResult((requestCode >> 7) & 0xFF, resultCode, data);
    }

    private static class ExtMenuItemWrapper {
        ExtContextMenuItem extContextMenuItem;
        Method method;

        ExtMenuItemWrapper(ExtContextMenuItem extContextMenuItem, Method method) {
            this.extContextMenuItem = extContextMenuItem;
            this.method = method;
        }
    }
}
