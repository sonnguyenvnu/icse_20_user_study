/// Leetcode 349. Intersection of Two Arrays
/// https://leetcode.com/problems/intersection-of-two-arrays/description/

import java.util.ArrayList;

class LinkedListSetSolution349 {

    private class LinkedList<E> {

        private class Node{
            public E e;
            public Node next;

            public Node(E e, Node next){
                this.e = e;
                this.next = next;
            }

            public Node(E e){
                this(e, null);
            }

            public Node(){
                this(null, null);
            }

            @Override
            public String toString(){
                return e.toString();
            }
        }

        private Node dummyHead;
        private int size;

        public LinkedList(){
            dummyHead = new Node();
            size = 0;
        }

        // 获�?�链表中的元素个数
        public int getSize(){
            return size;
        }

        // 返回链表是�?�为空
        public boolean isEmpty(){
            return size == 0;
        }

        // 在链表的index(0-based)�?置添加新的元素e
        // 在链表中�?是一个常用的�?作，练习用：）
        public void add(int index, E e){

            if(index < 0 || index > size)
                throw new IllegalArgumentException("Add failed. Illegal index.");

            Node prev = dummyHead;
            for(int i = 0 ; i < index ; i ++)
                prev = prev.next;

            prev.next = new Node(e, prev.next);
            size ++;
        }

        // 在链表头添加新的元素e
        public void addFirst(E e){
            add(0, e);
        }

        // 在链表末尾添加新的元素e
        public void addLast(E e){
            add(size, e);
        }

        // 获得链表的第index(0-based)个�?置的元素
        // 在链表中�?是一个常用的�?作，练习用：）
        public E get(int index){

            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Get failed. Illegal index.");

            Node cur = dummyHead.next;
            for(int i = 0 ; i < index ; i ++)
                cur = cur.next;
            return cur.e;
        }

        // 获得链表的第一个元素
        public E getFirst(){
            return get(0);
        }

        // 获得链表的最�?�一个元素
        public E getLast(){
            return get(size - 1);
        }

        // 修改链表的第index(0-based)个�?置的元素为e
        // 在链表中�?是一个常用的�?作，练习用：）
        public void set(int index, E e){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Set failed. Illegal index.");

            Node cur = dummyHead.next;
            for(int i = 0 ; i < index ; i ++)
                cur = cur.next;
            cur.e = e;
        }

        // 查找链表中是�?�有元素e
        public boolean contains(E e){
            Node cur = dummyHead.next;
            while(cur != null){
                if(cur.e.equals(e))
                    return true;
                cur = cur.next;
            }
            return false;
        }

        // 从链表中删除index(0-based)�?置的元素, 返回删除的元素
        // 在链表中�?是一个常用的�?作，练习用：）
        public E remove(int index){
            if(index < 0 || index >= size)
                throw new IllegalArgumentException("Remove failed. Index is illegal.");

            Node prev = dummyHead;
            for(int i = 0 ; i < index ; i ++)
                prev = prev.next;

            Node retNode = prev.next;
            prev.next = retNode.next;
            retNode.next = null;
            size --;

            return retNode.e;
        }

        // 从链表中删除第一个元素, 返回删除的元素
        public E removeFirst(){
            return remove(0);
        }

        // 从链表中删除最�?�一个元素, 返回删除的元素
        public E removeLast(){
            return remove(size - 1);
        }

        // 从链表中删除元素e
        public void removeElement(E e){

            Node prev = dummyHead;
            while(prev.next != null){
                if(prev.next.e.equals(e))
                    break;
                prev = prev.next;
            }

            if(prev.next != null){
                Node delNode = prev.next;
                prev.next = delNode.next;
                delNode.next = null;
                size --;
            }
        }

        @Override
        public String toString(){
            StringBuilder res = new StringBuilder();

            Node cur = dummyHead.next;
            while(cur != null){
                res.append(cur + "->");
                cur = cur.next;
            }
            res.append("NULL");

            return res.toString();
        }
    }

    private interface Set<E> {

        void add(E e);
        boolean contains(E e);
        void remove(E e);
        int getSize();
        boolean isEmpty();
    }

    private class LinkedListSet<E> implements Set<E> {

        private LinkedList<E> list;

        public LinkedListSet(){
            list = new LinkedList<>();
        }

        @Override
        public int getSize(){
            return list.getSize();
        }

        @Override
        public boolean isEmpty(){
            return list.isEmpty();
        }

        @Override
        public void add(E e){
            if(!list.contains(e))
                list.addFirst(e);
        }

        @Override
        public boolean contains(E e){
            return list.contains(e);
        }

        @Override
        public void remove(E e){
            list.removeElement(e);
        }
    }

    public int[] intersection(int[] nums1, int[] nums2) {

        LinkedListSet<Integer> set = new LinkedListSet<>();
        for(int num: nums1)
            set.add(num);

        ArrayList<Integer> list = new ArrayList<>();
        for(int num: nums2){
            if(set.contains(num)){
                list.add(num);
                set.remove(num);
            }
        }

        int[] res = new int[list.size()];
        for(int i = 0 ; i < list.size() ; i ++)
            res[i] = list.get(i);
        return res;
    }
}
