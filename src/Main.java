/** Assignment 1 - Proper Stack and Queue - 9/28/2021
 *  Sebastian Jones - Sacramento State University - CSC 130 - Professor Cooke
 */
public class Main {
    public static void main(String[] args){
        System.out.print("TESTING LINKED LIST\r\n");
        System.out.print("Expected List: 5, 10, Bean, 0\r\nActual List: ");

        LinkedList testList = new LinkedList();
        testList.addHead(10);
        testList.addTail("Beam");
        testList.addHead(5);
        testList.removeTail();
        testList.addTail("Bean");
        testList.addTail(0);
        testList.addHead("killme");
        testList.removeHead();

        Node index = testList.head;
        while(index != testList.tail){
            System.out.print(index.getValue() + ", ");
            index = index.getNext();
        }
        System.out.print(index.getValue()+"\r\n");
        System.out.print(testList.about()+"\r\n");
    }
}

/** Node Class
 *      Stores an indiscriminate variable and the next node in the chain/list/array.
 */
class Node {
    Object value;
    Node next;

    //Constructors
    public Node(Object value) {
        this.value = value;
    }
    public Node(Object value, Node next) {
        this.value = value;
        this.next = next;
    }

    public Object getValue() {
        return this.value;
    }
    public Node getNext() {
        return this.next;
    }
}

/** Doubly linked list
 *
 */
//TODO: Fix this shit
class LinkedList {
    Node head; //First
    Node tail; //Last

    //Constructors
    public LinkedList(){
    }
    public LinkedList(Node node) {
        this.head = node;
        this.tail = node;
    }
    public LinkedList(Node head, Node tail){
        this.head = head;
        this.tail = tail;
    }

    String about() {
        return "Author: Sebastian Jones";
    }
    void addHead(Object item) {
        if (peekHead() == null) {       //List is empty
            head = new Node(item);
            tail = new Node(item);
        } else {
            head = new Node(item, head);
        }
    }
    void addTail(Object item) {
        if (peekHead() == null) {       //List is empty
            head = new Node(item);
            tail = new Node(item);
        } else {
            tail.next = new Node(item);
            tail = tail.getNext();
        }
    }
    Object removeHead() {
        Node oldHead = head;

        head = head.getNext();

        return oldHead;
    }
    Object removeTail() {
        Node index = head;

        while(index.getNext().getNext() != null) {  //Can't go backwards so we gotta be two steps ahead
            index = index.getNext();
        }
        index = index.getNext();
        tail = index;

        Node oldTail = tail.getNext();              //Delete reference to old Tail to remove from Heap
        tail.next = null;

        return oldTail;
    }
    Object peekHead() {
        if(head == null) {
            return null;
        }
        return head.getValue();
    }
}
