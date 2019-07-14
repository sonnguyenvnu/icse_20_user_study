package com.crossoverjie.algorithm;

import java.util.Stack;

/**
 * Function: ä¸‰ç§?æ–¹å¼?å??å?‘æ‰“å?°å?•å?‘é“¾è¡¨
 *
 * @author crossoverJie
 *         Date: 10/02/2018 16:14
 * @since JDK 1.8
 */
public class ReverseNode {


    /**
     * åˆ©ç”¨æ ˆçš„å…ˆè¿›å?Žå‡ºç‰¹æ€§
     * @param node
     */
    public void reverseNode1(Node node){

        System.out.println("====ç¿»è½¬ä¹‹å‰?====");

        Stack<Node> stack = new Stack<>() ;
        while (node != null){

            System.out.print(node.value + "===>");

            stack.push(node) ;
            node = node.next ;
        }

        System.out.println("");

        System.out.println("====ç¿»è½¬ä¹‹å?Ž====");
        while (!stack.isEmpty()){
            System.out.print(stack.pop().value + "===>");
        }

    }


    /**
     * åˆ©ç”¨å¤´æ?’æ³•æ?’å…¥é“¾è¡¨
     * @param head
     */
    public  void reverseNode(Node head) {
        if (head == null) {
            return ;
        }

        //æœ€ç»ˆç¿»è½¬ä¹‹å?Žçš„ Node
        Node node ;

        Node pre = head;
        Node cur = head.next;
        Node next ;
        while(cur != null){
            next = cur.next;

            //é“¾è¡¨çš„å¤´æ?’æ³•
            cur.next = pre;
            pre = cur;

            cur = next;
        }
        head.next = null;
        node = pre;


        //é??åŽ†æ–°é“¾è¡¨
        while (node != null){
            System.out.println(node.value);
            node = node.next ;
        }

    }


    /**
     * é€’å½’
     * @param node
     */
    public void recNode(Node node){

        if (node == null){
            return ;
        }

        if (node.next != null){
            recNode(node.next) ;
        }
        System.out.print(node.value+"===>");
    }


    public static class Node<T>{
        public T value;
        public Node<T> next ;


        public Node(T value, Node<T> next ) {
            this.next = next;
            this.value = value;
        }
    }
}
