package org.nutz.mvc.upload.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

class RingItem {

    byte[] buffer;

    int max;
    /**
     * 左标记，DUMP 时包�?�
     */
    int l;
    /**
     * �?�标记，DUMP 时�?包�?�
     */
    int r;
    /**
     * 下一次 Mark 是开始的�?置
     */
    int nextmark;

    RingItem next;

    boolean isLoaded;
    boolean isStreamEnd;

    RingItem(int width) {
        this.buffer = new byte[width];
        this.next = this;
    }

    RingItem createNext() {
        RingItem ri = new RingItem(buffer.length);
        ri.next = next;
        next = ri;
        return ri;
    }

    void load(InputStream ins) throws IOException {
        if (isLoaded) {
            throw new ReloadLoadedRingItemException();
        }
        int bufferSize = buffer.length;
        max = ins.read(buffer, 0, bufferSize);

        // �?里�?在有内容了
        if (max < 0) {
            max = 0;
            isStreamEnd = true;
        }
        // 没有读全，继续读，直至read方法返回 -1, 或者读满.
        else {
            while (max < bufferSize) {
                int re = ins.read(buffer, max, bufferSize - max);
                if (re == -1) {
                    isStreamEnd = true;
                    break;
                }
                max += re;
            }
        }

        l = 0;
        r = 0;
        nextmark = 0;
        isLoaded = true;
    }

    void dump(OutputStream ops) throws IOException {
        if (l < r) {
            ops.write(buffer, l, r - l);
        }
        l = nextmark;
        r = l;
        isLoaded = (l < max);
    }

    /**
     * 试图从缓冲开头匹�?，如果匹�?失败，移动 'r' 并返回 false<br>
     * 如果匹�?�?功，则移动 'l'（匹�?的内容�?需�?读�?�） 并返回 true
     * <p>
     * 这个函数，在 BufferRing �?�现当�?的环节点返回 '>0' 时，需�?调用 next 的这个函数，看看是�?是�?�以完整被匹�?
     * 
     * @param bs
     *            数组
     * @param offs
     *            �??移�?
     * @return 本节点开头是�?�匹�?剩余的部分
     */
    boolean matchHeadingWithRemain(byte[] bs, int offs) {
        int i = 0;
        for (; offs < bs.length; offs++) {
            if (buffer[i++] != bs[offs]) {
                r = i;
                return false;
            }
        }
        // Matched, skip it
        l = i;
        r = i;
        nextmark = i;
        return true;
    }

    boolean isDone4Mark() {
        return nextmark == max;
    }

    /**
     * 从给定 offs 尽力匹�?给出的数组。
     * <p>
     * 需�?注�?的是，如果返回的是 >0 的数，内部的标志�?将被设置到第一个匹�?字符，以便 DUMP 内容。 <br>
     * 所以，如果下一个节点给出的结论是 -1，但是 'l' 并�?是0，那么说明这个匹�?是失败的，需�?将 本节点的 r 置到 max 处。
     * <p>
     * 返回值
     * <ul>
     * <li><b>-1</b> - 全部被匹�?
     * <li><b>0</b> - 未�?�现匹�?
     * <li><b>大于 0</b> - 在缓冲的末尾�?�现匹�?，但是没有匹�?全，希望下一个节点继续从这个�?置匹�?
     * </ul>
     * 
     * @param bs
     *            数组
     * @return -1, 0 或者 +n
     */
    int mark(byte[] bs, int[] fails) {
        if (!isLoaded)
            throw new MarkUnloadedRingItemException();

        byte start = bs[0];

        for (; r < max; r++) {
            // �?�能是边界，开始匹�?
            if (buffer[r] == start) {
                int re = 0; // 已�?匹�?长度
                int j = r; // 在内容值字节数组中的指针
                while (true) {
                    re++;
                    j++;
                    // 全部匹�?
                    if (re == bs.length) {
                        nextmark = j;
                        return -1;
                    }
                    // 到达本项目的结尾，但是并�?确定是�?�是边界，因为还未匹�?完
                    // 因此暂时�?�设这个�?会被匹�?
                    if (j == max) {
                        nextmark = max;
                        if (isStreamEnd) {
                            r = max;
                            return 0;
                        }
                        return re;
                    }
                    // 如果字符�?相等，那么查看一下回退数组
                    // 如果回退到 0，则退出循环，因为这�?是边界，�?�则继续循环匹�?边界
                    if (bs[re] != buffer[j]) {
                        re = fails[re];
                        // �?次判断回退�?��?置，如果还是�?相�?�，则退出循环
                        if (bs[re] != buffer[j]) {
                            break;
                        }
                        // 如果已�?回退到了 0，你这么�?�边界置为 j，表示从头�?�索
                        else if (re == 0) {
                            r = j;
                        }
                        // �?�则扩大边界，并继续循环
                        else {
                            r += re == 0 ? 1 : re;
                        }

                    }
                }
                // make 'r' jump to 'j'
                r = j;
            }
        }
        // Fail to found
        nextmark = max;
        return 0;
    }
}
