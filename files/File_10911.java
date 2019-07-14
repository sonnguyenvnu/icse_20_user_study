package com.vondear.rxtool;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 *
 * @author vondear
 * @date 2016/1/24
 * Shell相关工具类
 */
public class RxShellTool {

    public static final String COMMAND_SU = "su";
    public static final String COMMAND_SH = "sh";
    public static final String COMMAND_EXIT = "exit\n";
    public static final String COMMAND_LINE_END = "\n";

    /**
     * 判断设备是�?�root
     * @return {@code true}: root<br>{@code false}: 没root
     */
    public static boolean isRoot() {
        return execCmd("echo root", true, false).result == 0;
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param command 命令
     * @param isRoot  是�?�root
     * @return CommandResult
     */
    public static CommandResult execCmd(String command, boolean isRoot) {
        return execCmd(new String[]{command}, isRoot, true);
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param commands 多�?�命令链表
     * @param isRoot   是�?�root
     * @return CommandResult
     */
    public static CommandResult execCmd(List<String> commands, boolean isRoot) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot, true);
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param commands 多�?�命令数组
     * @param isRoot   是�?�root
     * @return CommandResult
     */
    public static CommandResult execCmd(String[] commands, boolean isRoot) {
        return execCmd(commands, isRoot, true);
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param command         命令
     * @param isRoot          是�?�root
     * @param isNeedResultMsg 是�?�需�?结果消�?�
     * @return CommandResult
     */
    public static CommandResult execCmd(String command, boolean isRoot, boolean isNeedResultMsg) {
        return execCmd(new String[]{command}, isRoot, isNeedResultMsg);
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param commands        命令链表
     * @param isRoot          是�?�root
     * @param isNeedResultMsg 是�?�需�?结果消�?�
     * @return CommandResult
     */
    public static CommandResult execCmd(List<String> commands, boolean isRoot, boolean isNeedResultMsg) {
        return execCmd(commands == null ? null : commands.toArray(new String[]{}), isRoot, isNeedResultMsg);
    }

    /**
     * 是�?�是在root下执行命令
     *
     * @param commands        命令数组
     * @param isRoot          是�?�root
     * @param isNeedResultMsg 是�?�需�?结果消�?�
     * @return CommandResult
     */
    public static CommandResult execCmd(String[] commands, boolean isRoot, boolean isNeedResultMsg) {
        int result = -1;
        if (commands == null || commands.length == 0) {
            return new CommandResult(result, null, null);
        }
        Process process = null;
        BufferedReader successResult = null;
        BufferedReader errorResult = null;
        StringBuilder successMsg = null;
        StringBuilder errorMsg = null;
        DataOutputStream os = null;
        try {
            process = Runtime.getRuntime().exec(isRoot ? COMMAND_SU : COMMAND_SH);
            os = new DataOutputStream(process.getOutputStream());
            for (String command : commands) {
                if (command == null) {
                    continue;
                }
                os.write(command.getBytes());
                os.writeBytes(COMMAND_LINE_END);
                os.flush();
            }
            os.writeBytes(COMMAND_EXIT);
            os.flush();

            result = process.waitFor();
            if (isNeedResultMsg) {
                successMsg = new StringBuilder();
                errorMsg = new StringBuilder();
                successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
                errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String s;
                while ((s = successResult.readLine()) != null) {
                    successMsg.append(s);
                }
                while ((s = errorResult.readLine()) != null) {
                    errorMsg.append(s);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (successResult != null) {
                    successResult.close();
                }
                if (errorResult != null) {
                    errorResult.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            if (process != null) {
                process.destroy();
            }
        }
        return new CommandResult(result, successMsg == null ? null : successMsg.toString(), errorMsg == null ? null
                : errorMsg.toString());
    }

    /**
     * 返回的命令结果
     */
    public static class CommandResult {

        /**
         * 结果�?
         **/
        public int result;
        /**
         * �?功的信�?�
         **/
        public String successMsg;
        /**
         * 错误信�?�
         **/
        public String errorMsg;

        public CommandResult(int result) {
            this.result = result;
        }

        public CommandResult(int result, String successMsg, String errorMsg) {
            this.result = result;
            this.successMsg = successMsg;
            this.errorMsg = errorMsg;
        }
    }
}
