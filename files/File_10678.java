package com.vondear.rxtool;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 *
 * @author vondear
 * @date 2016/12/21
 */

public class RxClipboardTool {
    /**
     * å¤?åˆ¶æ–‡æœ¬åˆ°å‰ªè´´æ?¿
     *
     * @param text æ–‡æœ¬
     */
    public static void copyText(Context context,CharSequence text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newPlainText("text", text));
    }

    /**
     * èŽ·å?–å‰ªè´´æ?¿çš„æ–‡æœ¬
     *
     * @return å‰ªè´´æ?¿çš„æ–‡æœ¬
     */
    public static CharSequence getText(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).coerceToText(context);
        }
        return null;
    }

    /**
     * å¤?åˆ¶uriåˆ°å‰ªè´´æ?¿
     *
     * @param uri uri
     */
    public static void copyUri(Context context,Uri uri) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newUri(context.getContentResolver(), "uri", uri));
    }

    /**
     * èŽ·å?–å‰ªè´´æ?¿çš„uri
     *
     * @return å‰ªè´´æ?¿çš„uri
     */
    public static Uri getUri(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getUri();
        }
        return null;
    }

    /**
     * å¤?åˆ¶æ„?å›¾åˆ°å‰ªè´´æ?¿
     *
     * @param intent æ„?å›¾
     */
    public static void copyIntent(Context context,Intent intent) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        clipboard.setPrimaryClip(ClipData.newIntent("intent", intent));
    }

    /**
     * èŽ·å?–å‰ªè´´æ?¿çš„æ„?å›¾
     *
     * @return å‰ªè´´æ?¿çš„æ„?å›¾
     */
    public static Intent getIntent(Context context) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = clipboard.getPrimaryClip();
        if (clip != null && clip.getItemCount() > 0) {
            return clip.getItemAt(0).getIntent();
        }
        return null;
    }
}
