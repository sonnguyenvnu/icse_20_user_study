/*
 * Symphony - A modern community (forum/BBS/SNS/blog) platform written in Java.
 * Copyright (C) 2012-present, b3log.org
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */
package org.b3log.symphony.util;

import jodd.io.FileUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.b3log.latke.Latkes;
import org.b3log.latke.ioc.BeanManager;
import org.b3log.latke.logging.Level;
import org.b3log.latke.logging.Logger;
import org.b3log.latke.service.LangPropsService;
import org.b3log.latke.util.Strings;
import org.b3log.symphony.SymphonyServletListener;
import org.b3log.symphony.model.Common;
import org.b3log.symphony.model.Option;
import org.b3log.symphony.service.OptionQueryService;
import org.json.JSONObject;

import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Symphony utilities.
 *
 * @author <a href="http://88250.b3log.org">Liang Ding</a>
 * @version 1.9.0.6, May 9, 2019
 * @since 0.1.0
 */
public final class Symphonys {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Symphonys.class);

    /**
     * Configurations.
     */
    private static final Properties CFG = new Properties();

    /**
     * User-Agent.
     */
    public static final String USER_AGENT_BOT = "Sym/" + SymphonyServletListener.VERSION + "; +https://github.com/b3log/symphony";

    /**
     * Reserved tags.
     */
    public static final String[] RESERVED_TAGS;

    /**
     * White list - tags.
     */
    public static final String[] WHITE_LIST_TAGS;

    /**
     * Reserved user names.
     */
    public static final String[] RESERVED_USER_NAMES;

    /**
     * Thread pool.
     */
    public static final ThreadPoolExecutor EXECUTOR_SERVICE = (ThreadPoolExecutor) Executors.newFixedThreadPool(128);

    /**
     * Cron thread pool.
     */
    public static final ScheduledExecutorService SCHEDULED_EXECUTOR_SERVICE = Executors.newScheduledThreadPool(4);

    /**
     * Available processors.
     */
    public static final int PROCESSORS = Runtime.getRuntime().availableProcessors();

    static {
        try {
            InputStream resourceAsStream;
            final String symPropsEnv = System.getenv("SYM_PROPS");
            if (StringUtils.isNotBlank(symPropsEnv)) {
                LOGGER.trace("Loading symphony.properties from env var [$SYM_PROPS=" + symPropsEnv + "]");
                resourceAsStream = new FileInputStream(symPropsEnv);
            } else {
                LOGGER.trace("Loading symphony.properties from classpath [/symphony.properties]");
                resourceAsStream = Latkes.class.getResourceAsStream("/symphony.properties");
            }

            CFG.load(resourceAsStream);
        } catch (final Exception e) {
            LOGGER.log(Level.ERROR, "Loads symphony.properties failed, exited", e);

            System.exit(-1);
        }
    }

    /**
     * �?�帖最�?时间间隔，用于防止刷�?，�?��?毫秒.
     */
    public static final long MIN_STEP_ARTICLE_TIME = getLong("minStepArticleTime");

    /**
     * 回帖最�?时间间隔，用于防止刷�?，�?��?毫秒.
     */
    public static final long MIN_STEP_CMT_TIME = getLong("minStepCmtTime");

    /**
     * �?�天室最�?�?�言时间间隔，用于防止刷�?，�?��?毫秒.
     */
    public static final long MIN_STEP_CHAT_TIME = getLong("minStepChatTime");

    /**
     * 用户�?�布帖�?必须注册时间超过该设置，�?��?毫秒。默认 0 为�?�?制，刚注册完就�?�以�?�帖.
     */
    public static final long NEWBIE_FIRST_ARTICLE = getLong("newbieFirstArticle");

    /**
     * 帖�?列表大�?.
     */
    public static final int ARTICLE_LIST_CNT = getInt("articleListCnt");

    /**
     * 帖�?列表分页窗�?�大�?.
     */
    public static final int ARTICLE_LIST_WIN_SIZE = getInt("articleListWindowSize");

    /**
     * 帖�?列表�?篇帖�?的�?�与者显示数.
     */
    public static final int ARTICLE_LIST_PARTICIPANTS_CNT = getInt("articleListParticipantsCnt");

    /**
     * 首页�?侧�?近期热议帖�?显示数.
     */
    public static final int SIDE_HOT_ARTICLES_CNT = getInt("sideHotArticlesCnt");

    /**
     * 首页�?侧�?清风明月显示数.
     */
    public static final int SIDE_BREEZEMOON_CNT = getInt("sideBreezemoonsCnt");

    /**
     * 首页�?侧�?�?�天室显示数.
     */
    public static final int SIDE_CHATROOM_MSG_CNT = getInt("sideChatroommsgsCnt");

    /**
     * 侧�?�?机文章显示数.
     */
    public static final int SIDE_RANDOM_ARTICLES_CNT = getInt("sideRandomArticlesCnt");

    /**
     * 侧�?标签显示数.
     */
    public static final int SIDE_TAGS_CNT = getInt("sideTagsCnt");

    /**
     * 相关文章显示数.
     */
    public static final int SIDE_RELEVANT_ARTICLES_CNT = getInt("sideRelevantArticlesCnt");

    /**
     * 用户主页列表大�?.
     */
    public static final int USER_HOME_LIST_CNT = getInt("userHomeListCnt");

    /**
     * 用户主页列表分页窗�?�大�?
     */
    public static final int USER_HOME_LIST_WIN_SIZE = getInt("userHomeListWindowSize");

    /**
     * 通知列表大�?.
     */
    public static final int NOTIFICATION_LIST_CNT = getInt("notificationListCnt");

    /**
     * 通知列表分页窗�?�大�?.
     */
    public static final int NOTIFICATION_LIST_WIN_SIZE = getInt("notificationListWindowSize");

    /**
     * 帖�?回帖列表大�?.
     */
    public static final int ARTICLE_COMMENTS_CNT = getInt("articleCommentsCnt");

    /**
     * 帖�?回帖列表分页窗�?�大�?.
     */
    public static final int ARTICLE_COMMENTS_WIN_SIZE = getInt("articleCommentsWindowSize");

    /**
     * 标签列表（/tags）大�?.
     */
    public static final int TAGS_CNT = getInt("tagsCnt");

    /**
     * 标签列表分页窗�?�大�?.
     */
    public static final int TAGS_WIN_SIZE = getInt("tagsWindowSize");

    /**
     * 标签的相关标签显示数.
     */
    public static final int TAG_RELATED_TAGS_CNT = getInt("tagRelatedTagsCnt");

    /**
     * 标签的相关标签查询�?��?，�?�有超过该值�?会被认为是相关标签.
     */
    public static final int TAG_RELATED_WEIGHT = getInt("tagRelatedWeight");

    /**
     * 标签关注者列表大�?.
     */
    public static final int TAG_FOLLOWERS_CNT = getInt("tagFollowersCnt");

    /**
     * 标签关注者列表分页窗�?�大�?.
     */
    public static final int TAG_FOLLOWERS_WIN_SIZE = getInt("tagFollowersWindowSize");

    /**
     * �?�城用户列表大�?.
     */
    public static final int CITY_USERS_CNT = getInt("cityUsersCnt");

    /**
     * �?�城用户列表分页窗�?�大�?.
     */
    public static final int CITY_USERS_WIN_SIZE = getInt("cityUsersWindowSize");

    /**
     * 排行榜大�?.
     */
    public static final int TOP_CNT = getInt("topCnt");

    /**
     * �?�天室消�?�显示数.
     */
    public static final int CHATROOMMSGS_CNT = getInt("chatroommsgsCnt");

    /**
     * �?�天室消�?�列表分页窗�?�大�?.
     */
    public static final int CHATROOMMSGS_WIN_SIZE = getInt("chatroommsgsWindowSize");

    /**
     * PC 端默认主题�??.
     */
    public static final String SKIN_DIR_NAME = get("skinDirName");

    /**
     * 移动端默认主题�??.
     */
    public static final String MOBILE_SKIN_DIR_NAME = get("mobileSkinDirName");

    /**
     * 第三方站点统计代�?�?置. 如果需�?�?�行请在行尾加上 '\'，比如百度统计的代�?如下：
     *
     * <pre><code>
     * &lt;script&gt;\
     * var _hmt = _hmt || [];\
     * (function() {\
     *   var hm = document.createElement("script");\
     *   hm.src = "//hm.baidu.com/hm.js?f241a238dc8343347478081db6c7cf5c";\
     *   var s = document.getElementsByTagName("script")[0];\
     *   s.parentNode.insertBefore(hm, s);\
     * })();\
     * &lt;/script&gt;
     * </code>
     * </pre>
     */
    public static final String SITE_VISIT_STATISTIC_CODE = get("siteVisitStatCode");

    /**
     * 上传渠�?��?置，�?��?置为七牛云 {@code qiniu} 或者本地 {@code local}.
     */
    public static final String UPLOAD_CHANNEL = Symphonys.get("upload.channel");

    /**
     * 是�?��?�用七牛云渠�?�上传.
     */
    public static final boolean QN_ENABLED = "qiniu".equalsIgnoreCase(UPLOAD_CHANNEL);

    /**
     * �?许上传图片最大值，�?��?字节.
     */
    public static final long UPLOAD_IMG_MAX = Symphonys.getLong("upload.img.maxSize");

    /**
     * �?许上传文件（�?�图片）的最大值，�?��?字节.
     */
    public static final long UPLOAD_FILE_MAX = Symphonys.getLong("upload.file.maxSize");

    /**
     * �?许上传文件的�?�缀，使用 {@code ,} 分隔.
     */
    public static final String UPLOAD_SUFFIX = get("upload.suffix");

    /**
     * 本地上传目录，如果�?�用了七牛云的�?该项�?置会被忽略.
     * 使用�?对路径指定文件存放路径，�?�?指定到容器下，以�?留下安全�?患.
     */
    public static String UPLOAD_LOCAL_DIR = get("upload.local.dir");

    static {
        final String userHome = System.getProperty("user.home");
        UPLOAD_LOCAL_DIR = StringUtils.replace(UPLOAD_LOCAL_DIR, "~", userHome);
        // �?始化上传目录，请注�?�?务器上文件读写�?��?
        if (!Symphonys.QN_ENABLED) {
            final File file = new File(Symphonys.UPLOAD_LOCAL_DIR);
            if (!FileUtil.isExistingFolder(file)) {
                try {
                    FileUtil.mkdirs(Symphonys.UPLOAD_LOCAL_DIR);
                } catch (IOException ex) {
                    LOGGER.log(Level.ERROR, "Init upload dir failed", ex);

                    System.exit(-1);
                }
            }

            LOGGER.info("Uses dir [" + file.getAbsolutePath() + "] for file uploading");
        }
    }

    /**
     * 上传七牛云 AK.
     */
    public static final String UPLOAD_QINIU_AK = get("upload.qiniu.accessKey");

    /**
     * 上传七牛云 SK.
     */
    public static final String UPLOAD_QINIU_SK = get("upload.qiniu.secretKey");

    /**
     * 上传七牛云域�??. 使用�?�独的域�??，�?��?出现安全�?患.
     */
    public static final String UPLOAD_QINIU_DOMAIN = get("upload.qiniu.domain");

    /**
     * 上传七牛云存储空间.
     */
    public static final String UPLOAD_QINIU_BUCKET = get("upload.qiniu.bucket");

    /**
     * 用户注册�?始化的积分值.
     */
    public static final int POINT_INIT = getInt("pointInit");

    /**
     * �?�帖所需积分值.
     */
    public static final int POINT_ADD_ARTICLE = getInt("pointAddArticle");

    /**
     * 更新帖�?所需积分值.
     */
    public static final int POINT_UPDATE_ARTICLE = getInt("pointUpdateArticle");

    /**
     * 回帖所需积分值.
     */
    public static final int POINT_ADD_COMMENT = getInt("pointAddComment");

    /**
     * 更新回帖所需积分值.
     */
    public static final int POINT_UPDATE_COMMENT = getInt("pointUpdateComment");

    /**
     * 回帖给自己的帖�?所需积分值.
     */
    public static final int POINT_ADD_SELF_ARTICLE_COMMENT = getInt("pointAddSelfArticleComment");

    /**
     * 邀请新用户注册获得奖励积分值（邀请人和被邀请人都会获得奖励）.
     */
    public static final int POINT_INVITE_REGISTER = getInt("pointInviteRegister");

    /**
     * �?日签到活动奖励�?机积分值下�?.
     */
    public static final int POINT_ACTIVITY_CHECKIN_MIN = getInt("pointActivityCheckinMin");

    /**
     * �?日签到活动奖励�?机积分值上�?.
     */
    public static final int POINT_ACTIVITY_CHECKIN_MAX = getInt("pointActivityCheckinMax");

    /**
     * �?日签到活动连续签到 10 天奖励积分值.
     */
    public static final int POINT_ACTIVITY_CHECKINT_STREAK = getInt("pointActivityCheckinStreak");

    /**
     * 感谢回帖积分值.
     */
    public static final int POINT_THANK_COMMENT = getInt("pointThankComment");

    /**
     * �?�布�?�城广播所需积分值.
     */
    public static final int POINT_ADD_ARTICLE_BROADCAST = getInt("pointAddArticleBroadcast");

    /**
     * �?�用帖�?打�?区所需积分值.
     */
    public static final int POINT_ADD_ARTICLE_REWARD = getInt("pointAddArticleReward");

    /**
     * 艾特所有回帖�?�与者�?个回帖者所需积分值.
     */
    public static final int POINT_AT_PARTICIPANTS = getInt("pointAtParticipants");

    /**
     * 置顶帖�?所需积分值.
     */
    public static final int POINT_STICK_ARTICLE = getInt("pointStickArticle");

    /**
     * 感谢帖�?积分值.
     */
    public static final int POINT_THANK_ARTICLE = getInt("pointThankArticle");

    /**
     * 置顶帖�?时长，�?��?毫秒.
     */
    public static final long STICK_ARTICLE_TIME = getLong("stickArticleTime");

    /**
     * 写字活动奖励积分值.
     */
    public static final int POINT_ACTIVITY_CHAR = getInt("pointActivityCharacter");

    /**
     * 数�?�导出所需积分值.
     */
    public static final int POINT_DATA_EXPORT = getInt("pointDataExport");

    /**
     * 购买邀请�?所需积分值.
     */
    public static final int POINT_BUY_INVITECODE = getInt("pointBuyInvitecode");

    /**
     * 邀请�?被使用奖励积分值.
     */
    public static final int POINT_INVITECODE_USED = getInt("pointInvitecodeUsed");

    /**
     * 贪�?�蛇所需积分值.
     */
    public static final int POINT_ACTIVITY_EATINGSNAKE = getInt("pointActivityEatingSnake");

    /**
     * 贪�?�蛇奖励积分值上�?.
     */
    public static final int POINT_ACTIVITY_EATINGSNAKE_COLLECT_MAX = getInt("pointActivityEatingSnakeCollectMax");

    /**
     * 优先（加精）帖�?奖励积分值.
     */
    public static final int POINT_PERFECT_ARTICLE = getInt("pointPerfectArticle");

    /**
     * 五�?棋输赢积分值.
     */
    public static final int POINT_ACTIVITY_GOBANG = getInt("pointActivityGobang");

    /**
     * 举报�?管�?�员处�?��?�奖励积分值.
     */
    public static final int POINT_REPORT_HANDLED = getInt("pointReportHandled");

    /**
     * 更新用户所需积分值.
     */
    public static final int POINT_CHANGE_USERNAME = getInt("pointChangeUsername");

    /**
     * 积分转账功能最低门槛积分值，转账�?�余�?必须大于等于该值. 设置一定的门槛值有助于防止用户通过账�?�互转刷积分。
     */
    public static final int POINT_TRANSER_MIN = getInt("pointTransferMin");

    /**
     * 积分兑�?�红包最低门槛积分值，兑�?��?�余�?必须大于等于该值.
     */
    public static final int POINT_EXCHANGE_MIN = getInt("pointExchangeMin");

    /**
     * 积分兑�?�红包兑�?�比，默认是 150 积分兑�?� ￥1.
     */
    public static final int POINT_EXCHANGE_UNIT = getInt("pointExchangeUnit");

    /**
     * 是�?�关闭上�?�?�彩活动.
     */
    public static final boolean ACTIVITY_1A0001_CLOSED = getBoolean("activity1A0001Closed");

    /**
     * �?�与上�?�?�彩活动活跃度门槛值，�?置的是百分比，一般用默认值就好.
     */
    public static final float ACTIVITY_1A0001_LIVENESS_THRESHOLD = getFloat("activity1A0001LivenessThreshold");

    /**
     * 昨日活跃奖励 - �?�与一个活动的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_ACTIVITY_PER = getFloat("activitYesterdayLivenessReward.activity.perPoint");

    /**
     * 昨日活跃奖励 - �?�布一篇帖�?的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_ARTICLE_PER = getFloat("activitYesterdayLivenessReward.article.perPoint");

    /**
     * 昨日活跃奖励 - �?�布一�?�回帖的返点
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_COMMENT_PER = getFloat("activitYesterdayLivenessReward.comment.perPoint");

    /**
     * 昨日活跃奖励 - 一个 PV 的返点
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_PV_PER = getFloat("activitYesterdayLivenessReward.pv.perPoint");

    /**
     * 昨日活跃奖励 - 一次打�?的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_REWARD_PER = getFloat("activitYesterdayLivenessReward.reward.perPoint");

    /**
     * 昨日活跃奖励 - 一次感谢�?作的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_THANK_PER = getFloat("activitYesterdayLivenessReward.thank.perPoint");

    /**
     * 昨日活跃奖励 - 一次点赞点踩的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_VOTE_PER = getFloat("activitYesterdayLivenessReward.vote.perPoint");

    /**
     * 昨日活跃奖励 - 一次采纳回答的返点.
     */
    public static final float ACTIVITY_YESTERDAY_REWARD_ACCEPT_ANSWER_PER = getFloat("activitYesterdayLivenessReward.acceptAnswer.perPoint");

    /**
     * 昨日活跃奖励积分值上�?.
     */
    public static final int ACTIVITY_YESTERDAY_REWARD_MAX = getInt("activitYesterdayLivenessReward.maxPoint");

    /**
     * 邮件渠�?�，�?��?置为本地 {@code local}�?阿里云 {@code aliyun} 或者 SendCloud {@code sendcloud}.
     * �?建议用本地渠�?�，会有很高概率被收件方�?务拒信。
     */
    public static final String MAIL_CHANNEL = get("mail.channel");

    /**
     * 按邮箱�?�缀指定邮件渠�?�，比如�?置 {@literal mail.channel.mailDomains=aliyun:163.com,126.com;sendcloud:qq.com} 表示
     *
     * <ul>
     * <li>163.com, 126.com 使用 aliyun 渠�?�</li>
     * <li>qq.com 使用 sendcloud</li>
     * </ul>
     * <p>
     * 其他�?�缀默认使用 {@code mail.channel} 的�?置
     */
    public static final String MAIL_CHANNEL_MAIL_DOMAINS = get("mail.channel.mailDomains");

    /**
     * �?许使用的邮箱�?�缀，留空则�?判断
     */
    public static final String MAIL_DOMAINS = get("mail.domains");

    /**
     * 群�?�邮件订阅时的推�??帖�?列表大�?.
     */
    public static final int MAIL_BATCH_ARTICLE_SIZE = getInt("mail.batch.articleSize");

    /**
     * 邮件渠�?� {@code sendcloud} API USER.
     */
    public static final String MAIL_SENDCLOUD_API_USER = get("mail.sendcloud.apiUser");

    /**
     * 邮件渠�?� {@code sendcloud} API KEY.
     */
    public static final String MAIL_SENDCLOUD_API_KEY = get("mail.sendcloud.apiKey");

    /**
     * 邮件渠�?� {@code sendcloud} �?�信地�?�.
     */
    public static final String MAIL_SENDCLOUD_FROM = get("mail.sendcloud.from");

    /**
     * 邮件渠�?� {@code sendcloud} 批�?�?��? API USER.
     */
    public static final String MAIL_SENDCLOUD_BATCH_API_USER = get("mail.sendcloud.batch.apiUser");

    /**
     * 邮件渠�?� {@code sendcloud} 批�?�?��? API KEY.
     */
    public static final String MAIL_SENDCLOUD_BATCH_API_KEY = get("mail.sendcloud.batch.apiKey");

    /**
     * 邮件渠�?� {@code sendcloud} 批�?�?��?�?�信地�?�.
     */
    public static final String MAIL_SENDCLOUD_BATCH_FROM = get("mail.sendcloud.batch.from");

    /**
     * 邮件渠�?� {@code aliyun} Access Key.
     */
    public static final String MAIL_ALIYUN_AK = get("mail.aliyun.accessKey");

    /**
     * 邮件渠�?� {@code aliyun} Secret Key.
     */
    public static final String MAIL_ALIYUN_SK = get("mail.aliyun.accessSecret");

    /**
     * 邮件渠�?� {@code aliyun} �?�信地�?�.
     */
    public static final String MAIL_ALIYUN_FROM = get("mail.aliyun.from");

    /**
     * 邮件渠�?� {@code aliyun} 批�?�?��?�?�信地�?�.
     */
    public static final String MAIL_ALIYUN_BATCH_FROM = get("mail.aliyun.batch.from");

    /**
     * 邮件渠�?� {@code local} 是�?�开�?� debug..
     */
    public static final boolean MAIL_LOCAL_ISDEBUG = getBoolean("mail.local.isdebug");

    /**
     * 邮件渠�?� {@code local} �?�信�??议.
     */
    public static final String MAIL_LOCAL_TRANSPORT_PROTOCOL = get("mail.local.transport.protocol");

    /**
     * 邮件渠�?� {@code local} �?�信�?务地�?�.
     */
    public static final String MAIL_LOCAL_HOST = get("mail.local.host");

    /**
     * 邮件渠�?� {@code local} �?�信�?务端�?�.
     */
    public static final String MAIL_LOCAL_PORT = get("mail.local.port");

    /**
     * 邮件渠�?� {@code local} 是�?�开�?� SMTP 验�?.
     */
    public static final String MAIL_LOCAL_SMTP_AUTH = get("mail.local.smtp.auth");

    /**
     * 邮件渠�?� {@code local} SMTP 是�?��?�用 SSL.
     */
    public static final String MAIL_LOCAL_SMTP_SSL = get("mail.local.smtp.ssl.enable");

    /**
     * 邮件渠�?� {@code local} SMTP 是�?��?�用 TLS.
     */
    public static final String MAIL_LOCAL_SMTP_STARTTLS = get("mail.local.smtp.starttls.enable");

    /**
     * 邮件渠�?� {@code local} �?�信地�?�.
     */
    public static final String MAIL_LOCAL_SMTP_SENDER = get("mail.local.smtp.sender");

    /**
     * 邮件渠�?� {@code local} SMTP 用户�??.
     */
    public static final String MAIL_LOCAL_SMTP_USERNAME = get("mail.local.smtp.username");

    /**
     * 邮件渠�?� {@code local} SMPT 密�?.
     */
    public static final String MAIL_LOCAL_SMTP_PASSWORD = get("mail.local.smtp.passsword");

    /**
     * 百度 LBS 接�?�凭�?，用于 IP 定�?.
     */
    public static final String BAIDU_LBS_AK = get("baidu.lbs.ak");

    /**
     * 百度�?�索推�?接�?�凭�?.
     */
    public static final String BAIDU_DATA_TOKEN = get("baidu.data.token");

    /**
     * 百度语音接�?�凭�?，用于文字转语音.
     */
    public static final String BAIDU_YUYIN_API_KEY = Symphonys.get("baidu.yuyin.apiKey");

    /**
     * 百度语音接�?�凭�?.
     */
    public static final String BAIDU_YUYIN_SECRET_KEY = Symphonys.get("baidu.yuyin.secretKey");

    /**
     * 是�?��?�用本地 Elasticsearch �?�索.
     */
    public static final boolean ES_ENABLED = getBoolean("es.enabled");

    /**
     * Elasticsearch �?务器地�?�.
     */
    public static final String ES_SERVER = get("es.server");

    /**
     * 是�?��?�用 <a href="https://www.algolia.com" target="_blank">Algolia</a>�?�索.
     */
    public static final boolean ALGOLIA_ENABLED = getBoolean("algolia.enabled");

    /**
     * Algolia App Id.
     */
    public static final String ALGOLIA_APP_ID = get("algolia.appId");

    /**
     * Algolia �?�索 key，�?�以暴露给�?端.
     */
    public static final String ALGOLIA_SEARCH_KEY = get("algolia.searchKey");

    /**
     * Algolia 管�?� key.
     */
    public static final String ALGOLIA_ADMIN_KEY = get("algolia.adminKey");

    /**
     * Algolia Index.
     */
    public static final String ALGOLIA_INDEX = get("algolia.index");

    /**
     * �?留标签，�?�有管�?�员�?�以使用.
     */
    public static final String RESERVEDTAGS = get("reservedTags");

    static {
        final String[] tags = RESERVEDTAGS.split(",");
        RESERVED_TAGS = new String[tags.length];
        for (int i = 0; i < tags.length; i++) {
            RESERVED_TAGS[i] = StringUtils.trim(tags[i]);
        }
    }

    /**
     * 系统公告标签�??.
     */
    public static final String SYS_ANNOUNCE_TAG = get("systemAnnounce");

    /**
     * �?留的用户�??.
     */
    public static final String RESERVED_USERNAMES = get("reservedUserNames");

    static {
        final String[] userNames = RESERVED_USERNAMES.split(",");
        RESERVED_USER_NAMES = new String[userNames.length];
        for (int i = 0; i < userNames.length; i++) {
            RESERVED_USER_NAMES[i] = StringUtils.trim(userNames[i]);
        }
    }

    /**
     * 白�??�?�标签，标签规范化�?作会过滤掉�?符�?�格�?的标签，通过这里的白�??�?�阻止过滤.
     */
    public static final String WHITELIST_TAGS = get("whitelist.tags");

    static {
        final String[] wlTags = WHITELIST_TAGS.split(",");
        WHITE_LIST_TAGS = new String[wlTags.length];
        for (int i = 0; i < wlTags.length; i++) {
            WHITE_LIST_TAGS[i] = StringUtils.trim(wlTags[i]);
        }
    }

    /**
     * 性能监控阈值，�?��?毫秒，如果 {@link org.b3log.latke.util.Stopwatchs} 埋点执行超过该值则会打�?�耗时日志.
     * 生产环境建议�?置为 {@code 0}，开�?��?测试环境按需�?置。
     */
    public static final int PERFORMANCE_THRESHOLD = getInt("performance.threshold");

    /**
     * Cookie 加密密钥，<b>生产环境必须修改</b>.
     */
    public static final String COOKIE_SECRET = get("cookie.secret");

    /**
     * 积分超过该值�?能使用匿�??�?�布帖�?或者回帖.
     */
    public static final int ANONYMOUS_POST_POINT = getInt("anonymous.postPoint");

    /**
     * �?�使�?管�?��?��?� - 其他管�?� - �?许匿�??�?览】设置为�?�，该项�?置的 URIs 也�?许匿�??�?览.
     * 匿�??�?览指的是�?�登录状�?下�?览。
     */
    public static final String ANONYMOUS_VIEW_SKIPS = get("anonymous.viewSkips");

    /**
     * �?�登录时最多�?许�?览多少个 URIs. 和上�?�一样，需�?匿�??�?览设置为�?�该项�?有�?义.
     */
    public static final int ANONYMOUS_VIEW_URIS = getInt("anonymous.viewURIs");

    /**
     * 邀请�?过期时间，�?��?毫秒.
     */
    public static final long INVITECODE_EXPIRED = getLong("invitecode.expired");

    /**
     * 页脚备案�?��?置.
     */
    public static final String FOOTER_BEIANHAO = get("footerBeiAnHao");

    /**
     * Markdown 处�?�超时，�?��?毫秒. 有的时候�?�能会因为 MD 引擎缺陷导致处�?��?�常耗时，所以需�?设置超时以释放请求线程和相关资�?.
     */
    public static final int MARKDOWN_TIMEOUT = getInt("markdown.timeout");

    /**
     * URL �?��?规则.
     * <p>
     * &lt;"url:method", permissions&gt;
     * </p>
     */
    public static final Map<String, Set<String>> URL_PERMISSION_RULES = new HashMap<>();

    static {
        // Loads permission URL rules
        final String prefix = "permission.rule.url.";

        final Set<String> keys = CFG.stringPropertyNames();
        for (final String key : keys) {
            if (key.startsWith(prefix)) {
                final String value = Symphonys.CFG.getProperty(key);
                final Set<String> permissions = new HashSet<>(Arrays.asList(value.split(",")));

                URL_PERMISSION_RULES.put(key, permissions);
            }
        }
    }

    static {
        try {
            final SSLContext ctx = SSLContext.getInstance("TLS");
            ctx.init(new KeyManager[0], new TrustManager[]{new X509TrustManager() {
                @Override
                public void checkClientTrusted(final X509Certificate[] arg0, final String arg1) {
                }

                @Override
                public void checkServerTrusted(final X509Certificate[] arg0, final String arg1) {
                }

                @Override
                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            }}, new SecureRandom());
            SSLContext.setDefault(ctx);
        } catch (final Exception e) {
            // ignore
        }

        // �?� Rhy �?��?统计数�?�，仅�?��?站点�??称�?URL。用于 Sym 使用统计，如果�?想�?��?请移除该代�?
        new Timer(true).schedule(new TimerTask() {
            @Override
            public void run() {
                final String symURL = Latkes.getServePath();
                if (Strings.isIPv4(symURL)) {
                    return;
                }

                HttpURLConnection httpConn = null;
                try {
                    final BeanManager beanManager = BeanManager.getInstance();
                    final OptionQueryService optionQueryService = beanManager.getReference(OptionQueryService.class);

                    final JSONObject statistic = optionQueryService.getStatistic();
                    final int articleCount = statistic.optInt(Option.ID_C_STATISTIC_ARTICLE_COUNT);
                    if (articleCount < 66) {
                        return;
                    }

                    final LangPropsService langPropsService = beanManager.getReference(LangPropsService.class);

                    httpConn = (HttpURLConnection) new URL("https://rhythm.b3log.org/sym").openConnection();
                    httpConn.setConnectTimeout(10000);
                    httpConn.setReadTimeout(10000);
                    httpConn.setDoOutput(true);
                    httpConn.setRequestMethod("POST");
                    httpConn.setRequestProperty(Common.USER_AGENT, USER_AGENT_BOT);

                    httpConn.connect();

                    try (final OutputStream outputStream = httpConn.getOutputStream()) {
                        final JSONObject sym = new JSONObject();
                        sym.put("symURL", symURL);
                        sym.put("symTitle", langPropsService.get("symphonyLabel", Latkes.getLocale()));

                        IOUtils.write(sym.toString(), outputStream, "UTF-8");
                        outputStream.flush();
                    }

                    httpConn.getResponseCode();
                } catch (final Exception e) {
                    // ignore
                } finally {
                    if (null != httpConn) {
                        try {
                            httpConn.disconnect();
                        } catch (final Exception e) {
                            // ignore
                        }
                    }
                }
            }
        }, 1000 * 60 * 60 * 2, 1000 * 60 * 60 * 12);
    }

    /**
     * Gets active thread count of thread pool.
     *
     * @return active thread count
     */
    public static int getActiveThreadCount() {
        return EXECUTOR_SERVICE.getActiveCount();
    }

    /**
     * Gets the max thread count of thread pool.
     *
     * @return max thread count
     */
    public static int getMaxThreadCount() {
        return EXECUTOR_SERVICE.getMaximumPoolSize();
    }

    /**
     * Gets a configuration string property with the specified key.
     *
     * @param key the specified key
     * @return string property value corresponding to the specified key, returns {@code null} if not found
     */
    private static String get(final String key) {
        return CFG.getProperty(key);
    }

    /**
     * Gets a configuration boolean property with the specified key.
     *
     * @param key the specified key
     * @return boolean property value corresponding to the specified key, returns {@code null} if not found
     */
    private static Boolean getBoolean(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Boolean.valueOf(stringValue);
    }

    /**
     * Gets a configuration float property with the specified key.
     *
     * @param key the specified key
     * @return float property value corresponding to the specified key, returns {@code null} if not found
     */
    private static Float getFloat(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Float.valueOf(stringValue);
    }

    /**
     * Gets a configuration integer property with the specified key.
     *
     * @param key the specified key
     * @return integer property value corresponding to the specified key, returns {@code null} if not found
     */
    private static Integer getInt(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Integer.valueOf(stringValue);
    }

    /**
     * Gets a configuration long property with the specified key.
     *
     * @param key the specified key
     * @return long property value corresponding to the specified key, returns {@code null} if not found
     */
    private static Long getLong(final String key) {
        final String stringValue = get(key);
        if (null == stringValue) {
            return null;
        }

        return Long.valueOf(stringValue);
    }

    /**
     * Private constructor.
     */
    private Symphonys() {
    }
}
