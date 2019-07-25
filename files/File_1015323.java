package cn.ieclipse.wechat;

import cn.ieclipse.smartim.IMHistoryManager;
import cn.ieclipse.smartim.common.IMUtils;
import cn.ieclipse.smartim.console.IMChatConsole;
import cn.ieclipse.smartim.model.IContact;
import cn.ieclipse.smartim.model.impl.AbstractFrom;
import cn.ieclipse.util.FileUtils;
import cn.ieclipse.util.StringUtils;
import io.github.biezhi.wechat.api.WechatClient;
import io.github.biezhi.wechat.model.UploadInfo;
import io.github.biezhi.wechat.model.WechatMessage;

import javax.swing.*;
import java.io.File;
import java.net.URLConnection;
import java.util.Arrays;

/**
 * Created by Jamling on 2017/11/1.
 */
public class WXChatConsole extends IMChatConsole {
    WechatClient client;
    WechatPanel imPanel;

    public WXChatConsole(IContact target, WechatPanel imPanel) {
        super(target, imPanel);
        this.imPanel = imPanel;
    }

    @Override
    public WechatClient getClient() {
        return (WechatClient) super.getClient();
    }

    @Override
    public void loadHistory(String raw) {
        if (IMUtils.isMySendMsg(raw)) {
            write(raw);
            return;
        }
        // unreachable code
        WechatMessage m = (WechatMessage) getClient().handleMessage(raw);
        AbstractFrom from = getClient().getFrom(m);
        write(WXUtils.formatHtmlIncoming(m, from));
    }

    @Override
    protected String formatInput(String name, String input) {
        return WXUtils.formatHtmlOutgoing(name, input, true);
    }

    @Override
    public void post(String msg) {
        WechatClient client = getClient();
        if (client.isLogin() && contact != null) {
            WechatMessage m = client.createMessage(0, msg, contact);
            client.sendMessage(m, contact);
        } else {
            error("�?��?失败，客户端异常（�?�能已断开连接或找�?到�?�系人）");
        }
    }

    @Override
    protected boolean hyperlinkActivated(String desc) {
        if (desc.startsWith("weixin://")) {
            JOptionPane.showInternalMessageDialog(null,
                    desc + "为微信专用�??议，请使用手机微信打开");
            return false;
        }
        return super.hyperlinkActivated(desc);
    }

    @Override
    public void sendFileInternal(final String file) {
        // error("暂�?支�?，敬请关注 https://github.com/Jamling/SmartIM 或
        // https://github.com/Jamling/SmartQQ4IntelliJ 最新动�?");
        final File f = new File(file);
        final WechatClient client = getClient();
        if (!checkClient(client)) {
            return;
        }

        String ext = FileUtils.getExtension(f.getPath()).toLowerCase();
        String mimeType = URLConnection.guessContentTypeFromName(f.getName());
        String media = "pic";
        int type = WechatMessage.MSGTYPE_IMAGE;
        String content = "";
        if (Arrays.asList("png", "jpg", "jpeg", "bmp").contains(ext)) {
            type = WechatMessage.MSGTYPE_IMAGE;
            media = "pic";
        } else if ("gif".equals(ext)) {
            type = WechatMessage.MSGTYPE_EMOTICON;
            media = "doc";
        } else {
            type = WechatMessage.MSGTYPE_FILE;
            media = "doc";
        }

        final UploadInfo uploadInfo = client.uploadMedia(f, mimeType, media);

        if (uploadInfo == null) {
            error("上传失败");
            return;
        }
        String link = StringUtils.file2url(file);
        String label = file.replace('\\', '/');
        String input = null;
        if (type == WechatMessage.MSGTYPE_EMOTICON
                || type == WechatMessage.MSGTYPE_IMAGE) {
            input = String.format("<img src=\"%s\" border=\"0\" alt=\"%s\"",
                    link, label);
            if (uploadInfo.CDNThumbImgWidth > 0) {
                input += " width=\"" + uploadInfo.CDNThumbImgWidth + "\"";
            }
            if (uploadInfo.CDNThumbImgHeight > 0) {
                input += " height=\"" + uploadInfo.CDNThumbImgHeight + "\"";
            }
            input = String.format("<a href=\"%s\" title=\"%s\">%s</a>", link,
                    link, input);
        } else {
            input = String.format("<a href=\"%s\" title=\"%s\">%s</a>", link,
                    label, label);
            content = client.createFileMsgContent(f, uploadInfo.MediaId);
        }

        final WechatMessage m = client.createMessage(type, content, contact);
        m.text = input;
        m.MediaId = uploadInfo.MediaId;

        client.sendMessage(m, contact);
        if (!hideMyInput()) {
            String name = client.getAccount().getName();
            String msg = WXUtils.formatHtmlOutgoing(name, m.text, false);
            insertDocument(msg);
            IMHistoryManager.getInstance().save(getHistoryDir(), getHistoryFile(), msg);
        }
    }
}
