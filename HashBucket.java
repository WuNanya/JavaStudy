//key-value模型
public class HashBucket {

    /*
    定义一个结点内部类
     */
    private static class Node {
        private int key;
        private int value;
        Node next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

    }

    private Node[] array;
    private int size; //当前的数据个数
    private static final double LOAD = 0.75;

    private double loadFactor() {
        return size * 1.0 / array.length;
    }

    //查找元素
    public int get(int key) {
        //1. key -> int类型
        //2.保证下标是合法

        //通过哈希函数找到index
        int index = key % array.length;

        Node head = array[index];

        //循环链表查找key
        for (Node cur = head; cur != null; cur = cur.next) {
            if (key == cur.key) {
                return cur.value;
            }
        }
        return -1;
    }

    /*
    插入元素
     */
    private int put(int key, int value) {
        //1.先通过哈希函数找到index
        int index = key % array.length;

        //将对应的index位置赋值一个头结点
        Node head = array[index];
        //2.在数组的index位置的链表中找与key相同的值，找到则返回已存在，找不到则插入
        for (Node cur = head; cur != null; cur = cur.next) {
            if (cur.key == key) {
                //如果找到了key相同的结点，则更新其value的值
                int oldValue = cur.value;
                cur.value = value;
                return oldValue;
            }
        }
        //如果没有找到该结点，则进行链表的头插法，将结点插入
        Node node = new Node(key, value);
        node.next = head;
//        head = node;  这里是一个局部变量所以是错的
        array[index] = node;
        size++;
        return -1;
    }

    //负载因子调节
    private void resize(){
        //先创建一个原结点容量两倍的新数组
        Node[] newArray = new Node[array.length * 2];
        Node next;
        //遍历数组中链表中的每一个值，运用头插，插入新的数组中
        for(int i = 0;i < array.length; i++){
            for(Node cur = array[i]; cur!=null; cur = next){
                next = cur.next;
                int index = cur.key % newArray.length;
                cur.next = newArray[index];
                newArray[index] = cur;
            }
        }
        array = newArray;
    }
}
