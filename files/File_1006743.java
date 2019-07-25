package org.javacore.base.String; /*
 * Copyright [2015] [Jeff Lee]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * @author BYSocket
 * @since 2016-01-06 19:51:00
 *  å¸¸è§?é?¢è¯•é¢˜ï¼šStringä½œä¸ºæ–¹æ³•å?‚æ•°ä¼ é€’,å?¦å¤–ä¸€ä¸ªä¾‹å­? ${@link StringT2}
 *  è¿™å°±å?«å?šâ€œå€¼ä¼ é€’â€?ï¼Œå?³æ–¹æ³•æ“?ä½œçš„æ˜¯å?‚æ•°å?˜é‡?ï¼ˆä¹Ÿå°±æ˜¯åŽŸåž‹å?˜é‡?çš„ä¸€ä¸ªå€¼çš„æ‹·è´?ï¼‰
 *  æ”¹å?˜çš„ä¹Ÿå?ªæ˜¯åŽŸåž‹å?˜é‡?çš„ä¸€ä¸ªæ‹·è´?è€Œå·²ï¼Œè€Œé?žå?˜é‡?æœ¬èº«
 */
public class StringT {
    public static void main(String[] args) {
        String str = "123";
        change(str);
        System.out.println(str);
    }

    public static void change(String str) {
        str = "456";
    }
}
